package fancycar.model;

import java.math.BigDecimal;

public class PickupTruck extends CarModels{
	protected String bedType;
	protected BigDecimal bedLength;
	
	public PickupTruck(int modelId, String brand, String model, FuelType fuelType, String bedType, BigDecimal bedLength) {
		super(modelId, brand, model, fuelType);
		this.bedType = bedType;
		this.bedLength = bedLength;
	}
	
	public PickupTruck(int modelId, String brand, String model, FuelType fuelType, String bedType) {
		super(modelId, brand, model, fuelType);
		this.bedType = bedType;
		this.bedLength = new BigDecimal(0);
	}
	
	public PickupTruck(int modelId, String brand, String model, FuelType fuelType, BigDecimal bedLength) {
		super(modelId, brand, model, fuelType);
		this.bedLength = bedLength;
	}
	
	public PickupTruck(int modelId) {
		super(modelId);
	}

	/** Getters and setters */
	
	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public BigDecimal getBedLength() {
		return bedLength;
	}

	public void setBedLength(BigDecimal bedLength) {
		this.bedLength = bedLength;
	}

	
	
	
}
