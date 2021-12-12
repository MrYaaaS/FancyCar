package fancycar.model;

public class SuvOrCrossover extends CarModels {
	protected int numberOfSeats;
	
	public SuvOrCrossover(int modelId, String brand, String model, FuelType fuelType, int numberOfSeats) {
		super(modelId, brand, model, fuelType);
		this.numberOfSeats = numberOfSeats;
	}
	

	public SuvOrCrossover(int modelId, String brand, String model, FuelType fuelType) {
		super(modelId, brand, model, fuelType);
	}
	
	public SuvOrCrossover(int modelId) {
		super(modelId);
	}


	
	/** Getters and setters */

	public int getNumberOfSeats() {
		return numberOfSeats;
	}


	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	
	
}
