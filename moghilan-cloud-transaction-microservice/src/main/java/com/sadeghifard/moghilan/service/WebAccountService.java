package com.sadeghifard.moghilan.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sadeghifard.moghilan.domain.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FeignClient("account-microservice")
public interface WebAccountService {
	
	@GetMapping("/account")
	Flux<Account> getAllAccounts();
	
	@GetMapping("/account/i/{id}")
	Mono<Account> getAccountById(@PathVariable Long id);
	
	@GetMapping("/account/an/{accountNumber}")
	Mono<Account> getAccountByNumber(@PathVariable Long accountNumber);
	
	@GetMapping("/account/nc/{customerNationalCode}")
	Flux<Account> getAccountsByCustomerNationalCode(@PathVariable Long customerNationalCode);
	
	@PostMapping(value = "/account")
	Account saveAccount(@RequestBody Mono<Account> account);
	
	@PutMapping(value = "/account")
	Account updateAccount(@RequestBody Mono<Account> account);
	
	@PutMapping(value = "/account/an/{accountNumber}")
	Mono<Account> updateByAccountNumber(@RequestBody Mono<Account> account, @PathVariable Long accountNumber);
	
	@PutMapping(value = "/account/i/{id}")
	Mono<Account> updateById(@RequestBody Mono<Account> account, @PathVariable Long id);
	
	@DeleteMapping("/account")
	Mono<String> deleteAccount(@RequestBody Mono<Account> account);
	
	@DeleteMapping("/account/an/{accountNumber}")
	Mono<String> deleteByAccountNumber(@PathVariable Long accountNumber);
	
	@DeleteMapping("/account/i/{id}")
	Mono<String> deleteById(@PathVariable Long id);
}
