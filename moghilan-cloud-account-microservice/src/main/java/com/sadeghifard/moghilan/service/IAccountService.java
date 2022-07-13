package com.sadeghifard.moghilan.service;

import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
import com.sadeghifard.moghilan.model.Account;

public interface IAccountService {
	Iterable<Account> getAllAccounts() throws ResourceNotFoundException;
	Account getAccountById(Long id) throws ResourceNotFoundException;
	Account getAccountByNumber(Long accountNumber) throws ResourceNotFoundException;
	Iterable<Account> getAccountsByCustomerNationalCode(Long customerNationalCode) throws ResourceNotFoundException;
	Account saveAccount(Account account) throws ResourceAlreadyReportedException;
	Account updateAccount(Account account) throws ResourceNotAcceptableException;
	Account updateByAccountNumber(Account account, Long accountNumber) throws ResourceNotFoundException;
	Account updateById(Account account, Long id) throws ResourceNotFoundException;
	String deleteAccount(Account account) throws ResourceNotFoundException;
	String deleteByAccountNumber(Long accountNumber) throws ResourceNotFoundException;
	String deleteById(Long id) throws ResourceNotFoundException;
}
