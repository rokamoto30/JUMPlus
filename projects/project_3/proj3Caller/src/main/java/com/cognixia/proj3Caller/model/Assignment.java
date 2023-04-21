package com.cognixia.proj3Caller.model;

public class Assignment {
	
	
	private Integer id;
	
	private Double grade;
	
	private Double weight;
	
	private String studentName;
	
	public Assignment() {}
	
	public Assignment(Integer id, Double grade, Double weight, String studentName) {
		super();
		this.id = id;
		this.grade = grade;
		this.weight = weight;
		this.studentName = studentName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	
	

	
}
