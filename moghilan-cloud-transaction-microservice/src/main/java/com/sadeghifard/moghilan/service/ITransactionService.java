package com.sadeghifard.moghilan.service;

import com.sadeghifard.moghilan.enums.EventType;
import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
import com.sadeghifard.moghilan.model.Transaction;

import reactor.core.publisher.Flux;

public interface ITransactionService {
	Iterable<Transaction> getAllTransactions() throws ResourceNotFoundException;
	Transaction getTransactionById(Long id) throws ResourceNotFoundException;
	Transaction getTransactionByEventKey(String eventKey) throws ResourceNotFoundException;
	Iterable<Transaction> getTransactionsByEventType(EventType eventType) throws ResourceNotFoundException;
	Transaction saveTransaction(Transaction transaction) throws ResourceAlreadyReportedException;
	Transaction updateTransaction(Transaction transaction) throws ResourceNotAcceptableException;
	Transaction updateTransactionById(Transaction transaction, Long id) throws ResourceNotFoundException;
	Transaction updateTransactionByEventKey(Transaction transaction, String eventKey) throws ResourceNotFoundException;
	String deleteTransaction(Transaction transaction) throws ResourceNotFoundException;
	String deleteTransactionById(Long id) throws ResourceNotFoundException;
	String deleteTransactionByEventKey(String eventKey) throws ResourceNotFoundException;
	String deleteTransactionByEventType(EventType eventType) throws ResourceNotFoundException;
}
