package com.cognixia.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "movie_id" }) })
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Min(value=0)
	@Max(value=5)
	@Column(nullable = false)
	private Double rating;
	
	private Boolean favorite;
	
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn( name = "movie_id", referencedColumnName = "id")
	private Movie movie;
	
	
	public Rating() {}
	
	public Rating(Integer id, @Min(0) @Max(5) Double rating, Boolean favorite, User user, Movie movie) {
		super();
		this.id = id;
		this.rating = rating;
		this.favorite = favorite;
		this.user = user;
		this.movie = movie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Boolean getFavorite() {
		return favorite;
	}

	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", rating=" + rating + ", favorite=" + favorite + ", user=" + user + ", movie="
				+ movie + "]";
	}
	
}
