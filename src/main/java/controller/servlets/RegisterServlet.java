package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.UserModel;
import util.StringUtils;
import util.ValidationUtil;

/**
 * Servlet implementation class RegisterServlet
 * Handles user registration requests, validating the input, and interacting with the database to create new user records.
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	/**
	 * Default constructor.
	 */
	public RegisterServlet() {
		super();
		// Constructor for the RegisterServlet
	}

	/**
	 * Processes GET requests to display the registration page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward the request to the signup page
		request.getRequestDispatcher("/pages/signup.jsp").forward(request, response);
	}

	/**
	 * Processes POST requests to handle the actual registration process.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter(StringUtils.FIRST_NAME);
		String lastName = request.getParameter(StringUtils.LAST_NAME);
		String userName = request.getParameter("userName");
		String email = request.getParameter(StringUtils.EMAIL);
		String address = request.getParameter(StringUtils.ADDRESS);
		String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
		String password = request.getParameter(StringUtils.PASSWORD);
		String userRole = request.getParameter(StringUtils.ROLE);

		// Assign default user role if none specified
		if (StringUtils.isNullOrEmpty(userRole)) {
			userRole = StringUtils.DEFAULT_ROLE;
		}

		// Create a new user model with the provided details
		UserModel userModel = new UserModel(firstName, lastName, userName, email, address, phoneNumber, password, userRole);

		// Validate input data
		if (!ValidationUtil.isTextOnly(firstName) ||
				!ValidationUtil.isTextOnly(lastName) ||
				!ValidationUtil.isTextOnly(userName) ||
				!ValidationUtil.isEmail(email) ||
				!ValidationUtil.isTextOnly(address) ||
				!ValidationUtil.isNumbersOnly(phoneNumber)) {
			// Set error message if validation fails
			request.setAttribute("errorMessage", StringUtils.MESSAGE_ERROR_INCORRECT_DATA);
			request.getRequestDispatcher("/pages/signup.jsp").forward(request, response);
		} else {
			// Attempt to register the user in the database
			int result = dbController.addUser(userModel);
			System.out.println("result=" + result); // Debugging statement to log the result

			// Handle different outcomes based on the registration attempt
			if (result == 1) {
				// Registration successful
				request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_REGISTER_MESSAGE);
				request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
			} else if (result == 0) {
				// Registration failed with error
				request.setAttribute("errorMessage", StringUtils.ERROR_MESSAGE);
				request.getRequestDispatcher("/pages/signup.jsp").forward(request, response);
			} else {
				// Server error occurred
				request.setAttribute("errorMessage", StringUtils.SERVER_ERROR_MESSAGE);
				request.getRequestDispatcher("/pages/signup.jsp").forward(request, response);
			}
		}
	}
}
