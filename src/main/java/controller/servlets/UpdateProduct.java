package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DatabaseController;
import model.Product;
import util.StringUtils;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	DatabaseController dbController = new DatabaseController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            Product product = dbController.selectProduct(productID);
            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/pages/editproducts.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Product not found");
                request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid product ID");
            request.getRequestDispatcher("/pages/productlist.jsp").forward(request, response);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 try {
			 System.out.println("Received productID: " + request.getParameter("productId"));
			 System.out.println("Received price: " + request.getParameter("price"));
			 System.out.println("Received stock: " + request.getParameter("stock"));
			 System.out.println("Received img: " + request.getParameter("image"));
			 System.out.println("Received name: " + request.getParameter("productName"));
			 System.out.println("Received desc: " + request.getParameter("description"));
			 System.out.println("Received cat: " + request.getParameter("category"));
			
	            int productID = Integer.parseInt(request.getParameter("productId"));
	            String productName = request.getParameter("productName");
	            String category = request.getParameter("category");
	            int price = Integer.parseInt(request.getParameter("price"));
	            int stock = Integer.parseInt(request.getParameter("stock"));
	            String description = request.getParameter("description");
	            Part imagePart = request.getPart("image");

	            Product product = new Product(productID, productName, category, price, stock, description, imagePart);

	            int result = dbController.updateProduct(product);
	            if (result > 0) {
	                request.setAttribute("registerMessage", "Successfully Updated");
	                request.getRequestDispatcher("/pages/productlist.jsp").forward(request, response);
	            } else {
	                request.setAttribute("registerMessage", "No changes made");
	                request.getRequestDispatcher("/pages/productlist.jsp").forward(request, response);
	            }
	        } catch (NumberFormatException | IOException | ServletException e) {
	            request.setAttribute("error", "Error processing request");
	            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
	            e.printStackTrace();
	        }
	    }
}
