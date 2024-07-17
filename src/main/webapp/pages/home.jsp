<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tech Space</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/home.css">

</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="main-content">
		<img
			src="${pageContext.request.contextPath}/stylesheets/images/image 2.png"
			alt="AirPods Max">
		<div class="product-info">
			<h1 class="product-title">
				Air </br> Pods </br> Max
			</h1>
			<p class="product-description">
				Meet AirPods Max: The epitome of</br> immersive sound and luxury design.</br>
				With custom drivers, active noise</br> cancellation,and seamless Apple </br>integration,
				elevate your audio experience</br> to new heights.
			</p>
			<a href="${pageContext.request.contextPath}/ShowProductsServlet"
				class="shop-now-btn">Shop Now</a>

		</div>
		<img
			src="${pageContext.request.contextPath}/stylesheets/images/image 3.png"
			class="nd-img" alt="AirPods Max">
	</div>
	</section>
	<section class="category-section">
		<h2>SHOP BY CATEGORY</h2>
		<div class="categories">
			<div class="category">
				<a
					href="${pageContext.request.contextPath}/ShowProductsServlet?category=smartphone">
					<img
					src="${pageContext.request.contextPath}/stylesheets/images/Frame 703.png"
					alt="Smartphones & Tablets">
				</a>
			</div>
			<div class="category">
				<a
					href="${pageContext.request.contextPath}/ShowProductsServlet?category=wearable">
					<img
					src="${pageContext.request.contextPath}/stylesheets/images/Frame 705.png"
					alt="Wearable Technology">
				</a>
			</div>
			<div class="category">
				<a
					href="${pageContext.request.contextPath}/ShowProductsServlet?category=laptops">
					<img
					src="${pageContext.request.contextPath}/stylesheets/images/Frame 704.png"
					alt="Laptops & Computers">
				</a>
			</div>
			<div class="category">
				<a
					href="${pageContext.request.contextPath}/ShowProductsServlet?category=audio">
					<img
					src="${pageContext.request.contextPath}/stylesheets/images/Frame 706.png"
					alt="Audio Devices">
				</a>
			</div>
			<div class="category">
				<a
					href="${pageContext.request.contextPath}/ShowProductsServlet?category=gamming">
					<img
					src="${pageContext.request.contextPath}/stylesheets/images/Frame 707.png"
					alt="Gaming">
				</a>
			</div>
			<div class="category">
				<a
					href="${pageContext.request.contextPath}/ShowProductsServlet?category=Home appliance">
					<img
					src="${pageContext.request.contextPath}/stylesheets/images/Frame 708.png"
					alt="Home Appliances">
				</a>
			</div>
		</div>
	</section>
	<section class="about-section">
		<div class="images-wrapper">

			<img
				src="${pageContext.request.contextPath}/stylesheets/images/Frame 734.png"
				alt=""> <img
				src="${pageContext.request.contextPath}/stylesheets/images/Frame 730.png"
				alt=""> <img
				src="${pageContext.request.contextPath}/stylesheets/images 733.png"
				alt="">

		</div>
		<div class="about-content">
			<h2>ABOUT US</h2>
			<p>Welcome to TechSpace, your premier destination for all things
				tech and electronics. Founded with a passion for innovation and a
				commitment to exceptional customer service, we strive to provide you
				with the latest gadgets, electronics, and accessories to enhance
				your digital lifestyle. From smartphones to smart home devices, our
				curated selection offers quality products from top brands at
				competitive prices.</p>
			<a class="know-more-btn"
				href="${pageContext.request.contextPath}/pages/aboutus.jsp">Know
				More</a>
		</div>
	</section>

	</section>
	<section class="service-highlights-container">
		<h2 class="service-highlights-title">
			Shop With Confidence </br>On The TechSpace
		</h2>
		<div class="service-highlights">
			<div class="service">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/Frame 745 (2).png"
					alt="Free Shipping">
				<h3>Free Shipping</h3>
				<p>Enjoy free shipping on all TechSpace orders. Shop worry-free
					and let us cover the shipping fees.</p>
			</div>
			<div class="service">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/Frame 745 (1).png"
					alt="24/7 Support Services">
				<h3>24/7 Support Services</h3>
				<p>At TechSpace, customer satisfaction is key. Get 24/7 support
					for all your questions and concerns, day or night.</p>
			</div>
			<div class="service">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/Frame 745.png"
					alt="Returns and Exchanges">
				<h3>Returns and Exchanges</h3>
				<p>Not happy with your purchase? No worries! TechSpace offers
					hassle-free returns. Return your order within two days for a full
					refund or exchange.</p>
			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp" />

</body>
</html>