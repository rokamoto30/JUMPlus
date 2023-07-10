package com.furnitureApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furnitureApp.dto.loginDto;
import com.furnitureApp.exception.InvalidException;
import com.furnitureApp.model.User;
import com.furnitureApp.service.AuthService;

import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
@CrossOrigin
@Validated
public class UserController {
    @Autowired
    AuthService service;

    @PostMapping("/register")
    public String register(@Valid @RequestBody User user) throws InvalidException {
    	
		return service.register(user);
//    	return service.createProduct(newProd);
	}
    
    @PostMapping("/authenticate")
    public String editProduct(@Valid @RequestBody loginDto creds) throws InvalidException {
		return service.auth(creds);
//    	return service.createProduct(newProd);
	}

}
