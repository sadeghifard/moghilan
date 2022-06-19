package com.sadeghifard.moghilan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sadeghifard.moghilan.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	void deleteByCustomerNumber(Long customerNumber);
	Optional<Customer> findByCustomerNumber(Long customerNumber);
	void deleteByNationalCode(Long nationalCode);
	Optional<Customer> findByNationalCode(Long nationalCode);
}
