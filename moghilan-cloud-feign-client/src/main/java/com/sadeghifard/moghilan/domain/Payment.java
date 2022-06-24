package com.sadeghifard.moghilan.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.sadeghifard.moghilan.enums.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long paymentCode;
	private PaymentType paymentType;
	private Long paymentAmount;
	private LocalDateTime paymentDate;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
