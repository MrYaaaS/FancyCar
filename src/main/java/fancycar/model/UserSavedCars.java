package fancycar.model;

public class UserSavedCars {
	protected Users user;
	protected CarListings car;
	
	public UserSavedCars(Users user, CarListings car) {
		this.user = user;
		this.car = car;
	}

	
	/** Getters and setters */
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
	
}
