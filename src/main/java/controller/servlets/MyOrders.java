package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.Order;

import javax.servlet.http.Cookie;

/**
 * Servlet implementation class MyOrders
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/MyOrders" })
public class MyOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseController dbController;


	public MyOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		dbController = new DatabaseController();
		super.init();


	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Cookie[] cookies = request.getCookies();
		String userName = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user_name")) {
					userName = cookie.getValue();
					System.out.println(userName);
					break;
				}
            }
        }

        try {
            ArrayList<Order> userOrders = dbController.getUserOrders(userName);
            request.setAttribute("userOrders", userOrders);
            request.getRequestDispatcher("pages/userorders.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}