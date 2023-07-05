package com.furnitureApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.furnitureApp.model.Purchase;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Integer>{
    
}
