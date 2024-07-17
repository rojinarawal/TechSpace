<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/orderlist.css">
</head>

<body>

	<jsp:include page="dashboard.jsp" />

	<!-- Main content -->
	<section class="main-dashboard">
		<div class="dash-content">
			<h1>Overview</h1>

			<div class="overview">
				<div class="heading">
					<span class="subheading">Analytics</span>
				</div>

				<div class="boxes">
					<div class="box box1">
						<span class="text">Total Sales</span></br> <span class="number">2,230
						</span>
					</div>
					<div class="box box2">
						<span class="text">Total Orders</span></br> <span class="number">1,672
						</span>
					</div>
					<div class="box box4">
						<span class="text">Total customer</span></br> <span class="number">3,426
						</span>
					</div>
				</div>
			</div>

			<div class="heading1">
				<span class="subheading">Latest Orders</span>
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
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${order.orderID}</td>
							<td contenteditable="false">${order.orderDate}</td>
							<td contenteditable="false">${order.userName}</td>
							<td>Rs ${order.cost}</td>
							<td>
								<form id="status" action="" method="post">
									<input type="hidden" name="orderID" value="${order.orderID}">
									<select name="status" onchange="">
										<option value="Pending">Pending</option>
										<option value="Completed">Completed</option>
										<option value="Cancelled">Cancelled</option>
									</select>
								</form>
							</td>
							<td>
								<button type="submit">Save</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</body>
</html>
