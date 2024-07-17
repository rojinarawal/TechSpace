package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.database.DatabaseController;
import model.UserModel;

/**
 * Servlet implementation class UserProfileServlet
 * Handles display of user profile information.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserProfileServlet" })
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseController dbController;

	/**
	 * Default constructor. Initializes the servlet.
	 */
	public UserProfileServlet() {
		super();
	}

	/**
	 * Initializes the servlet and its database controller.
	 */
	@Override
	public void init() throws ServletException {
		dbController = new DatabaseController();
		super.init();
	}

	/**
	 * Handles the GET request by retrieving user profile details based on the username stored in cookies.
	 * The profile information is then forwarded to a JSP page for display.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Append the servlet context path to the response for debugging purposes.
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// Retrieve cookies from the request to find the username cookie.
		Cookie[] cookies = request.getCookies();
		String userName = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					userName = cookie.getValue();
					break;
				}
			}
		}

		// Fetch specific user data from the database using the username.
		try {
			ArrayList<UserModel> user = dbController.getSpecificUser(userName);
			// Set the fetched user details in the request attribute to be accessed in the JSP.
			request.setAttribute("user", user);
			// Forward the request to the user profile page.
			request.getRequestDispatcher("/pages/userprofile1.jsp").forward(request, response);

		} catch (ClassNotFoundException e) {
			// Handle ClassNotFoundException, which might be thrown if the JDBC driver isn't found.
			e.printStackTrace();
		} catch (SQLException e) {
			// Handle SQLException for any SQL related errors.
			e.printStackTrace();
		}
	}

	/**
	 * Handles POST requests by simply redirecting them to the doGet method.
	 * This allows the servlet to handle both GET and POST requests similarly.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}