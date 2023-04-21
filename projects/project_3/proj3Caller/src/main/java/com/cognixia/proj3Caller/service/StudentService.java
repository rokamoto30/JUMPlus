package com.cognixia.proj3Caller.service;

import com.cognixia.proj3Caller.model.Assignment;
import com.cognixia.proj3Caller.model.Course;
import com.cognixia.proj3Caller.model.User;
import com.cognixia.proj3Caller.network.Request;

public class StudentService {
	public static User login(String username, String password) throws Exception {
		String endpoint = String.format("/student/login/%s/%s", username, password);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, User.class);		
	}
	
	public static void getCourses(String username) throws Exception {
		String endpoint = String.format("/student/getCourses/%s", username);
		String response = Request.send(endpoint, "GET");
		Course[] courses = Request.parse(response, Course[].class);		
		for (Course cur : courses) {
			System.out.println(cur.getCourseName());
		}
	}
	
	public static User create(String username, String password, String firstName, String lastName) throws Exception {
		String endpoint = String.format("/student/create/%s/%s/%s/%s", username, password, firstName, lastName);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, User.class);		
	}
	
	public static void getAssignments(String username, String courseName) throws Exception {
		String endpoint = String.format("/student/getAssignments/%s/%s", username, courseName);
		String response = Request.send(endpoint, "GET");
		Assignment[] found = Request.parse(response,  Assignment[].class);		
		
		System.out.println(courseName);
		for (Assignment cur : found) {
			System.out.println(cur.getGrade());
		}
	}
	
	
	
//	public static StringBuilder courseFormat(Rating[] ratings) {
//		int maxMovieLen = "Movie Name".length();
//		int maxRatingLen = "Rating".length();
//		int maxFavoriteLen = "Favorited".length();
//		StringBuilder table = new StringBuilder();
//		for (Rating rating : ratings) {
//			if (rating.getMovie_name() != null && rating.getMovie_name().length() + 1 > maxMovieLen) {
//				maxMovieLen = rating.getMovie_name().length() + 1 ;
//			}
//			if (rating.getRating() != null && rating.getRating().toString().length() + 1 > maxRatingLen) {
//				maxRatingLen = rating.getRating().toString().length() + 1;
//			}
//			if (rating.getFavorite() != null && rating.getFavorite().toString().length() + 1 > maxFavoriteLen) {
//				maxFavoriteLen = rating.getFavorite().toString().length() + 1;
//			}
//		}
//		String header = String.format("| %-" + maxMovieLen +"s | %-" + maxRatingLen +"s | %-" + maxFavoriteLen + "s|\n", "Movie Name", "Rating", "Favorited");
//		String breaker = "=".repeat(header.length()) + "\n";
//		table.append(breaker);
//		table.append(header);
//		table.append(breaker);
//		for (Rating rating : ratings) {
//			table.append(String.format("| %-" + maxMovieLen +"s | %-" + maxRatingLen +"s | %-" + maxFavoriteLen + "s|\n", rating.getMovie_name(), rating.getRating().toString(), rating.getFavorite().toString()));
//		}
//		table.append(breaker);
//		return table;
//	}
}
