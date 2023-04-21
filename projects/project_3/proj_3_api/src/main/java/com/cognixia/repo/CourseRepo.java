package com.cognixia.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognixia.model.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {
	@Query("SELECT c.* FROM course c LEFT JOIN teacher t ON c.teacher_id = t.id WHERE t.username = ?1")
	public List<Course> getCourses(String username);
	
	@Query("SELECT c.* FROM course c LEFT JOIN teacher t ON c.teacher_id = t.id WHERE t.username = ?1 AND c.name = ?2")
	public Optional<Course> getCourse(String username, String courseName);
	
//	@Query("SELECT c.id, c.name, AVGW(a.grade, a.weight) AS c.avg FROM assignment a LEFT JOIN course c ON a.course_id = c.id WHERE a.student_id = ?1 GROUP BY c.id")
//	public List<Course> getCoursesStudent(Integer student_id);
//	
//	@Query("SELECT c.id, c.name, AVGW(a.grade, a.weight) AS c.avg FROM assignment a LEFT JOIN course c ON a.course_id = c.id WHERE c.teacher_id = ?1 GROUP BY c.id")
//	public List<Course> getCoursesTeacher(Integer teacher_id);
//	
}
