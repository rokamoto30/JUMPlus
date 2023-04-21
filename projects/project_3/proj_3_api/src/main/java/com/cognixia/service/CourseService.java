package com.cognixia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exeption.InvalidException;
import com.cognixia.model.Course;
import com.cognixia.model.Student;
import com.cognixia.model.Teacher;
import com.cognixia.repo.CourseRepo;

@Service
public class CourseService {
	@Autowired
	private CourseRepo repo; 
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
//	public List<Course> getCoursesStudent(String studentName) {
//		Student student = studentService.getStudent(studentName);
//		return repo.getCoursesStudent(student.getId());
//	}
//	
//	public List<Course> getCoursesTeacher(String teacherName) {
//		Teacher teacher = teacherService.getTeacher(teacherName);
//		return repo.getCoursesTeacher(teacher.getId());
//	}
	
	public Course getCourse(String username, String courseName) throws InvalidException {
		Optional<Course> found = repo.getCourse(username, courseName);
		if (found.isEmpty()) {
			throw new InvalidException("Course doesnt exist");
		} else {
			return found.get();
		}
	}
	
	public boolean courseExists(String username, String courseName) {
		Optional<Course> found = repo.getCourse(username, courseName);
		return found.isPresent();
	}
	
	public Course createCourse(String username, String courseName) throws InvalidException {
		if (courseExists(username, courseName)) {
			throw new InvalidException("Course already exists");
		}
		Course newCourse = new Course(null, courseName, null, teacherService.getTeacher(username), null, null);
		Course added = repo.save(newCourse);
		return added;
		
	}
	
	public Course updateCourse(String curUser, String username, String courseName) throws InvalidException {
		Course found = getCourse(curUser, courseName);
		found.setName(courseName);	
		found.setTeacher(teacherService.getTeacher(username));
		Course updated = repo.save(found);
		return updated;
	}
	
}
