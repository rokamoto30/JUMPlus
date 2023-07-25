package com.cognixia.service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.model.Contact;
import com.cognixia.model.User;
import com.cognixia.repo.ContactRepo;
import com.cognixia.repo.UserRepo;
import com.expenseTracking.exception.InvalidException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


@Service
public class AppService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ContactRepo contactRepo;
	
	public void validator(User user) throws InvalidException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    Set<ConstraintViolation<User>> violations = validator.validate(user);
	    if (!violations.isEmpty()) {
	      throw new InvalidException("Email not valid");
	    }
	}
	
	public User createUser(User user) throws InvalidException {
		validator(user);
		Optional<User> found = userRepo.findByEmail(user.getEmail());
		if (found.isPresent()) {
			throw new InvalidException("user already exists");
		}
		user.setId(null);
		return userRepo.save(user);
	}
	
	public User login(User user) throws InvalidException {
		validator(user);
		Optional<User> found = userRepo.login(user.getEmail(), user.getPassword());
		if (found.isPresent()) {
			return found.get();
		}
		throw new InvalidException("Login failed");
	}
	
	public Contact createContact(Contact contact) {
		contact.setId(null);
		return contactRepo.save(contact);
	}
	public Contact updateContact(Contact contact) throws InvalidException {
		Optional<Contact> found = contactRepo.findById(contact.getId());
		if (found.isEmpty()) {
			throw new InvalidException("Contact not found");
		}
		return contactRepo.save(contact);
	}
	
	public void deleteContact(Integer id) throws InvalidException {
		Optional<Contact> found = contactRepo.findById(id);
		if (found.isEmpty()) {
			throw new InvalidException("Contact not found");
		}
		contactRepo.deleteById(id);
	}
	public List<Contact> sortId(User user) {
		return contactRepo.sort(user.getId(), "c.id");
	}
	public List<Contact> sortName(User user) {
		return contactRepo.sort(user.getId(), "c.name");
	}
	public List<Contact> sortCreated(User user) {
		return contactRepo.sort(user.getId(), "c.created");
	}
}
