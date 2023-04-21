package com.cognixia.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Assignment implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private Double grade;
	
	@Column(nullable=false)
	private Double weight;
	
	
	@ManyToOne
	@JoinColumn( name = "course_id", referencedColumnName = "id")
	private Course course;
	
	@ManyToOne
	@JoinColumn( name = "student_id", referencedColumnName = "id")
	private Student student;
	
	
	public Assignment() {}

	public Assignment(Integer id, Double grade, Double weight, Course course, Student student) {
		super();
		this.id = id;
		this.grade = grade;
		this.weight = weight;
		this.course = course;
		this.student = student;
	}

	@Override
	public String toString() {
		return "Assignment [id=" + id + ", grade=" + grade + ", weight=" + weight + ", course=" + course + ", student="
				+ student + "]";
	}
	
	
}
