package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.Product;

/**
 * Servlet implementation class SearchServlet
 * This servlet handles search functionality within the application.
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseController databasecontroller = new DatabaseController();

	/**
	 * Default constructor.
	 */
	public SearchServlet() {
		super();
	}

	/**
	 * Handles GET requests by performing a product search based on user input.
	 * The search results are forwarded to a JSP page for display.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve the search term from the request parameter
		String search_bar = request.getParameter("query");
		// Prepare the search term for a SQL LIKE query
		String search = "%" + search_bar + "%" ;

		// Log the formatted search term to the console (used for debugging purposes)
		System.out.println(search);
		// Call the DatabaseController to retrieve a list of products matching the search criteria
		List<Product> productsList = databasecontroller.getproductInfobySearch(search);
		// Set the search results as an attribute on the request object for retrieval in the JSP
		request.setAttribute("productList", productsList);
		// Forward the request to the product.jsp page to display the search results
		request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
	}
}