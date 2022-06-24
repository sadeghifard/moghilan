package com.sadeghifard.moghilan.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sadeghifard.moghilan.domain.Customer;

@FeignClient("customer-microservice")
public interface WebCustomerService {
	
	@GetMapping("/customer")
	ResponseEntity<Iterable<Customer>> getAllCustomers();
	
	@GetMapping("/customer/i/{id}")
	ResponseEntity<Customer> getCustomerById(@PathVariable Long id);
	
	@GetMapping("/customer/cn/{customerNumber}")
	ResponseEntity<Customer> getByCustomerNumber(@PathVariable Long customerNumber);
	
	@GetMapping("/customer/nc/{nationalCode}")
	ResponseEntity<Customer> getByNationalCode(@PathVariable Long nationalCode);
	
	@PostMapping(value = "/customer", consumes = "application/json")
	ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer);
	
	@PutMapping(value = "/customer", consumes = "application/json")
	ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer);
	
	@PutMapping(value = "/customer/i/{id}", consumes = "application/json")
	ResponseEntity<Customer> updateById(@RequestBody Customer customer, @PathVariable Long id);
	
	@PutMapping(value = "/customer/cn/{customerNumber}", consumes = "application/json")
	ResponseEntity<Customer> updateByCustomerNumber(@RequestBody Customer customer, Long customerNumber);
	
	@PutMapping(value = "/customer/nc/{nationalCode}", consumes = "application/json")
	ResponseEntity<Customer> updateByNationalCode(@RequestBody Customer customer, @PathVariable Long nationalCode);
	
	@DeleteMapping("/customer")
	ResponseEntity<String> deleteCustomer(@RequestBody Customer customer);
	
	@DeleteMapping("/customer/cn/{customerNumber}")
	ResponseEntity<String> deleteByCustomerNumber(@PathVariable Long customerNumber);
	
	@DeleteMapping("/customer/i/{id}")
	ResponseEntity<String> deleteById(@PathVariable Long id);
	
	@DeleteMapping("/customer/nc/{nationalCode}")
	ResponseEntity<String> deleteByNationalCode(@PathVariable Long nationalCode);

}