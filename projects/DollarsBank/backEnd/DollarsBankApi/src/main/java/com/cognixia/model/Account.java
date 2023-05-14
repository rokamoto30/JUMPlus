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
public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id")
	private User userId;
	
	@JsonIgnore
	@OneToMany(mappedBy = "accountId", cascade=CascadeType.ALL)
	private List<Trans> trans;
	
	@Column()
	private String name;
	
	@Column()
	private Double balance;
	
	public Account() {}

	public Account(Integer id, User userId, List<Trans> trans, String name, Double balance) {
		super();
		this.id = id;
		this.userId = userId;
		this.trans = trans;
		this.name = name;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public List<Trans> getTrans() {
		return trans;
	}

	public void setTrans(List<Trans> trans) {
		this.trans = trans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
