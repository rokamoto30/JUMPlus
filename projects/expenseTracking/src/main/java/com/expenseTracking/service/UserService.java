package com.expenseTracking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenseTracking.exception.InvalidException;
import com.expenseTracking.model.User;
import com.expenseTracking.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo repo;
	
	public User create(User user) throws InvalidException {
		Optional<User> found = repo.findByUsername(((User) user).getUsername());
        if(found.isPresent()) {
            throw new InvalidException("User already exists");
        }
        user.setId(null);;
        return repo.save(user);
	}
	
	public User login(String username, String password) throws InvalidException {
		Optional<User> found = repo.login(username, password);
		if (found.isEmpty()) {
			throw new InvalidException("Incorrect credentials");
		}
		return found.get();
	}
	
	public User update(User user) throws InvalidException {
		if (repo.findById(user.getId()).isPresent() ) {
			return repo.save(user);
		}
		throw new InvalidException("User doesn't exist");

	}
	
}
