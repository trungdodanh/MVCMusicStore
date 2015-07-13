<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Store Index</title>
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
		
		<div id="categories" class="col-left">
			<c:forEach items="${genreList}" var="genre" varStatus="status">
		   		<c:url var="albumUrl" value="Browse?genre=${genre.name}" />
				<li><a href="${albumUrl}"><c:out value="${genre.name}"></c:out></a></li>
		   	</c:forEach>
		</div>
		
		<div class="col-right">
			<h2><em>Browse Genres</em></h2>
		   	<p>Select from ${genreCount} genres: </p>
		   	<c:forEach items="${genreList}" var="genre" varStatus="status">
		   		<c:url var="albumUrl" value="Browse?genre=${genre.name}" />
				<li style="color: #333; list-style: square;"><a href="${albumUrl}"><c:out value="${genre.name}"></c:out></a></li>
		   	</c:forEach>
	   	</div>
	   	
	   	<div id="footer">
			built with <span id="update-message">Spring 3.0 MVC</span>
		</div>
	</div>
</body>
</html>