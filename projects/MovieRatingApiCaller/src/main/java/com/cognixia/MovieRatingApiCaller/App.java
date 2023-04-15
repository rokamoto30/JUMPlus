package com.cognixia.MovieRatingApiCaller;

import java.io.IOException;

import com.cognixia.MovieRatingApiCaller.model.Movie;
import com.cognixia.MovieRatingApiCaller.model.Rating;
import com.cognixia.MovieRatingApiCaller.model.User;
import com.cognixia.MovieRatingApiCaller.network.ApiException;
import com.cognixia.MovieRatingApiCaller.service.MovieService;
import com.cognixia.MovieRatingApiCaller.service.RatingService;
import com.cognixia.MovieRatingApiCaller.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * TOGGLE SHOW CONSOLE ON STANDARD OUT TO SUPRESS CONSOLE SWITCHING
 */
public class App 
{
	static String curUser;
    public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException
    {
    	tester();
    }
    public static void tester() {
    	try{
    		User user;
    		user = UserService.login("ryan", "password");
    		System.out.println(user);
    		//user = UserService.create("rob", "password", "password" "rob@gmail.com");
    		System.out.println(user);
    		user = UserService.update("robby", "robby", "password", "rob@gmail.com");
    		System.out.println(user);
    		
    		Movie[] movies;
    		movies = MovieService.getMovies();
    		System.out.println(MovieService.tableFormat(movies));
    		System.out.println("valid movies");
    		movies = MovieService.getMoviesValid();
    		System.out.println(MovieService.tableFormat(movies));
    		
    		Rating[] ratings;
    		ratings = RatingService.getRatings("ryan");
    		System.out.println(ratings);
    		Rating rating;
    		rating = RatingService.update("ryan", "The_Last_of_Us", 4.0, true);
    		
    		
    		
    	} catch (ApiException e) {
    		System.out.println(e.getMessage());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}		
    }
}
