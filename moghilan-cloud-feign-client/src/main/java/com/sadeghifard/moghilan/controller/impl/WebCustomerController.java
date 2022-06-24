package com.sadeghifard.moghilan.controller.impl;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sadeghifard.moghilan.domain.Customer;
import com.sadeghifard.moghilan.service.WebCustomerService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class WebCustomerController {
	
	private final WebCustomerService  webCustomerService;

	@GetMapping("/customer")
	Flux<Customer> getAllCustomers(){
		return webCustomerService.getAllCustomers();
	}
	
	@GetMapping("/customer/i/{id}")
	Mono<Customer> getCustomerById(@PathVariable Long id){
		return webCustomerService.getCustomerById(id);
	}
	
	@GetMapping("/customer/cn/{customerNumber}")
	Mono<Customer> getByCustomerNumber(@PathVariable Long customerNumber){
		return webCustomerService.getByCustomerNumber(customerNumber);
	}
	
	@GetMapping("/customer/nc/{nationalCode}")
	Mono<Customer> getByNationalCode(@PathVariable Long nationalCode){
		return webCustomerService.getByNationalCode(nationalCode);
	}
	
	@PostMapping(value = "/customer")
	Mono<Customer> saveCustomer(@RequestBody Publisher<Customer> customer){
		return webCustomerService.saveCustomer(customer);
	}
	
	@PutMapping(value = "/customer")
	Mono<Customer> updateCustomer(@RequestBody Publisher<Customer> customer){
		return webCustomerService.updateCustomer(customer);
	}
	
	@PutMapping(value = "/customer/i/{id}")
	Mono<Customer> updateById(@RequestBody Publisher<Customer> customer, @PathVariable Long id){
		return webCustomerService.updateById(customer, id);
	}
	
	@PutMapping(value = "/customer/cn/{customerNumber}")
	Mono<Customer> updateByCustomerNumber(@RequestBody Publisher<Customer> customer, Long customerNumber){
		return webCustomerService.updateByCustomerNumber(customer, customerNumber);
	}
	
	@PutMapping(value = "/customer/nc/{nationalCode}")
	Mono<Customer> updateByNationalCode(@RequestBody Publisher<Customer> customer, @PathVariable Long nationalCode){
		return webCustomerService.updateByNationalCode(customer, nationalCode);
	}
	
	@DeleteMapping("/customer")
	Mono<String> deleteCustomer(@RequestBody Publisher<Customer> customer){
		return webCustomerService.deleteCustomer(customer);
	}
	
	@DeleteMapping("/customer/cn/{customerNumber}")
	Mono<String> deleteByCustomerNumber(@PathVariable Long customerNumber){
		return webCustomerService.deleteByCustomerNumber(customerNumber);
	}
	
	@DeleteMapping("/customer/i/{id}")
	Mono<String> deleteById(@PathVariable Long id){
		return webCustomerService.deleteById(id);
	}
	
	@DeleteMapping("/customer/nc/{nationalCode}")
	Mono<String> deleteByNationalCode(@PathVariable Long nationalCode){
		return webCustomerService.deleteByNationalCode(nationalCode);
	}
}
