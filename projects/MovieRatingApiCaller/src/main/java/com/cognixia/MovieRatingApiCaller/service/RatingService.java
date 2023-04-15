package com.cognixia.MovieRatingApiCaller.service;

import com.cognixia.MovieRatingApiCaller.model.Movie;
import com.cognixia.MovieRatingApiCaller.model.Rating;
import com.cognixia.MovieRatingApiCaller.model.User;
import com.cognixia.MovieRatingApiCaller.network.ApiException;
import com.cognixia.MovieRatingApiCaller.network.Request;

public class RatingService {
	
	public static Rating[] getRatings(String curUser) throws Exception {
		String endpoint = String.format("/rating/%s", curUser);
		String response = Request.send(endpoint, "GET");
		System.out.println(response);
		return Request.parse(response, Rating[].class);
	}
	
	public static Rating create(String username, String movie, Double rating) throws Exception {
		String endpoint = String.format("/rating/%s/%s/%s", username, movie, rating);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, Rating.class);		
	}
	
	public static Rating update(String curUser, String movie, Double rating, Boolean favorite) throws Exception {
		String endpoint = String.format("/rating/%s/%s/%s/%s", curUser, movie, rating, favorite);
		String response = Request.send(endpoint, "PUT");
		return Request.parse(response, Rating.class);		
	}
}
