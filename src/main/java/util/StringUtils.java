package util;

public class StringUtils {
public static final String INSERT_USER_INFO = "INSERT INTO user_info "
		+ "(first_name, last_name, user_name, email, address,  phone_number, password, confirm_password) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String GET_ALL_USER_INFO = "SELECT * FROM user_info";

}
