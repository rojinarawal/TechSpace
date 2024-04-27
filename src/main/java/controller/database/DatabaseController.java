package controller.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			u.setString(7, userModel.getPassword());
			u.setString(8, userModel.getRole());

			int result = u.executeUpdate();
			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getUserLoginInfo(String user_name, String password) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
			st.setString(1, user_name);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return 1;
			} else {
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
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
	        // Check if the product ID already exists
	        if (productExists(con, productModel.getProductID())) {
	            System.out.println("Product with ID " + productModel.getProductID() + " already exists.");
	            return -2; // Return -2 to indicate that the product already exists
	        }
	        
	        PreparedStatement st = con.prepareStatement(StringUtils.ADD_PRODUCT);
	        // Set parameters for the prepared statement
	        st.setInt(1, productModel.getProductID());
	        st.setString(2, productModel.getProductname());
	        st.setString(3, productModel.getDescription());
	        st.setString(4, productModel.getCategory());
	        st.setInt(5, productModel.getPrice());
	        st.setInt(6, productModel.getStock());
	        st.setString(7, productModel.getImageUrlFromPart());

	        int result = st.executeUpdate();
	        // Return 1 if the insertion was successful
	        return result > 0 ? 1 : 0;
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return -1; // Return -1 if an exception occurs
	    }
	}

	private boolean productExists(Connection con, int productId) throws SQLException {
	    String query = "SELECT COUNT(*) FROM product WHERE productId = ?";
	    try (PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setInt(1, productId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                int count = rs.getInt(1);
	                return count > 0; // Return true if product with given ID exists
	            }
	        }
	    }
	    return false; // Return false if an error occurs or no product found
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
				products.setDescription(result.getString("description"));
				products.setCategory(result.getString("category"));
				products.setPrice(result.getInt("price"));
				products.setStock(result.getInt("stock"));
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
	    Product product = null;
	    // Step 1: Establishing a Connection
	    try (Connection conn = getConnection();
	         // Step 2: Create a PreparedStatement using the query string
	         PreparedStatement preparedStatement = conn.prepareStatement("SELECT productId, product_name, category, price, stock FROM products WHERE productId = ?")) {
	        
	        // Step 3: Set the parameter value
	        preparedStatement.setInt(1, productId);
	        
	        // Step 4: Execute the query
	        ResultSet rs = preparedStatement.executeQuery();

	        // Step 5: Process the ResultSet object.
	        while (rs.next()) {
	            int productID = rs.getInt("productId");
	            String productname = rs.getString("product_name");
	            String category = rs.getString("category");
	            int price = rs.getInt("price");
	            int stock = rs.getInt("stock");

	            product = new Product(productID, productname, category, price, stock);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace(); // Handle the exception appropriately
	        return null;
	    }
	    return product;
	}
} 

	
