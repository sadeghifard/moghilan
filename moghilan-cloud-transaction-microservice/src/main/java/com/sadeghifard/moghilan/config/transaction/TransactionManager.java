package com.sadeghifard.moghilan.config.transaction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.binder.BinderFactory;
import org.springframework.cloud.stream.binder.kafka.KafkaMessageChannelBinder;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.ReactiveTransaction;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.GenericReactiveTransaction;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Transactional
@Component("transactionManager")
public class TransactionManager implements ReactiveTransactionManager {
	
	private final TransactionStatus transactionStatus;
	private final BinderFactory binders;
	
	@Value(value = "${spring.cloud.stream.kafka.binder.transaction.transaction-id-prefix}")
	private String txId;
	
	@Override
	public Mono<Void> commit(ReactiveTransaction transaction) throws TransactionException {
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
	}

	@Override
	public Mono<ReactiveTransaction> getReactiveTransaction(TransactionDefinition definition) throws TransactionException {
		Integer isoLevel = definition.ISOLATION_READ_COMMITTED;
		GenericReactiveTransaction transaction = new GenericReactiveTransaction(definition, false, false, false, false, isoLevel);
		
		if( transaction.isNewTransaction() && transaction.isNewSynchronization()) {
			transaction.setRollbackOnly();
		} else if(transaction.hasTransaction() && transaction.getTransaction() != null && !transaction.isCompleted()) {
	    	transaction.setCompleted();
	    } else {
	    	transaction.setRollbackOnly();
	    }
		return Mono.just(transaction);
	}

	@Override
	public Mono<Void> rollback(ReactiveTransaction transaction) throws TransactionException {
		if(!transaction.isCompleted()) {
			transaction.setRollbackOnly();
			return Mono.empty();
		}
		return null;
	}

}
