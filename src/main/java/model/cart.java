package model;
import java.io.Serializable;


public class cart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int cart_id;
	private int product_id;
	private int quantity;


	public cart() {
		//default constructor
	}

	public cart(int cartid,int productid, int quantity) {
		this.cart_id=cart_id;
		this.product_id=product_id;
		this.quantity=quantity;
	}

	public int getCartId() {
		return cart_id;
	}

	public void setCartId(int cartid) {
		this.cart_id = cart_id;
	}


	public int getProductId() {
		return product_id;
	}

	public void setProductId(int productid) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity){
		this.quantity=quantity;
	}


}