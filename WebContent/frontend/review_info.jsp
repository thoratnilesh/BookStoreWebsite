<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Write a Review - Online Book Store</title>
	<link rel="stylesheet" href="css/style.css" ></link>
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<form id="reviewForm">
			<table class="normal" width="50%">
				<tr>
					<td><h3>You already wrote a review for this book</h3></td>
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
					<td>
						<div id="rateYo"></div>
						<br/>
						<input type="text" name="headline" size="60" readonly="readonly" value="${review.headline}" />
						<br/>
						<br/>
						<textarea name="comment" rows="10" cols="70" readonly="readonly" >${review.comment}</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
	<script type="text/javascript">

	$(document).ready(function() {
		
		$("#rateYo").rateYo({
		    startWidth: "40px",
		    fullStar: true,
		    rating: ${review.rating},
		    readOnly: true
		    }
		});
	});
	</script>
	
</html>