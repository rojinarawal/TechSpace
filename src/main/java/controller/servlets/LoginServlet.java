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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        this.dbController =new DatabaseController();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("user_name");
		String password = request.getParameter("password");
		
		// Create a LoginModel object to hold user credentials
        UserModel userModel = new UserModel(userName, password);
		
		int loginResult = dbController.getUserLoginInfo(userModel);
		
		if (loginResult == 1) {
			
			String userRole = dbController.getRole(userName);
			
            // Create a new session or get the existing session
            HttpSession session = request.getSession(true);
            // Set session timeout to 30 minutes (in seconds)
            session.setMaxInactiveInterval(30 * 60);
            
            // Store user information in session attributes
            session.setAttribute(StringUtils.USER_NAME, userName);
            session.setAttribute("role", userRole);
            
            // Store user information securely in cookies
            Cookie userNameCookie = new Cookie(StringUtils.USER, userName);
            Cookie userRoleCookie = new Cookie("role", userRole);
            
            // Set cookie max age to match session timeout
            userNameCookie.setMaxAge(30 * 60); // 30 minutes (in seconds)
            userRoleCookie.setMaxAge(30 * 60); // 30 minutes (in seconds)
            
            // Add cookies to the response
            response.addCookie(userNameCookie);
            response.addCookie(userRoleCookie);
            

            if (userRole.equals("Admin")) {
                // Redirect admin to the admin dashboard
                response.sendRedirect(request.getContextPath() + "/pages/dashboard.jsp");
                
            }else if (userRole.equals("User")) {
                response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
                
            } else {
                request.setAttribute("errorMessage", "Invalid user role");
                request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
            }
            
			
		}else if (loginResult == 0) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.LOGIN_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		 } else if (loginResult == -1) {
	            // Username not found
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_ACCOUNT);
				request.setAttribute(StringUtils.USER_NAME, userName);
	            request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		}else {
			request.setAttribute("errorMessage",StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		}
	}
	
}
