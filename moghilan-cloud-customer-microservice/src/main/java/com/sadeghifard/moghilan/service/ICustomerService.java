package com.sadeghifard.moghilan.service;

import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
import com.sadeghifard.moghilan.model.Customer;

public interface ICustomerService {
	
	Iterable<Customer> getAllCustomers();
	Customer getCustomerById(Long id) throws ResourceNotFoundException;
	Customer getByCustomerNumber(Long customerNumber) throws ResourceNotFoundException;
	Customer getByNationalCode(Long nationalCode) throws ResourceNotFoundException;
	Customer saveCustomer(Customer customer)throws ResourceAlreadyReportedException;
	Customer updateCustomer(Customer customer)throws ResourceNotAcceptableException;
	Customer updateById(Customer customer, Long id) throws ResourceNotFoundException;
	Customer updateByCustomerNumber(Customer customer, Long customerNumber) throws ResourceNotFoundException;
	Customer updateByNationalCode(Customer customer, Long nationalCode) throws ResourceNotFoundException;
	String deleteCustomer(Customer customer) throws ResourceNotFoundException;
	String deleteByCustomerNumber(Long customerNumber) throws ResourceNotFoundException;
	String deleteById(Long id) throws ResourceNotFoundException;
	String deleteByNationalCode(Long nationalCode) throws ResourceNotFoundException;
}
