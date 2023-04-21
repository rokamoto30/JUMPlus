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
import com.cognixia.model.Course;
import com.cognixia.model.Teacher;
import com.cognixia.service.AssignmentService;
import com.cognixia.service.CourseService;
import com.cognixia.service.TeacherService;

@RequestMapping("/api")
@RestController
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private AssignmentService assignmentService;
	
	
	@GetMapping("/teacher/login/{username}/{password}")
	public Teacher login(@PathVariable String username, @PathVariable String password) throws InvalidException {
		return teacherService.login(username, password);
	}
	
	@GetMapping("/teacher/getCourses/{username}")
	public Teacher login(@PathVariable String username) throws InvalidException {
		return courseService.login(username);
	}
	
	@GetMapping("/teacher/getCourse/{username}/{courseName")
	public List<Assignment> getCourse(@PathVariable String username, @PathVariable String courseName) throws InvalidException {
		return assignmentService.getTeacherCourse(username, courseName);
	}
	
	@PostMapping("/teacher/create/{username}/{password}/{firstName}/{lastName}")
	public ResponseEntity<Teacher> createTeacher(@PathVariable String username, @PathVariable String password, @PathVariable String firstName, @PathVariable String lastName) throws InvalidException {
		Teacher created = teacherService.createUser(username, password, firstName, lastName);
		return ResponseEntity.status(201).body(created);
	}
	
	@PostMapping("/teacher/createCourse/{username}/{courseName}")
	public ResponseEntity<Course> createCourse(@PathVariable String username, @PathVariable String courseName) throws InvalidException {
		Course created = courseService.createCourse(username, courseName);
		return ResponseEntity.status(201).body(created);
	}
	
	@PostMapping("/teacher/createAssignnment/{teacherName}/{studentName}/{course}/{grade}/{weight}")
	public ResponseEntity<Assignment> createAssignnment(@PathVariable String teacherName, @PathVariable String studentName, @PathVariable String course, @PathVariable Double grade, @PathVariable Double weight) throws InvalidException {
		Assignment created = assignmentService.createAssignment(grade, weight, course, studentName, teacherName);
		return ResponseEntity.status(201).body(created);
	}

}
