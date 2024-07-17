package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.Product;
import model.cart;
import util.StringUtils;

/**
 * Servlet implementation class ViewCartItems
 */
@WebServlet("/ViewCartItems")
public class ViewCartItems extends HttpServlet {
private static final long serialVersionUID = 1L;

private DatabaseController dbController = new DatabaseController(); // Creating an instance of DatabaseController
    /**
     * Default constructor.
     */
    public ViewCartItems() {
        // TODO Auto-generated constructor stub
    }

/**
* @see Servlet#init(ServletConfig)
*/
public void init(ServletConfig config) throws ServletException {
// TODO Auto-generated method stub
}

/**
* @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Retrieving username from session
        Integer userId = (Integer) request.getSession().getAttribute("userID");

        // Getting list of products in cart for the user
        ArrayList<Product> cartitem = dbController.getproductinfo(userId);

        // Setting attribute for cart list and forwarding to cart page
        request.setAttribute("cartitems", cartitem);
        request.getRequestDispatcher("/pages/ViewCartItems.jsp").forward(request, response);
    }

// Method to fetch cart items from database


/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*
/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // Retrieving parameters from the request
        int product_id = Integer.parseInt(request.getParameter("productID"));
        int cart_id = (int) request.getSession().getAttribute("cart_id");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        if (cart_id == 0) {
            // If cart ID is null, forwarding request to error page
            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
            System.out.println("cart_id null");
        } else {
            // If cart ID is not null, creating CartModel instance
            cart Cart = new cart(cart_id, product_id, quantity);
            // Adding cart item to the database and getting result
            int result = dbController.addcartitems(cart_id,product_id,quantity);

            // Handling different results
            switch(result) {
                case 1 -> {
                	request.getSession().setAttribute("successMessage", "Item added successfully!");
                    response.sendRedirect(request.getContextPath() + "/ViewCartItems");
                }
                default -> {
                    // If unexpected result, setting error message and forwarding to error page
                   
                    request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
                    System.out.println("unexpected default");
                }
            }
        }
    }

}