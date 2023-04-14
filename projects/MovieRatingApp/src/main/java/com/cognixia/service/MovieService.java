package com.cognixia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.Movie;
import com.cognixia.repo.MovieRepo;

@Service
public class MovieService {
	@Autowired
	private MovieRepo repo; 
	
	public Movie getMovie(String movie) throws InvalidException {
		Optional<Movie> found = repo.getMovie(movie);
		if (found.isPresent()) {
			return found.get();
		}
		throw new InvalidException("Movie not found");
	}
	
	public List<Movie> getMovies() {
		return repo.getMovies();
	}
	
	public List<Movie> getMoviesNoGuest() {
		return repo.getMoviesNoGuest();
	}
}
