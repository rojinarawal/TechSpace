<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Products List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/productlists.css">
</head>
<div class="container1">
    
<body>
    <section>
        <div class="sidebar">
            <div class="menu">
                <img class="logo" src="${pageContext.request.contextPath}/stylesheets/images/logo (1).png" alt="logo">
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
                                <img src="${pageContext.request.contextPath}/stylesheets/images/icons8-dashboard-24.png" alt="search icon"> 
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img src="${pageContext.request.contextPath}/stylesheets/images/order.svg" alt="search icon"> 

                                <span>Order List</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img src="${pageContext.request.contextPath}/stylesheets/images/productlist.svg" alt="product">
                                <span>Product List</span>
                            </a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath() %>/AdminLogoutServlet"> 
                                <span>Log Out</span>
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>

    </section>
    <div class="main">
        <h1>Products List</h1>
        
        <div class="product-header">
            <span class="ram">380 Products Found</span>
            <button id="openModalBtn" class="add-product-btn">Add Product +</button>
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
						<td>
							<form id="deleteForm-${product.productID}" method="post"
								action="${pageContext.request.contextPath}/DeleteProduct">
								<input type="hidden" name="deleteId"
									value="${product.productID}">
								<button type="button" class="delete-button"
									onclick="confirmDelete('${product.productID}')">
									<img
										src="${pageContext.request.contextPath}/stylesheets/images/trash-2.png"
										alt="delete">
								</button>
							</form>
							
							<a href="${pageContext.request.contextPath}/UpdateProduct?productID=${product.productID}">update</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
        <div class="numbers">
            
            <span style="color:#9BA4B4;">Showing 7 of 380 Products</span>
            <div class="numbers-controls">
                <a href="#">&lt;</a>
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">&gt;</a>
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
					<label for="productId">Product ID</label> 
					<input type="text" id="productId" name="productId" readonly>
				</div>
				<div class="form-group">
					<label for="productName">Product Name</label> <input type="text"
						id="productName" name="product_name"
						placeholder="Enter Product Name">
				</div>
				<div class="form-group">
					<label for="category">Category</label> <select id="category"
						name="category">
						<option value="">Select Category</option>
						<option value="smartphones">Smartphones & Tablets</option>
						<option value="wearable">Wearable Technology</option>
						<option value="laptops">Laptops & Computers</option>
						<option value="audio">Audio Devices</option>
						<option value="Gaming">Gaming</option>
						<option value="Home_appliances">Home Appliances</option>
					</select>
				</div>
				<div class="form-group">
					<label for="stock">Stock</label> <input type="text" id="stock"
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
					<label for="imageUpload">Product Image</label> 
					<input type="file" id="imageUpload" name="image" accept="image/*">
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
	
		function confirmDelete(productID) {
			if (confirm("Are you sure you want to delete this product: "
					+ productID + "?")) {
				document.getElementById("deleteForm-" + productID).submit();
			}
		}
	  
		</script>
</body>
</html>
