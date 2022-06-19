package com.sadeghifard.moghilan.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sadeghifard.moghilan.exception.ResourceException;
import com.sadeghifard.moghilan.model.Customer;
import com.sadeghifard.moghilan.repository.CustomerRepository;
import com.sadeghifard.moghilan.service.ICustomerService;

import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class CustomerService implements ICustomerService{
	
	private final CustomerRepository customerRepository;

	@Override
	public Iterable<Customer> getAllCustomers() {
		try {
			return customerRepository.findAll();
		} catch (Exception e) {
			throw new ResourceException("Customer", "Get All", null);
		}
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new ResourceException("Customer", "Customer ID", id));
	}

	@Override
	public Customer getByCustomerNumber(Long customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber)
				.orElseThrow(() -> new ResourceException("Customer", "Customer Number", customerNumber));
	}

	@Override
	public Customer getByNationalCode(Long nationalCode) {
		return customerRepository.findByNationalCode(nationalCode)
				.orElseThrow(() -> new ResourceException("Customer", "National Code", nationalCode));
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		try {
			customer.setCreateDate(LocalDateTime.now());
			return customerRepository.save(customer);
		} catch (Exception e) {
			throw new ResourceException("Customer", "Save Customer", customer);
		}
	}
	
	@Override
	public Customer updateCustomer(Customer customer) {
		try {
			customer.setModifyDate(LocalDateTime.now());
			return customerRepository.save(customer);
		} catch (Exception e) {
			throw new ResourceException("Customer", "update Customer", customer);
		}
	}

	@Override
	public Customer updateById(Customer customer, Long id) {
		Customer existCustomer = getCustomerById(id);
		if(existCustomer != null) {
			customer.setModifyDate(LocalDateTime.now());
			existCustomer = customerRepository.save(customer);
		}
		return existCustomer;
	}
	
	@Override
	public Customer updateByCustomerNumber(Customer customer, Long customerNumber) {
		Customer existCustomer = getByCustomerNumber(customerNumber);
		if(existCustomer != null) {
			customer.setModifyDate(LocalDateTime.now());
			existCustomer = customerRepository.save(customer);
		}
		return existCustomer;
	}


	@Override
	public Customer updateByNationalCode(Customer customer, Long nationalCode) {
		Customer existCustomer = getByNationalCode(nationalCode);
		if(existCustomer != null) {
			customer.setModifyDate(LocalDateTime.now());
			existCustomer = customerRepository.save(customer);
		}
		return existCustomer;
	}
	
	@Override
	public String deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
		return "Customer deleted successfully";
	}

	@Override
	public String deleteByCustomerNumber(Long customerNumber) {
		customerRepository.deleteByCustomerNumber(customerNumber);
		return "Customer deleted successfully by account number = " + customerNumber;
	}

	@Override
	public String deleteById(Long id) {
		customerRepository.deleteById(id);
		return "Customer deleted successfully by ID = " + id;
	}

	@Override
	public String deleteByNationalCode(Long nationalCode) {
		customerRepository.deleteByNationalCode(nationalCode);
		return "Customer deleted successfully by national code = " + nationalCode;
	}
}
