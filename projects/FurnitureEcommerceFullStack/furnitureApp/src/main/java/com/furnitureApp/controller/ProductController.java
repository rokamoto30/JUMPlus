package com.furnitureApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furnitureApp.dto.newPrododuct;
import com.furnitureApp.exception.InvalidException;
import com.furnitureApp.model.Product;
import com.furnitureApp.model.Stock;
import com.furnitureApp.service.ProductService;

import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping("/product/create")
    public Product create(@Valid @RequestBody newPrododuct newProd) {
		return service.createProduct(newProd);
//    	return service.createProduct(newProd);
	}
    
    @PutMapping("/product/edit")
    public Product editProduct(@Valid @RequestBody Product product) throws InvalidException {
    	System.out.println("product: " + product);
		return service.editProduct(product);
//    	return service.createProduct(newProd);
	}
    
    @PutMapping("/stock/edit")
    public Stock editStock(@Valid @RequestBody Stock stock) throws InvalidException {
		return service.editStock(stock);
//    	return service.createProduct(newProd);
	}
    
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id) throws InvalidException {
		return service.getProduct(id);
	}
    
    @GetMapping("/stock/{id}")
    public Stock getStock(@PathVariable int id) throws InvalidException {
		return service.getStock(id);
	}
    
    @GetMapping("/product")
    public List<Product> getProducts() throws InvalidException {
		return service.getProducts();
	}


}
