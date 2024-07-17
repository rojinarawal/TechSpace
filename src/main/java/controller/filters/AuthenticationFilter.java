package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String uri = req.getRequestURI();

		if (uri.endsWith(".css") || uri.endsWith(".jpg") || uri.endsWith(".jpeg") || uri.endsWith(".png") || uri.endsWith(".svg")) {
			chain.doFilter(request, response); // Allow access to CSS and image files
			return;
		}

		if (session == null || session.getAttribute("user_name") == null) {
			if (uri.endsWith("/login.jsp") || uri.endsWith("/LoginServlet") || uri.endsWith("/signup.jsp") || uri.endsWith("/RegisterServlet") || uri.endsWith("/home.jsp")) {
				chain.doFilter(req, res); // Allow access to login, register, and home pages
				return;
			} else {
				res.sendRedirect(req.getContextPath() + "/pages/home.jsp"); // Redirect to home page if not logged in
				return;
			}
		} 
		if (session != null || session.getAttribute("user_name") != null || session.getAttribute("role") != null){
		    String userRole = (String) session.getAttribute("role");
		    if (userRole.equals("Admin")) {
		        if (uri.matches("/dashboard.jsp")) {
		            res.sendRedirect(req.getContextPath() + "/pages/orderlist.jsp"); // Redirect to order list if admin accessing specific pages
		            return;
		        }
		    }
		}
		
		chain.doFilter(req, res); // Allow access to other pages
	}


	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Authentication Filter Initialized");
	}

}
