<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard Design</title>
<script src="https://kit.fontawesome.com/8abb2279ae.js" crossorigin="anonymous"></script>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/stylesheets/dashboard.css">
</head>
<body>
    <section>
        <div class="sidebar">
            <div class="menu">
                <img class="logo" src="${pageContext.request.contextPath}/stylesheets/images/logo (1).png" alt="logo">
                <div class="horizontal_line"></div>
                <div class="container">
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/pages/orderlist.jsp">
                                <img src="${pageContext.request.contextPath}/stylesheets/images/order.svg" alt="search icon"> <!-- Replace with your SVG file -->

                                <span>Order List</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/pages/productlist.jsp">
                                <img src="${pageContext.request.contextPath}/stylesheets/images/productlist.svg" alt="product">
                                <span>Product List</span>
                            </a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath() %>/AdminLogoutServlet">
                                <img src="${pageContext.request.contextPath}/stylesheets/images/settings.svg" alt="product">
                                <span>Logout</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>



    </section>
</body>
</html>