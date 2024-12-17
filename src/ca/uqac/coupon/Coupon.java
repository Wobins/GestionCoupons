package ca.uqac.coupon;

public class Coupon {
	private String id;
	private String password;
	private double amount = 5;
	
	public Coupon(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	//getters
	public double getAmount() {
		return amount;
	}
	public String getPassword() {
		return password;
	}
	public String getId() {
		return id;
	}
	
	// setters
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setId(String id) {
		this.id = id;
	}
}
