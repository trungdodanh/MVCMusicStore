<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Album Details</title>
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
		
		<h2>Details</h2>
		
		<fieldset style="width: 300px">
			<legend>ALbum</legend>
			<c:out value=" Genre : ${detailsAlbum.genreId.name}" /><br><br>
			<c:out value=" Artist : ${detailsAlbum.artistId.name}" /><br><br>
			<c:out value=" Title : ${detailsAlbum.title}" /><br><br>
			<c:out value=" Price : ${detailsAlbum.price}" /><br><br>
			<c:out value=" Album Art URL : ${detailsAlbum.albumArtUrl}" /><br><br>
		</fieldset>
		
		<a href="EditAlbum?editAlbumId=${detailsAlbumId}">Edit</a> | <a id="current" href="/MVCMusicStore/StoreManager/">Back to List</a>
		
		<div id="footer">
			built with <span id="update-message">Spring 3.0 MVC</span>
		</div>
	</div>
</body>
</html>