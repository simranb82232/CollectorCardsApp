package application;

public class BasketballCard {
	// instance variables to set price, order selection, and id of the item ordered
	private double price;
	private String selection;
	private int id;
	
	// constructor
	public BasketballCard() {
	}
	
	// getters and setters for variables
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}