package com.sadeghifard.moghilan.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.sadeghifard.moghilan.enums.EventType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "event_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private EventType eventType;
	
	@Column(name = "event_key", nullable = false)
	private String eventKey;
	
	@Column(name = "object_specification", nullable = false)
	private String objectSpec;
	
	@CreatedDate
	@Column(name = "create_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createDate;
	
	public Transaction(EventType eventType, String eventKey, String objectSpec, LocalDateTime createDate) {
		this.eventType = eventType;
		this.eventKey = eventKey;
		this.objectSpec = objectSpec;
		this.createDate = createDate;
	}

}
