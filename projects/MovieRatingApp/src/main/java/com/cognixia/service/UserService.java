package com.cognixia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.User;
import com.cognixia.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;
	
	public Boolean userExists(String username) {
		Optional<User> found = repo.getUser(username);
		return found.isPresent();
	}
	
	public User login(String username, String password) throws InvalidException {
		Optional<User> found = repo.getUser(username);
		if (found.isEmpty()) {
			throw new InvalidException("User doesnt exist");
		}
		User user = found.get();
		if (user.getPassword().equals(password)) {
			return user;
		}
		else {
			throw new InvalidException("Password Doesnt Match");
		}
	}
	
	public User createUser(String username, String password, String email) throws InvalidException {
		Optional<User> found = repo.getUser(username);
		if (found.isPresent()) {
			throw new InvalidException("User already exist"); 
		}
		User newUser = new User(null, username, password, email, null);
		User created = repo.save(newUser);
		return created;
	}
	
	public User updateUser(String curUser, String username, String password, String email) throws InvalidException {
		Optional<User> found = repo.getUser(curUser);
		if (found.isEmpty()) {
			throw new InvalidException("User doesnt exist");
		}
		User foundUser = found.get();
		foundUser.setUsername(username);
		foundUser.setPassword(password);
		foundUser.setEmail(email);
		
		User updated = repo.save(foundUser);
		return updated;
	}
	
}
