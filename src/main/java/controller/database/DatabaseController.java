package controller.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Part;

import com.mysql.cj.protocol.Resultset;

import model.Order;
import model.OrderDetails;
import model.PasswordHashing;
import model.Product;
import model.UserModel;
import model.cart;
import util.StringUtils;

//This class handles all database interactions for the application.
public class DatabaseController implements Serializable {

	// Unique identifier for Serializable class
	private static final long serialVersionUID = 1L;

	// Establishes database connection using JDBC and returns the connection object.
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/techspace";
		String user = "root";
		String pass = "root";
		return DriverManager.getConnection(url, user, pass);
	}

	// Adds a user to the database and checks for existing username before inserting.
	public int addUser(UserModel userModel) {
		try (Connection con = getConnection()) {
			PreparedStatement u = con.prepareStatement(StringUtils.INSERT_USER);
			con.setAutoCommit(false);
			PreparedStatement checkUserNameun = con.prepareStatement(StringUtils.GET_USER_NAME);
			checkUserNameun.setString(1, userModel.getUserName());

			try (ResultSet checkUserNameRs = checkUserNameun.executeQuery()) {
				if (checkUserNameRs.next() && checkUserNameRs.getInt(1) > 0) {
					return -2; // UserName already exists
				}
			}

			// Sets user details into the INSERT_USER prepared statement.
			u.setString(1, userModel.getFirstName());
			u.setString(2, userModel.getLastName());
			u.setString(3, userModel.getUserName());
			u.setString(4, userModel.getEmail());
			u.setString(5, userModel.getAddress());
			u.setString(6, userModel.getPhoneNumber());
			u.setString(7, PasswordHashing.encrypt(userModel.getUserName(), userModel.getPassword()));
			u.setString(8, userModel.getRole());

			// If user role is 'user', also inserts an entry into the cart table.
			if (userModel.getRole().equals("user")) {
				PreparedStatement cartInsertStatement = con.prepareStatement(StringUtils.INSERT_CART_ID);
				cartInsertStatement.setString(1, null);
				cartInsertStatement.setString(2, userModel.getUserName());
				cartInsertStatement.executeUpdate();
			}

			int result = u.executeUpdate();
			con.commit();
			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Retrieves and verifies user login credentials against the database.
	public int getUserLoginInfo(UserModel userModel) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
			st.setString(1, userModel.getUserName());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String userDb = rs.getString(StringUtils.USER_NAME);
				String encryptedPwd = rs.getString(StringUtils.PASSWORD);
				String decryptedPwd = PasswordHashing.decrypt(encryptedPwd, userDb);

				if (userDb.equals(userModel.getUserName()) && decryptedPwd.equals(userModel.getPassword())) {
					return 1; // Login successful
				} else {
					return 0; // Username or password mismatch
				}
			} else {
				return -1; // Username not found in the database
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -2; // Internal error
		}
	}

	// Gets a unique user ID from the username.
	public int getUserID(String userName){
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement("Select userID from user_info where user_name=?");
			st.setString(1, userName);
			ResultSet rs= st.executeQuery();
			if(rs.next()) {
				return rs.getInt("userID");
			} else {
				return 0; // No user found
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1; // Error occurred
		}
	}

	// Fetches the user's cart ID using their user ID.
	public int getCartID(int userID){
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement("Select cartID from cart where userID = ?");
			st.setInt(1, userID);
			ResultSet rs= st.executeQuery();
			if(rs.next()) {
				return rs.getInt("cartID");
			} else {
				return 0; // No cart found
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1; // Error occurred
		}
	}

	// Retrieves a list of all users from the database.
	public ArrayList<UserModel> getAllUsersInfo() {
		try (Connection con = getConnection()) {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM user_info");
			ResultSet result = stmt.executeQuery();
			ArrayList<UserModel> users = new ArrayList<>();

			while (result.next()) {
				UserModel user = new UserModel();
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setUserName(result.getString("user_name"));
				user.setEmail(result.getString("email"));
				user.setAddress(result.getString("address"));
				user.setPhoneNumber(result.getString("phone_number"));
				user.setPassword(result.getString("password"));
				users.add(user);
			}
			return users;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null; // Error occurred
		}
	}

	// Determines the role of a user based on their username.
	public String getRole(String userName) {
		try (Connection con = getConnection()) {
			PreparedStatement stmt = con.prepareStatement("SELECT role FROM user_info WHERE user_name = ?");
			stmt.setString(1, userName);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getString("role");
			} else {
				return null; // Role not found
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null; // Error occurred
		}
	}

	// Inserts a new product into the database.
	public int addProduct(Product productModel) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.ADD_PRODUCT);
			st.setString(1, productModel.getProductname());
			st.setString(2, productModel.getCategory());
			st.setInt(3, productModel.getPrice());
			st.setInt(4, productModel.getStock());
			st.setString(5, productModel.getDescription());
			st.setString(6, productModel.getImageUrlFromPart());

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0; // Successful if more than 0 rows affected
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1; // Error occurred
		}
	}

	// Retrieves all products from the database.
	public ArrayList<Product> getAllProducts() {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement("SELECT * FROM products");
			ResultSet result = st.executeQuery();
			ArrayList<Product> productList = new ArrayList<>();

			while (result.next()) {
				Product product = new Product();
				product.setProductID(result.getInt("productId"));
				product.setProductname(result.getString("product_name"));
				product.setCategory(result.getString("category"));
				product.setPrice(result.getInt("price"));
				product.setStock(result.getInt("stock"));
				product.setDescription(result.getString("description"));
				product.setImageUrlFromDB(result.getString("image"));
				productList.add(product);
			}
			return productList;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null; // Error occurred
		}
	}

	// Selects a specific product by its ID from the database.
	public Product selectProduct(int productId) {
		Product product = new Product();
		try (Connection conn = getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM products WHERE productId = ?")) {
			preparedStatement.setInt(1, productId);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				product.setProductID(rs.getInt("productId"));
				product.setProductname(rs.getString("product_name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getInt("price"));
				product.setStock(rs.getInt("stock"));
				product.setDescription(rs.getString("description"));
				product.setImageUrlFromDB(rs.getString("image"));
			} else {
				product.setProductID(productId); // Indicates search was performed
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			product = new Product();
			product.setProductID(productId); // Indicates error or not found
		}
		return product;
	}

	// Deletes a product from the database by its ID.
	public int deleteProduct(int productID) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_PRODUCT);
			st.setInt(1, productID);
			return st.executeUpdate(); // Returns the number of rows affected
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1; // Error occurred
		}
	}

	// Updates the details of an existing product in the database.
	public int updateProduct(Product product) {
		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(StringUtils.UPDATE_PRODUCT)) {
			statement.setString(1, product.getProductname());
			statement.setString(2, product.getCategory());
			statement.setInt(3, product.getPrice());
			statement.setInt(4, product.getStock());
			statement.setString(5, product.getDescription());
			statement.setString(6, product.getImageUrlFromPart());
			statement.setInt(7, product.getProductID());
			return statement.executeUpdate(); // Returns the number of rows affected
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return 0; // No rows updated due to an error
	}

	// Fetches a product by its ID, creating a new Product object with the data.
	public Product getProductById(int productId) {
		Product product = null;
		try {
			PreparedStatement st = getConnection().prepareStatement("SELECT * FROM products WHERE productId = ?");
			st.setInt(1, productId);
			ResultSet result = st.executeQuery();

			if (result.next()) {
				product = new Product();
				product.setProductID(result.getInt("productId"));
				product.setProductname(result.getString("product_name"));
				product.setCategory(result.getString("category"));
				product.setPrice(result.getInt("price"));
				product.setStock(result.getInt("stock"));
				product.setDescription(result.getString("description"));
				product.setImageUrlFromDB(result.getString("image"));
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return product;
	}

	// Searches for products based on a text query and returns a list of matching products.
	public ArrayList<Product> getproductInfobySearch(String search) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.SEARCH_PRODUCT);
			stmt.setString(1, search);
			stmt.setString(2, search);
			ResultSet result = stmt.executeQuery();
			ArrayList<Product> searchproducts = new ArrayList<>();

			while (result.next()) {
				Product product = new Product();
				product.setProductID(result.getInt("productId"));
				product.setProductname(result.getString("product_name"));
				product.setCategory(result.getString("category"));
				product.setPrice(result.getInt("price"));
				product.setStock(result.getInt("stock"));
				product.setDescription(result.getString("description"));
				product.setImageUrlFromDB(result.getString("image"));
				searchproducts.add(product);
			}
			return searchproducts;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null; // Error occurred
		}
	}

	// Retrieves information about products in a user's cart.
	public ArrayList<Product> getproductinfo(int user_id) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.GET_CART_PRODUCT_INFO);
			stmt.setInt(1, user_id);
			ResultSet result = stmt.executeQuery();
			ArrayList<Product> cartdetails = new ArrayList<>();

			while (result.next()) {
				Product product = new Product();
				product.setProductID(result.getInt("productId"));
				product.setProductname(result.getString("product_name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getInt("price"));
				product.setImageUrlFromDB(result.getString("image"));
				product.setStock(result.getInt("quantity"));
				cartdetails.add(product);
			}
			return cartdetails;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null; // Error occurred
		}
	}

	// Removes an item from a user's cart by product and cart IDs.
	public int deleteCartItem(int productId, int cart_id) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.DELETE_CART);
			st.setInt(1, productId);
			st.setInt(2, cart_id);
			return st.executeUpdate(); // Returns the number of rows affected
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1; // Error occurred
		}
	}

	// Adds items to a user's cart, updating the cart with new quantities or products.
	public int addcartitems(int cartID, int productID, int quantity) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.ADD_TO_CART);
			st.setInt(1, cartID);
			st.setInt(2, productID);
			st.setInt(3, quantity);
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0; // Returns 1 if successful
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1; // SQL exception occurred
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1; // Class not found exception
		}
	}

	// Fetches the cart ID for a specific user by username.
	public int getcart_id(String user_name) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.CART_ID);
			st.setString(1, user_name);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return rs.getInt("cart_id");
			} else {
				return 0; // No cart found
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return 0; // Error occurred
		}
	}

	
	public ArrayList<Order> getOrderDetail(int userID) {
		ArrayList<Order> orders = new ArrayList<>();
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.GET_ORDERS_INFO);
			st.setInt(1, userID);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				Order order = new Order();
				Product product = new Product();
				order.setOrderID(result.getInt("orderID"));
				order.setUserID(result.getInt("userID"));
				order.setOrderDate(result.getDate("orderDate"));
				order.setTotal_amount(result.getInt("total_amount"));
				order.setOrder_status(result.getString("order_status"));
				order.setUserName(result.getString("user_name"));
				order.setProduct(product);
				orders.add(order);
			}
			return orders;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return orders; // Error occurred, return potentially partial list
		}
	}
	
	

	// Updates user profile information in the database.
	public int updateUserProfile(UserModel user) throws SQLException, ClassNotFoundException {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("Update user_info set user_name=?,first_name=?, last_name=?, email=?, address=?, phone_number=? where userId =?");
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getAddress());
			preparedStatement.setString(6, user.getPhoneNumber());
			preparedStatement.setInt(7, user.getUserID());

			int res = preparedStatement.executeUpdate();
			return res; // Returns the number of rows updated
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return 0; // Error occurred
		}
	}

	// Fetches detailed information for a specific user based on username.
	public ArrayList<UserModel> getSpecificUser(String userName) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM user_info where user_name = ?");
		stmt.setString(1, userName);

		ArrayList<UserModel> users = new ArrayList<>();

		ResultSet result = stmt.executeQuery();

		while (result.next()) {
			UserModel user = new UserModel();
			user.setUserID(result.getInt("userID"));
			user.setFirstName(result.getString("first_name"));
			user.setLastName(result.getString("last_name"));
			user.setUserName(result.getString("user_name"));
			user.setEmail(result.getString("email"));
			user.setAddress(result.getString("address"));
			user.setPhoneNumber(result.getString("phone_number"));
			user.setPassword(result.getString("password"));
			user.setRole(result.getString("role"));
			users.add(user);
		}
		System.out.println(users); // Debugging statement, prints the list of users
		return users;
	}
}


