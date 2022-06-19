package com.sadeghifard.moghilan.service;

import org.springframework.http.ResponseEntity;

import com.sadeghifard.moghilan.enums.PaymentType;
import com.sadeghifard.moghilan.model.Payment;


public interface IPaymentService {
	Iterable<Payment> getAllPayments();
	Payment getById(Long id);
	Payment getByPaymentCode(Long paymentCode);
	Iterable<Payment> getByPaymentType(PaymentType paymentType);
	Payment savePayment(Payment payment);
	Payment updatePayment(Payment payment);
	Payment updateById(Payment payment, Long id);
	Payment updateByPaymentCode(Payment payment, Long paymentCode);
	String deletePayment(Payment payment);
	String deleteById(Long id);
	String deleteByPaymentCode(Long paymentCode);
}
