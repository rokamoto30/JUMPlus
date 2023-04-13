package com.cognixia.MovieRatingApiCaller;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.cognixia.MovieRatingApiCaller.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/api/user/login/ryan/password"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		User user;
		try {
			ObjectMapper mapper = new ObjectMapper();
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			user = mapper.readValue(response.body(), User.class);	
			System.out.println(user.getEmail());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(response.body());
		
    }
}
