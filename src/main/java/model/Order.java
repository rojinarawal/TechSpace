package model;

import java.sql.Date;

public class Order {
	private int OrderID;
	private java.sql.Date date;
	private int quantity;
	private String Status;
	
	public Order(int orderID, Date date, String email, int productName, int quantity, int cost, int address,
			String status) {
		super();
		OrderID = orderID;
		this.date = date;
		this.quantity = quantity;
		Status = status;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

}
