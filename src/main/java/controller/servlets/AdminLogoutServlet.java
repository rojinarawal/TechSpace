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
 * Servlet implementation class AdminLogoutServlet
 * This servlet handles the logout process for administrators by clearing cookies,
 * invalidating the session, and redirecting to the login page.
 */
@WebServlet("/AdminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. Calls the parent constructor.
	 */
	public AdminLogoutServlet() {
		super();
	}

	/**
	 * Handles GET requests for logging out administrators.
	 * This method clears all cookies associated with the current user, invalidates the current session,
	 * and redirects the user to the login page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve all cookies from the request
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0); // Expire the cookie immediately to delete it
				response.addCookie(cookie); // Update the cookie in the response to reflect the deletion
			}
		}

		// Get the current session, but do not create a new one if it doesn't exist
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // Invalidate the session to clear all session data
		}

		// Redirect the user to the login page after logout
		response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
	}

	/**
	 * Handles POST requests by simply forwarding them to the doGet method.
	 * This is a common pattern when both GET and POST should be handled in the same way.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}