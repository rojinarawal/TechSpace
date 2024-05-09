package model;

import java.sql.Date;
import java.util.ArrayList;

public class Order extends UserModel {
	private int orderID;
	private java.sql.Date orderDate;
	private int total_amount;
	private String order_status;
	private ArrayList<OrderDetails> orderdetails;

	// Default constructor
	public Order() {
	}

	// Constructor with all fields
	public Order(int orderID, Date orderDate, int total_amount, String order_status) {
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.total_amount = total_amount;
		this.order_status = order_status;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public java.sql.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public ArrayList<OrderDetails> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(ArrayList<OrderDetails> orderdetails) {
		this.orderdetails = orderdetails;
	}
}




