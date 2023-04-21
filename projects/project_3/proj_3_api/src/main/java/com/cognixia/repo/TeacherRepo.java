package com.cognixia.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
	public Optional<Teacher> findByUsername(String username);
}
