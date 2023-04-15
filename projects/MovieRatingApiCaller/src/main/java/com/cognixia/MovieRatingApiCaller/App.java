package com.cognixia.MovieRatingApiCaller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
	private static Scanner sc;
	private static String user;
	private static String cookiePath = "./src/main/java/com/cognixia/MovieRatingApiCaller/cookie.txt";
	
    public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException
    {
    	production();
    }
    
    private static void loadUser() {
    	try(BufferedReader reader = new BufferedReader(new FileReader(new File(cookiePath)))) {
			String savedUser = reader.readLine();
			if (savedUser != null) {
				user = savedUser;
			} else {
				user = "guest";
			}
			System.out.println("Welcome " + user + " to my movie rating app!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void logout() {
		try(BufferedWriter writer = new BufferedWriter( new FileWriter(new File(cookiePath), false))) {
			writer.write("");
			user=null;
			System.out.println("Logged out");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void login() {
    	boolean loop = true;
		while(loop) {
			try {
				System.out.println("Enter Username:");
				String username = sc.nextLine();
				
				System.out.println("Enter Password:");
				String password = sc.nextLine();
				
				User loggedIn = UserService.login(username, password);
				// if exception not thrown, then success
				user = loggedIn.getUsername();
				System.out.println("Logged in as " + user);
				
				//update cookies
				try(BufferedWriter writer = new BufferedWriter( new FileWriter(new File(cookiePath), false))) {
					writer.write(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
				loop = false;
        
			} catch (ApiException e) {
	    		System.out.println(e.getMessage());
				System.out.println("Press q to quit or any other key to try again:");
				
				String ans = sc.nextLine();
				if (ans.toLowerCase().equals("q")) {
					System.out.println("Exiting");
					loop = false; 
				}
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
		}
	}
    
    public static void production() {
    	sc = new Scanner(System.in);
		
		loadUser();
				
    	boolean running = true;
		while (running) {
			System.out.print(user + "> ");
			String command = sc.nextLine(); 
			switch(command.toLowerCase()) {
				case "login":
					login();
					break;
				
				default:
					System.out.println("Please type a valid input/command. For help, type help");
					break;
			}
			
		}
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
