package com.sadeghifard.moghilan.controller;

import org.springframework.http.ResponseEntity;

import com.sadeghifard.moghilan.enums.PaymentType;
import com.sadeghifard.moghilan.model.Payment;

public interface IPaymentController {
	ResponseEntity<Iterable<Payment>> getAllPayments();
	ResponseEntity<Payment> getById(Long id);
	ResponseEntity<Payment> getByPaymentCode(Long paymentCode);
	ResponseEntity<Iterable<Payment>> getByPaymentType(String paymentType);
	ResponseEntity<Payment> savePayment(Payment payment);
	ResponseEntity<Payment> updatePayment(Payment payment);
	ResponseEntity<Payment> updateById(Payment payment, Long id);
	ResponseEntity<Payment> updateByPaymentCode(Payment payment, Long paymentCode);
	ResponseEntity<String> deletePayment(Payment payment);
	ResponseEntity<String> deleteById(Long id);
	ResponseEntity<String> deleteByPaymentCode(Long paymentCode);
}
