package com.cognixia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.Rating;
import com.cognixia.model.RatingDTO;
import com.cognixia.model.User;
import com.cognixia.repo.RatingDtoRepo;
import com.cognixia.service.RatingService;

@RequestMapping("/api")
@RestController
public class RatingController {
	@Autowired
	private RatingService service;
	
	@Autowired
	private RatingDtoRepo dtoRepo;
	
//	@GetMapping("/rating/{username}")
//	public List<Rating> getRatings(@PathVariable String username) {
//		return service.getUserRatings(username);
//	}
	
	@GetMapping("/rating/{username}")
	public List<RatingDTO> getRatings(@PathVariable String username) {
		return dtoRepo.getUserRatings(username);
	}
	
	@PostMapping("/rating/{username}/{movie}/{rating}")
	public ResponseEntity<Rating> createRating(@PathVariable String username, @PathVariable String movie, @PathVariable Double rating ) throws InvalidException {
		Rating created = service.createRating(rating, username, movie);
		return ResponseEntity.status(201).body(created);
		
	}
}
