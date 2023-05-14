package com.cognixia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.Account;
import com.cognixia.model.Trans;
import com.cognixia.model.User;
import com.cognixia.service.UserService;

@RequestMapping("/api")
@RestController
public class UserController {
	@Autowired
	UserService service;
	
	@GetMapping("/user/login/{username}/{password}")
	public User login(@PathVariable String username, @PathVariable String password) throws InvalidException {
		return service.login(username, password);
	}
	
	@PostMapping("/user/create/{username}/{password}")
	public User createUser(@PathVariable String username, @PathVariable String password) throws InvalidException {
		return service.createUser(username, password);
	}
	
	@PostMapping("/account/create/{username}/{accountName}/{balance}")
	public Account createAccount(@PathVariable String username, @PathVariable String accountName, @PathVariable Double balance ) throws InvalidException {
		return service.createAccount(username, accountName, balance);
	}
	
	@GetMapping("/account/get/{username}")
	public List<Account> getAccounts(@PathVariable String username) throws InvalidException {
		return service.getAccounts(username);
	}
	
	@PostMapping("/trans/create/{amount}/{desc}/{username}/{accountName}")
	public Trans createTrans(@PathVariable Double amount , @PathVariable String desc, @PathVariable String username, @PathVariable String accountName) throws InvalidException {
		return service.makeTransaction(amount, desc, username, accountName);
	}
	
	@PostMapping("/trans/transfer/{amount}/{desc}/{sender}/{senderAccount}/{reciver}/{reciverAccount}")
	public List<Trans> transfer(@PathVariable Double amount , @PathVariable String desc, @PathVariable String sender, @PathVariable String senderAccount, @PathVariable String reciver, @PathVariable String reciverAccount) throws InvalidException {
		return service.transfer(amount, desc, sender, senderAccount, reciver, reciverAccount);
	}
	
	@GetMapping("/trans/get/{username}")
	public List<Trans> getTrans(@PathVariable String username) throws InvalidException {
		return service.getTransactions(username);
	}
	
	
}
