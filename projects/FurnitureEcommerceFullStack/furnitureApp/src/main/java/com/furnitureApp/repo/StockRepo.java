package com.furnitureApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.furnitureApp.model.Stock;

@Repository
public interface StockRepo extends JpaRepository<Stock, Integer>  {
    
}
