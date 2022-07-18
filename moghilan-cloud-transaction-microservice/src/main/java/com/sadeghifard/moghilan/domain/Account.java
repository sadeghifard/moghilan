package com.sadeghifard.moghilan.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.sadeghifard.moghilan.enums.AccountStatus;
import com.sadeghifard.moghilan.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long accountNumber;
	private AccountType accountType;
	private Long balance;
	private Long transactionLimit;
	private String secureWord;
	private AccountStatus accountStatus;
	private Long branchNumber;
	private Long customerNationalCode;
	private String paymentType;
	private Long paymentAmount;
	private LocalDateTime paymentDate;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
