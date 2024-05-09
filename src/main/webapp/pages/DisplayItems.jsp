<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="util.StringUtils"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Insert title here</title>

<link rel="stylesheet"

	href="${pageContext.request.contextPath}/stylesheets/cart.css">

</head>

<body>

  <table>

        <tr>

            <th>productId</th>

            <th>product_name</th>

            <th>description</th>

            <th>category</th>

            <th>price</th>

            <th>stock</th>

            <th>image</th>

        </tr>

        <% ResultSet rs = (ResultSet) request.getAttribute("productList"); %>

        <% while (rs.next()) { %>

            <tr>

                <td><%= rs.getInt(1) %></td>

                <td><%= rs.getString(2) %></td>

                <td><%= rs.getString(3) %></td>

                <td><%= rs.getString(4) %></td>

                <td><%= rs.getInt(5) %></td>

                <td><%= rs.getInt(6) %></td>

                <td><%= rs.getString(7) %></td>

                <td><a href='DisplayItems?Items?ItemId=<%= rs.getInt(1) %>'>Add To Cart</a></td>

            </tr>

        <% } %>

    </table>

</body>

</html>