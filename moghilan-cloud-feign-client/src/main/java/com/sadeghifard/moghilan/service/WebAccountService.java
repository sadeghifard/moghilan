package com.sadeghifard.moghilan.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sadeghifard.moghilan.domain.Account;
import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;

@FeignClient("account-microservice")
public interface WebAccountService {
	
	@GetMapping("/account")
	ResponseEntity<Iterable<Account>> getAllAccounts();
	
	@GetMapping("/account/i/{id}")
	ResponseEntity<Account> getAccountById(@PathVariable Long id);
	
	@GetMapping("/account/an/{accountNumber}")
	ResponseEntity<Account> getAccountByNumber(@PathVariable Long accountNumber);
	
	@GetMapping("/account/nc/{customerNationalCode}")
	ResponseEntity<Iterable<Account>> getAccountsByCustomerNationalCode(@PathVariable Long customerNationalCode);
	
	@PostMapping(value = "/account", consumes = "application/json")
	ResponseEntity<Account> saveAccount(@RequestBody Account account);
	
	@PutMapping(value = "/account", consumes = "application/json")
	ResponseEntity<Account> updateAccount(@RequestBody Account account);
	
	@PutMapping(value = "/account/an/{accountNumber}", consumes = "application/json")
	ResponseEntity<Account> updateByAccountNumber(@RequestBody Account account, @PathVariable Long accountNumber);
	
	@PutMapping(value = "/account/i/{id}", consumes = "application/json")
	ResponseEntity<Account> updateById(@RequestBody Account account, @PathVariable Long id);
	
	@DeleteMapping("/account")
	ResponseEntity<String> deleteAccount(@RequestBody Account account);
	
	@DeleteMapping("/account/an/{accountNumber}")
	ResponseEntity<String> deleteByAccountNumber(@PathVariable Long accountNumber);
	
	@DeleteMapping("/account/i/{id}")
	ResponseEntity<String> deleteById(@PathVariable Long id);
}
