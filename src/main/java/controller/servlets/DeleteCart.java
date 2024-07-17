package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import util.StringUtils;

/**
 * Servlet implementation class DeleteCart
 * Handles deletion of items from a user's shopping cart.
 */
@WebServlet("/DeleteCart")
public class DeleteCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final DatabaseController dbController;

	/**
	 * Constructor to initialize the servlet and its database controller dependency.
	 */
	public DeleteCart() {
		this.dbController = new DatabaseController();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String delete_id_temp = request.getParameter("productID");
		int delete_id = Integer.parseInt(delete_id_temp);

		// Check if a product ID is provided for deletion
		if (delete_id != 0) {
			// Perform the delete operation via the doDelete method
			int cart_id = (int) session.getAttribute("cart_id");

			// Invoke the database controller to delete the item
			if (dbController.deleteCartItem(delete_id, cart_id) == 1) {
				// If deletion is successful, redirect to the cart viewing page
				response.sendRedirect(request.getContextPath() + "/ViewCartItems");
			} else {
				// If deletion fails, forward to an error handling page
				request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
			}
		}
	}
}
			
