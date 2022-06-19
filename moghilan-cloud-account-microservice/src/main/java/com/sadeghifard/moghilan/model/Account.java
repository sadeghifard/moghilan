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

import com.sadeghifard.moghilan.enums.AccountStatus;
import com.sadeghifard.moghilan.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "account_no", nullable = false)
	private Long accountNumber;
	
	@Column(name = "account_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@Column(name = "balance", nullable = false)
	private Long balance;
	
	@Column(name = "transaction_limit")
	private Long transactionLimit;
	
	@Column(name = "secure_word")
	private String secureWord;
	
	@Column(name = "account_status")
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	
	@Column(name = "branch_no")
	private Long branchNumber;
	
	@Column(name = "customer_national_code")
	private Long customerNationalCode;
	
	@Column(name = "create_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createDate;
	
	@Column(name = "modify_date", nullable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifyDate;
}
