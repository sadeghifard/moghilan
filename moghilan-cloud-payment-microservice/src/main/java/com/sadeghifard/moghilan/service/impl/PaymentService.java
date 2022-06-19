package com.sadeghifard.moghilan.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sadeghifard.moghilan.enums.PaymentType;
import com.sadeghifard.moghilan.exception.ResourceException;
import com.sadeghifard.moghilan.model.Payment;
import com.sadeghifard.moghilan.repository.PaymentRepository;
import com.sadeghifard.moghilan.service.IPaymentService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PaymentService implements IPaymentService {

	private final PaymentRepository paymentRepository;
	
	@Override
	public Iterable<Payment> getAllPayments() {
		try {
			return paymentRepository.findAll();
		} catch (Exception e) {
			throw new ResourceException("Payment", "Get All", null);
		}
	}

	@Override
	public Payment getById(Long id) {
		return paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceException("Payment", "Payment ID", id));
	}

	@Override
	public Payment getByPaymentCode(Long paymentCode) {
		return paymentRepository.findByPaymentCode(paymentCode)
				.orElseThrow(() -> new ResourceException("Payment", "Payment Code", paymentCode));
	}

	@Override
	public Iterable<Payment> getByPaymentType(PaymentType paymentType) {
		return paymentRepository.findByPaymentType(paymentType)
				.orElseThrow(() -> new ResourceException("Payment", "Payment Type", paymentType));
	}

	@Override
	public Payment savePayment(Payment payment) {
		try {
			payment.setModifyDate(LocalDateTime.now());
			return paymentRepository.save(payment);
		} catch (Exception e) {
			throw new ResourceException("Payment", "Save Payment", payment);
		}
	}

	@Override
	public Payment updatePayment(Payment payment) {
		try {
			payment.setModifyDate(LocalDateTime.now());
			return paymentRepository.save(payment);
		} catch (Exception e) {
			throw new ResourceException("Payment", "Update Payment", payment);
		}
	}

	@Override
	public Payment updateById(Payment payment, Long id) {
		Payment existPayment = getById(id);
		if(existPayment != null) {
			payment.setModifyDate(LocalDateTime.now());
			existPayment = paymentRepository.save(payment);
		}
		return existPayment;
	}

	@Override
	public Payment updateByPaymentCode(Payment payment, Long paymentCode) {
		Payment existPayment = getByPaymentCode(paymentCode);
		if(existPayment != null) {
			payment.setModifyDate(LocalDateTime.now());
			existPayment = paymentRepository.save(payment);
		}
		return existPayment;
	}

	@Override
	public String deletePayment(Payment payment) {
		paymentRepository.delete(payment);
		return "Payment deleted successfully";
	}

	@Override
	public String deleteById(Long id) {
		paymentRepository.deleteById(id);
		return "Payment deleted successfully with ID = " + id;
	}

	@Override
	public String deleteByPaymentCode(Long paymentCode) {
		Payment existPayment = getByPaymentCode(paymentCode);
		if(existPayment != null) {
			paymentRepository.deleteByPaymentCode(paymentCode);
		}
		return "Payment deleted successfully with payment code = " + paymentCode;
	}

}
