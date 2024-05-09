package model;

public class OrderDetails extends Product {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int detailsID;
	private int quantity;
	
	public OrderDetails(int detailsID, int quantity) {
		super();
		this.detailsID = detailsID;
		this.quantity =quantity;
	}
	
	public OrderDetails() {
		// TODO Auto-generated constructor stub
	}

	public int getDetailsID() {
		return detailsID;
	}

	public void setDetailsID(int detailsID) {
		this.detailsID = detailsID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
