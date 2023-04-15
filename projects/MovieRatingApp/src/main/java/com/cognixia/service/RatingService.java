package com.cognixia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.Rating;
import com.cognixia.model.RatingDTO;
import com.cognixia.repo.RatingRepo;


@Service
public class RatingService {
	@Autowired
	private RatingRepo repo;
	@Autowired
	private UserService userService;
	@Autowired
	private MovieService movieService;
	
	public Rating getRating(String username, String movie) throws InvalidException {
		Optional<Rating> found = repo.getRating(username, movie);
		if (found.isPresent()) {
			return found.get();
		}
		throw new InvalidException("Rating doesnt exist"); 
	}
	
	public Boolean ratingExists(String username, String movie) {
		Optional<Rating> found = repo.getRating(username, movie);
		return found.isPresent();
	}
	
	public List<Rating> getUserRatings(String username) {
		return repo.getUserRatings(username);
	}
	
	public Rating createRating(Double rating, String username, String movie) throws InvalidException {
		if (username != "guest" && ratingExists(username, movie)) {
			throw new InvalidException("Rating already exist"); 
		}
		Rating newRating = new Rating(null, rating, false, userService.getUser(username), movieService.getMovie(movie) );
		Rating created = repo.save(newRating);
		return created;
	}
	
	public Rating updateRating(Double rating, Boolean favorite, String username, String movie) throws InvalidException {
		Optional<Rating> found = repo.getRating(username, movie);
		if (found.isEmpty()) {
			throw new InvalidException("Rating doesnt exist");
		}
		Rating foundRating = found.get();
		foundRating.setRating(rating);
		foundRating.setFavorite(favorite);
		foundRating.setMovie(movieService.getMovie(movie));
		foundRating.setUser(userService.getUser(username));
		Rating updated = repo.save(foundRating);
		return updated;
	}
}
