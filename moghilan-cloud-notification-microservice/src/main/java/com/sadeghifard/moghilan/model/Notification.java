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

import org.hibernate.annotations.Where;

import com.sadeghifard.moghilan.enums.NotificationStatus;
import com.sadeghifard.moghilan.enums.NotificationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "notification_title")
	private String notificationTitle;
	
	@Column(name = "notification_type")
	@Enumerated(EnumType.STRING)
	private NotificationType notificationType;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@Column(name = "payment_amount")
	private Long paymentAmount;
	
	@Column(name = "transaction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime transactionDate;
	
	@Column(name = "account_no")
	private Long accountNumber;
	
	@Column(name = "account_balance")
	private Long accountBalance;
	
	@Column(name = "email")
	@Where(clause = "notification_type = 'EMAIL' ")
	private String email;
	
	@Column(name = "phone_no")
	@Where(clause = "notification_type = 'SMS' ")
	private String phoneNumber;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;
	
	@Column(name = "create_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createDate;
	
	@Column(name = "modify_date", nullable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifyDate;
}
