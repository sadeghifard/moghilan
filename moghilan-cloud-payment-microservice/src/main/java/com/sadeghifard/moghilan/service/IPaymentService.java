package com.sadeghifard.moghilan.service;

import com.sadeghifard.moghilan.enums.PaymentType;
import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
import com.sadeghifard.moghilan.model.Payment;


public interface IPaymentService {
	Iterable<Payment> getAllPayments() throws ResourceNotFoundException;
	Payment getById(Long id) throws ResourceNotFoundException;
	Payment getByPaymentCode(Long paymentCode) throws ResourceNotFoundException;
	Iterable<Payment> getByPaymentType(PaymentType paymentType) throws ResourceNotFoundException;
	Payment savePayment(Payment payment) throws ResourceAlreadyReportedException;
	Payment updatePayment(Payment payment) throws ResourceNotAcceptableException;
	Payment updateById(Payment payment, Long id);
	Payment updateByPaymentCode(Payment payment, Long paymentCode) throws ResourceNotFoundException;
	String deletePayment(Payment payment) throws ResourceNotFoundException;
	String deleteById(Long id) throws ResourceNotFoundException;
	String deleteByPaymentCode(Long paymentCode) throws ResourceNotFoundException;
}
