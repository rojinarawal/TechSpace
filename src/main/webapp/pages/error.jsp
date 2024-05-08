<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 40px;
        color: #333;
    }
    .container {
        background-color: #fff;
        border: 1px solid #ddd;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h1 {
        color: #d9534f;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Error</h1>
        <p>Sorry, there was a problem processing your request.</p>
        <p><strong>Message:</strong> ${request.getAttribute("error") != null ? request.getAttribute("error") : "An unknown error occurred."}</p>
        <p><a href="${pageContext.request.contextPath}/pages/home.jsp">Return Home</a></p>
    </div>
</body>
</html>
