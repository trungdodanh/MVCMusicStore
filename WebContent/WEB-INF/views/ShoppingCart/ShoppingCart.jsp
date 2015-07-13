<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Shopping Cart</title>
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
		   		<c:url var="albumUrl" value="/MVCMusicStore/Store/Browse?genre=${genre.name}" />
				<li><a href="${albumUrl}"><c:out value="${genre.name}"></c:out></a></li>
		   	</c:forEach>
		</div>
		<div class="col-right">
			<form:form modelAttribute="myCart" commandName="myCart" method="POST" action="/MVCMusicStore/ShoppingCart">
			
				<h2>Review your cart :</h2>
				<input type="submit" value="Checkout >>" />
				<div style="height:20px"></div>
				<div id="update-message">${removedAlbumInfo}</div>
				<table>
					<thead>
						<tr>
							<th>Album Name</th>
							<th>Price (each)</th>
							<th>Quantity</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${albumList}" var="album">
							<tr id="row">
								<td><a href="/MVCMusicStore/Store/Details?albumId=${album.albumId}"><c:out value="${album.title}" /></a></td>
								<td><c:out value="${album.price}" /></td>
								<td><c:out value="1" /></td>
								<td><a href="/MVCMusicStore/ShoppingCart.html?deletedAlbumID=${album.albumId}"><c:out value="Remove from cart" /></a></td>
							</tr>
						</c:forEach>
						<tr>
							<td>Total</td>
							<td></td>
							<td></td>
							<td>${totalPrice}</td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
		<div id="footer">
			building with <span id="update-message">Spring 3.0 MVC</span>
		</div>
	</div>
</body>
</html>