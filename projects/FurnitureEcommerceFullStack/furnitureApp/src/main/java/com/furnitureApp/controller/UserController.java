package com.furnitureApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furnitureApp.exception.InvalidException;
import com.furnitureApp.service.UserService;

@RequestMapping("/api")
@RestController
@CrossOrigin
@Validated
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/hello")
	public String hello() {
		return "Hello";
	}

}
