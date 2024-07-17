package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLogoutServlet
 * Handles the logout process for users by invalidating the session and clearing cookies.
 */
@WebServlet("/UserLogoutServlet")
public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. Constructs the base HttpServlet class.
	 */
	public UserLogoutServlet() {
		super();
	}

	/**
	 * Handles GET requests to effectively log out a user.
	 * This method invalidates any existing session and clears any cookies related to the user session.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the existing session without creating a new one
		HttpSession session = request.getSession(false);
		if (session != null) {
			// Invalidate the current session to clear any stored session data
			session.invalidate();
		}

		// Retrieve and clear all cookies
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0); // Set the expiration time of the cookie to zero to delete it
				response.addCookie(cookie); // Add the modified cookie back to the response to enforce deletion
			}
		}

		// Redirect the user to the login page after logging out
		response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
	}

	/**
	 * Handles POST requests by redirecting them to the doGet method.
	 * This is common in scenarios where both GET and POST requests in a servlet should handle the same logic.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}