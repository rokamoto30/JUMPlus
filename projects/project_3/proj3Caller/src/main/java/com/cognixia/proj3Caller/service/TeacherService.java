package com.cognixia.proj3Caller.service;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import com.cognixia.proj3Caller.model.Assignment;
import com.cognixia.proj3Caller.model.Course;
import com.cognixia.proj3Caller.model.User;
import com.cognixia.proj3Caller.network.ApiException;
import com.cognixia.proj3Caller.network.Request;

public class TeacherService {
	public static User login(String username, String password) throws Exception {
		String endpoint = String.format("/teacher/login/%s/%s", username, password);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, User.class);		
	}
	
	public static void getCourses(String username) throws Exception {
		String endpoint = String.format("/teacher/getCourses/%s", username);
		String response = Request.send(endpoint, "GET");
		Course[] courses = Request.parse(response, Course[].class);		
		for (Course cur : courses) {
			System.out.println(cur.getCourseName());
		}
	}
	
	public static User create(String username, String password, String firstName, String lastName) throws Exception {
		String endpoint = String.format("/teacher/create/%s/%s/%s/%s", username, password, firstName, lastName);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, User.class);		
	}
	
	public static Course createCourse(String username, String courseName) throws Exception {
		String endpoint = String.format("/teacher/createCourse/%s/%s", username, courseName);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, Course.class);		
	}
	
	public static Assignment createAssignment(String teacherName, String studentName, String courseName, Double grade, Double weight) throws Exception {
		String endpoint = String.format("/teacher/createAssignnment/%s/%s/%s/%s/%s", teacherName, studentName, courseName, grade, weight);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, Assignment.class);		
	}
	
	public static void getStudents (String username, String courseName) throws Exception {
		String endpoint = String.format("/teacher/getStudents/%s/%s", username, courseName);
		String response = Request.send(endpoint, "GET");
		Assignment[] found = Request.parse(response,  Assignment[].class);		
		
		System.out.println(courseName);
		//https://stackoverflow.com/questions/32565450/collectiondouble-to-doublestream
		Double avg = Arrays.stream(found).mapToDouble(a->a.getGrade()).average().orElse(0.0);
		Double med = Arrays.stream(found).map(a->a.getGrade()).sorted().skip(Math.floorDiv(found.length, 2)).findFirst().get();
		
		System.out.println("Avarage = " + String.format("%-5.5s", avg));
		System.out.println("Median = " + String.format("%-5.5s", med));
		for (Assignment cur : found) {
			String studentRecord = String.format("%-5.5s", cur.getGrade()) + " " + cur.getStudentName();
			System.out.println(studentRecord);
//			System.out.print(Math.floor(cur.getGrade() * 100) / 100);
//			System.out.println(" " + cur.getStudentName());
		}
	}
	public static void removeStudent(String studentName, String courseName) throws ApiException {
		String endpoint = String.format("/teacher/deleteStudent/%s/%s", studentName, courseName);
		String response = Request.send(endpoint, "DELETE");
	}
}
