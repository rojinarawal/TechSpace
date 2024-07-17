<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<%
String contextPath = request.getContextPath();
%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>UserProfile</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/userprofile.css">
</head>
<body>
	<section class="user">
		<section class="dashboard">
			<div class="images">
				<img src="<%=contextPath%>/stylesheets/images/profile.png"
					alt="Profile Picture">
				<h2>Roberto Alexandra</h2>
			</div>

			<div class="options">
				<a href="">My Profile</a> 
				<a href="">Manage My Account</a> 
				 <a href="">My Orders</a>
				  <a href="<%=request.getContextPath()%>/UserLogoutServlet">Logout</a>
			</div>

		</section>
		<section class="userrightpart">
			<section class="bgimage">
				<img src="<%=contextPath%>/stylesheets/images/Rectangle 55.png "
					alt="background">
			</section>
			<section class="account_settings_content">
				<section class="acsetting_outersection">
					<section class="account_settings">
						<div class="accountsettingheader">
							<h1 class="as_header">Account Settings</h1>
							<hr class="as_line">
						</div>
						<div class="as_info">
							<form action="UpdateProfile" method="post">
								<c:forEach var="user" items="${userDetail}">
									<div class="as_info_one">
										<div class="input-box">
											<span class="details">First Name</span> <input type="text"
												name="firstName" placeholder="Enter your last name" required
												value='<c:out value="${user.firstName}"></c:out>'>
										</div>
										<div class="input-box">
											<span class="details">Last Name</span> <input type="text"
												name=lastName placeholder="Enter your last name" required
												value='<c:out value="${user.lastName}"></c:out>'>
										</div>
									</div>
									<div class="as_info_one">

										<div class="input-box">
											<span class="details">User Name</span> <input type="text"
												name="userName" placeholder="Enter your username " required
												value='<c:out value="${user.userName}"></c:out>'>
										</div>
										<div class="input-box">
											<span class="details">Email</span> <input type="text"
												name="email" required pattern=".*@gmail.com" required
												value='<c:out value="${user.email}"></c:out>'>
										</div>
									</div>
									<div class="as_info_one">
										<div class="input-box">
											<span class="details">Address</span> <input type="text"
												name="address" placeholder="Your current address" required
												value='<c:out value="${user.address}"></c:out>'>
										</div>
										<div class="input-box">
											<span class="details">Phone Number</span> <input type="text"
												name="phoneNumber" required maxlength="10" required
												value='<c:out value="${user.phoneNumber}"></c:out>'>
										</div>


										<input type="text" id="userid" name="userId"
											value='<c:out value="${user.userID}"></c:out>' hidden /> <input
											type="text" id="role" name="role"
											value='<c:out value="${user.role}"></c:out>' hidden /> <input
											type="text" id="password" name="password"
											value='<c:out value="${user.password}"></c:out>' hidden />
									</div>
								</c:forEach>
								<input type="submit" value="UPDATE" class="update-button" />

							</form>

						</div>
					</section>
				</section>
				<section class="buttons">
					<button class="update-button">UPDATE</button>
					<button class="cancel-button">CANCEL</button>
				</section>
			</section>
		</section>
	</section>

</body>
</html>