<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="util.StringUtils"%>
<%
    // Get the session and request objects
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute(StringUtils.USER_NAME);
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="https://kit.fontawesome.com/8abb2279ae.js"
	crossorigin="anonymous"></script>
<title>Header</title>
</head>
<style>
li, a {
	font-family: sans-serif;
	font-weight: 500;
	font-size: 16px;
	color: #9BA4B4;
	text-decoration: none;
}

.navbar_user {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

header {
	display: flex;
	justify-content: space-between;
	/* Ensures space between the logo and menu */
	/* Vertically aligns logo and menu items */
	padding: 8px 30px; /* Adjusts padding around the header */
	background-color: whitesmoke;
}

.nav_right {
	display: flex;
	margin-left: 200px;
}

.logo {
	cursor: pointer;
	height: 40px;
	margin-top: 10px;
}

.navbar_user {
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 100%;
}

.nav_bar {
	list-style: none;
	margin-right: 45px;
	display: flex;
	align-items: center;
	margin: 0;
	padding: 0;
	margin-left: auto;
	margin-right: 0;
	/* padding: 24px 0; */
}

.nav_bar li {
	/* display: inline-block; */
	padding: 0px 16px;
}

.nav_right {
	display: flex;
	align-items: center;
	gap: 24px; /* Adjusts spacing between nav items and icons */
}

.nav_bar {
	display: flex;
	list-style: none;
	gap: 24px; /* Adjust spacing between menu items */
	margin: 0;
	padding: 0;
}

.nav_bar li a {
	transition: all 0.3s ease 0s;
}

.nav_bar li a:hover {
	color: #272728;
}

.navbar-icon {
	margin: 0 16px; /* Adjusts space between icons */
	color: #9BA4B4; /* Matches the icon color to text */
}

.navbar-icon:hover {
	color: #272728; /* Darkens the icon color on hover */
}

.navbar {
	display: flex;
	align-items: center;
	padding: 0 20px;
	transition: all 0.3s ease 0s;
}
</style>

<body>
	<header>
		<section class="navbar_user">
			<div class="navbar_logo">
				<img class="logo"
					src="${pageContext.request.contextPath}/stylesheets/images/logo (1).png"
					alt="logo">
			</div>
			<div class="nav_right">
				<nav>
					<ul class="nav_bar">
						<li><a
							href="${pageContext.request.contextPath}/pages/home.jsp">HOME</a></li>
						<li><a
							href="${pageContext.request.contextPath}/ShowProductsServlet">SHOP</a></li>
						<li><a
							href="${pageContext.request.contextPath}/pages/aboutus.jsp">ABOUT
								US</a></li>
					</ul>
				</nav>
				<div class="icons">
					<% if (currentUser != null) { %>
					<a href="<%= request.getContextPath() %>/UserLogoutServlet"
						class="navbar-icon"> Logout <i
						class="fa-solid fa-right-from-bracket"></i>
					</a> <a href="${pageContext.request.contextPath}/UserProfileServlet"
						class="navbar-icon"> <i class="fa-solid fa-user"></i>
					</a> <a href="#" class="navbar-icon"> <i
						class="fa-solid fa-cart-shopping"></i>
					</a>

					<% } else { %>
					<a href="${pageContext.request.contextPath}/pages/login.jsp"
						class="navbar-icon"> Login <i class="fa-solid fa-user"></i>
					</a> <a href="${pageContext.request.contextPath}/pages/signup.jsp"
						class="navbar-icon"> Sign Up <i class="fa-solid fa-user-plus"></i>
					</a>
					<% } %>

				</div>
			</div>
		</section>
	</header>

</body>
</html>
