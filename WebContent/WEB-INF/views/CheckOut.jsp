<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Check out</title>
	<link rel="stylesheet" type="text/css" href='/MVCMusicStore/resources/Site.css'/>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1><a href="/MVCMusicStore/">SPRING MVC MUSIC STORE</a></h1>
			<ul id="navlist">
				<li class="first"><a href="/MVCMusicStore/" id="current">Home</a></li>
				<li><a href="/MVCMusicStore/Store/">Store</a></li>
				<li><a href="/MVCMusicStore/ShoppingCart">Cart(${itemNumbers})</a></li>
				<li><a href="/MVCMusicStore/StoreManager/">Admin</a></li>
			</ul>
		</div>
		
		<h2>Checkout Complete</h2>
		<h3>Thank you for your order! your order number is : ${myCartId}</h3>
		<h3>How about shopping for more music in our <a href="/MVCMusicStore/Store/">Store</a> ?</h3>
		
		<div id="footer">
			building with <em>Spring 3.0 MVC</em>
		</div>
	</div>
</body>
</html>