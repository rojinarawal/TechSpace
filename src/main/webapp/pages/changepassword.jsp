<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ChangePassword</title>
<link rel="stylesheet" href="../stylesheets/changepassword.css">
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
			<section class="password_settings_content">
				<section class="pssetting_outersection">
					<section class="password_settings">
						<div class="pssettingheader">
							<h1 class="ps_header">Password Settings</h1>
							<hr class="ps_line">
						</div>
						<div class="ps_info">
							<form action="#">
								<div class="ps_info_one">
									<div class="input-box">
										<span class="details">Current Password</span> <input
											type="text" name="" placeholder="Enter your password"
											required>
									</div>
									<div class="input-box">
										<span class="details">New Password</span> <input type="text"
											name="" placeholder="Enter your new password" required>
									</div>
									<div class="input-box">
										<span class="details">Retype Password</span> <input
											type="text" name="" placeholder="Confirm your new password"
											required>
									</div>
								</div>
							</form>
						</div>
					</section>
				</section>
				<section class="buttons">
					<button class="change-button">CHANGE</button>
					<button class="cancel-button">CANCEL</button>
				</section>
			</section>
		</section>
	</section>

</body>
</html>