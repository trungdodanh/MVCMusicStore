
<%@ include file="/WEB-INF/views/taglib.jsp" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		   		<c:url var="albumUrl" value="Store/Browse?genre=${genre.name}" />
				<li><a href="${albumUrl}"><c:out value="${genre.name}" /></a></li>
		   	</c:forEach>
		</div>
		<div class="col-right">
			<div id="promotion"></div>
			<h2>Fress <em>off the grill</em></h2>
			<ul id="album-list">
			<c:forEach items="${albumList}" var="album">
				<li>
					<c:url var="albumUrl" value="Store/Details?albumId=${album.albumId}" />
					<img alt="Album art of album : ${album.title}" src="/MVCMusicStore/resources/placeholder.gif">
					<a href="${albumUrl}"><span><c:out value="${album.title}" /></span></a>
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