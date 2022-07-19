package com.sadeghifard.moghilan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sadeghifard.moghilan.enums.EventType;
import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
import com.sadeghifard.moghilan.model.Transaction;
import com.sadeghifard.moghilan.repository.TransactionRepository;
import com.sadeghifard.moghilan.service.ITransactionService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@Transactional
@AllArgsConstructor
public class TransactionService implements ITransactionService {
	
	private final TransactionRepository transactionRepository;

	@Override
	public Iterable<Transaction> getAllTransactions() throws ResourceNotFoundException {
		return transactionRepository.findAll();
	}

	@Override
	public Transaction getTransactionById(Long id) throws ResourceNotFoundException {
		return transactionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction", "ID", id));
	}

	@Override
	public Transaction getTransactionByEventKey(String eventKey) throws ResourceNotFoundException {
		return transactionRepository.findByEventKey(eventKey)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction", "eventKey", eventKey));
	}

	@Override
	public Iterable<Transaction> getTransactionsByEventType(EventType eventType) throws ResourceNotFoundException {
		return transactionRepository.findByEventType(eventType);
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) throws ResourceAlreadyReportedException {
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) throws ResourceNotAcceptableException {
		return null;
	}

	@Override
	public Transaction updateTransactionById(Transaction transaction, Long id) throws ResourceNotFoundException {
		Transaction existTransaction = getTransactionById(id);
		if(existTransaction != null) {
			return transactionRepository.save(transaction);
		} else {
			throw new ResourceNotFoundException("Transaction", "ID", id);
		}
	}

	@Override
	public Transaction updateTransactionByEventKey(Transaction transaction, String eventKey)
			throws ResourceNotFoundException {
		Transaction existTransaction = getTransactionByEventKey(eventKey);
		if(existTransaction != null) {
			return transactionRepository.save(transaction);
		} else {
			throw new ResourceNotFoundException("Transaction", "eventKey", eventKey);
		}
	}

	@Override
	public String deleteTransaction(Transaction transaction) throws ResourceNotFoundException {
		transactionRepository.delete(transaction);
		return "Deleted";
	}

	@Override
	public String deleteTransactionById(Long id) throws ResourceNotFoundException {
		Transaction existTransaction = getTransactionById(id);
		if(existTransaction != null) {
			transactionRepository.deleteById(id);
			return "Deleted";
		} else {
			throw new ResourceNotFoundException("Transaction", "ID", id);
		}
	}

	@Override
	public String deleteTransactionByEventKey(String eventKey) throws ResourceNotFoundException {
		Transaction existTransaction = getTransactionByEventKey(eventKey);
		if(existTransaction != null) {
			transactionRepository.deleteByEventKey(eventKey);
			return "Deleted";
		} else {
			throw new ResourceNotFoundException("Transaction", "eventKey", eventKey);
		}
	}

	@Override
	public String deleteTransactionByEventType(EventType eventType) throws ResourceNotFoundException {
		Iterable<Transaction> existTransaction = getTransactionsByEventType(eventType);
		if(existTransaction != null) {
			transactionRepository.deleteByEventType(eventType);
			return "Deleted";
		} else {
			throw new ResourceNotFoundException("Transaction", "eventType", eventType);
		}
	}

}
