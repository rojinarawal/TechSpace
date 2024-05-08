package controller.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.Order;

/**
 * Servlet implementation class OrderPlace
 */
@WebServlet("/OrderPlace")
public class OrderPlace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPlace() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve form data
        String Email = request.getParameter("email");
        int productId = Integer.parseInt(request.getParameter("productId"));
        Date date = java.sql.Date.valueOf(LocalDate.now());
        

        // Create an Order object with the retrieved data
        Order orderModel = new Order(Email, productId,  date);

        // Place the order
        DatabaseController dbController = new  DatabaseController();
        int result = dbController.placeOrder(StringUtils.ADD_TO_ORDER, orderModel);

        // Redirect to appropriate page based on the result
        if (result == 1) {
            request.setAttribute("successMessage", "Your product was ordered successfully!!");
        } else {
            request.setAttribute("errorMessage", "Failed to place order!!");
        }
        request.getRequestDispatcher("pages/addtocart.jsp").forward(request, response);
    }

}

