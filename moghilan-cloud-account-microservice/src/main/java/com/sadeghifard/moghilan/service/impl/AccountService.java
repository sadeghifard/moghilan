package com.sadeghifard.moghilan.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sadeghifard.moghilan.exception.ResourceException;
import com.sadeghifard.moghilan.model.Account;
import com.sadeghifard.moghilan.repository.AccountRepository;
import com.sadeghifard.moghilan.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AccountService implements IAccountService {
	
	private final AccountRepository accountRepository;
	
	@Override
	public Iterable<Account> getAllAccounts(){
		try {
			return accountRepository.findAll();
		} catch (Exception e) {
			throw new ResourceException("Account", "Get All", null);
		}
	}
	
	@Override
	public Account getAccountById(Long id) {
		return accountRepository.findById(id)
				.orElseThrow(() -> new ResourceException("Account", "Account ID" , id));
	}
	
	@Override
	public Account getAccountByNumber(Long accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceException("Account", "Account Number" , accountNumber));
	}
	
	@Override
	public Iterable<Account> getAccountsByCustomerNationalCode(Long customerNationalCode){
		return accountRepository.findByCustomerNationalCode(customerNationalCode)
				.orElseThrow(() -> new ResourceException("Account", "Customer National Code", customerNationalCode));
	}
	
	@Override
	public Account saveAccount(Account account) {
		try {
			account.setCreateDate(LocalDateTime.now());
			return accountRepository.save(account);
		} catch (Exception e) {
			throw new ResourceException("Account", "save Account", account);
		}
	}
	

	@Override
	public Account updateAccount(Account account) {
		try {
			account.setModifyDate(LocalDateTime.now());
			return accountRepository.save(account);
		} catch (Exception e) {
			throw new ResourceException("Account", "Update Account", account);
		}
	}

	@Override
	public Account updateByAccountNumber(Account account, Long accountNumber) {
		Account existAccount = getAccountByNumber(accountNumber);
		if(existAccount != null) {
			account.setModifyDate(LocalDateTime.now());
			existAccount = accountRepository.save(account);
		}
		return existAccount;
	}
	
	@Override
	public Account updateById(Account account, Long id) {
		Account existAccount = getAccountById(id);
		if(existAccount != null) {
			account.setModifyDate(LocalDateTime.now());
			existAccount = accountRepository.save(existAccount);
		}
		return existAccount;
	}

	@Override
	public String deleteAccount(Account account) {
		accountRepository.delete(account);
		return "Account deleted successfully";
	}
	
	@Override
	public String deleteByAccountNumber(Long accountNumber) {
		Account existAccount = getAccountByNumber(accountNumber);
		if(existAccount != null) {
			accountRepository.deleteByAccountNumber(accountNumber);
		}
		return "Account deleted successfully by account number = " + accountNumber;
	}

	@Override
	public String deleteById(Long id) {
		Account existAccount = getAccountById(id);
		if(existAccount != null) {
			accountRepository.deleteById(id);
		}
		return "Account deleted successfully by account ID = "+ id;
	}
}
