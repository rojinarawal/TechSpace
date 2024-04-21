/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * 
 */
public class UserModel implements Serializable  {	

	/**
	 * @param args
	 */
	
		private String firstName;
		private String lastName;
		private String userName;
		private String email;
		private String address;
		private String phoneNumber;
		private String password;
		private String confirmPassword;
		
		public UserModel (String firstName, String lastName, String userName, String email, String address, String phoneNumber, String password, String confirmPassword) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.userName = userName;
			this.email = email;
			this.address = address;
			this.phoneNumber = phoneNumber;
			this.password =  password;
			this.confirmPassword = confirmPassword;
			
		}
		
		public UserModel() {
			// TODO Auto-generated constructor stub
		}
		
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		

}