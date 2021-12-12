package fancycar.model;

public class CarModels {
	protected int modelId;
	protected String brand;
	protected String model;
	protected FuelType fuelType;
	
	
	public enum FuelType {
		GASOLINE, DIESEL, ELECTRICAL, HYBRID, UNKNOWN
	}
	
	public CarModels(int modelId, String brand, String model, FuelType fuelType) {
		super();
		this.modelId = modelId;
		this.brand = brand;
		this.model = model;
		this.fuelType = fuelType;
	}
	
	
	public CarModels(int modelId) {
		this.modelId = modelId;
	}

	/**  setters and Getters  */

	public int getModelId() {
		return modelId;
	}


	public void setModelId(int modelId) {
		this.modelId = modelId;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public FuelType getFuelType() {
		return fuelType;
	}


	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public String toString() {
		return brand + " " + model;
	}
}
