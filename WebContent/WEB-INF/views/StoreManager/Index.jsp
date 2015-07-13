<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.entities.*" %>
<jsp:useBean id="albumModel" type="com.service.AlbumService" scope="request" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Store Manager</title>
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
		
		<h2>Index</h2>
		<p><a href="/MVCMusicStore/StoreManager/CreateAlbum">Create New</a></p>
		
		<table>
			<thead>
				<tr>
					<th>Genre</th>
					<th>Artist</th>
					<th>Title</th>
					<th>Price</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				
				<% //for(Album album : albumModel.getAll()) { %>
				<c:forEach items="${albumList}" var="album" varStatus="status">
				<tr>
					<td>${album.genreId.name}</td>
					<td>${album.artistId.name}</td>
					<td>${album.title}</td>
					<td>${album.price}</td>
					<td>
						<a href="EditAlbum?editAlbumId=${album.albumId}">Edit</a> |
						<a href="DetailsAlbum?detailsAlbumId=${album.albumId}">Details</a> |
						<a href="DeleteAlbum?deleteAlbumId=${album.albumId}">Delete</a>
					</td>
				</tr>
				</c:forEach>
				<% //} %>
			</tbody>
		</table>
		
		<div id="footer">
			built with <span id="update-message">Spring 3.0 MVC</span>
		</div>
	</div>
</body>
</html>