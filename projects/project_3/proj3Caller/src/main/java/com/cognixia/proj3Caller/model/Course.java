package com.cognixia.proj3Caller.model;



public class Course {
	private Integer id;
	
	private String courseName;
	
	private Double avg;
	private Double median;
	
	
	public Course() {}


	public Course(Integer id, String courseName, Double avg, Double median) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.avg = avg;
		this.median = median;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public Double getAvg() {
		return avg;
	}


	public void setAvg(Double avg) {
		this.avg = avg;
	}


	public Double getMedian() {
		return median;
	}


	public void setMedian(Double median) {
		this.median = median;
	}

	
}
