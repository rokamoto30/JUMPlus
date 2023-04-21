package com.cognixia.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Assignment> students;
	
	@ManyToOne
	@JoinColumn( name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;
	
	private Double avg;
	private Double median;
	
	public Course() {}

	public Course(Integer id, String name, List<Assignment> students, Teacher teacher, Double avg, Double median) {
		super();
		this.id = id;
		this.name = name;
		this.students = students;
		this.teacher = teacher;
		this.avg = avg;
		this.median = median;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Assignment> getStudents() {
		return students;
	}

	public void setStudents(List<Assignment> students) {
		this.students = students;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", students=" + students + ", teacher=" + teacher + ", avg="
				+ avg + ", median=" + median + "]";
	}

	
	
}
