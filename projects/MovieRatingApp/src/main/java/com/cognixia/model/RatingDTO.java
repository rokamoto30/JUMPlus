package com.cognixia.model;

import org.springframework.stereotype.Component;

@Component
public class RatingDTO {
private Integer id;
	
	private Double rating;
	
	private Boolean favorite;
	
	private Integer user;
	
	private Integer movie;
	
	
	public RatingDTO() {}

	public RatingDTO(Integer id, Double rating, Boolean favorite, Integer user, Integer movie) {
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

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getMovie() {
		return movie;
	}

	public void setMovie(Integer movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", rating=" + rating + ", favorite=" + favorite + ", user=" + user + ", movie="
				+ movie + "]";
	}
}
