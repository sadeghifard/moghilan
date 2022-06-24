package com.sadeghifard.moghilan.service;

import org.reactivestreams.Publisher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sadeghifard.moghilan.domain.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FeignClient("customer-microservice")
public interface WebCustomerService {
	
	@GetMapping("/customer")
	Flux<Customer> getAllCustomers();
	
	@GetMapping("/customer/i/{id}")
	Mono<Customer> getCustomerById(@PathVariable Long id);
	
	@GetMapping("/customer/cn/{customerNumber}")
	Mono<Customer> getByCustomerNumber(@PathVariable Long customerNumber);
	
	@GetMapping("/customer/nc/{nationalCode}")
	Mono<Customer> getByNationalCode(@PathVariable Long nationalCode);
	
	@PostMapping(value = "/customer")
	Mono<Customer> saveCustomer(@RequestBody Publisher<Customer> customer);
	
	@PutMapping(value = "/customer")
	Mono<Customer> updateCustomer(@RequestBody Publisher<Customer> customer);
	
	@PutMapping(value = "/customer/i/{id}")
	Mono<Customer> updateById(@RequestBody Publisher<Customer> customer, @PathVariable Long id);
	
	@PutMapping(value = "/customer/cn/{customerNumber}")
	Mono<Customer> updateByCustomerNumber(@RequestBody Publisher<Customer> customer, Long customerNumber);
	
	@PutMapping(value = "/customer/nc/{nationalCode}")
	Mono<Customer> updateByNationalCode(@RequestBody Publisher<Customer> customer, @PathVariable Long nationalCode);
	
	@DeleteMapping("/customer")
	Mono<String> deleteCustomer(@RequestBody Publisher<Customer> customer);
	
	@DeleteMapping("/customer/cn/{customerNumber}")
	Mono<String> deleteByCustomerNumber(@PathVariable Long customerNumber);
	
	@DeleteMapping("/customer/i/{id}")
	Mono<String> deleteById(@PathVariable Long id);
	
	@DeleteMapping("/customer/nc/{nationalCode}")
	Mono<String> deleteByNationalCode(@PathVariable Long nationalCode);
}