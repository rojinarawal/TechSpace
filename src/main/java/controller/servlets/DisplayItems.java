package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;

@WebServlet("/DisplayItems")
public class DisplayItems extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DatabaseController dbController = new DatabaseController();
            Connection con = dbController.getConnection();
            ResultSet rs = dbController.getProducts(con);

            request.setAttribute("productList", rs); // Set ResultSet as request attribute

            // Forward the request to the JSP file for presentation
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/DisplayItems.jsp");
            dispatcher.forward(request, response);

            // Close resources
            rs.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            // Handle exceptions
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}