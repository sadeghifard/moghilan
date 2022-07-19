package com.sadeghifard.moghilan.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.sadeghifard.moghilan.controller.ITransactionController;
import com.sadeghifard.moghilan.enums.EventType;
import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
import com.sadeghifard.moghilan.model.Transaction;
import com.sadeghifard.moghilan.service.impl.TransactionService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class TransactionController implements ITransactionController {
	
	private final TransactionService transactionService;

	@Override
	public Flux<Transaction> getAllTransactions() throws ResourceNotFoundException {
		return Flux.fromIterable(transactionService.getAllTransactions());
	}

	@Override
	public Mono<Transaction> getTransactionById(Long id) throws ResourceNotFoundException {
		return Mono.just(transactionService.getTransactionById(id));
	}

	@Override
	public Mono<Transaction> getTransactionByEventKey(String eventKey) throws ResourceNotFoundException {
		return Mono.just(transactionService.getTransactionByEventKey(eventKey));
	}

	@Override
	public Flux<Transaction> getTransactionsByEventType(EventType eventType) throws ResourceNotFoundException {
		return Flux.fromIterable(transactionService.getTransactionsByEventType(eventType));
	}

	@Override
	public Mono<Transaction> saveTransaction(Transaction transaction) throws ResourceAlreadyReportedException {
		return Mono.just(transactionService.saveTransaction(transaction));
	}

	@Override
	public Mono<Transaction> updateTransaction(Transaction transaction) throws ResourceNotAcceptableException {
		return Mono.just(transactionService.updateTransaction(transaction));
	}

	@Override
	public Mono<Transaction> updateTransactionById(Transaction transaction, Long id) throws ResourceNotFoundException {
		return Mono.just(transactionService.updateTransactionById(transaction, id));
	}

	@Override
	public Mono<Transaction> updateTransactionByEventKey(Transaction transaction, String eventKey)
			throws ResourceNotFoundException {
		return Mono.just(transactionService.updateTransactionByEventKey(transaction, eventKey));
	}

	@Override
	public Mono<String> deleteTransaction(Transaction transaction) throws ResourceNotFoundException {
		return Mono.just(transactionService.deleteTransaction(transaction));
	}

	@Override
	public Mono<String> deleteTransactionById(Long id) throws ResourceNotFoundException {
		return Mono.just(transactionService.deleteTransactionById(id));
	}

	@Override
	public Mono<String> deleteTransactionByEventKey(String eventKey) throws ResourceNotFoundException {
		return Mono.just(transactionService.deleteTransactionByEventKey(eventKey));
	}

	@Override
	public Mono<String> deleteTransactionByEventType(EventType eventType) throws ResourceNotFoundException {
		return Mono.just(transactionService.deleteTransactionByEventType(eventType));
	}

}
