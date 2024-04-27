package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.Product;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
@MultipartConfig

public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Product> productList = dbController.getAllProducts();

		request.setAttribute("productList", productList);
		request.getRequestDispatcher("/pages/productlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve form data
		int productId = Integer.parseInt(request.getParameter("productId"));
		String productName = request.getParameter("product_name");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		Part imagePart = request.getPart("image");
		
		Product productModel = new Product(productId, productName, description, category, price, stock, imagePart);

        // Call addProduct method of DatabaseController to add the product to the database
        int result = dbController.addProduct(productModel);
        
		if (result == 1) {
			// Product added successfully, redirect to doGet to refresh the product list
			response.sendRedirect(request.getContextPath() + "/AddProduct");
		} else {
			// Handle error (e.g., display an error message)
			// You can forward to an error page or display a message on the same page
			// For example:
			request.setAttribute("errorMessage", "Failed to add product.");
			request.getRequestDispatcher("/AddProduct").forward(request, response);
		}
	}
}
