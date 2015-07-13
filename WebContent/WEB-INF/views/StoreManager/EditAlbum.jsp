<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit Album</title>
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
		
		<h2>Edit</h2>
		
		<form:form modelAttribute="editAlbum" method="POST" commandName="editAlbum" action="/MVCMusicStore/StoreManager/EditAlbum?editAlbumId=${editAlbum.albumId}">
			<fieldset>
				<h3>Genre</h3>
				<select name="genreSelected">
					<c:forEach items="${genres}" var="item">
						<option value="${item.genreId}" ${item.name == selectedGenre ? 'selected' : '' }>${item.name}</option>
					</c:forEach>
				</select>
				<br><br>
				
				<h3>Artist</h3>
				<select name="artistSelected">
					<c:forEach items="${artists}" var="item">
						<option value="${item.artistId}" ${item.name == selectedArtist ? 'selected' : '' }>${item.name}</option>
					</c:forEach>
				</select>
				<br><br>
				
				<h3>Title</h3>
				<form:input name="txtTitle" path="title" />
				<br><br>
				
				<h3>Price</h3>
				<form:input path="price" />
				<br><br>
				
				<h3>AlbumArtUrl </h3>
				<form:input path="albumArtUrl" />
				<br><br>
				
				<input type="submit" value="Save" />
				<br><br>
				
			</fieldset>	
		</form:form>
		
		<a href="/MVCMusicStore/StoreManager/" id="current">Back to List</a>
		
		<div id="footer">
			<h3>Building with Spring 3.0 MVC</h3>
		</div>
	</div>
</body>
</html>