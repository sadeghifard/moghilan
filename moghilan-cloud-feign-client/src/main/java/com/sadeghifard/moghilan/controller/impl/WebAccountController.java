package com.sadeghifard.moghilan.controller.impl;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sadeghifard.moghilan.domain.Account;
import com.sadeghifard.moghilan.service.WebAccountService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class WebAccountController {

	private final WebAccountService webAccountService;
	
	
	@GetMapping("/account")
	Flux<Account> getAllAccounts(){
		return webAccountService.getAllAccounts();
	}
	
	@GetMapping("/account/i/{id}")
	Mono<Account> getAccountById(@PathVariable Long id){
		return webAccountService.getAccountById(id);
	}
	
	@GetMapping("/account/an/{accountNumber}")
	Mono<Account> getAccountByNumber(@PathVariable Long accountNumber){
		return webAccountService.getAccountByNumber(accountNumber);
	}
	
	@GetMapping("/account/nc/{customerNationalCode}")
	Flux<Account> getAccountsByCustomerNationalCode(@PathVariable Long customerNationalCode){
		return webAccountService.getAccountsByCustomerNationalCode(customerNationalCode);
	}
	
	@PostMapping(value = "/account")
	Mono<Account> saveAccount(@RequestBody Publisher<Account> account){
		return webAccountService.saveAccount(account);
	}
	
	@PutMapping(value = "/account", consumes = "application/json")
	Mono<Account> updateAccount(@RequestBody Publisher<Account> account){
		return webAccountService.updateAccount(account);
	}
	
	@PutMapping(value = "/account/an/{accountNumber}")
	Mono<Account> updateByAccountNumber(@RequestBody Publisher<Account> account, @PathVariable Long accountNumber){
		return webAccountService.updateByAccountNumber(account, accountNumber);
	}
	
	@PutMapping(value = "/account/i/{id}")
	Mono<Account> updateById(@RequestBody Publisher<Account> account, @PathVariable Long id){
		return webAccountService.updateById(account, id);
	}
	
	@DeleteMapping("/account")
	Mono<String> deleteAccount(@RequestBody Publisher<Account> account){
		return webAccountService.deleteAccount(account);
	}
	
	@DeleteMapping("/account/an/{accountNumber}")
	Mono<String> deleteByAccountNumber(@PathVariable Long accountNumber){
		return webAccountService.deleteByAccountNumber(accountNumber);
	}
	
	@DeleteMapping("/account/i/{id}")
	Mono<String> deleteById(@PathVariable Long id){
		return webAccountService.deleteById(id);
	}
}
