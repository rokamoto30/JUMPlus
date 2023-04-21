package com.cognixia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.exeption.InvalidException;
import com.cognixia.model.Assignment;
import com.cognixia.model.Student;
import com.cognixia.service.AssignmentService;
import com.cognixia.service.StudentService;

@RequestMapping("/api")
@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	AssignmentService assignmentService;
	
	@GetMapping("/student/login/{username}/{password}")
	public Student login(@PathVariable String username, @PathVariable String password) throws InvalidException {
		return studentService.login(username, password);
	}
	
	@GetMapping("/student/getCourses/{username}")
	public List<Assignment> getCourses(@PathVariable String username) throws InvalidException {
		return assignmentService.getStudentCourses(username);
	}
	
	@GetMapping("/student/getCourse/{username}/{courseName")
	public List<Assignment> getCourses(@PathVariable String username, @PathVariable String courseName) throws InvalidException {
		return assignmentService.getStudentAssignents(username, courseName);
	}
	
	@PostMapping("/student/create/{username}/{password}/{firstName}/{lastName}")
	public ResponseEntity<Student> createStudent(@PathVariable String username, @PathVariable String password, @PathVariable String firstName, @PathVariable String lastName) throws InvalidException {
		Student created = studentService.createUser(username, password, firstName, lastName);
		return ResponseEntity.status(201).body(created);
	}
	
	
}
