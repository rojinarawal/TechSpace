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
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/pages/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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

	    // If userRole is null or empty, assign a default role of "User"
	    if (StringUtils.isNullOrEmpty(userRole)) {
	        userRole = StringUtils.DEFAULT_ROLE;
	    }

	    UserModel userModel = new UserModel(firstName, lastName, userName, email, address, phoneNumber, password, userRole);
	    // Implement data validation here (e.g., check for empty fields, email format,
	    // etc.)
	    if (!ValidationUtil.isTextOnly(firstName) ||
	            !ValidationUtil.isTextOnly(lastName) ||
	            !ValidationUtil.isTextOnly(userName) ||
	            !ValidationUtil.isEmail(email) ||
	            !ValidationUtil.isTextOnly(address) ||
	            !ValidationUtil.isNumbersOnly(phoneNumber)) {
	        request.setAttribute("errorMessage", StringUtils.MESSAGE_ERROR_INCORRECT_DATA);
	        request.getRequestDispatcher("/pages/signup.jsp").forward(request, response);
	    } else {
	        int result = dbController.addUser(userModel);
	        System.out.println("result=" + result);

	        if (result == 1) {
	            request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_REGISTER_MESSAGE);
	            request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
	        } else if (result == 0) {
	            request.setAttribute("errorMessage", StringUtils.ERROR_MESSAGE);
	            request.getRequestDispatcher("/pages/signup.jsp").forward(request, response);
	        } else {
	            request.setAttribute("errorMessage", StringUtils.SERVER_ERROR_MESSAGE);
	            request.getRequestDispatcher("/pages/signup.jsp").forward(request, response);
	        }
	    }
	}
}