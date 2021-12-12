package fancycar.model;

public class CarListings {
	protected String vin;
	protected CarModels carmodel;
	protected Sellers sellers;
	protected String pictureUrl;
	protected int year;
	protected String city;
	protected String exteriorColor;
	protected String interiorColor;
	protected int mileage;
	protected boolean hasAccident = false;
	protected boolean isCPO = false;
	protected int Price  = -1;
	
	public CarListings(String vin, CarModels carmodel, Sellers sellers, String pictureUrl, int year, String city,
			String exteriorColor, String interiorColor, int mileage, boolean hasAccident, boolean isCPO, int price) {
		this.vin = vin;
		this.carmodel = carmodel;
		this.sellers = sellers;
		this.pictureUrl = pictureUrl;
		this.year = year;
		this.city = city;
		this.exteriorColor = exteriorColor;
		this.interiorColor = interiorColor;
		this.mileage = mileage;
		this.hasAccident = hasAccident;
		this.isCPO = isCPO;
		Price = price;
	}

	public CarListings(String vin) {
		this.vin = vin;
	}

	/** Getters and setters */
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public CarModels getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(CarModels carmodel) {
		this.carmodel = carmodel;
	}

	public Sellers getSellers() {
		return sellers;
	}

	public void setSellers(Sellers sellers) {
		this.sellers = sellers;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getExteriorColor() {
		return exteriorColor;
	}

	public void setExteriorColor(String exteriorColor) {
		this.exteriorColor = exteriorColor;
	}

	public String getInteriorColor() {
		return interiorColor;
	}

	public void setInteriorColor(String interiorColor) {
		this.interiorColor = interiorColor;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public boolean isHasAccident() {
		return hasAccident;
	}

	public void setHasAccident(boolean hasAccident) {
		this.hasAccident = hasAccident;
	}

	public boolean isCPO() {
		return isCPO;
	}

	public void setCPO(boolean isCPO) {
		this.isCPO = isCPO;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	@Override
	public String toString() {
		return "CarListings{" +
			"vin='" + vin + '\'' +
			", carmodel=" + carmodel +
			", sellers=" + sellers +
			", pictureUrl='" + pictureUrl + '\'' +
			", year=" + year +
			", city='" + city + '\'' +
			", exteriorColor='" + exteriorColor + '\'' +
			", interiorColor='" + interiorColor + '\'' +
			", mileage=" + mileage +
			", hasAccident=" + hasAccident +
			", isCPO=" + isCPO +
			", Price=" + Price +
			'}';
	}
}
