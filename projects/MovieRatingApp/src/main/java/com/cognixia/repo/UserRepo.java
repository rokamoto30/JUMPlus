package com.cognixia.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.User;

import jakarta.transaction.Transactional;
@Repository
public interface UserRepo extends JpaRepository <User,Integer> {
	
	@Query(value="SELECT * from user u where u.username = ?1", nativeQuery = true)
	public Optional<User> getUser(String username);
}
