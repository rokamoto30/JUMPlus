package com.cognixia.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.RatingDTO;

@Repository
public interface RatingDtoRepo extends JpaRepository <RatingDTO,Integer> {
	@Query(value="SELECT r.* FROM rating r LEFT JOIN user u ON r.user_id = u.id WHERE u.username = ?1", nativeQuery = true)
	public List<RatingDTO> getUserRatings(String username);

}
