package com.furnitureApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.furnitureApp.model.Checkout;

@Repository
public interface CheckoutRepo extends JpaRepository<Checkout, Integer>{
    
}
