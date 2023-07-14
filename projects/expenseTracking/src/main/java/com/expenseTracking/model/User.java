package com.expenseTracking.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(unique = true, nullable = false)
	private String username;
	
	@NotBlank
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = true)
	private Double monthBudget;
	
	@Column(nullable = true)
	private Double yearBudget;
	
	@JsonIgnore
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Expense> expenses;
	
	public User() {
		
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getMonthBudget() {
		return monthBudget;
	}

	public void setMonthBudget(Double monthBudget) {
		this.monthBudget = monthBudget;
	}

	public Double getYearBudget() {
		return yearBudget;
	}

	public void setYearBudget(Double yearBudget) {
		this.yearBudget = yearBudget;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "username";
	}
	
	
	
	
}
