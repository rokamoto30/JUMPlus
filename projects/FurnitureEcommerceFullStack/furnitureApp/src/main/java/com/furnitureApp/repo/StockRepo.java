package com.furnitureApp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.furnitureApp.model.Stock;

import jakarta.transaction.Transactional;

@Repository
public interface StockRepo extends JpaRepository<Stock, Integer>  {
	@Transactional
	@Modifying
	@Query(value = "UPDATE stock s SET s.price = ?1, s.quantity=?2 WHERE s.id = ?3", nativeQuery = true)
	public int updateStock(Double cost, Integer quantity, Integer id);
	
	@Query(value="SELECT * FROM stock s WHERE s.product = ?1", nativeQuery=true)
	public Optional<Stock> getStockByProductId(Integer id);
}
