package com.cognixia.proj3Caller.model;

public class User {
	private Integer id;
	
	private String firstName;
	private String lastName;
	private Boolean isTeacher;
	
	private String username;
	
	private String password;
	
	
	public User() {}


	public User(Integer id, String firstName, String lastName, Boolean isTeacher, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isTeacher = isTeacher;
		this.username = username;
		this.password = password;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Boolean getIsTeacher() {
		return isTeacher;
	}


	public void setIsTeacher(Boolean isTeacher) {
		this.isTeacher = isTeacher;
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

	
	
	
	
	
}
