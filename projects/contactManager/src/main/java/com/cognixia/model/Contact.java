package com.cognixia.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	@Column(nullable = false)
	public String name;
	
	@Column(nullable = false)
	public String number;
		
	@Column(nullable = false)
	public LocalDate created;
	
	@ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    public User user;
	
	public Contact() {}

	public Contact(Integer id, String name, String number, LocalDate created, User user) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.created = created;
		this.user = user;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	};

	
}
