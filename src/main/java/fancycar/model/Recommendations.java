package fancycar.model;

import java.util.Date;

public class Recommendations {
	protected int recommendationId;
	protected Users user;
	protected CarListings car;
	protected Date created;
	
	public Recommendations(int recommendationId, Users user, CarListings car, Date created) {
		this.recommendationId = recommendationId;
		this.user = user;
		this.car = car;
		this.created = created;
	}
	
	public Recommendations(Users user, CarListings car, Date created) {
		this.user = user;
		this.car = car;
		this.created = created;
	}

	
	/** Getters and setters */
	
	public int getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public CarListings getCar() {
		return car;
	}

	public void setCar(CarListings car) {
		this.car = car;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
