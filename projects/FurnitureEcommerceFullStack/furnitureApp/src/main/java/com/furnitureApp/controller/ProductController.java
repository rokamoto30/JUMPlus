package com.furnitureApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furnitureApp.dto.newPrododuct;
import com.furnitureApp.model.Product;
import com.furnitureApp.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
@CrossOrigin
@Validated
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping("/product/create")
	public Product create(@Valid @RequestBody newPrododuct newProd) {
		return service.createProduct(newProd);
	}


}
