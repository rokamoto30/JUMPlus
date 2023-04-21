package com.cognixia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exeption.InvalidException;
import com.cognixia.model.Teacher;
import com.cognixia.repo.TeacherRepo;

@Service
public class TeacherService {
	@Autowired
	TeacherRepo repo;
	
	public Teacher getTeacher(String username) throws InvalidException {
		Optional<Teacher> found = repo.findByUsername(username);
		if (found.isEmpty()) {
			throw new InvalidException("User doesn't exist");
		} else {
			return found.get();
		}
		
	}
	
	public Teacher login(String username, String password) throws InvalidException {
		Optional<Teacher> found = repo.findByUsername(username);
		if (found.isEmpty()) {
			throw new InvalidException("User doesn't exist");
		}
		Teacher user = found.get();
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			throw new InvalidException("Password doesn't exist");
		}
	}
	
	public Teacher createUser(String username, String password, String firstname, String lastName) throws InvalidException {
		Optional<Teacher> found = repo.findByUsername(username);
		if (found.isPresent()) {
			throw new InvalidException("User already exist"); 
		}
		Teacher newUser = new Teacher(null, firstname, lastName, username, password, null);
		Teacher created = repo.save(newUser);
		return created;
	}
	
	public Teacher updateUser(String curUser, String username, String password, String firstname, String lastName) throws InvalidException {
		Optional<Teacher> found = repo.findByUsername(username);
		if (found.isEmpty()) {
			throw new InvalidException("User doesnt exist");
		}
		Teacher foundUser = found.get();
		foundUser.setUsername(username);
		foundUser.setPassword(password);
		foundUser.setFirstName(firstname);
		foundUser.setLastName(lastName);
		
		Teacher updated = repo.save(foundUser);
		return updated;
	}
}
