package com.cognixia.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognixia.model.Student;


public interface StudentRepo extends JpaRepository<Student, Integer> {
	public Optional<Student> findByUsername(String username);
}
