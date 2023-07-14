package com.expenseTracking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expenseTracking.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	@Query(value="SELECT * FROM user u WHERE u.username = ?1", nativeQuery=true)
	public Optional<User> findByUsername(String username);
	
	@Query(value="SELECT * FROM user u WHERE u.username = ?1 AND u.password = ?2", nativeQuery=true)
	public Optional<User> login(String username, String password);
}
