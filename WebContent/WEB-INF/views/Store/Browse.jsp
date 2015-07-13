<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Browse</title>
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
			<h2>${genre} <em>Albums</em></h2>
			<ul id="album-list">
			<c:forEach items="${foundAlbum}" var="album">
				<li>
					<c:url var="albumUrl" value="Details?albumId=${album.albumId}" />
					<img alt="Album art of album : ${album.title}" src="/MVCMusicStore/resources/placeholder.gif">
					<a href="${albumUrl}"><span><c:out value="${album.title}"></c:out></span></a>
				</li>
			</c:forEach>
			</ul>
		</div>
		<div id="footer">
			built with <span id="update-message">Spring 3.0 MVC</span>
		</div>
	</div>
</body>
</html>