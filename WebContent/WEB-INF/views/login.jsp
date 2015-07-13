<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
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
		<form:form modelAttribute="login" commandName="login" method="POST" action="/MVCMusicStore/Account/login">
			
			<h2>Log On</h2>
			<h3>Please enter your username and password. <a href="/MVCMusicStore/addUser">Register</a> if you don't have any account</h3>
			<fieldset>
				<legend>Account information</legend>
				<h3>Username</h3>
				<form:input path="UsersName"/>
				<br><br>
				
				<h3>Password</h3>
				<form:input path="PassWord"/>
				<br><br>
				
				<input type="submit" value="Log On" />
			</fieldset>
			
		</form:form>
		
		<div id="footer">
			building with <em>Spring 3.0 MVC</em>
		</div>
	</div>
</body>
</html>