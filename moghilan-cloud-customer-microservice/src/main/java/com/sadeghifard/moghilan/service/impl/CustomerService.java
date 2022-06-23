package com.sadeghifard.moghilan.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
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
			throw new ResourceNotFoundException("Customer", "Get All", null);
		}
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer ID", id));
	}

	@Override
	public Customer getByCustomerNumber(Long customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Number", customerNumber));
	}

	@Override
	public Customer getByNationalCode(Long nationalCode) {
		return customerRepository.findByNationalCode(nationalCode)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "National Code", nationalCode));
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		try {
			customer.setCreateDate(LocalDateTime.now());
			return customerRepository.save(customer);
		} catch (Exception e) {
			throw new ResourceAlreadyReportedException("Customer", "Save Customer", customer);
		}
	}
	
	@Override
	public Customer updateCustomer(Customer customer) {
		try {
			customer.setModifyDate(LocalDateTime.now());
			return customerRepository.save(customer);
		} catch (Exception e) {
			throw new ResourceNotAcceptableException("Customer", "Update Customer", customer);
		}
	}

	@Override
	public Customer updateById(Customer customer, Long id) {
		try {
			Customer existCustomer = getCustomerById(id);
			if(existCustomer != null) {
				customer.setModifyDate(LocalDateTime.now());
				existCustomer = customerRepository.save(customer);
			}
			return existCustomer;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Customer", "Customer ID", customer);
		}
	}
	
	@Override
	public Customer updateByCustomerNumber(Customer customer, Long customerNumber) {
		try {
			Customer existCustomer = getByCustomerNumber(customerNumber);
			if(existCustomer != null) {
				customer.setModifyDate(LocalDateTime.now());
				existCustomer = customerRepository.save(customer);
			}
			return existCustomer;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Customer", "Customer Number", customer);
		}
	}


	@Override
	public Customer updateByNationalCode(Customer customer, Long nationalCode) {
		try {
			Customer existCustomer = getByNationalCode(nationalCode);
			if(existCustomer != null) {
				customer.setModifyDate(LocalDateTime.now());
				existCustomer = customerRepository.save(customer);
			}
		return existCustomer;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Customer", "Customer Natinal Code", nationalCode);
		}
	}
	
	@Override
	public String deleteCustomer(Customer customer) {
		try {
			customerRepository.delete(customer);
			return "Customer deleted successfully";
		} catch (Exception e) {
			throw new ResourceNotFoundException("Customer", "Delete Customer", customer);
		}
	}

	@Override
	public String deleteByCustomerNumber(Long customerNumber) {
		try {
			customerRepository.deleteByCustomerNumber(customerNumber);
			return "Customer deleted successfully by account number = " + customerNumber;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Customer", "Customer Number", customerNumber);
		}
	}

	@Override
	public String deleteById(Long id) {
		try {
			customerRepository.deleteById(id);
			return "Customer deleted successfully by ID = " + id;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Customer", "Customer ID", id);
		}
	}

	@Override
	public String deleteByNationalCode(Long nationalCode) {
		try {
			customerRepository.deleteByNationalCode(nationalCode);
			return "Customer deleted successfully by national code = " + nationalCode;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Customer", "Customer National Code", nationalCode);
		}
	}
}
