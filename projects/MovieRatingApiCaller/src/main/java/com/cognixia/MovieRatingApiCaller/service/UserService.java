package com.cognixia.MovieRatingApiCaller.service;

import com.cognixia.MovieRatingApiCaller.model.User;
import com.cognixia.MovieRatingApiCaller.network.ApiException;
import com.cognixia.MovieRatingApiCaller.network.Request;


public class UserService {
	public static User login(String username, String password) throws Exception {
		String endpoint = String.format("/user/login/%s/%s", username, password);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, User.class);		
	}
	
	public static User create(String username, String password, String confirmPass, String email) throws Exception {
		if (!password.equals(confirmPass)) {
			throw new ApiException("password doesnt match");
		}
		String endpoint = String.format("/user/create/%s/%s/%s", username, password, email);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, User.class);		
	}
	
	public static User update(String curUser, String username, String password, String email) throws Exception {
		String endpoint = String.format("/user/update/%s/%s/%s/%s", curUser, username, password, email);
		String response = Request.send(endpoint, "PUT");
		return Request.parse(response, User.class);		
	}
}
