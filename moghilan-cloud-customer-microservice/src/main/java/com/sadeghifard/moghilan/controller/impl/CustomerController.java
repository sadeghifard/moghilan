package com.sadeghifard.moghilan.controller.impl;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sadeghifard.moghilan.controller.ICustomerController;
import com.sadeghifard.moghilan.model.Customer;
import com.sadeghifard.moghilan.service.impl.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerController implements ICustomerController{
	
	private final CustomerService customerService;

	@Override
	@GetMapping("/customer")
	public ResponseEntity<Iterable<Customer>> getAllCustomers() {
		List<Customer> customers = (List<Customer>) customerService.getAllCustomers();
		customers = customers.stream().filter(c -> c != null).toList();
		if(customers != null && !customers.isEmpty() ) {
			return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
		} else {
			return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@GetMapping("/customer/i/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		Customer customer = customerService.getCustomerById(id);
		if(customer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(customer, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@GetMapping("/customer/cn/{customerNumber")
	public ResponseEntity<Customer> getByCustomerNumber(@PathVariable Long customerNumber) {
		Customer customer = customerService.getByCustomerNumber(customerNumber);
		if(customer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(customer, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@GetMapping("/customer/nc/{nationalCode}")
	public ResponseEntity<Customer> getByNationalCode(@PathVariable Long nationalCode) {
		Customer customer = customerService.getByNationalCode(nationalCode);
		if(customer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(customer, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@PostMapping("/customer")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		Customer existeCustomer = customerService.saveCustomer(customer);
		if(existeCustomer != null) {
			return new ResponseEntity<Customer>(existeCustomer, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Customer>(existeCustomer, HttpStatus.NO_CONTENT);
		}
	}
	
	@Override
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		Customer existCustomer = customerService.updateCustomer(customer);
		if(existCustomer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(customer, HttpStatus.NO_CONTENT);
		}
	}
	
	@Override
	@PutMapping("/customer/i/{id}")
	public ResponseEntity<Customer> updateById(@RequestBody Customer customer, @PathVariable Long id) {
		Customer existCustomer = customerService.updateById(customer, id);
		if(existCustomer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(customer, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@PutMapping("/customer/cn/{customerNumber}")
	public ResponseEntity<Customer> updateByCustomerNumber(@RequestBody Customer customer, @PathVariable Long customerNumber) {
		Customer existCustomer = customerService.updateByCustomerNumber(customer, customerNumber);
		if(existCustomer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(customer, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@PutMapping("/customer/nc/{nationalCode}")
	public ResponseEntity<Customer> updateByNationalCode(Customer customer, Long nationalCode) {
		Customer existCustomer = customerService.updateByNationalCode(customer, nationalCode);
		if(existCustomer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(customer, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@Override
	@DeleteMapping("/account")
	public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer) {
		String message = customerService.deleteCustomer(customer);
		return ResponseEntity.ok(message);
	}

	@Override
	@DeleteMapping("/account/cn/{customerNumber}")
	public ResponseEntity<String> deleteByCustomerNumber(@PathVariable Long customerNumber) {
		String message = customerService.deleteByCustomerNumber(customerNumber);
		return ResponseEntity.ok(message);
	}

	@Override
	@DeleteMapping("/account/i/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		String message = customerService.deleteById(id);
		return ResponseEntity.ok(message);
	}

	@Override
	@DeleteMapping("/account/nc/{nationalCode}")
	public ResponseEntity<String> deleteByNationalCode(Long nationalCode) {
		String message = customerService.deleteByNationalCode(nationalCode);
		return ResponseEntity.ok(message);
	}
}
