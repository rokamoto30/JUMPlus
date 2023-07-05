package com.furnitureApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furnitureApp.dto.newPrododuct;
import com.furnitureApp.model.Product;
import com.furnitureApp.model.Stock;
import com.furnitureApp.repo.ProductRepo;
import com.furnitureApp.repo.StockRepo;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    @Autowired
    StockRepo stockRepo;

    @Transactional
    public Product createProduct(newPrododuct newProd){
	    Product created = repo.save(new Product(newProd.getName(), newProd.getImgUrl()));
        Stock newStock = new Stock(newProd.getQuantity(), newProd.getPrice(), created);
        stockRepo.save(newStock);
	    return created;
	}
    
}
