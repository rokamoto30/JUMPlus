package com.cognixia.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Rating;

@Repository
public interface RatingRepo extends JpaRepository <Rating,Integer>{
//	@Query(value="SELECT * FROM rating r LEFT JOIN movie m ON r.movie_id = m.id WHERE r.user_id=?1 AND r.movie_id = ?2", nativeQuery = true)
	@Query(value="SELECT r.id FROM rating r LEFT JOIN movie m ON r.movie_id = m.id WHERE r.user_id=?1 AND r.movie_id = ?2", nativeQuery = true)
	public Optional<Rating> getRating(int user_id, String movie_name);
}
