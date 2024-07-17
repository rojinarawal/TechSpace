<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>UserProfile</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/userorders.css">
</head>
<script>
	function updateUserProfile() {
		window.location.href = '${pageContext.request.contextPath}/pages/userprofile.jsp';
	}
</script>

<body>
	<section class="user">
		<section class="dashboard">
			<c:forEach var="user" items="${user}">
				<div class="images">
					<img
						src="${pageContext.request.contextPath}/stylesheets/images/profile.png"
						alt="Profile Picture">
					<h1>
						<c:out value="${user.userName}"></c:out>
					</h1>

				</div>
			</c:forEach>

			<div class="options">
				<a href="">My Profile</a> <a href="">Manage My Account</a> <a
					href="">My Orders</a> <a
					href="<%=request.getContextPath()%>/UserLogoutServlet">Logout</a>
			</div>

		</section>

		<section class="userrightpart">
			<section class="bgimage">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/Rectangle 55.png"
					alt="background">
			</section>
			<section class="account_settings_content">
				<section class="acsetting_outersection">
					<section class="account_settings">
						<div class="accountsettingheader">
							<h1 class="as_header">Account Settings</h1>
							<hr class="as_line">
						</div>
						<div class="activity">
							<table class="activity-table">
								<thead>

									<tr>
										<th>Order ID</th>
										<th>Order Date</th>
										<th>Product Name</th>
										<th>Cost</th>
										<th>Status</th>


									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${user}">
										<tr>
											<td>MN_001</td>
											<td>10-04-2024</td>
											<td>Emily Carter</td>
											<td>Rs 3,289</td>
											<td>Completed</td>

										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</section>
				</section>
				<section class="buttons"></section>
			</section>
		</section>
	</section>

</body>
</html>