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
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final DatabaseController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProduct() {
		this.dbController = new DatabaseController();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deleteId = request.getParameter("deleteId");

		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");
		int productId = Integer.parseInt(req.getParameter("deleteId")); // Convert String to int
		if (dbController.deleteProduct(productId) == 1) {
			 resp.sendRedirect(req.getHeader("referer"));
		} else {
			System.out.print("error");
			resp.sendRedirect(req.getHeader("referer")); // Redirect back to the previous page
		}
	}
}
