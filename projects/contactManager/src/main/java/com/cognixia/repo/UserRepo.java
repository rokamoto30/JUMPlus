package com.cognixia.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	@Query(value="SELECT * FROM user u WHERE u.email = ?1", nativeQuery=true)
	public Optional<User> findByEmail(String email);
	
	@Query(value="SELECT * FROM user u WHERE u.email = ?1 AND u.password = ?2", nativeQuery=true)
	public Optional<User> login(String email, String password);
}
