package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.StringUtils;

public class DatabaseController {
	
	public Connection getConnection() throws SQLException, ClassNotFoundException { 
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/techspace";
		String user = "root";
		String pass = "root";
		return DriverManager.getConnection(url, user, pass);
		
		public int addUser(UserModel userModel) {
			try (Connection con = getConnection()) {
				PreparedStatement st = con.prepareStatement(StringUtils.INSERT_USER_INFO);
				
				//check for existing username
				PreparedStatement checkUsername = con.prepareStatement(StringUtils.GET_ALL_USER_INFO);
				checkUsernameSt.setString(1, studentModel.getUsername());
				ResultSet checkUsernameRs = checkUsernameSt.executeQuery();
				checkUsernameRs.next();
				if (checkUsernameRs.getInt(1) > 0) {
					return -2; //Username already exists
				}
				
				st.setString(1, studentModel.getUsername());
				st.setString(2, studentModel.getFirstName());
				st.setString(3, studentModel.getLastName());
				st.setDate(4, Date.valueOf(studentModel.getDob()));
				st.setString(5, studentModel.getGender());
				st.setString(6, studentModel.getEmail());
				st.setString(7, studentModel.getPhoneNumber());
				st.setString(8, studentModel.getSubject());
				st.setString(9, studentModel.getPassword());
				
				int result = st.executeUpdate();
				return result > 0 ? 1 : 0;
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
				return -1;
			}
		}
		
		
		
}
