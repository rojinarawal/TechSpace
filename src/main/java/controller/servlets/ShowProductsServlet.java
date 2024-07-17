package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.Product;

/**
 * Servlet implementation class ShowProductsServlet
 * This servlet handles retrieving all products from the database and displaying them on a web page.
 */
@WebServlet("/ShowProductsServlet")
@MultipartConfig
public class ShowProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseController dbController = new DatabaseController();

	/**
	 * Default constructor.
	 */
	public ShowProductsServlet() {
		super();
	}

	/**
	 * Handles the GET request by retrieving all products from the database.
	 * It sets these products as an attribute in the request object and forwards to the product display page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve a list of all products from the database using the DatabaseController
		ArrayList<Product> productList = dbController.getAllProducts();

		// Set the retrieved product list as a request attribute for access in the JSP
		request.setAttribute("productList", productList);

		// Forward the request to the JSP page that will display the product list
		request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
	}

	/**
	 * Handles the POST request but it is not implemented in this servlet.
	 * Typically, POST would handle creating or updating data.
	 */
}