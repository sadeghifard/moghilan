package com.sadeghifard.moghilan.controller;


import org.springframework.http.ResponseEntity;

import com.sadeghifard.moghilan.model.Account;

public interface IAccountController {
	ResponseEntity<Iterable<Account>> getAllAccounts();
	ResponseEntity<Account> getAccountById(Long id);
	ResponseEntity<Account> getAccountByNumber(Long accountNumber);
	ResponseEntity<Iterable<Account>> getAccountsByCustomerNationalCode(Long customerNationalCode);
	ResponseEntity<Account> saveAccount(Account account);
	ResponseEntity<Account> updateAccount(Account account);
	ResponseEntity<Account> updateByAccountNumber(Account account, Long accountNumber);
	ResponseEntity<Account> updateById(Account account, Long id);
	ResponseEntity<String> deleteAccount(Account account);
	ResponseEntity<String> deleteByAccountNumber(Long accountNumber);
	ResponseEntity<String> deleteById(Long id);
}
