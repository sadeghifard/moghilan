package com.sadeghifard.moghilan.controller.impl;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sadeghifard.moghilan.domain.Payment;
import com.sadeghifard.moghilan.enums.PaymentType;
import com.sadeghifard.moghilan.service.WebPaymentService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@AllArgsConstructor
public class WebPaymentController {
	
	private final WebPaymentService webPaymentService;
	

	@GetMapping("/payment")
	Flux<Payment> getAllPayments(){
		return webPaymentService.getAllPayments();
	}
	
	@GetMapping("/payment/i/{id}")
	Mono<Payment> getById(@PathVariable Long id){
		return webPaymentService.getById(id);
	}
	
	@GetMapping("/payment/pc/{paymentCode}")
	Mono<Payment> getByPaymentCode(@PathVariable Long paymentCode){
		return webPaymentService.getByPaymentCode(paymentCode);
	}
	
	@GetMapping("/payment/pc/{paymentCode}")
	Flux<Payment> getByPaymentType(@PathVariable PaymentType paymentType){
		return webPaymentService.getByPaymentType(paymentType);
	}
	
	@PostMapping(value = "/payment")
	Mono<Payment> savePayment(@RequestBody Publisher<Payment> payment){
		return webPaymentService.savePayment(payment);
	}
	
	@PutMapping(value = "/account")
	Mono<Payment> updatePayment(Payment payment){
		return webPaymentService.updatePayment(payment);
	}
	
	@PutMapping(value = "/account/i/{id}")
	Mono<Payment> updateById(@RequestBody Publisher<Payment> payment, @PathVariable Long id){
		return webPaymentService.updateById(payment, id);
	}
	
	@PutMapping(value = "/account/pc/{paymentCode}")
	Mono<Payment> updateByPaymentCode(@RequestBody Publisher<Payment> payment, @PathVariable Long paymentCode){
		return webPaymentService.updateByPaymentCode(payment, paymentCode);
	}
	
	@DeleteMapping("/payment")
	Mono<String> deletePayment(@RequestBody Publisher<Payment> payment){
		return webPaymentService.deletePayment(payment);
	}
	
	@DeleteMapping("/payment/i/{id}")
	Mono<String> deleteById(Long id){
		return webPaymentService.deleteById(id);
	}
	
	@DeleteMapping("/payment/pc/{paymentCode}")
	Mono<String> deleteByPaymentCode(@PathVariable Long paymentCode){
		return webPaymentService.deleteByPaymentCode(paymentCode);
	}


}
