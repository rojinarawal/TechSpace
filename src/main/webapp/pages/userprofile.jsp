<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>UserProfile</title>
<link rel="stylesheet" href="../stylesheets/userprofile.css">
</head>
<body>
	<section class="user">
		<section class="dashboard">
			<div class="images">
				<img src="../stylesheets/images/profile.png" alt="Profile Picture">
				<h2>Roberto Alexandra</h2>
			</div>

			<div class="options">
				<a href="">My Profile</a> <a href="">Manage My Account</a> <a
					href="">Change Password</a> <a href="">My Orders</a> <a href="">Logout</a>
			</div>

		</section>
		<section class="userrightpart">
			<section class="bgimage">
				<img src="../stylesheets/images/Rectangle 55.png" alt="background">
			</section>
			<section class="account_settings_content">
				<section class="acsetting_outersection">
					<section class="account_settings">
						<div class="accountsettingheader">
							<h1 class="as_header">Account Settings</h1>
							<hr class="as_line">
						</div>
						<div class="as_info">
							<form action="#">
								<div class="as_info_one">
									<div class="input-box">
										<span class="details">First Name</span> <input type="text"
											name="" placeholder="Enter your first name" required>
									</div>
									<div class="input-box">
										<span class="details">Last Name</span> <input type="text"
											name="" placeholder="Enter your last name" required>
									</div>
								</div>
								<div class="as_info_one">
									<div class="input-box">
										<span class="details">User Name</span> <input type="text"
											name="" placeholder="Enter your username " required>
									</div>
									<div class="input-box">
										<span class="details">Email</span> <input type="text" name=""
											placeholder="lux@gmail.com" required pattern=".*@gmail.com">
									</div>
								</div>
								<div class="as_info_one">
									<div class="input-box">
										<span class="details">Address</span> <input type="text"
											name="" placeholder="Your current address" required>
									</div>
									<div class="input-box">
										<span class="details">Phone Number</span> <input type="text"
											name="" placeholder="Your contact number" required
											maxlength="10">
									</div>
								</div>

							</form>
						</div>
					</section>
				</section>
				<section class="buttons">
					<button class="update-button">UPDATE</button>
					<button class="cancel-button">CANCEL</button>
				</section>
			</section>
		</section>
	</section>

</body>
</html>