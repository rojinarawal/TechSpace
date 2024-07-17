package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DatabaseController;
import model.Product;
import util.StringUtils;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,    // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	/**
	 * Default constructor.
	 */
	public UpdateProduct() {
		super();
	}

	/**
	 * Handles the GET request by retrieving a product based on its ID and forwarding
	 * to an edit page with the product's data pre-filled.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int productID = Integer.parseInt(request.getParameter("productID"));
			Product product = dbController.selectProduct(productID);
			if (product != null) {
				request.setAttribute("product", product);
				request.getRequestDispatcher("/pages/editproducts.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Product not found");
				request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Invalid product ID");
			request.getRequestDispatcher("/pages/productlist.jsp").forward(request, response);
		}
	}

	/**
	 * Handles the POST request to update the product details in the database.
	 * Retrieves form data including product details and image, constructs a Product object,
	 * and calls the database controller to update the product.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int productID = Integer.parseInt(request.getParameter("productID"));
			String productName = request.getParameter("productname");
			String category = request.getParameter("category");
			int price = Integer.parseInt(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			String description = request.getParameter("description");
			Part imagePart = request.getPart("image"); // Retrieve the image part of the form data

			Product product = new Product(productID, productName, category, price, stock, description, imagePart);

			int result = dbController.updateProduct(product);
			if (result > 0) {
				request.setAttribute("registerMessage", "Product is successfully updated");
				response.sendRedirect(request.getContextPath() + "/AddProduct");
			} else {
				request.setAttribute("registerMessage", "No changes made");
				request.getRequestDispatcher("/pages/productlist.jsp").forward(request, response);
			}
		} catch (NumberFormatException | IOException | ServletException e) {
			request.setAttribute("error", "Error processing request");
			request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
}