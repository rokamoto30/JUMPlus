package com.cognixia.MovieRatingApiCaller.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.cognixia.MovieRatingApiCaller.model.User;
import com.cognixia.MovieRatingApiCaller.network.ApiException;
import com.cognixia.MovieRatingApiCaller.network.Request;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserService {
	public static User login(String username, String password) throws JsonParseException, JsonMappingException, IOException {
		String endpoint = String.format("user/login/%s/%s", username, password);
		try {
			String response = Request.send(endpoint, "GET");
			return Request.parse(response, User.class);
		} catch (ApiException e){
			System.out.println(e.getMessage());
		}
		
		
		return null;
		
	}
}
