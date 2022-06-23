package com.sadeghifard.moghilan.service.impl;

import java.time.LocalDateTime;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
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
			throw new ResourceNotFoundException("Account", "Get All", null);
		}
	}
	
	@Override
	public Account getAccountById(Long id) {
		return accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "Account ID" , id));
	}
	
	@Override
	public Account getAccountByNumber(Long accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "Account Number" , accountNumber));
	}
	
	@Override
	public Iterable<Account> getAccountsByCustomerNationalCode(Long customerNationalCode){
		return accountRepository.findByCustomerNationalCode(customerNationalCode)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "Customer National Code", customerNationalCode));
	}
	
	@Override
	public Account saveAccount(Account account) {
		try {
			account.setCreateDate(LocalDateTime.now());
			return accountRepository.save(account);
		} catch (Exception e) {
			throw new ResourceAlreadyReportedException("Account", "save Account", account);
		}
	}
	

	@Override
	public Account updateAccount(Account account) {
		try {
			account.setModifyDate(LocalDateTime.now());
			return accountRepository.save(account);
		} catch (Exception e) {
			throw new ResourceNotAcceptableException("Account", "Update Account", account);
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
		try {
			accountRepository.delete(account);
			return "Account deleted successfully";
		} catch (Exception e) {
			throw new ResourceNotFoundException("Account", "Delete Account", account);
		}
	}
	
	@Override
	public String deleteByAccountNumber(Long accountNumber) {
		Account existAccount = getAccountByNumber(accountNumber);
		try {
			if(existAccount != null) {
				accountRepository.deleteByAccountNumber(accountNumber);
			}
			return "Account deleted successfully by account number = " + accountNumber;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Account", "Delete by Account Number", accountNumber);
		}
	}

	@Override
	public String deleteById(Long id) {
		try {
			Account existAccount = getAccountById(id);
			if(existAccount != null) {
				accountRepository.deleteById(id);
			}
			return "Account deleted successfully by account ID = "+ id;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Account", "Delete by Account ID", id);
		}
	}
}
