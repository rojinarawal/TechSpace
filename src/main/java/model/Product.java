package model;

import java.io.File;
import java.io.Serializable;

import javax.servlet.http.Part;

import util.StringUtils;

public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Product() {
		//default constructor
	}
	
	private int productID;
	private String productname; 
	private String category; 
	private int price; 
	private int stock;
	private String description; 
	private String imageUrl;
	
	public Product(int productID, String productname, String category, int price, int stock,  String description, Part imagePart) {
		this.productID =productID;
		this.productname = productname;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.imageUrl = getImageUrl(imagePart);
	}

	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int ProductID) {
		this.productID = ProductID;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImageUrlFromPart() {
		return imageUrl;
	}

	public void setImageUrlFromPart(String imageUrlFromPart) {
		this.imageUrl = imageUrlFromPart;
	}

	public void setImageUrlFromDB(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	private String getImageUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "download.jpg";
		}
		return imageUrlFromPart;
	}

}
