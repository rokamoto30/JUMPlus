package com.cognixia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.model.Movie;
import com.cognixia.service.MovieService;

@RequestMapping("/api")
@RestController
public class MovieController {
	
	@Autowired
	MovieService service;
	
	@GetMapping("/movie")
	public List<Movie> getMovies() {
		return service.getMovies();
	}
	@GetMapping("/movie/valid")
	public List<Movie> getMoviesValid() {
		return service.getMoviesNoGuest();
	}
}
