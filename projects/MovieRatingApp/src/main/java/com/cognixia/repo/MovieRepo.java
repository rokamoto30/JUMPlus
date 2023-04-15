package com.cognixia.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Movie;

@Repository
public interface MovieRepo extends JpaRepository <Movie,Integer>{
	@Query(value = "SELECT m.id, m.name, AVG(r.rating) as rating, COUNT(r.rating) as count FROM movie m LEFT JOIN rating r ON m.id = r.movie_id GROUP BY m.id ORDER BY m.name", nativeQuery = true)
	public List<Movie> getMovies();
	
	@Query(value = "SELECT m.id, m.name, AVG(r.rating) as rating, COUNT(r.rating) as count FROM movie m LEFT JOIN rating r ON m.id = r.movie_id LEFT JOIN user u ON r.user_id = u.id WHERE u.username != 'guest' OR u.username IS NULL GROUP BY m.id ORDER BY m.name", nativeQuery = true)
	public List<Movie> getMoviesNoGuest();
	
	@Query(value = "SELECT m.* FROM movie m WHERE m.name =?1", nativeQuery = true)
	public Optional<Movie> getMovie(String movie);
	
}
