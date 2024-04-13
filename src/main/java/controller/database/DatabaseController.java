package controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			
			u.setString (1, userModel.getFirstName());
			u.setString (2, userModel.getLastName());
			u.setString(3, userModel.getUserName());
			u.setString(4, userModel.getEmail());
			u.setString(5, userModel.getAddress());
			u.setString(6, userModel.getPhoneNumber());
			u.setString(7, userModel.getPassword());
			u.setString(8, userModel.getConfirmPassword());
			
			int result = u.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
	
