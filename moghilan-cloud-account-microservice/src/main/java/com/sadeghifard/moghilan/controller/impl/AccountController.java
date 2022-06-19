package com.sadeghifard.moghilan.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sadeghifard.moghilan.controller.IAccountController;
import com.sadeghifard.moghilan.model.Account;
import com.sadeghifard.moghilan.service.impl.AccountService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class AccountController implements IAccountController {
	
	private final AccountService accountService;
	
	@GetMapping("/account")
	public ResponseEntity<Iterable<Account>> getAllAccounts (){
		List<Account> accounts = (List<Account>) accountService.getAllAccounts();
		accounts = accounts.stream().filter(c -> c != null).toList();
		if(accounts != null ) {
			return new ResponseEntity<Iterable<Account>>(accounts, HttpStatus.OK);
		} else {
			return new ResponseEntity<Iterable<Account>>(accounts, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/account/{id}")
	public ResponseEntity<Account> getAccountById (@PathVariable Long id){
		Account account = accountService.getAccountById(id);
		if(account != null) {
			return new ResponseEntity<Account>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<Account>(account, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/account/{accountNum}")
	public ResponseEntity<Account> getAccountByNumber(@PathVariable Long accountNumber) {
		Account account = accountService.getAccountByNumber(accountNumber);
		if(account != null) {
			return new ResponseEntity<Account>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<Account>(account, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/account/{cusNationalCod}")
	public ResponseEntity<Iterable<Account>> getAccountsByCustomerNationalCode (@PathVariable Long cusNationalCod){
		List<Account> accounts = (List<Account>) accountService.getAccountsByCustomerNationalCode(cusNationalCod);
		accounts = accounts.stream().filter(c -> c != null).toList();
		if(accounts != null && !accounts.isEmpty()) {
			return new ResponseEntity<Iterable<Account>>(accounts, HttpStatus.OK);
		} else {
			return new ResponseEntity<Iterable<Account>>(accounts, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/account")
	public ResponseEntity<Account> saveAccount (@RequestBody Account account){
		Account existAccount = accountService.saveAccount(account);
		if(existAccount != null ) {
			return new ResponseEntity<Account>(existAccount, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Account>(existAccount, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@Override
	@PutMapping("/account")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PutMapping("/account/{accountNumber}")
	public ResponseEntity<Account> updateByAccountNumber(@RequestBody Account account, @PathVariable Long accountNumber) {
		Account existAccount = accountService.updateByAccountNumber(account, accountNumber);
		if(existAccount != null ) {
			return new ResponseEntity<Account>(existAccount, HttpStatus.OK);
		} else {
			return new ResponseEntity<Account>(existAccount, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PutMapping("/account/{id}")
	public ResponseEntity<Account> updateById (@RequestBody Account account, @PathVariable Long id){
		Account existAccount = accountService.updateById(account, id);
		if(existAccount != null ) {
			return new ResponseEntity<Account>(existAccount, HttpStatus.OK);
		} else {
			return new ResponseEntity<Account>(existAccount, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/account")
	public ResponseEntity<String> deleteAccount (@RequestBody Account account){
		String message = accountService.deleteAccount(account);
		return ResponseEntity.ok(message);
	}
	
	@DeleteMapping("/account/{accountNum}")
	public ResponseEntity<String> deleteByAccountNumber(@PathVariable Long accountNum){
		String message = accountService.deleteByAccountNumber(accountNum);
		return ResponseEntity.ok(message);
	}
	
	@DeleteMapping("/account/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		String message = accountService.deleteById(id);
		return ResponseEntity.ok(message);
	}
}