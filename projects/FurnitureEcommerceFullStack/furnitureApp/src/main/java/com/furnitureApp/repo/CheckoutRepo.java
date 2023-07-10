package com.furnitureApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.furnitureApp.model.Checkout;

@Repository
public interface CheckoutRepo extends JpaRepository<Checkout, Integer>{
	@Query(value="SELECT * FROM checkout c WHERE c.user_id = ?1", nativeQuery=true)
	public List<Checkout> getCheckoutByUserId(Integer id);
}
