package com.cognixia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exeption.InvalidException;
import com.cognixia.model.Student;
import com.cognixia.repo.StudentRepo;

@Service
public class StudentService {
	@Autowired
	private StudentRepo repo;
	
	public Student getStudent(String username) throws InvalidException {
		Optional<Student> found = repo.findByUsername(username);
		if (found.isEmpty()) {
			throw new InvalidException("User doesn't exist");
		} 
		return found.get();
	}
	
	public Student login(String username, String password) throws InvalidException {
		Optional<Student> found = repo.findByUsername(username);
		if (found.isEmpty()) {
			throw new InvalidException("User doesn't exist");
		}
		Student user = found.get();
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			throw new InvalidException("Password doesn't exist");
		}
	}
	
	public Student createUser(String username, String password, String firstname, String lastName) throws InvalidException {
		Optional<Student> found = repo.findByUsername(username);
		if (found.isPresent()) {
			throw new InvalidException("User already exist"); 
		}
		Student newUser = new Student(null, firstname, lastName, username, password, null);
		Student created = repo.save(newUser);
		return created;
	}
	
	public Student updateUser(String curUser, String username, String password, String firstname, String lastName) throws InvalidException {
		Optional<Student> found = repo.findByUsername(username);
		if (found.isEmpty()) {
			throw new InvalidException("User doesnt exist");
		}
		Student foundUser = found.get();
		foundUser.setUsername(username);
		foundUser.setPassword(password);
		foundUser.setFirstName(firstname);
		foundUser.setLastName(lastName);
		
		Student updated = repo.save(foundUser);
		return updated;
	}
}
