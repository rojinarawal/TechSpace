<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.database.DatabaseController, model.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String productIdStr = request.getParameter("productId"); // Get the product ID as a string from request
    DatabaseController dbController = new DatabaseController();
    Product product = null;

    try {
        int productId = Integer.parseInt(productIdStr); // Convert string to integer
        product = dbController.getProductById(productId); // Fetch product using the numeric ID
    } catch (NumberFormatException e) {
        // Handle exception for invalid integer conversion
        e.printStackTrace(); // Log the error or handle it as necessary
    }

    if (product != null) {
        request.setAttribute("product", product);
    } else {
        // Handle the case where product is null (not found or invalid productId)
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/productdec.css">
    <title>Product Description</title>
</head>
	<jsp:include page="header.jsp" />
<body class="productdescription">
    <section class="productdesc_image">
        <!-- Assuming images are stored under a specific path and accessible via a context path -->
        <img src="${pageContext.request.contextPath}/stylesheets/images/${product.imageUrlFromPart}" alt="${product.productname}" class="productdesc_product_image">
    </section>
    <section class="productdesc_info">
        <div class="productdesc_info_heading">
            <h3 class="productdesc_topheading">NEW COLLECTION</h3>
            <h1 class="productdesc_productname">${product.productname}</h1>
        </div>

        <div class="productdesc_price_quantity">
            <div class="productdesc_pq">
                <span class="productdesc_topic_headings">PRICE</span>
                <span class="productdesc_price_amount">Rs ${product.price}</span>
            </div>
            <div class="productdesc_pq">
                <span class="productdesc_topic_headings">QUANTITY</span>
                <!-- Assuming there's a way to define quantity, otherwise this needs to be adjusted -->
                <div class="productdesc_quantity_selector">
                    <button class="productdesc_quantity_btn_decrease">-</button>
                    <input type="text" class="productdesc_quantity_input" value="1">
                    <button class="product_desc_quantity_btn_increase">+</button>
                </div>
            </div>
        </div>
        <div class="productdesc_description">
            <span class="productdesc_topic_headings">DESCRIPTION</span>
            <p>${product.description}</p>
        </div>
        <div class="productdesc_totalprice">
            <span class="productdesc_topic_headings">TOTAL PRICE</span>
            <span class="productdesc_totalprice_amount">Rs ${product.price}</span>
        </div>
        <div class="productdesc_all_buttons">
        <form action="/TechSpace/ViewCartItems" method="post">
							<input type="hidden" name="productID"
								value="${product.productID}" >
								<input type="hidden" name="quantity"
								value= "1" >
								<button type="submit" class="productdesc_button">ADD TO CART</button>
					</form>
            
            <button class="productdesc_button">BUY NOW</button>
        </div>
    </section>
</body>
</html>
