package com.cognixia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exeption.InvalidException;
import com.cognixia.model.Assignment;
import com.cognixia.model.Course;
import com.cognixia.repo.AssignmentRepo;

@Service
public class AssignmentService {
	@Autowired
	AssignmentRepo repo;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	StudentService studentService;
	
	public List<Assignment> getStudentAssignents(String username, String courseName) { // return assignment 
		return repo.getStudentCourse(username, courseName);
	}
	
	public List<Assignment> getStudentCourses(String username) { // return assignment 
		return repo.getStudentCourses(username);
	}
	
	public Optional<Assignment> getTeacherCourseStat(String teacherName, String courseName) throws InvalidException{
		Integer course_id = courseService.getCourse(teacherName, courseName).getId();
		return repo.getTeacherCourseStat(course_id);
	}
		
	public List<Assignment> getTeacherCourse(String teacherName, String courseName) throws InvalidException {
		Integer course_id = courseService.getCourse(teacherName, courseName).getId();
		return repo.getTeacherCourse(course_id);
	}
	
	public Assignment createAssignment(Double grade, Double weight, String course, String studentName, String teacherName) throws InvalidException {
		Assignment newAssignemnt = new Assignment(null, grade, weight, courseService.getCourse(teacherName, course), studentService.getStudent(studentName));
		Assignment added = repo.save(newAssignemnt);
		return added;
	}
		
}
