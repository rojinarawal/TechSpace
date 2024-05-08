package controller.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Part;

import model.Order;
import model.PasswordHashing;
import model.Product;
import model.UserModel;
import util.StringUtils;

public class DatabaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/techspace";
		String user = "root";
		String pass = "root";
		return DriverManager.getConnection(url, user, pass);
	}

	public int addUser(UserModel userModel) {
		try (Connection con = getConnection()) {
			PreparedStatement u = con.prepareStatement(StringUtils.INSERT_USER);

			// Check for existing username
			PreparedStatement checkUserNameun = con.prepareStatement(StringUtils.GET_USER_NAME);
			checkUserNameun.setString(1, userModel.getUserName());

			try (ResultSet checkUserNameRs = checkUserNameun.executeQuery()) {
				if (checkUserNameRs.next() && checkUserNameRs.getInt(1) > 0) {
					return -2; // UserName already exists
				}
			}

			// Set parameters for INSERT_USER statement
			u.setString(1, userModel.getFirstName());
			u.setString(2, userModel.getLastName());
			u.setString(3, userModel.getUserName());
			u.setString(4, userModel.getEmail());
			u.setString(5, userModel.getAddress());
			u.setString(6, userModel.getPhoneNumber());
			u.setString(7, PasswordHashing.encrypt(userModel.getUserName(), userModel.getPassword()));
			u.setString(8, userModel.getRole());

			int result = u.executeUpdate();
			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getUserLoginInfo(UserModel userModel) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
			st.setString(1, userModel.getUserName());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// Get username from the database
				String userDb = rs.getString(StringUtils.USER_NAME);

				// Get password from the database
				String encryptedPwd = rs.getString(StringUtils.PASSWORD);

				String decryptedPwd = PasswordHashing.decrypt(encryptedPwd, userDb);
				// Check if the username and password match the credentials from the database
				if (userDb.equals(userModel.getUserName()) && decryptedPwd.equals(userModel.getPassword())) {
					// Login successful, return 1
					return 1;
				} else {
					// Username or password mismatch, return 0
					return 0;
				}
			} else {
				// Username not found in the database, return -1
				return -1;
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}
				

	public ArrayList<UserModel> getAllUsersInfo() {
		try {
			PreparedStatement stmt = getConnection().prepareStatement("SELECT* FROM user_info");
			ResultSet result = stmt.executeQuery();

			ArrayList<UserModel> users = new ArrayList<UserModel>();

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
			return null;
		}
	}

	public String getRole(String userName) {
		try (Connection con = getConnection()) {
			PreparedStatement stmt = con.prepareStatement("SELECT role FROM user_info WHERE user_name = ?");
			stmt.setString(1, userName);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getString("role");
			} else {
				// User role not found
				return null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int addProduct(Product productModel) {
	    try (Connection con = getConnection()) {
	        
	        PreparedStatement st = con.prepareStatement(StringUtils.ADD_PRODUCT);
	        // Set parameters for the prepared statement
	        st.setString(1, productModel.getProductname());
	        st.setString(2, productModel.getCategory());
	        st.setInt(3, productModel.getPrice());
	        st.setInt(4, productModel.getStock());
	        st.setString(5, productModel.getDescription());
	        st.setString(6, productModel.getImageUrlFromPart());

	        int result = st.executeUpdate();
	        // Return 1 if the insertion was successful
	        return result > 0 ? 1 : 0;
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return -1; // Return -1 if an exception occurs
	    }
	}


	public ArrayList<Product> getAllProducts() {
		try {
			PreparedStatement st = getConnection().prepareStatement("SELECT* FROM products");
			ResultSet result = st.executeQuery();

			ArrayList<Product> productList = new ArrayList<Product>();

			while (result.next()) {
				Product products = new Product();
				products.setProductID(result.getInt("productId"));
				products.setProductname(result.getString("product_name"));
				products.setCategory(result.getString("category"));
				products.setPrice(result.getInt("price"));
				products.setStock(result.getInt("stock"));
				products.setDescription(result.getString("description"));
				products.setImageUrlFromDB(result.getString("image"));
				productList.add(products);
			}
			return productList;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Product selectProduct(int productId) {
	    Product product = new Product();  // This will handle both found and not found/error cases
	    try (Connection conn = getConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM products WHERE productId = ?")) {
	        preparedStatement.setInt(1, productId);  // Set the parameter value
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            // Populate product properties from the database
	            product.setProductID(rs.getInt("productId"));
	            product.setProductname(rs.getString("product_name"));
	            product.setCategory(rs.getString("category"));
	            product.setPrice(rs.getInt("price"));
	            product.setStock(rs.getInt("stock"));
	            product.setDescription(rs.getString("description"));
	            product.setImageUrlFromDB(rs.getString("image"));
	        } else {
	            product.setProductID(productId);  // Set the product ID to indicate the search was performed
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace(); // Ideally, use logging framework
	        // Set some error indication, could also consider returning null or throwing a custom exception
	        product = new Product(); // This reset might be redundant, consider just setting an error flag or similar
	        product.setProductID(productId);
	    }
	    return product;  // Return the product, populated with data or as a new instance with error indicated
	}

	public int deleteProduct(int productID) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_PRODUCT);
			st.setInt(1, productID);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	

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
	        return statement.executeUpdate(); // This will return the number of rows affected
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    return 0; // Return 0 to indicate no rows were updated due to an error
	}

}

//	public int placeOrder(String query, Order orderModel) {
//		Connection dbConnection = getConnection();
//		if (dbConnection != null) {
//			try {
//
//				PreparedStatement statement = dbConnection.prepareStatement(query);
//				statement.setString(1, orderModel.getEmail());
//				statement.setInt(2, orderModel.getProductName());
//				statement.setDate(3, orderModel.getDate());
//
//				int result = statement.executeUpdate();
//				if (result >= 0)
//					return 1;
//				else
//					return 0;
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				return -2;
//			}
//		} else {
//			return -3;
//		}
//	}

	
