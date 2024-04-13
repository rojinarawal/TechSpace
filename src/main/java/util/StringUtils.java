package util;

public class StringUtils {
public static final String INSERT_USER = "INSERT INTO user_info "
		+ "(first_name, last_name, user_name, email, address,  phone_number, password, confirm_password) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String GET_ALL_USER_INFO = "SELECT * FROM user_info";
	
	public static final String FIRST_NAME="firstName";
	public static final String LAST_NAME="lastName";
	public static final String USER_NAME="userName";
	public static final String EMAIL="email";
	public static final String ADDRESS="address";
	public static final String PHONE_NUMBER="phoneNumber";
	public static final String PASSWORD="password";
	public static final String CONFIRM_PASSWORD="confirmPassword";

	//Start message

	public static final String REGISTER_ERROR_MESSAGE="Please correct the form data.";
	public static final String SERVER_ERROR_MESSAGE="An unexpected server error occurred.";
	public static final String SUCCESS_MESSAGE="successMessage";
	public static final String ERROR_MESSAGE="errorMessage";
	public static final String LOGIN_ERROR_MESSAGE="Invalid credential.";
	public static final String SUCCESS_LOGIN_MESSAGE="Login successful.";
	//End messages

	//Start JSP Route
	public static final String LOGIN_PAGE = "/pages/login.jsp";
	public static final String REGISTER_PAGE = "/pages/signup.jsp";
	//End JSP Route

	//Start Servlet Route
	public static final String REGISTER_SERVLET="/RegisterServlet";
	//End Servlet Route

	public static final String SUCCESS_REGISTER_MESSAGE = "Successfully Registered.";
	public static final String ERROR_REGISTER_MESSAGE = "Please correct the form data.";
}
