package com.furnitureApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.furnitureApp.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    
}
