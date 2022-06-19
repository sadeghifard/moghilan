package com.sadeghifard.moghilan.service;

import com.sadeghifard.moghilan.model.Customer;

public interface ICustomerService {
	
	Iterable<Customer> getAllCustomers();
	Customer getCustomerById(Long id);
	Customer getByCustomerNumber(Long customerNumber);
	Customer getByNationalCode(Long nationalCode);
	Customer saveCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	Customer updateById(Customer customer, Long id);
	Customer updateByCustomerNumber(Customer customer, Long customerNumber);
	Customer updateByNationalCode(Customer customer, Long nationalCode);
	String deleteCustomer(Customer customer);
	String deleteByCustomerNumber(Long customerNumber);
	String deleteById(Long id);
	String deleteByNationalCode(Long nationalCode);
}
