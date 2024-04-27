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
       
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        System.out.println("Running filter in"+uri);


        // Allow access to CSS files
        if (uri.endsWith(".css") || uri.endsWith("/productlist.jsp") || uri.endsWith("/AddProduct") || uri.endsWith(".jpg") || uri.endsWith(".jpeg") || uri.endsWith(".png") || uri.endsWith(".svg") ) {
            chain.doFilter(request, response);
            return;
        }

        // Check if the request is for login page, login servlet, or register servlet
        boolean isLogin = uri.endsWith("/login.jsp");
        boolean isLoginServlet = uri.endsWith("/LoginServlet");
        boolean isRegisterServlet = uri.endsWith("/RegisterServlet");
		boolean isRegister = uri.endsWith("/signup.jsp");
		boolean isLogoutServlet = uri.endsWith("/LogoutServlet");
		
        // Attempt to retrieve the current session
        HttpSession session = req.getSession(false);
        boolean isLoggedIn = session != null && session.getAttribute("user_name") != null;


		if(!isLoggedIn) {
			if(!isLogin && !isLoginServlet && !isRegister && !isRegisterServlet) {
				res.sendRedirect(req.getContextPath()+"/pages/login.jsp");
			}else {
				chain.doFilter(request, response);
			}
		}
		// If the user is logged in and the requested URI does not indicate an attempt
		// to access the login page or logout servlet,
		// redirect the user to the home page to prevent access to login-related pages.
		else if (isLoggedIn && !(!isLogin || isLogoutServlet)) {
			res.sendRedirect(req.getContextPath() + "/pages/home.jsp");
		}
		// If none of the above conditions are met, allow the request to continue down
		// the filter chain.
		else {
			chain.doFilter(request, response);
		}
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter Initialized");
	}

}
