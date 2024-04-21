package controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserModel;
import util.StringUtils;

public class DatabaseController {
	
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
			
			//check for existing username
			PreparedStatement checkUserNameun = con.prepareStatement(StringUtils.GET_USER_NAME);
			checkUserNameun.setString(1, userModel.getUserName());
			
			
			try (ResultSet checkUserNameRs = checkUserNameun.executeQuery()){
			if (checkUserNameRs.next() && checkUserNameRs.getInt(1) > 0) {
				return -2; //UserName already exists
				}
			}
			u.setString (1, userModel.getFirstName());
			u.setString (2, userModel.getLastName());
			u.setString (3, userModel.getUserName());
			u.setString (4, userModel.getEmail());
			u.setString (5, userModel.getAddress());
			u.setString (6, userModel.getPhoneNumber());
			u.setString (7, userModel.getPassword());
			u.setString (8, userModel.getConfirmPassword());
		
			int result = u.executeUpdate();
			return result > 0 ? 1 : 0;			
				
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int getUserLoginInfo(String user_name, String password) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
			st.setString(1, user_name);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				return 1;
			}else {
				return 0;
			}
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public ArrayList<UserModel> getAllUsersInfo(){
		try {
			PreparedStatement stmt = getConnection().prepareStatement("SELECT* FROM user_info");
			ResultSet result = stmt.executeQuery();
			
			ArrayList<UserModel> users = new ArrayList<UserModel>();
			
			while(result.next()) {
				UserModel user = new UserModel();
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setUserName(result.getString("user_name"));
				user.setEmail(result.getString("email"));
				user.setAddress(result.getString("address"));
				user.setPhoneNumber(result.getString("phone_number"));
				user.setPassword(result.getString("password"));
				user.setConfirmPassword(result.getString("confirm_password"));
				
				users.add(user);
			}
			return users;
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
	
