package com.furnitureApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furnitureApp.dto.checkoutDTO;
import com.furnitureApp.exception.InvalidException;
import com.furnitureApp.model.Checkout;
import com.furnitureApp.service.CheckoutService;

import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class CheckoutController {
	@Autowired
	CheckoutService service;
	
	@PostMapping("/checkout/create")
	public Checkout create(@Valid @RequestBody checkoutDTO checkoutDTO) throws InvalidException {
		return service.createCheckout(checkoutDTO);
	}
	
	@GetMapping("/checkout/{id}")
	public Checkout getCheckout(@PathVariable int id) throws InvalidException {
		return service.getCheckoutById(id);
	}
	
	@GetMapping("/checkout/user/{id}")
	public List<Checkout> getCheckoutByUser(@PathVariable int id) throws InvalidException {
		return service.getCheckoutByUserId(id);
	}
}
