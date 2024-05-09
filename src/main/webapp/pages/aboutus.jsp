<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/aboutus.css">
<title>About Us</title>
</head>

<jsp:include page="header.jsp" />
<body class="aboutus">
	<section class="aboutus_herosection">
		<div class="aboutus_herosection_leftpart">
			<div class="solidcontainer"></div>
			<div>
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/aboutus.jpg"
					alt="" class="aboutus_herosection_image">
			</div>
		</div>
		<div class="aboutus_herosection_rightpart">
			<h1 class="ourstory_heading">OUR STORY</h1>
			<P class="ourstory_paragraph">At TechSpace, our story is simple
				yet profound. Fuelled by a passion for innovation, we embarked on a
				journey in 2024 to redefine the tech industry. Today, we stand as a
				beacon for cutting-edge gadgets and electronics, fostering a
				community of tech enthusiasts who share our vision for a digitally
				empowered future. Welcome to TechSpace, where innovation thrives and
				possibilities are limitless.</P>
		</div>
	</section>
	<section class="aboutus_values">
		<span class="aboutus_values_heading">OUR VALUES</span>
		<div class="aboutus_values_content">
			<div class="values">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/project-management_1087840.png"
					alt="" class="values_image"> <span class="values_name">Innovation</span>
				<p class="values_paragraph">Driving tech forward with
					cutting-edge solutions and groundbreaking products, always
					exploring new possibilities and pushing boundaries.</p>
			</div>
			<div class="values">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/social-media_2327640.png"
					alt="" class="values_image"> <span class="values_name">Community
					Engagement</span>
				<p class="values_paragraph">Fostering an inclusive, vibrant
					community where tech enthusiasts connect, share knowledge, and
					inspire one another, creating a supportive network.</p>
			</div>
			<div class="values">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/insurance_11598024.png"
					alt="" class="values_image"> <span class="values_name">Reliability</span>
				<p class="values_paragraph">Ensuring consistent quality,
					dependable service, and trustworthy support, building lasting
					relationships founded on reliability and customer satisfaction.</p>
			</div>
		</div>
	</section>
	<section class="aboutus_team">
		<span class="aboutus_team_heading">OUR TEAM</span>
		<div class="team_content">
			<div class="team">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/femaleprofile1.jpg"
					alt="" class="aboutus_team_image"> <span
					class="aboutus_team_name">Rojina Rawal</span> <span
					class="aboutus_team_roles">Project Manager</span>
			</div>
			<div class="team">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/maleprofile.jpg"
					alt="" class="aboutus_team_image"> <span
					class="aboutus_team_name">Niroj Acharya</span> <span
					class="aboutus_team_roles">Web Developer</span>
			</div>
			<div class="team">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/femaleprofile2.jpg"
					alt="" class="aboutus_team_image"> <span
					class="aboutus_team_name">Paleswan Shrestha</span> <span
					class="aboutus_team_roles">Web Developer</span>
			</div>
			<div class="team">
				<img
					src="${pageContext.request.contextPath}/stylesheets/images/femaleprofile3.jpg"
					alt="" class="aboutus_team_image"> <span
					class="aboutus_team_name">Muskan Niraula</span> <span
					class="aboutus_team_roles">Web Designer</span>
			</div>
		</div>

	</section>
	<section class="aboutus_contactus">
		<div class="aboutus_contactus_leftpart">
			<div class="aboutus_contactus_location">
				<span class="aboutus_contactus_location_heading">LOCATION</span> <span
					class="aboutus_contactus_location_place">TechSpace Company <br>
					8th Floor <br> Kamalpokhari, Kathmandu <br> Nepal
				</span>
			</div>
			<div class="aboutus_contactus_contactdetails">
				<span class="aboutus_contactus_contactdetails_heading">CONTACT
					DETAILS</span>
				<div class="aboutus_contactus_contactdetails_phoneemail">
					<div class="aboutus_contactus_contactdetails_phone">
						<span class="aboutus_contactus_contactdetails_phone_heading">Phone</span>
						<span class="aboutus_contactus_contactdetails_phone_number">+977-9803237789</span>
						<span class="aboutus_contactus_contactdetails_phone_number">+977-9806438298</span>
					</div>
					<div class="aboutus_contactus_contactdetails_email">
						<span class="aboutus_contactus_contactdetails_email_heading">Email</span>
						<span class="aboutus_contactus_contactdetails_email_emailid">techspace@gmail.com</span>
						<span class="aboutus_contactus_contactdetails_email_emailid">techadmin@gmail.com</span>

					</div>
				</div>

			</div>
		</div>
		<div class="aboutus_contactus_rightpart">
			<div class="aboutus_contactus_rightpart_heading">
				<span class="aboutus_contactus_heading">CONTACT US</span> <span
					class="aboutus_contactus_subheading">We are looking forward
					to hearing from you soon!</span>
			</div>
			<form action="#" class="aboutus_contactus_rightpart_form">
				<div class="aboutus_contactus_rightpart_info">
					<div class="aboutus_contactus_rightpart_info_align">
						<input type="text" class="aboutus_contactus_smallertextfield"
							placeholder="NAME"> <input type="text"
							class="aboutus_contactus_smallertextfield" placeholder="EMAIL">
					</div>
					<div class="aboutus_contactus_rightpart_info_align">
						<input type="text" class="aboutus_contactus_smallertextfield"
							placeholder="PHONE"> <input type="text"
							class="aboutus_contactus_smallertextfield" placeholder="ADDRESS">
					</div>
					<div class="aboutus_contactus_rightpart_info_align">
						<input type="textfield" class="aboutus_contactus_longertextfield"
							placeholder="MESSAGE">
					</div>

				</div>
				<div class="aboutus_contactus_button">
					<button class="aboutus_contactus_sendbutton">SEND</button>
				</div>
			</form>
		</div>
		
	</section>
	
	<jsp:include page="footer.jsp" />
</body>
</html>