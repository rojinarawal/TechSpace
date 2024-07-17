<%@ page import="controller.database.DatabaseController, java.util.ArrayList, model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/product4.css">
</head>
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
						<li class="dropdown"><a
							href="${pageContext.request.contextPath}/pages/product.jsp">SHOP
								<i class="fa-solid fa-chevron-down"></i>
						</a></li>
						<li><a
							href="${pageContext.request.contextPath}/pages/aboutus.jsp">ABOUT
								US</a></li>
					</ul>
				</nav>
				<div class="icons">
					<nav class="navbar">
						<a href="${pageContext.request.contextPath}/pages/userprofile.jsp"
							class="navbar-icon"> <i class="fa-regular fa-user"></i>
						</a> <a href="#" class="navbar-icon" style="margin: 0 16px;"> <i
							class="fa-solid fa-cart-shopping"></i>
						</a>
						<!-- Add this form within your existing HTML where the search icon is -->
						<form id="searchForm" action="/TechSpace/SearchServlet"
							method="get" class="search-form">
							<input type="text" name="query" placeholder="Search " required>
							<button type="submit">
								<i class="fa-solid fa-magnifying-glass"></i>
							</button>
						</form>

					</nav>
				</div>
			</div>
		</section>


	</header>
	<!-- Main content and other sections... -->
	<div class="main-content">
		<div class="product-info">
			<h1 class="product-title">Iphone 15 Pro Max</h1>
			<p class="product-description">
				Meet AirPods Max: The epitome of</br> immersive sound and luxury design.</br>
				With custom drivers, active noise</br> cancellation,and seamless Apple </br>integration,
				elevate your audio experience</br> to new heights.
			</p>
			<button class="shop-now-btn">Shop Now</button>

		</div>
		<img
			src="${pageContext.request.contextPath}/stylesheets/images/image_77.jpg"
			alt="Samsung Galaxy S23 Ultra">
	</div>
	<div class="navbar">
		<a href="#!" data-category="smartphone">Smartphones & Tablets</a> <a
			href="#!" data-category="wearable">Wearable Technology</a> <a
			href="#!" data-category="laptops">Laptops & Computers</a> <a
			href="#!" data-category="audio">Audio Devices</a> <a href="#!"
			data-category="gamming">Gamming</a> <a href="#!" data-category="home">Home
			Appliances</a>
	</div>
	<div id="all-products" class="product-grid">
		<c:forEach var="product" items="${productList}">
			<div class="product" data-category="${product.category}">
				<div class="product-card">
					<img
						src="${pageContext.request.contextPath}/stylesheets/images/${product.imageUrlFromPart}"
						alt="${product.productname}">
					<button class="add-to-cart-btn">Add to Cart</button>
				</div>
				<h3>${product.productname}</h3>
				<p class="price">Rs. ${product.price}</p>
				<a
					href="${pageContext.request.contextPath}/pages/productdescp.jsp?productId=${product.productID}"
					class="view-desc-btn">View Description</a>
			</div>
		</c:forEach>
	</div>



	<a href="#!" class="back-to-top"> Back to Top</a>
	<jsp:include page="footer.jsp" />
	<script>
 // Get URL query parameter
    function getCategoryFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('category');
    }

    // Filter products based on the category
    function filterProducts(category) {
        const products = document.querySelectorAll('#all-products .product');
        products.forEach(product => {
            if (product.getAttribute('data-category') === category || !category) {
                product.style.display = ''; // Show the product
            } else {
                product.style.display = 'none'; // Hide the product
            }
        });
    }

    // Set up event listeners and filter on page load
    document.addEventListener("DOMContentLoaded", () => {
        const categoryFromURL = getCategoryFromURL();
        filterProducts(categoryFromURL);

        const navbarLinks = document.querySelectorAll('.navbar a');
        navbarLinks.forEach(link => {
            link.addEventListener('click', function(e) {
                e.preventDefault();
                const category = this.getAttribute('data-category');
                filterProducts(category);
                updateActiveClass(navbarLinks, this);
            });
        });

        function updateActiveClass(links, activeLink) {
            links.forEach(link => {
                link.classList.remove('active');
            });
            activeLink.classList.add('active');
        }
    });

        document.addEventListener("DOMContentLoaded", function() {
  const backToTopButton = document.querySelector('.back-to-top');

  // Function to scroll to the top of the page
  backToTopButton.addEventListener('click', function() {
    window.scrollTo({
      top: 0,
      behavior: 'smooth' // Smooth scroll
    });
  });
});

    </script>
</body>
</html>