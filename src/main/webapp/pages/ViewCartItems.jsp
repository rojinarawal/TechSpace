<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/cart.css">
</head>
<body>
	<section class="cart">
		<section class="heading">
			<h1 class="mycartheading">MY CART</h1>
		</section>
		<div class="downpart">
			<section class="cartitems">
				<table>
					<tr>
						<th>Product</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Total</th>
						<th>Action</th>
					</tr>
					<c:forEach var="item" items="${cartitems}">
					<tr>
						<td>
							<div class="product_list">
								<img src="${pageContext.request.contextPath}/stylesheets/images/${product.imageUrlFromPart}" alt="product image"
									class="cart_product_image"> <span class="product_name">${item.productName}</span>
							</div>
						</td>
						<td>Rs.${item.price}</td>
						<td>
							<div class="cart_quantity">
								<p"${item.quantity}"></p>
							</div>
						</td>
						<td>Rs.${item.total}</td>
						<td> 
						<form action="/TechSpace/DeleteProduct" method="post">
							<input type="hidden" name="productID"
								value="${item.productID}" >
						<button class="delete" type="submit"> X </button></form>
						
						</td>			
					</tr>
					</c:forEach>
				</table>
			</section>
			<section class="ordersummary">
				<h1 class="ordersummary_heading">Order Summary</h1>
				<div class="cart_total_info">
					<div class="cost">
						<span class="cost_title">Sub Total</span> <span class="cost_price">Rs.72,000</span>
					</div>
					<div class="cost">
						<span class="cost_title">Shipping</span> <span class="cost_price">Rs.500</span>
					</div>
					<div class="cost">
						<span class="total_title">Total</span> <span class="total_title">Rs.72,500</span>
					</div>
				</div>
				<button class="cart_checkout">CHECKOUT</button>
			</section>
		</div>

	</section>

</body>
</html>