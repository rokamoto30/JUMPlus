package com.cognixia.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognixia.model.Assignment;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
	@Query("SELECT a.* FROM assignment a LEFT JOIN student s ON a.student_id = s.id LEFT JOIN course c ON a.course_id = c.id WHERE s.username = ?1 AND c.name=?2")
	public List<Assignment> getStudentCourse(String studentName, String courseName);
	
	@Query("SELECT a.id, AVGW(a.grade, a.weight) AS a.grade, a.course_id, a.student_id FROM assignment a LEFT JOIN student s ON a.student_id = s.id LEFT JOIN course c ON a.course_id = c.id WHERE s.username=?1 GROUP BY (s.username, c.name)")
	public List<Assignment> getStudentCourses(String studentName);

	
	@Query("SELECT a.id, AVGW(a.grade, a.weight) AS a.grade, a.course_id, a.student_id FROM assignment a WHERE a.course_id = ?1 GROUP BY a.course_id")
	public Optional<Assignment> getTeacherCourseStat(Integer course_id);
	
	@Query("SELECT a.id, AVGW(a.grade, a.weight) AS a.grade, a.course_id, a.student_id FROM assignment a WHERE a.course_id = ?1 GROUP BY (a.course_id, a.student_id)")
	public List<Assignment> getTeacherCourse(Integer course_id);
}
