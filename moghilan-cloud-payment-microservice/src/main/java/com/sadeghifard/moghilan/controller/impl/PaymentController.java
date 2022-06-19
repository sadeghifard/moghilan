package com.sadeghifard.moghilan.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sadeghifard.moghilan.controller.IPaymentController;
import com.sadeghifard.moghilan.enums.PaymentType;
import com.sadeghifard.moghilan.model.Payment;
import com.sadeghifard.moghilan.service.impl.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PaymentController implements IPaymentController {
	
	private final PaymentService paymentService;

	@Override
	@GetMapping("/payment")
	public ResponseEntity<Iterable<Payment>> getAllPayments() {
		List<Payment> payments = (List<Payment>) paymentService.getAllPayments();
		payments = payments.stream().filter(p -> p != null).toList();
		if(payments != null && !payments.isEmpty()) {
			return new ResponseEntity<Iterable<Payment>>(payments, HttpStatus.OK);
		} else {
			return new ResponseEntity<Iterable<Payment>>(payments, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@GetMapping("/payment/{id}")
	public ResponseEntity<Payment> getById(Long id) {
		Payment existPayment = paymentService.getById(id);
		if(existPayment != null) {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.OK);
		} else {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@GetMapping("/payment/{paymentCode}")
	public ResponseEntity<Payment> getByPaymentCode(Long paymentCode) {
		Payment existPayment = paymentService.getByPaymentCode(paymentCode);
		if(existPayment != null) {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.OK);
		} else {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@GetMapping("/payment/{paymentType}")
	public ResponseEntity<Iterable<Payment>> getByPaymentType(@PathVariable String paymentType) {
		PaymentType existPaymentType = PaymentType.valueOf(paymentType);
		List<Payment> payments = (List<Payment>) paymentService.getByPaymentType(existPaymentType);
		payments = payments.stream().filter(p -> p != null).toList();
		if(payments != null && !payments.isEmpty()) {
			return new ResponseEntity<Iterable<Payment>>(payments, HttpStatus.OK);
		} else {
			return new ResponseEntity<Iterable<Payment>>(payments, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@PostMapping("/payment")
	public ResponseEntity<Payment> savePayment(@RequestBody Payment payment) {
		Payment existPayment = paymentService.savePayment(payment);
		if(existPayment != null) {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@PutMapping("/payment")
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
		Payment existPayment = paymentService.updatePayment(payment);
		if(existPayment != null) {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.OK);
		} else {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@PutMapping("/payment/{id}")
	public ResponseEntity<Payment> updateById(@RequestBody Payment payment, @PathVariable Long id) {
		Payment existPayment = paymentService.updateById(payment, id);
		if(existPayment != null) {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.OK);
		} else {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@PutMapping("/payment/{paymentCode}")
	public ResponseEntity<Payment> updateByPaymentCode(@RequestBody Payment payment, @PathVariable Long paymentCode) {
		Payment existPayment = paymentService.updateByPaymentCode(payment, paymentCode);
		if(existPayment != null) {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.OK);
		} else {
			return new ResponseEntity<Payment>(existPayment, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@DeleteMapping("/payment")
	public ResponseEntity<String> deletePayment(@RequestBody Payment payment) {
		String message = paymentService.deletePayment(payment);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/payment/{id}")
	public ResponseEntity<String> deleteById(Long id) {
		String message = paymentService.deleteById(id);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/payment/{paymentCode}")
	public ResponseEntity<String> deleteByPaymentCode(Long paymentCode) {
		String message = paymentService.deleteByPaymentCode(paymentCode);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
