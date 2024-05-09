package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import util.StringUtils;

/**
 * Servlet implementation class DeleteCart
 */
@WebServlet("/DeleteCart")
public class DeleteCart extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
private final DatabaseController dbController;
    public DeleteCart() {
        this.dbController = new DatabaseController();
    }
/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
response.getWriter().append("Served at: ").append(request.getContextPath());
}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String delete_id = request.getParameter("productID");

        if (delete_id != null && !delete_id.isEmpty()) {
            // If deleteId is provided, invoke doDelete method
            doDelete(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int delete_id = Integer.parseInt(getInitParameter("productID"));
        HttpSession session = req.getSession();
        int cart_id = (int) session.getAttribute("cart_id");

       
            if (dbController.deleteCartItem(delete_id, cart_id) == 1) {
                // If deletion is successful, redirect to cart list page
                resp.sendRedirect(req.getContextPath() + "/ViewCartItems");
            } else {
                // If deletion fails, forward to error page
                req.getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            }
       
    }

}