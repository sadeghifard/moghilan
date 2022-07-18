package com.sadeghifard.moghilan.config.transaction;

import org.apache.kafka.streams.errors.StreamsException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.binder.BinderFactory;
import org.springframework.cloud.stream.binder.kafka.KafkaMessageChannelBinder;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.ReactiveTransaction;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.GenericReactiveTransaction;

import com.sadeghifard.moghilan.domain.Event;
import com.sadeghifard.moghilan.enums.EventType;
import com.sadeghifard.moghilan.utile.Utility;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Transactional
@Aspect
@Component("transactionManager")
public class MoghilanKafkaTransactionManager implements ReactiveTransactionManager {
	
	private final TransactionStatus transactionStatus;
	private final BinderFactory binders;
	private final StreamBridge streamBridge;
	private Object dlqMessage;
	
	@Value(value = "${spring.cloud.stream.kafka.binder.transaction.transaction-id-prefix}")
	private String txId;
	
	@Before("execution(* MoghilanKafkaTransactionManager.commit(.))")
	@KafkaListener(topics = "${spring.cloud.stream.kafka.binder.producer-properties.dlq-name:errors}")
	private void process(@Payload Object dlqMessage) {
		this.dlqMessage = dlqMessage;
	}
	
	@Override
	public Mono<Void> commit(ReactiveTransaction transaction) throws TransactionException {
		if(dlqMessage == null) {
			ProducerFactory<byte[], byte[]> accountProducerFactory = ((KafkaMessageChannelBinder) binders.getBinder("account-broker", MessageChannel.class)).getTransactionalProducerFactory();
			KafkaTransactionManager<byte[], byte[]> accountTxManager = new KafkaTransactionManager<>(accountProducerFactory);
		    accountTxManager.setTransactionIdPrefix(txId);
		   
		    ProducerFactory<byte[], byte[]> customerProducerFactory = ((KafkaMessageChannelBinder) binders.getBinder("customer-broker", MessageChannel.class)).getTransactionalProducerFactory();
		    KafkaTransactionManager<byte[], byte[]> customerTxManager = new KafkaTransactionManager<>(customerProducerFactory);
		    customerTxManager.setTransactionIdPrefix(txId);
		    
		    ProducerFactory<byte[], byte[]> paymentProducerFactory = ((KafkaMessageChannelBinder) binders.getBinder("payment-broker", MessageChannel.class)).getTransactionalProducerFactory();
		    KafkaTransactionManager<byte[], byte[]> paymentTxManager = new KafkaTransactionManager<>(paymentProducerFactory);
		    paymentTxManager.setTransactionIdPrefix(txId);
		    
		    if(transactionStatus.isNewTransaction()) {
		    	accountTxManager.commit(transactionStatus);
		    	customerTxManager.commit(transactionStatus);
		    	paymentTxManager.commit(transactionStatus);
		    }
		    return Mono.empty();
		} else {
			throw new RuntimeException(dlqMessage.toString()); // TO DO: Change the type of exception.
		}
		
	}

	@Override
	public Mono<ReactiveTransaction> getReactiveTransaction(TransactionDefinition definition) throws TransactionException {
		if(dlqMessage == null) {
			Integer isoLevel = definition.ISOLATION_READ_COMMITTED;
			GenericReactiveTransaction transaction = new GenericReactiveTransaction(definition, false, false, false, false, isoLevel);
			
			if(transaction.hasTransaction() && transaction.getTransaction() != null && !transaction.isCompleted()) {
		    	transaction.setCompleted();
		    } else {
		    	transaction.setRollbackOnly();
		    }
			return Mono.just(transaction);
		} else {
			sendMessage("errors", new Event(EventType.ROLLBACK, Utility.tokenGenerator(), new StreamsException("Rollback")));
			return Mono.error(() ->  new StreamsException("Rollback"));
		}
	}

	@Override
	public Mono<Void> rollback(ReactiveTransaction transaction) throws TransactionException {
		if(!transaction.isCompleted()) {
			transaction.setRollbackOnly();
			return Mono.empty();
		}
		return null;
	}
	
	private void sendMessage(String destination, Event event) {
	  	event.setKey(Utility.tokenGenerator());
	    GenericMessage<?> message = (GenericMessage<?>) MessageBuilder.withPayload(event)
	      .setHeader("partitionKey", event.getKey())
	      .build();
	    streamBridge.send(destination, message);
	}

}
