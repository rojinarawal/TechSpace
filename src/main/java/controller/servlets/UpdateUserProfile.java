package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.UserModel;

/**
 * Servlet to handle user profile updates.
 * This servlet supports asynchronous operations and is responsible for both retrieving user details for update and processing the updates.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProfile" })
public class UpdateUserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController;

	/**
	 * Default constructor.
	 */
	public UpdateUserProfile() {
		super();
	}

	/**
	 * Initializes the servlet and its dependencies.
	 * This method sets up the database controller needed for database operations.
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dbController = new DatabaseController();
	}

	/**
	 * Handles GET requests by retrieving the current user's profile details and forwarding them to a JSP for editing.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String userName = null;
		// Iterate through cookies to find the user's cookie
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					userName = cookie.getValue();
					break;
				}
			}
		}

		// Retrieve user details from the database
		try {
			ArrayList<UserModel> user = dbController.getSpecificUser(userName);
			System.out.println("User: " + user);
			// Set user details in request scope to be used in the view
			request.setAttribute("userDetail", user);
			request.getRequestDispatcher("/pages/userprofile.jsp").forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles POST requests to update user profile details in the database.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		System.out.println(lastName); // Debugging to check received data
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		String role = request.getParameter("role");

		// Create a new UserModel with updated data from request
		UserModel user = new UserModel();

		user.setUserID(userId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setEmail(email);
		user.setAddress(address);
		user.setPhoneNumber(phoneNumber);
		user.setPassword(password);
		user.setRole(role);

		// Update user profile in the database
		try {
			int res = dbController.updateUserProfile(user);

			// Redirect based on the outcome of the update
			if(res == 1) {
				response.sendRedirect("UserProfileServlet"); // Redirect to the profile page if update is successful
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}