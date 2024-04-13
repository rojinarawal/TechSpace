<%@page import= "util.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="en" dir="ltr">

    <head>
        <meta charset="UTF-8">
        <title> Register</title>
      	 <link rel="stylesheet" type="text/css"
      	 	href="/TechSpace/stylesheets/signup.css" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <section>
         <div class="heading"><p> TechSpace</p></div>   
        
        <div class="container">
            <div class="title">Sign Up</div>
            <div class="content">
                <form action="/TechSpace/RegisterServlet" method="post">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">First Name</span>
                            <input type="text" placeholder="First Name" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Last Name</span>
                            <input type="text" placeholder="Last Name" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Username</span>
                            <input type="text" placeholder="Username" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Email</span>
                            <input type="text" placeholder="example@gmail.com" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Address</span>
                            <input type="text" placeholder="Address" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Phone Number</span>
                            <input type="text" placeholder="Number" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Password</span>
                            <input type="text" placeholder="Password" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Confirm Password</span>
                            <input type="text" placeholder="Confirm Password" required>
                        </div>
                    </div>
                    
                    <div class="button">
                        <input type="submit" value="Sign Up">
                    </div>
                </form>
                <div class="login-link">
                    <p>Already have an account? <a href="#">Sign in</a></p>
                </div>
               </div>
        
        </div>

    </section>
    <div class="image-container">
        <img src="/stylesheets/images/wishlist.jpeg" alt="TechSpace">
        </div>
    

    </body>

    </html>