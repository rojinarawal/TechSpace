<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/editproduct.css">
</head>
<body>
	<div class="container1">
		<section>
			<div class="sidebar">
				<div class="menu">
					<img class="logo"
						src="${pageContext.request.contextPath}/stylesheets/images/logo (1).png"
						alt="logo">
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
									src="${pageContext.request.contextPath}/stylesheets/images/icons8-dashboard-24.png"
									alt="search icon"> <span>Dashboard</span>
							</a></li>
							<li><a href="#"> <img
									src="${pageContext.request.contextPath}/stylesheets/images/order.svg"
									alt="search icon"> <span>Order List</span>
							</a></li>
							<li><a href="#"> <img
									src="${pageContext.request.contextPath}/stylesheets/images/productlist.svg"
									alt="product"> <span>Product List</span>
							</a></li>
							<li><a
								href="<%= request.getContextPath() %>/AdminLogoutServlet"> <span>Log
										Out</span>
							</a></li>
						</ul>
					</div>

				</div>
			</div>

		</section>


		<div class="modal-overlay" id="editProductModal">
			<div class="modal">
				<div class="modal-header">
					<h2>Edit Product</h2>
				</div>
				<div class="modal-content">
					<form id="editProductForm" action="/TechSpace/UpdateProduct"
						method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label>Product ID</label> <input type="text" name="productID"
								value="${product.productID}">
						</div>
						<div class="form-group">
							<label>Product Name</label> <input type="text" name="productname"
								value="${product.productname}" required>
						</div>
						<div class="form-group">
							<label>Category</label> <select name="category" required>
								<option value="">Select Category</option>
								<option value="smartphones"
									${product.category eq 'smartphones' ? 'selected' : ''}>Smartphones
									& Tablets</option>
								<option value="wearable"
									${product.category eq 'wearable' ? 'selected' : ''}>Wearable
									Technology</option>
								<option value="laptops"
									${product.category eq 'laptops' ? 'selected' : ''}>Laptops
									& Computers</option>
								<option value="audio"
									${product.category eq 'audio' ? 'selected' : ''}>Audio
									Devices</option>
								<option value="gaming"
									${product.category eq 'gaming' ? 'selected' : ''}>Gaming</option>
								<option value="home_appliances"
									${product.category eq 'home_appliances' ? 'selected' : ''}>Home
									Appliances</option>
							</select>
						</div>

						<div class="form-group">
							<label>Price</label> <input type="text" name="price"
								value="${product.price}" required>
						</div>
						<div class="form-group">
							<label>Stock</label> <input type="text" name="stock"
								value="${product.stock}" required>
						</div>
						<div class="form-group">
							<label>Product Description</label>
							<textarea name="description" required>${product.description}</textarea>
						</div>
						<div class="form-group">
							<label>Product Image</label> <input type="file" name="image">
							<img
								src="${pageContext.request.contextPath}/stylesheets/images/${product.imageUrlFromPart}" alt="Product_image" style="width: 200px; height: 150px;" />
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">Save
								Product</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>