package com.cognixia.MovieRatingApiCaller.model;




public class Rating {
	private Integer id;
	
	private Double rating;
	
	private Boolean favorite;
	
	private User user;
	
	private Movie movie;
	
	
	public Rating() {}
	
	public Rating(Integer id, Double rating, Boolean favorite, User user, Movie movie) {
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
