package com.sadeghifard.moghilan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sadeghifard.moghilan.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	void deleteByAccountNumber(Long accountNumber);
	Optional<Account> findByAccountNumber(Long accountNumber);
	Optional<List<Account>>findByCustomerNationalCode(Long customerNationalCode);


}
