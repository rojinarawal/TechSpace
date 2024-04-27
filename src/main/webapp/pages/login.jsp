<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TechSpace Log-in</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/login.css">
</head>

<body>
	<section class="login">
		<section>
			<% 
		String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
		if(errorMessage != null && !errorMessage.isEmpty()){
		%>
			<p class="error-message"><%=errorMessage %></p>
			<%
		}
		%>
			<h1>Sign in</h1>
			<div class="info">
				<form action="/TechSpace/LoginServlet" class="info_container">
					<div class="user-details">
						<div class="input-box">
							<span class="details">Username</span> <input type="text"
								name="user_name" placeholder="Enter your Username" required>
						</div>
						<div class="input-box">
							<span class="details">Password</span> <input type="password"
								name="password" placeholder="Enter your password" required>
							<span class="login-fp">Forgot Password?</span>
						</div>

					</div>
					<div class="login-button">
						<input type="submit" value="Sign in" class="login_button">
					</div>
				</form>
				<div class="login-link">
					<p>
						Don't have an account? <a
							href="${pageContext.request.contextPath}/pages/signup.jsp">Sign
							up</a>
					</p>
				</div>
			</div>
		</section>
		<section class="iamge">
			<div class="image_container">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/whitish.jpg"
					alt="TechSpace" class="image_container">
			</div>
		</section>
	</section>


</body>

</html>