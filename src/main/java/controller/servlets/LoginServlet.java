package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.PasswordHashing;
import util.StringUtils;
import model.UserModel;

/**
 * Servlet to handle login requests and manage user sessions and cookies.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	/**
	 * Default constructor. Initializes the DatabaseController.
	 */
	public LoginServlet() {
		this.dbController = new DatabaseController();
	}

	/**
	 * Handles POST requests for user login.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("user_name");
		String password = request.getParameter("password");

		// Create a UserModel object to hold user credentials
		UserModel userModel = new UserModel(userName, password);

		// Authenticate user using database controller
		int loginResult = dbController.getUserLoginInfo(userModel);

		// Process the result of the login attempt
		if (loginResult == 1) {
			String userRole = dbController.getRole(userName);

			// Set up a new session and configure it
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(30 * 60); // 30 minutes

			// Retrieve user ID and cart ID from database
			int userID = dbController.getUserID(userName);
			int cart_id = dbController.getCartID(userID);

			// Save user details in session attributes
			if (userID > 0) {
				session.setAttribute("userID", userID);
			}
			session.setAttribute(StringUtils.USER_NAME, userName);
			session.setAttribute("role", userRole);
			session.setAttribute("cart_id", cart_id);

			// Create cookies for the user name and role
			Cookie userNameCookie = new Cookie(StringUtils.USER, userName);
			Cookie userRoleCookie = new Cookie("role", userRole);
			userNameCookie.setMaxAge(30 * 60); // 30 minutes
			userRoleCookie.setMaxAge(30 * 60);
			response.addCookie(userNameCookie);
			response.addCookie(userRoleCookie);

			// Redirect user based on the role
			if (userRole.equals("Admin")) {
				// Redirect admin to the admin dashboard
				response.sendRedirect(request.getContextPath() + "/pages/orderlist.jsp");

			}else if (userRole.equals("User")) {
				response.sendRedirect(request.getContextPath() + "/pages/home.jsp");

			} else {
				// Handle unknown user role
				request.setAttribute("errorMessage", "Invalid user role");
				request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
			}


		}else if (loginResult == 0) {
			// Handle incorrect login credentials
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.LOGIN_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		} else if (loginResult == -1) {
			// Handle non-existent username
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.MESSAGE_ERROR_CREATE_ACCOUNT);
			request.setAttribute(StringUtils.USER_NAME, userName);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		}else {
			// Handle server errors
			request.setAttribute("errorMessage",StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		}
	}

}
