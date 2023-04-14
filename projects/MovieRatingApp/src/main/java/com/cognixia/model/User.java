package com.cognixia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Pattern(regexp = "^.+@.+$")
    @Column(unique=true, nullable = false)
    private String email;
    
	@JsonIgnore
    @OneToMany(mappedBy = "rating", cascade = CascadeType.ALL)
    private List<Rating> ratings;
    
    public User() {}

	public User(Integer id, String username, String password, @Pattern(regexp = "^.+@.+$") String email,
			List<Rating> ratings) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.ratings = ratings;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", ratings=" + ratings + "]";
	}
    
    
}
