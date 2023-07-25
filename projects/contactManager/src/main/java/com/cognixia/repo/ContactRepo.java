package com.cognixia.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer>{
	@Query(value="SELECT * FROM contact c WHERE c.user_id = ?1 ORDER BY ?2", nativeQuery=true)
	public List<Contact> sort(Integer userId, String sortMethod);
}
