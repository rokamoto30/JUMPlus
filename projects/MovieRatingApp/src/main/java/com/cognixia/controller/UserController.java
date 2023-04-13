package com.cognixia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.exception.InvalidException;
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
	
	@PostMapping("/user/create/{username}/{password}/{email}")
	public ResponseEntity<User> createUser(@PathVariable String username, @PathVariable String password, @PathVariable String email) throws InvalidException {
		User created = service.createUser(username, password, email);
		return ResponseEntity.status(201).body(created);
	}
	
	@PutMapping("/user/update/{curuser}/{username}/{password}/{email}")
	public ResponseEntity<User> updateUser(@PathVariable String curuser, @PathVariable String username, @PathVariable String password, @PathVariable String email) throws InvalidException {
		User updated = service.updateUser(curuser, username, password, email);
		return ResponseEntity.status(201).body(updated);
	}
}
