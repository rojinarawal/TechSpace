<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html lang="en">
<%String contextPath=request.getContextPath(); %>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Products List</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/productlist.css">

</head>
<body>
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
			<h1>Products List</h1>

			<div class="product-header">
				<span class="ram">380 Products Found</span>
				<button id="openModalBtn" class="add-product-btn">Add
					Product +</button>
			</div>
			<table class="product-table">
				<thead>
					<tr>
						<th>Product ID</th>
						<th>Product Name</th>
						<th>Category</th>
						<th>Price</th>
						<th>Stock</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${productList}">
						<tr>
							<td>${product.productID}</td>
							<td>${product.productname}</td>
							<td>${product.category}</td>
							<td>${product.price}</td>
							<td>${product.stock}</td>
							<td><img src="<%=contextPath %>/stylesheets/images/edit.png"
								alt="update"> | <img
								src="<%=contextPath %>/stylesheets/images/trash-2.png"
								alt="delete"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="numbers">

				<span>Showing 7 of 380 Products</span>
				<div class="numbers-controls">
					<a href="#">&lt;</a> <a href="#">1</a> <a href="#">2</a> <a
						href="#">3</a> <a href="#">4</a> <a href="#">&gt;</a>
				</div>
			</div>
		</div>
	</div>

	<div class="modal-overlay" id="addProductModal">
		<div class="modal">
			<div class="modal-header">
				<h2>Add Product</h2>
				<button class="close-modal" onclick="closeModal()">×</button>
			</div>
			<div class="modal-content">
				<form id="addProductForm" action="/TechSpace/AddProduct"
					method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="productId">Product ID</label> <input type="text"
							id="productId" name="productId"
							placeholder="Enter the Product ID">
					</div>
					<div class="form-group">
						<label for="productName">Product Name</label> <input type="text"
							id="productName" name="productName"
							placeholder="Enter Product Name">
					</div>
					<div class="form-group">
						<label for="category">Category</label> <select id="category"
							name="category">
							<!-- Add name="category" here -->	
							<option value="">Select Category</option>	
							<option>Smartphones & Tablets</option>
							<option>Wearable Technology</option>
							<option>Laptops & Computers</option>
							<option>Audio Devices</option>
							<option>Gaming</option>
							<option>Home Appliances</option>
						</select>
					</div>
					<div class="form-group">
						<label for="stock">Stock</label> <input type="number" id="stock"
							name="stock" placeholder="Add Stock">
					</div>
					<div class="form-group">
						<label for="price">Price</label> <input type="text" id="price"
							name="price" placeholder="Enter Price">
					</div>
					<div class="form-group">
						<label for="shortDescription">Product Description</label>
						<textarea id="shortDescription" name="description"
							placeholder="Enter  description here..."></textarea>
					</div>
					<div class="form-group">
						<label for="imageUpload">Product Image</label> <input type="file" 
							name="image" id="imageUpload">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Add Product</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
    function closeModal() {
        document.getElementById('addProductModal').style.display = 'none';
    }
    
    document.getElementById('openModalBtn').onclick = function() {
        document.getElementById('addProductModal').style.display = 'flex';
    };
    
    window.onclick = function(event) {
        var modal = document.getElementById('addProductModal');
        if (event.target === modal) {
            closeModal();
        }
    };
    </script>
</body>
</html>
