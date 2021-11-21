package fancycar.model;

import java.math.BigDecimal;

public class SedanOrCoupe extends CarModels{
	protected BigDecimal gasTank;
	
	public SedanOrCoupe(int modelId, String brand, String model, FuelType fuelType, BigDecimal gasTank) {
		super(modelId, brand, model, fuelType);
		this.gasTank = gasTank;
	}
	
	public SedanOrCoupe(int modelId) {
		super(modelId);
	}

	/** Getters and setters */
	
	public BigDecimal getGasTank() {
		return gasTank;
	}

	public void setGasTank(BigDecimal gasTank) {
		this.gasTank = gasTank;
	}

}
