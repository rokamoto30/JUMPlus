package com.furnitureApp.model;

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
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
    @NotBlank
	@Column(unique = true, nullable = false)
	private String username;
	
    @NotBlank
	@Column(nullable = false)
	private String password;

    @NotBlank
    @Column(nullable = false)
	private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Checkout> checkouts;

    public User() {}

    
}
