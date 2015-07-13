<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.entities.*" %>
<%@ include file="/WEB-INF/views/taglib.jsp" %>
<jsp:useBean id="usersModel" type="com.service.UsersService" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Create a User</title>
	<link rel="stylesheet" type="text/css" href='/MVCMusicStore/resources/Site.css'/>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1><a href="/MVCMusicStore/">SPRING MVC MUSIC STORE</a></h1>
			<ul id="navlist">
				<li class="first"><a href="/MVCMusicStore/" id="current">Home</a></li>
				<li><a href="/MVCMusicStore/Store/">Store</a></li>
			</ul>
		</div>
		
		<div id="login-error">${error}</div>
		<form action="addUser.html" id="addUserForm" method="post">
			<fieldset>
				<legend>Login information</legend>
				<label for="usersname"> Username : </label><br>
				<input type="text" name="txtUsername" id=" usersname" /><br><br>
				
				<label for="password"> Password : </label><br>
				<input type="password" name="txtPassword" id=" password" /><br><br>
				
				<label for="passwordConfirm"> Confirm : </label><br>
				<input type="password" name="txtPasswordConfirm" id=" passwordConfirm" /><br><br>
			</fieldset>
			
			<fieldset>
				<legend>Shipping information</legend>
				<legend>Login information</legend>
				<label for="firsname"> First name : </label><br>
				<input type="text" name="txtFirstname" id=" firstname" /><br><br>
				
				<label for="lastname"> Last name : </label><br>
				<input type="text" name="txtLastname" id=" lastname" /><br><br>
				
				<label for="address"> Address : </label><br>
				<input type="text" name="txtAddress" id="address" /><br><br>
				
				<label for="city"> City : </label><br>
				<input type="text" name="txtCity" id=" city" /><br><br>
				
				<label for="state"> State : </label><br>
				<input type="text" name="txtState" id=" state" /><br><br>
				
				<label for="postalCode"> Postal Code : </label><br>
				<input type="text" name="txtPostalCode" id=" postalCode" /><br><br>
				
				<label for="country"> Country : </label><br>
				<input type="text" name="txtCountry" id=" country" /><br><br>
				
				<label for="phone"> Phone : </label><br>
				<input type="text" name="txtPhone" id=" phone" /><br><br>
				
				<label for="email"> Email Address : </label><br>
				<input type="text" name="txtEmail" id=" email" />
				<form:errors path="email" cssStyle="color: #ff0000;" /><br><br>
			</fieldset>
			
			<fieldset>
				<legend>Payment</legend>
				<p>We are running promotion: all music is free with the promo code "FREE"</p>
				
				<label for="promoCode"> Promo Code : </label><br>
				<input type="text" name="txtPromoCode" id=" promoCode" /><br><br>
				
			</fieldset>
			
			<input type="submit" id="submit" value="Create" />
		</form>
		
		<div id="footer">
			building with <span id="update-message">Spring 3.0 MVC</span>
		</div>
	</div>
</body>
</html>