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
 * Servlet implementation class UserProfileServelet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserProfileServlet" })
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseController dbController;


	public UserProfileServlet() {
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
		//ArrayList<UserModel> user = dbController.getAllUsersInfo();

		
		Cookie[] cookies =request.getCookies();
		String userName=null;
		if(cookies != null) {
			for(Cookie cookie:cookies) {
				if (cookie.getName().equals("user")) {
					userName= cookie.getValue();
					break;
				}
			}
		}

		
		try {
			
			ArrayList<UserModel> user = dbController.getSpecificUser(userName);
			request.setAttribute("user", user);
			request.getRequestDispatcher("pages/userprofile1.jsp").forward(request, response);
			
		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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