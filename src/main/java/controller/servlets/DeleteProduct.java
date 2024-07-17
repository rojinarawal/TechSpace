package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;

/**
 * Servlet implementation class DeleteProduct
 * This servlet handles the deletion of product entries from the database.
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final DatabaseController dbController;

	/**
	 * Default constructor. Initializes the database controller used to interact with the database.
	 */
	public DeleteProduct() {
		this.dbController = new DatabaseController();
	}

	/**
	 * Handles the POST request to initiate product deletion.
	 * This method checks if a deletion ID is provided and if so, it calls the doDelete method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve the product ID to delete from request parameters
		String deleteId = request.getParameter("deleteId");

		// Ensure the deletion ID is present and valid before proceeding
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}

	/**
	 * Custom deletion method to handle the deletion process.
	 * Performs the actual database deletion of the product based on the provided ID.
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Log to console when deletion is triggered (for debugging purposes)
		System.out.println("delete triggered");

		// Parse the product ID from the request
		int productId = Integer.parseInt(req.getParameter("deleteId"));
		
		// Call the database controller to delete the product by its ID
		if (dbController.deleteProduct(productId) == 1) {
			// Redirect back to the referring page if deletion is successful
			resp.sendRedirect(req.getHeader("referer"));
		} else {
			// Log an error and redirect back to the referring page if deletion fails
			System.out.print("error");
			resp.sendRedirect(req.getHeader("referer"));
		}
	}
}