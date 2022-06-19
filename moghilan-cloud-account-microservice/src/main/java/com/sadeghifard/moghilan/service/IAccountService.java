package com.sadeghifard.moghilan.service;

import com.sadeghifard.moghilan.model.Account;

public interface IAccountService {
	Iterable<Account> getAllAccounts();
	Account getAccountById(Long id);
	Account getAccountByNumber(Long accountNumber);
	Iterable<Account> getAccountsByCustomerNationalCode(Long customerNationalCode);
	Account saveAccount(Account account);
	Account updateAccount(Account account);
	Account updateByAccountNumber(Account account, Long accountNumber);
	Account updateById(Account account, Long id);
	String deleteAccount(Account account);
	String deleteByAccountNumber(Long accountNumber);
	String deleteById(Long id);
}
