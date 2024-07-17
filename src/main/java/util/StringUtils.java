package util;

import java.io.File;

public class StringUtils {
	// SQL queries
	public static final String INSERT_USER = "INSERT INTO user_info "
			+ "(first_name, last_name, user_name, email, address, phone_number, password, role) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String GET_ALL_USER_INFO = "SELECT * FROM user_info";
	public static final String GET_LOGIN_USER_INFO = "SELECT * FROM user_info WHERE user_name = ?";
	public static final String ADD_PRODUCT = "INSERT INTO products (product_name, category, price, stock, description, image) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String IMAGE_DIR_USER = "Users\\Acer\\eclipse-workspace\\TechSpace\\src\\main\\webapp\\resources\\images\\products\\";
	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR_USER;
	public static final String QUERY_DELETE_PRODUCT = "DELETE FROM products WHERE productId = ?";
	public static final String UPDATE_PRODUCT = "UPDATE products SET product_name = ?, category = ?, price = ?, stock = ?, description = ?, image = ? WHERE productId = ?";
	public static final String SELECT_PRODUCT_BY_ID = "SELECT productId, product_name, category, price, stock, description, image from products where productId =?";

	// Field names
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String USER_NAME = "user_name";
	public static final String USERNAME = "userName";
	public static final String EMAIL = "email";
	public static final String ADDRESS = "address";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String PASSWORD = "password";
	public static final String CONFIRM_PASSWORD = "confirmPassword";
	public static final String ROLE = "role";

	// Default role
	public static final String DEFAULT_ROLE = "User";

	/**
	 * Checks if a string is null or empty.
	 * @param str The string to check.
	 * @return True if the string is null or empty, false otherwise.
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	// Error messages
	/**
	 * Error messages for various scenarios.
	 */
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String REGISTER_ERROR_MESSAGE = "Please correct the form data.";
	public static final String SERVER_ERROR_MESSAGE = "An unexpected server error occurred.";
	public static final String LOGIN_ERROR_MESSAGE = "Invalid credential.";
	public static final String USERNAME_ERROR_MESSAGE = "Username is already registered.";
	public static final String EMAIL_ERROR_MESSAGE = "Email is already registered.";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";
	public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";

	// Success messages
	public static final String SUCCESS_MESSAGE = "successMessage";
	public static final String SUCCESS_LOGIN_MESSAGE = "Login successful.";
	public static final String SUCCESS_REGISTER_MESSAGE = "Successfully Registered.";

	// JSP Routes
	public static final String LOGIN_PAGE = "/pages/login.jsp";
	public static final String REGISTER_PAGE = "/pages/signup.jsp";
	public static final String HOME_PAGE = "/pages/home.jsp";
	public static final String ADMIN_DASHBOARD= "/pages/orderlist.jsp";
	public static final String ERROR_PAGE = "/pages/error.jsp";


	// Servlet Routes
	public static final String REGISTER_SERVLET = "/RegisterServlet";

	// SQL queries for validation
	public static final String GET_USER_NAME = "SELECT COUNT(*) FROM user_info where user_name = ? ";
	public static final String GET_PHONE = "SELECT COUNT(*) FROM user_info where phone_number = ? ";
	public static final String GET_EMAIL = "SELECT COUNT(*) FROM user_info where email = ? ";

	// Session attributestechspace
	public static final String USER = "user";
	public static final String JSESSIONID = "JSESSIONID";

	//Cart
	public static final java.lang.String INSERT_CART_ID = "INSERT INTO cart (cartID, userID) VALUES (?,?)";
	public static final java.lang.String DELETE_CART = "DELETE FROM cart_item where productID=? and cartID=?";
	public static final java.lang.String CART_ID = "SELECT cartID FROM cart WHERE user_name=?";
	public static final java.lang.String ADD_TO_CART = "INSERT INTO cart_item (cartID, productID, quantity) VALUES (?, ?, ?)";
	public static final java.lang.String GET_CART_PRODUCT_INFO = "SELECT p.productId, p.product_name, p.description, p.category, p.price, p.image, ci.quantity FROM products p JOIN cart_item ci ON p.productId = ci.productID JOIN cart c ON ci.cartID = c.cartID JOIN user_info u ON c.userID = u.userID WHERE u.userID = ?";

	//searchproduct
	public static final String SEARCH_PRODUCT = "SELECT * FROM products WHERE product_name LIKE ? OR price LIKE ?";

	//orderlist
	public static final String GET_ORDERS_INFO = "SELECT o.orderID, o.userID, o.orderDate, o.total_amount, o.order_status " +
		    "FROM order o " +
		    "JOIN order_details od ON o.orderID = od.orderID " +
		    "WHERE o.userID = ?";
}