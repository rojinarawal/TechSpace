<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>OrderList</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/orderlist.css">
<!-- <script src="https://kit.fontawesome.com/8abb2279ae.js" crossorigin="anonymous"></script> -->

</head>
<body>
	<!-- <section>
        <div class="sidebar">
            <div class="menu">
                <img class="logo" src="../images/logo.png" alt="logo">
                <div class="input-box">
                    <input type="text" placeholder="Search" class="input-field" autocomplete="off">
                    <div class="icon">
                        <i class="fa-solid fa-magnifying-glass" aria-hidden="true"></i>
                    </div>
                </div>
                <div class="horizontal_line"></div>
                <div class="container">
                    <ul>
                        <li class="active">
                            <a href="#">
                                <img src="../images/dashboard.png" alt="search icon"> 
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img src="../images/order.png" alt="search icon"> 
                                <span>Order List</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img src="../images/productlist.png" alt="product">
                                <span>Product List</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img src="../images/message.png" alt="message"> 
                                <span>Message</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img src="../images/settings.png" alt="settings"> 
                                <span>Settings</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section> -->

	<div class="container">
		<div class="sidebar">

			<div class="logo">TECH SPACE</div>
			<div class="menu">
				<input type="search" placeholder="Search" /> <a href="#">Dashboard</a>
				<a href="#">Order List</a> <a href="#" class="active">Product
					List</a> <a href="#">Messages</a> <a href="#">Settings</a>
			</div>
		</div>

		<div class="main">
			<h1>Order</h1>

			<div class="order-header">
				<span class="orderfound">15 Orders Found</span>
				<h2>All Orders</h2>
			</div>

			<table class="orderhistory-table">
				<thead>
					<tr>
						<th>Order ID</th>
						<th>Order Date</th>
						<th>Customer</th>
						<th>Product</th>
						<th>Quantity</th>
						<th>Cost</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>MM01</td>
						<td>04-20-2024</td>
						<td>John Doe</td>
						<td>Macbook Air</td>
						<td>2</td>
						<td>Rs 120,000</td>
						<td class="status-delivered">Delivered</td>
					</tr>
					<tr>
						<td>MM02</td>
						<td>04-25-2024</td>
						<td>Jane Smith</td>
						<td>Smartwatch</td>
						<td>1</td>
						<td>Rs 50,000</td>
						<td class="status-pending">Pending</td>
					</tr>

					<tr>
						<td>MM03</td>
						<td>04-28-2024</td>
						<td>Robert Johnson</td>
						<td>Google Pixel 6</td>
						<td>1</td>
						<td>Rs 60,000</td>
						<td class="status-cancelled">Cancelled</td>
					</tr>

					<tr>
						<td>MM04</td>
						<td>05-02-2024</td>
						<td>Emily Brown</td>
						<td>Apple Watch Series 7</td>
						<td>1</td>
						<td>Rs 45,000</td>
						<td class="status-delivered">Delivered</td>
					</tr>

					<tr>
						<td>MM05</td>
						<td>05-05-2024</td>
						<td>Michael Taylor</td>
						<td>Samsung Galaxy Z Fold 3</td>
						<td>1</td>
						<td>Rs 150,000</td>
						<td class="status-pending">Pending</td>
					</tr>

					<tr>
						<td>MM06</td>
						<td>05-08-2024</td>
						<td>Sophia Wilson</td>
						<td>Microsoft Surface Laptop 4</td>
						<td>1</td>
						<td>Rs 100,000</td>
						<td class="status-cancelled">Cancelled</td>
					</tr>

					<tr>
						<td>MM07</td>
						<td>05-10-2024</td>
						<td>Oliver Anderson</td>
						<td>PlayStation 5</td>
						<td>1</td>
						<td>Rs 55,000</td>
						<td class="status-delivered">Delivered</td>
					</tr>
				</tbody>

			</table>

			<div class="numbers">

				<span>Showing 7 of 15 Orders</span>
				<div class="numbers-controls">
					<a href="#">&lt;</a> <a href="#">1</a> <a href="#">2</a> <a
						href="#">3</a> <a href="#">4</a> <a href="#">&gt;</a>
				</div>
			</div>
		</div>
	</div>

	</main>


</body>
</html>