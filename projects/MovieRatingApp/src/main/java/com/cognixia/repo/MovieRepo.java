package com.cognixia.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Movie;

@Repository
public interface MovieRepo extends JpaRepository <Movie,Integer>{
	@Query(value = "SELECT m.id, m.name, AVG(r.rating) as rating, COUNT(r.rating) as count FROM rating r LEFT JOIN movie m ON r.movie_id = m.id GROUP BY m.id", nativeQuery = true)
	public List<Movie> getMovies();
	
	@Query(value = "SELECT m.id, m.name, AVG(r.rating) as rating, COUNT(r.rating) as count FROM rating r LEFT JOIN movie m ON r.movie_id = m.id LEFT JOIN user u ON r.user_id = u.id WHERE u.username != 'guest' GROUP BY m.id", nativeQuery = true)
	public List<Movie> getMoviesNoGuest();
	
	@Query(value = "SELECT m.id, m.name FROM movie m WHERE m.name =?1", nativeQuery = true)
	public Optional<Movie> getMovie(String movie);
	
}
