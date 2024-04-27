<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<%String contextPath=request.getContextPath(); %>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard Design</title>
<link rel="stylesheet"
	href="<%=contextPath %>/stylesheets/dashboard.css">
<script src="https://kit.fontawesome.com/8abb2279ae.js"
	crossorigin="anonymous"></script>

</head>

<body>
	<section>
		<div class="sidebar">
			<div class="menu">
				<img class="logo"
					src="<%=contextPath %>/stylesheets/images/logo (1).png" alt="logo">
				<div class="input-box">
					<input type="text" placeholder="Search" class="input-field"
						autocomplete="off">
					<div class="icon">
						<i class="fa-solid fa-magnifying-glass" aria-hidden="true"></i>
					</div>
				</div>
				<div class="horizontal_line"></div>
				<div class="container">
					<ul>
						<li class="active"><a href="#"> <img
								src="<%=contextPath %>/stylesheets/images/icons8-dashboard-24.png"
								alt="search icon"> <!-- Replace with your SVG file --> <span>Dashboard</span>
						</a></li>
						<li><a href=""> <img
								src="<%=contextPath %>/stylesheets/images/order.svg"
								alt="search icon"> <!-- Replace with your SVG file --> <span>Order
									List</span>
						</a></li>
						<li><a
							href="${pageContext.request.contextPath}/pages/productlist.jsp">
								<img src="<%=contextPath%>/stylesheets/images/productlist.svg"
								alt="product"> <span>Product List</span>
						</a></li>
						<li><a
							href="<%= request.getContextPath() %>/AdminLogoutServlet"> <%-- 	<a href="#"> <img src="<%=contextPath %>/stylesheets/images/message.svg" alt="message"> --%>
								<!-- Replace with your SVG file --> <span>Log Out</span>
						</a></li>
						<li><a href="#"> <img
								src="<%=contextPath %>/stylesheets/images/settings.svg"
								alt="settings"> <!-- Replace with your SVG file --> <span>Settings</span>
						</a></li>
					</ul>
				</div>

			</div>
		</div>

	</section>
	<section class="main-dashboard">
		<div class="dash-content">
			<h1>Overview</h1>
			<div class="overview">

				<div class="heading">
					<span class="subheading">Analytics</span>
					<button class="btn">
						This Month <i class="fa-solid fa-chevron-down"></i>
					</button>

				</div>
				<div class="boxes">
					<div class="box box1">
						<span class="text">Total Sales</span> <br /> <span class="number">2,230
							<span class="percentage">+7%</span>
						</span>
					</div>
					<div class="box box2">
						<span class="text">Total Orders</span> <br /> <span
							class="number">1,672 <span class="percentage">+6.1%</span></span>
					</div>
					<div class="box box3">
						<span class="text">New Customers</span> <br /> <span
							class="number">2,084 <span class="percentage">+4%</span></span>
					</div>
					<div class="box box4">
						<span class="text">Revenue Generated</span> <br /> <span
							class="number">3,426 <span class="percentage">+1.4%</span></span>
					</div>
				</div>
			</div>
			<div class="heading1">
				<span class="subheading">Latest Order</span>
				<button class="btn">View all Orders</button>

			</div>
			<div class="activity">
				<table class="activity-table">
					<thead>
						<tr>
							<th>Order ID</th>
							<th>Order Date</th>
							<th>Customer</th>
							<th>Cost</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>MN_001</td>
							<td>10-04-2024</td>
							<td>Emily Carter</td>
							<td>Rs 3,289</td>
							<td>Completed</td>
						</tr>
						<tr>
							<td>MN_002</td>
							<td>13-04-2024</td>
							<td>Lucas Bennett</td>
							<td>Rs 1,700</td>
							<td>Pending</td>
						</tr>
						<tr>
							<td>MN_003</td>
							<td>15-04-2024</td>
							<td>Oliver Thompson</td>
							<td>Rs 4,500</td>
							<td>Cancelled</td>
						</tr>
						<tr>
							<td>MN_004</td>
							<td>17-04-2024</td>
							<td>Liam Rodriguez</td>
							<td>Rs 2,200</td>
							<td>Completed</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>

</body>

</html>