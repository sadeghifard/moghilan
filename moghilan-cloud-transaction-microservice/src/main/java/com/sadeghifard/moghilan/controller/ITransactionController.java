package com.sadeghifard.moghilan.controller;

import com.sadeghifard.moghilan.enums.EventType;
import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
import com.sadeghifard.moghilan.model.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionController {
	Flux<Transaction> getAllTransactions() throws ResourceNotFoundException;
	Mono<Transaction> getTransactionById(Long id) throws ResourceNotFoundException;
	Mono<Transaction> getTransactionByEventKey(String eventKey) throws ResourceNotFoundException;
	Flux<Transaction> getTransactionsByEventType(EventType eventType) throws ResourceNotFoundException;
	Mono<Transaction> saveTransaction(Transaction transaction) throws ResourceAlreadyReportedException;
	Mono<Transaction> updateTransaction(Transaction transaction) throws ResourceNotAcceptableException;
	Mono<Transaction> updateTransactionById(Transaction transaction, Long id) throws ResourceNotFoundException;
	Mono<Transaction> updateTransactionByEventKey(Transaction transaction, String eventKey) throws ResourceNotFoundException;
	Mono<String> deleteTransaction(Transaction transaction) throws ResourceNotFoundException;
	Mono<String> deleteTransactionById(Long id) throws ResourceNotFoundException;
	Mono<String> deleteTransactionByEventKey(String eventKey) throws ResourceNotFoundException;
	Mono<String> deleteTransactionByEventType(EventType eventType) throws ResourceNotFoundException;
}
