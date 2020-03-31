package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class TxrServiceImpl implements TxrService {

	@Autowired
	private AccountRepository accountRepository;

	@Transactional
	public boolean txr(double amount, String fromAccNum, String toAccNum) {
		Account fromAccount = 
				accountRepository
				.findById(fromAccNum)
				.orElseThrow(()->new TxrException("account - "+fromAccNum +" not exist"));
		Account toAccount = accountRepository
				.findById(toAccNum)
				.orElseThrow(()->new TxrException("account - "+toAccNum +" not exist"));
		if(fromAccount.getBalance()<amount)throw new TxrException("No enough funds");
		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		return true;
	}

}
