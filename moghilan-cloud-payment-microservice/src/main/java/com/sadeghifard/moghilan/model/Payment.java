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

import com.sadeghifard.moghilan.enums.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "payment_code" , nullable = false, unique = true)
	private Long paymentCode;
	
	@Column(name = "payment_type")
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	
	@Column(name = "payment_amount", nullable = false )
	private Long paymentAmount;
	
	@Column(name = "payment_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime paymentDate;
	
	@Column(name = "create_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createDate;
	
	@Column(name = "modify_date", nullable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifyDate;
}
