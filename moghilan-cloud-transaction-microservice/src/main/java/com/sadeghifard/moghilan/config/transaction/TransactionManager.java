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
		ProducerFactory<byte[], byte[]> producerFactory = ((KafkaMessageChannelBinder) binders.getBinder(null, MessageChannel.class)).getTransactionalProducerFactory();
	    KafkaTransactionManager<byte[], byte[]> txManager = new KafkaTransactionManager<>(producerFactory);
	    txManager.setTransactionIdPrefix(txId);
	    if(transactionStatus.isNewTransaction()) {
	    	txManager.commit(transactionStatus);
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
