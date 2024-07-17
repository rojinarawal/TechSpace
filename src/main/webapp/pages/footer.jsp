<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Footer</title>
<script src="https://kit.fontawesome.com/1165876da6.js"
	crossorigin="anonymous"></script>
</head>
<style>
* {
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #394867;
	text-decoration: none;
}

footer {
	background-color: #fcfcfc;
	width: 100%;
	bottom: 0;
}

.row {
	display: flex;
	flex-direction: row;
	max-width: 90%;
	margin-left: auto;
	margin-right: auto;
	padding: 20px;
}

.column1 {
	flex-basis: 40%;
}

.column1 logo {
	justify-content: space-around;
	margin-bottom: 24px;
}

.column1 p {
	width: 70%;
	margin-top: 24px;
}

.column2, .column3, .column4 {
	flex-basis: 20%;
}

.column2 h4 a {
	margin-bottom: 24px;
	list_style: none;
}

.column3 h4 {
	margin-bottom: 24px;
}

.column4 h4 {
	margin-bottom: 24px;
}

.logo {
	margin-bottom: 24px;
	justify-content: space-around;
}

h4 {
	font-size: 14px;
	margin-bottom: 24px;
	text-align: left;
	position: relative;
}

.bottom-bar {
	background: #14274e;
	text-align: center;
	padding: 10px 0;
	margin-top: 50px;
}

.bottom-bar p {
	color: #ffffff;
	margin: 0;
	font-size: 16px;
	padding: 7px;
}

form {
	padding-bottom: 15px;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

form input {
	border-radius: 6px;
	padding: 12px;
	width: 230px;
	height: 20px;
	margin-right: 10px;
	border: 1px solid #151515;
}

form button {
	background-color: #14274e;
	padding: 12px;
	border-radius: 6px;
	cursor: pointer;
	width: 120px;
	display: flex;
	justify-content: center;
	align-items: center;
}

form button p {
	font-size: 14px;
	color: #fff;
	margin: 0;
}

.links {
	display: flex;
	flex-direction: column;
}

.links p {
	padding-bottom: 5px;
}

.social-icons {
	display: flex;
	flex-direction: row;
}

.social-icons li {
	margin-right: 8px;
	list-style-type: none;
}
</style>
<body>
	<footer>
		<div class="row">
			<div class="column1">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/logo (1).png" />
				<p>
					Your premier destination for discovering,acquiring, and
					experiencing the latest in electronics and gadgets, all curated to
					enhance your <br> digital lifestyle.
				</p>
			</div>
			<div class="column2">
				<h4>
					<a class="jer"
						href="${pageContext.request.contextPath}/pages/home.jsp">HOME</a>
				</h4>
				<div class="links">
					<p>
						<a href="${pageContext.request.contextPath}/pages/product.jsp">Products</a>
					<p>
					<p>
						<a href="${pageContext.request.contextPath}/pages/aboutus.jsp">About
							Us</a>
					</p>
					<p>
						<a href="${pageContext.request.contextPath}/pages/product.jsp">Contact
							Us</a>
					</p>
					<p>
						<a href="${pageContext.request.contextPath}/pages/userprofile.jsp">My
							Profile</a>
					</p>
				</div>
			</div>
			<div class="column3">
				<h4>COMPANY</h4>
				<div class="links">
					<p>Terms & Condition</p>
					<p>Privacy Policy</p>
				</div>
			</div>
			<div class="column4">
				<h4>SIGN UP TO OUR NEWSLETTER</h4>
				<form>
					<input type="email" placeholder="E-mail" required />
					<button type="submit" class="btn">
						<p>SUBSCRIBE</p>
					</button>
				</form>

				<ul class="social-icons">
					<li><a href=""><i
							class="fab fa-facebook" style="color: #14274e"></i></a></li>
					<li><a href=""><i
							class="fab fa-instagram" style="color: #14274e"></i></a></li>
					<li><a href=""><i class="fab fa-twitter"
							style="color: #14274e"></i></a></li>
				</ul>
			</div>
		</div>
		<div class="bottom-bar">
			<p>Copyright &copy; 2024 TechSpace. All rights reserved</p>
		</div>
	</footer>
</body>
</html>