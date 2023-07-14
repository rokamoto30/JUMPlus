package com.expenseTracking.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Expense {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	@Column(nullable = false)
	public LocalDate date;
	
	@Column(nullable = false)
	public String description;
	
	@Column(nullable = false)
	public Double amount;
	
	@Column(nullable = false)
	public boolean recurring;
	
	@ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    public User user;
	
	public Expense() {
		
	}

	public Expense(LocalDate date, String description, Double amount, boolean recurring, User user) {
		super();
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.recurring = recurring;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", date=" + date + ", description=" + description + ", amount=" + amount
				+ ", recurring=" + recurring + ", user=" + user + "]";
	}
	
	
	
	
}
