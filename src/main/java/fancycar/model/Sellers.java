package fancycar.model;

public class Sellers {
	protected int sellerId;
	protected String name;
	protected String zip;
	protected boolean hasFranchise;
	
	public Sellers(int sellerId, String name, String zip, boolean hasFranchise) {
		this.sellerId = sellerId;
		this.name = name;
		this.zip = zip;
		this.hasFranchise = hasFranchise;
	}
	
	public Sellers(int sellerId, String name) {
		this.sellerId = sellerId;
		this.name = name;
	}
	
	
	/**  setters and Getters  */
	public Sellers(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public boolean isHasFranchise() {
		return hasFranchise;
	}

	public void setHasFranchise(boolean hasFranchise) {
		this.hasFranchise = hasFranchise;
	}
	
	
}
