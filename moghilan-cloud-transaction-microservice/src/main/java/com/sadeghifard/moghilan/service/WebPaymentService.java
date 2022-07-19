package com.sadeghifard.moghilan.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sadeghifard.moghilan.domain.Payment;
import com.sadeghifard.moghilan.enums.PaymentType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FeignClient("payment-microservice")
public interface WebPaymentService {
	
	@GetMapping("/payment")
	Flux<Payment> getAllPayments();
	
	@GetMapping("/payment/i/{id}")
	Payment getById(@PathVariable Mono<Long> id);
	
	@GetMapping("/payment/pc/{paymentCode}")
	Payment getByPaymentCode(@PathVariable Mono<Long> paymentCode);
	
	@GetMapping("/payment/pc/{paymentCode}")
	Flux<Payment> getByPaymentType(@PathVariable PaymentType paymentType);
	
	@PostMapping(value = "/payment")
	Payment savePayment(@RequestBody Mono<Payment> payment);
	
	@PutMapping(value = "/account")
	Payment updatePayment(@RequestBody Mono<Payment> payment);
	
	@PutMapping(value = "/account/i/{id}")
	Mono<Payment> updateById(@RequestBody Mono<Payment> payment, @PathVariable Long id);
	
	@PutMapping(value = "/account/pc/{paymentCode}")
	Mono<Payment> updateByPaymentCode(@RequestBody Mono<Payment> payment, @PathVariable Long paymentCode);
	
	@DeleteMapping("/payment")
	String deletePayment(@RequestBody Mono<Payment> payment);
	
	@DeleteMapping("/payment/i/{id}")
	String deleteById(@PathVariable Mono<Long> id);
	
	@DeleteMapping("/payment/pc/{paymentCode}")
	String deleteByPaymentCode(@PathVariable Mono<Long> paymentCode);
}
