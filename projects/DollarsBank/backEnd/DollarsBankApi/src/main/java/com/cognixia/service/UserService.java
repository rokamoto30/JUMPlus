package com.cognixia.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.Account;
import com.cognixia.model.Trans;
import com.cognixia.model.User;
import com.cognixia.model.repo.AccountRepo;
import com.cognixia.model.repo.TransRepo;
import com.cognixia.model.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	TransRepo transRepo;
	
	public User getUser(String username) throws InvalidException {
		Optional<User> found = userRepo.findByUsername(username);
		if (found.isEmpty()) {
			throw new InvalidException("User doesn't exist");
		}
		return found.get();
	}
	
	public Account getAccount(String username, String accountName) throws InvalidException {
		User user = getUser(username);
		Optional<Account> found = accountRepo.getAccount(user.getId(), accountName);
		if (found.isEmpty()) {
			throw new InvalidException("Account doesn't exist");
		}
		return found.get();
	}
	
	public User login(String username, String password) throws InvalidException {
		User user = getUser(username);
		if(user.getPassword().equals(password)) {
			return user;
		} else {
			throw new InvalidException("Password doesn't match");
		}
	}
	
	public User createUser(String username, String password) throws InvalidException {
		Optional<User> found = userRepo.findByUsername(username);
		if (found.isPresent()) {
			throw new InvalidException("User already exists");
		}
		User user = new User(null, username, password, null, null);
		User created = userRepo.save(user);
		return created;
	}
		
	public Account createAccount(String username, String accountName, Double balance) throws InvalidException {
		User user = getUser(username);
		if (accountRepo.getAccount(user.getId(), accountName).isPresent()) {
			throw new InvalidException("An account already exists with that name");
		}
		Account account = new Account(null, user, null, accountName, balance);
		Account created = accountRepo.save(account);
		return created;
	}
	
	public List<Account> getAccounts(String username) throws InvalidException {
		return accountRepo.getAccounts(getUser(username).getId());
	}
		
	public Trans makeTransaction(Double amount, String desc, String username, String accountName) throws InvalidException {
		User user = getUser(username);
		Account account = getAccount(username, accountName);
		
		Trans trans = new Trans(null, amount, desc, user, account);
		Trans created = transRepo.save(trans);
		
		account.setBalance(account.getBalance() + amount);
		accountRepo.save(account);
		
		return created;
	}
	
	public List<Trans> getTransactions(String username) throws InvalidException {
		return transRepo.getTransactions(getUser(username).getId());
	}
	
	public List<Trans> transfer(Double amount, String desc, String senderUsername, String senderAccountName, String reciverUsername, String reciverAccountName) throws InvalidException {
		amount = Math.abs(amount);
		Trans trans = makeTransaction(amount*-1, desc, senderUsername, senderAccountName);
		Trans trans2 = makeTransaction(amount, desc, reciverUsername, reciverAccountName);
		List<Trans> transList= Arrays.asList(trans, trans2);
		return transList;
	}
	
}
