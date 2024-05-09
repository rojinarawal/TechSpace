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
            con.setAutoCommit(false);
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

            if (userModel.getRole().equals("user")) {
                // Insert into the cart table
                
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
	
	public int getUserID(String userName){
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement("Select userID from user_info where user_name=?");
			st.setString(1, userName);
			ResultSet rs= st.executeQuery();
			if(rs.next()) {
				return rs.getInt("userID");
			}else {
				return 0;
			}
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}	
	}
	public int getCartID(int userID){
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement("Select cartID from user_info where userID = ?");
			st.setInt(1, userID);
			ResultSet rs= st.executeQuery();
			if(rs.next()) {
				return rs.getInt("cartID");
			}else {
				return 0;
			}
		}catch (SQLException | ClassNotFoundException ex) {
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

	public Product getProductById(int productId) {
	    Product product = null; // Initialize to null to handle the case where no product is found
	    try {
	        // Prepare the SQL statement to fetch a single product by its ID
	        PreparedStatement st = getConnection().prepareStatement("SELECT * FROM products WHERE productId = ?");
	        st.setInt(1, productId); // Set the product ID in the SQL query

	        // Execute the query
	        ResultSet result = st.executeQuery();

	        // Check if a product is found
	        if (result.next()) {
	            // Create a new Product object and populate it with data from the database
	            product = new Product();
	            product.setProductID(result.getInt("productId"));
	            product.setProductname(result.getString("product_name"));
	            product.setCategory(result.getString("category"));
	            product.setPrice(result.getInt("price"));
	            product.setStock(result.getInt("stock"));
	            product.setDescription(result.getString("description"));
	            product.setImageUrlFromDB(result.getString("image")); // Ensure your Product class has a method to set the image URL
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	        // Optionally handle the exception by logging or throwing a custom exception
	    }
	    return product; // Return the product found, or null if no product was found or an error occurred
	}
	
	public ArrayList<Product> getproductInfobySearch(String search) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.SEARCH_PRODUCT);
            stmt.setString(1, search);
            stmt.setString(2, search);
            ResultSet result = stmt.executeQuery();

            ArrayList<Product> searchproducts = new ArrayList<Product>();

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
            return null;
        }
    }
	

	public ArrayList<Product> getproductinfo(String user_name) {

		try {

			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.GET_CART_PRODUCT_INFO);
			stmt.setString(1, user_name);

			ResultSet result = stmt.executeQuery();

			ArrayList<Product> cartdetails = new ArrayList<Product>();
			while (result.next()) {
				Product product = new Product();
				product.setProductID(result.getInt("productId"));
				product.setProductname(result.getString("product_name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getInt("price"));
				product.setImageUrlFromDB(result.getString("images"));
				product.setStock(result.getInt("quantity"));

				cartdetails.add(product);
			}
			return cartdetails;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}



	//remove cart item
	public int deleteCartItem(int productId, int cart_id) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.DELETE_CART);
			st.setInt(1, productId);
			st.setInt(2, cart_id);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	//cart

	public int addcartitems(cart cart) {
		try (Connection con = getConnection()) {
			// Prepare the SQL statement using ON DUPLICATE KEY UPDATE
			PreparedStatement st = con.prepareStatement(StringUtils.ADD_TO_CART);
			st.setInt(1, cart.getCartId());
			st.setInt(2, cart.getProductId());
			st.setInt(3, cart.getQuantity());
			
			int result = st.executeUpdate(); // Insert, update, delete
			return result > 0 ? 1 : 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1; // Handle SQL exception
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1; // Handle class not found exception
		}
	}

	public int getcart_id(String user_name) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.CART_ID);
			st.setString(1, user_name);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return rs.getInt("cart_id");
			} else {
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public ArrayList<Order> getOrdersinfo(int userID) {
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.GET_ORDERS_INFO);
			st.setInt(1, userID);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				Order order = new Order();
				order.setOrderID(result.getInt("orderID"));
				order.setUserID(result.getInt("userID"));
				order.setOrderDate(result.getDate("orderDate"));
				order.setTotal_amount(result.getInt("total_amount"));
				order.setOrder_status(result.getString("order_status"));		
				order.setUserName(result.getString("user_name"));
				orders.add(order);
			}
			return orders;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return orders;
		}
	}
	
	public Order fetchOrderDetails(int orderID) {
        Order order = new Order();
        try {
            PreparedStatement st = getConnection().prepareStatement(StringUtils.FETCH_ORDER);
            st.setInt(1, orderID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                order.setOrderID(rs.getInt("orderID"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setTotal_amount(rs.getInt("total_amount"));
                order.setOrder_status(rs.getString("order_status"));

                PreparedStatement userQuery = getConnection().prepareStatement(StringUtils.FIND_USER);
                userQuery.setInt(1, rs.getInt("userID"));
                ResultSet userData = userQuery.executeQuery();

                if (userData.next()) {
                    order.setUserID(userData.getInt("userID"));
                    order.setUserName(userData.getString("user_name"));
                }

                PreparedStatement st1 = getConnection().prepareStatement(StringUtils.FETCH_ORDER_ITEMS);
                st1.setInt(1, rs.getInt("orderID"));
                ResultSet itemsData = st1.executeQuery();

                ArrayList<OrderDetails> itemsList = new ArrayList<OrderDetails>();
                while (itemsData.next()) {
                    OrderDetails item = new OrderDetails ();
                    item.setDetailsID(itemsData.getInt("detailID"));
                    item.setQuantity(itemsData.getInt("quantity"));

                    PreparedStatement productQuery = getConnection().prepareStatement(StringUtils.FIND_PRODUCT);
                    productQuery.setInt(1, itemsData.getInt("productID"));
                    ResultSet productData = productQuery.executeQuery();

                    if (productData.next()) {
                        item.setProductID(productData.getInt("productID"));
                        item.setProductname(productData.getString("name"));
                        item.setPrice(productData.getInt("price"));
                    }
                    itemsList.add(item);
                }

                order.setOrderdetails(itemsList);
            }
            return order;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return order;
        }
    }
}
