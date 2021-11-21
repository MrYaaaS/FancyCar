package fancycar.model;

import java.math.BigDecimal;
import java.util.Date;


public class Reviews {
	protected int reviewId;
	protected Date Created;
	protected String Content;
	protected BigDecimal Rating;
	protected Users User;
	protected CarListings car;
	
	public Reviews(int reviewId, Date created, String content, BigDecimal rating, Users user, CarListings car) {
		this.reviewId = reviewId;
		Created = created;
		Content = content;
		Rating = rating;
		User = user;
		this.car = car;
	}
	
	public Reviews(Date created, String content, BigDecimal rating, Users user, CarListings car) {
		Created = created;
		Content = content;
		Rating = rating;
		User = user;
		this.car = car;
	}
	
	public Reviews(Date created, BigDecimal rating, CarListings car) {
		Created = created;
		Rating = rating;
		this.car = car;
	}

	
	/** Getters and setters */

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public BigDecimal getRating() {
		return Rating;
	}

	public void setRating(BigDecimal rating) {
		Rating = rating;
	}

	public Users getUser() {
		return User;
	}

	public void setUser(Users user) {
		User = user;
	}

	public CarListings getCar() {
		return car;
	}

	public void setCar(CarListings car) {
		this.car = car;
	}
	
	
}
