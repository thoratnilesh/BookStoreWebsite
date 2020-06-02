<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Review Posted - Online Book Store</title>
	<link rel="stylesheet" href="css/style.css" ></link>
	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<form id="reviewForm" action="submit_review" method="post" >
			<table class="normal" width="50%">
				<tr>
					<td><h2>Your Reviews</h2></td>
					<td><h2>${loggedCustomer.fullname}</h2></td>
				</tr>
				<tr>
					<td colspan="3"><hr/></td>
				</tr>
				<tr>
					<td>
						<span id="book-title">${book.title}</span><br/>
						<img class="book-large" src="data:image/png;base64,${book.base64Image}"/>
					</td>
					<td colspan="2">
						<h3>Your review has been posted. Thank you</h3>
					</td>
				</tr>
			</table>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>