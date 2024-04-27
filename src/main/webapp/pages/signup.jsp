<%@ page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" type="text/css"
	href="/TechSpace/stylesheets/signup.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
	<section>


		<div class="container">
			<div class="title">Sign Up</div>
			<div class="content">
				<form action="/TechSpace/RegisterServlet" method="post">
					<div class="user-details">
						<div class="input-box">
							<span class="details">First Name</span> <input type="text"
								name="firstName" placeholder="First Name" required>
						</div>
						<div class="input-box">
							<span class="details">Last Name</span> <input type="text"
								name="lastName" placeholder="Last Name" required>
						</div>
						<div class="input-box">
							<span class="details">User Name</span> <input type="text"
								name="userName" placeholder="Username" required>
						</div>
						<div class="input-box">
							<span class="details">Email</span> <input type="text"
								name="email" placeholder="example@gmail.com" required>
						</div>
						<div class="input-box">
							<span class="details">Address</span> <input type="text"
								name="address" placeholder="Address" required>
						</div>
						<div class="input-box">
							<span class="details">Phone Number</span> <input type="text"
								name="phoneNumber" placeholder="Number" required>
						</div>
						<div class="input-box">
							<span class="details">Password</span> <input type="password"
								name="password" placeholder="Password" required>
						</div>
						<div class="input-box">
							<span class="details">Confirm Password</span> <input
								type="password" name="confirmPassword"
								placeholder="Confirm Password" required>
						</div>
					</div>

					<div class="button">
						<input type="submit" value="Sign Up">
					</div>
				</form>
				<div class="login-link">
					<p>
						Already have an account? <a href="login.jsp">Sign in</a>
					</p>
				</div>
			</div>

		</div>

	</section>
	<div>
		<img
			src="${pageContext.request.contextPath}/stylesheets/images/wishlist.jpeg"
			alt="TechSpace" class="image-container">
	</div>


</body>

</html>