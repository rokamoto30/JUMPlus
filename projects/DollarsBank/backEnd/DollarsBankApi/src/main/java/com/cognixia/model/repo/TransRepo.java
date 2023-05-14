package com.cognixia.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Trans;

@Repository
public interface TransRepo extends JpaRepository<Trans, Integer>{
	@Query(value="SELECT t.* FROM trans t WHERE t.user_id = ?1", nativeQuery = true)
	public List<Trans> getTransactions(Integer id);
	
	
}
