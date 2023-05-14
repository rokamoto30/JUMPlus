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
import jakarta.persistence.OneToMany;

@Entity
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userId", cascade=CascadeType.ALL)
	private List<Account> accounts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userId", cascade=CascadeType.ALL)
	private List<Trans> trans;
	
	public User() {}

	public User(Integer id, String username, String password, List<Account> accounts, List<Trans> trans) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.accounts = accounts;
		this.trans = trans;
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

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Trans> getTrans() {
		return trans;
	}

	public void setTrans(List<Trans> trans) {
		this.trans = trans;
	}

	
}
