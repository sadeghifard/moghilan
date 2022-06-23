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

@FeignClient("account-microservice")
public interface WebAccountService {
	
	@GetMapping("/account/nc/{cusNationalCod}")
	ResponseEntity<Iterable<Account>> getAccountsByCustomerNationalCode (@PathVariable Long cusNationalCod);
	
	@PutMapping(value = "/account/i/{id}", consumes = "application/json")
	public ResponseEntity<Account> updateById (@RequestBody Account account, @PathVariable Long id);
	
	@DeleteMapping("/account/i/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id);
	
	@PostMapping(value = "/account", consumes = "application/json")
	public ResponseEntity<Account> saveAccount (@RequestBody Account account);
}
