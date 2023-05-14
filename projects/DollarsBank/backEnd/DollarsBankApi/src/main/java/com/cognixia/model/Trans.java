package com.cognixia.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Trans implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Double amount;
	
	@Column()
	private String description;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id")
	private User userId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn( name = "account_id", referencedColumnName = "id")
	private Account accountId;

	public Trans(){}

	public Trans(Integer id, Double amount, String desc, User userId, Account accountId) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = desc;
		this.userId = userId;
		this.accountId = accountId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}
	
	

}
