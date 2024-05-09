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

@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProfile" })
public class UpdateUserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController;

	public UpdateUserProfile() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dbController = new DatabaseController();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		try {
			ArrayList<UserModel> user = dbController.getSpecificUser(userName);
			System.out.println("User: "+user);
			request.setAttribute("userDetail", user);
			request.getRequestDispatcher("/pages/userprofile.jsp").forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		System.out.println(lastName);
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		String role = request.getParameter("role");

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

		try {
			int res = dbController.updateUserProfile(user);
			
			if(res == 1) {
				response.sendRedirect("UserProfileServlet");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
