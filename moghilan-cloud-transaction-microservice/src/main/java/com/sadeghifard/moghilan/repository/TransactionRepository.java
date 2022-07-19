package com.sadeghifard.moghilan.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sadeghifard.moghilan.enums.EventType;
import com.sadeghifard.moghilan.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	void deleteByEventKey(String eventKey);
	void deleteByEventType(EventType eventType);
	List<Transaction> findByCreateDate(LocalDateTime createDate);
	Optional<Transaction> findByEventKey(String eventKey);
	List<Transaction> findByEventType(EventType eventType);

}
