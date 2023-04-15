package com.cognixia.MovieRatingApiCaller.service;

import com.cognixia.MovieRatingApiCaller.model.Movie;
import com.cognixia.MovieRatingApiCaller.model.Rating;
import com.cognixia.MovieRatingApiCaller.network.Request;

public class RatingService {
	
	public static Rating[] getRatings(String curUser) throws Exception {
		String endpoint = String.format("/rating/%s", curUser);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Rating[].class);
	}
}
