<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Admin Login</title>
	<link rel="stylesheet" href="../css/style.css" ></link>
	<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<div align="center">
		<h1>Book Store Admin</h1>
		<h2>Admin Login</h2>
		
		<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
		</c:if>
		<form id="loginForm" action="login" method="post">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="20"></td>
				</tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
					</td>
				<tr>
			</table>
		</form>
	</div>
</body>
	<script type="text/javascript">

	$(document).ready(function() {
		$("#loginForm").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				password: "required"
			},
			
			message: {
				email: {
					equired: "Please enter email",
					email: "Please enter an valid email address"
				}, 
				password: "Please enter password"
			}
		});
	});
</script>
	
</html>