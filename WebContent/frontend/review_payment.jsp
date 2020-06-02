<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review Payment - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h3><i>Please carefully review the following information before making payment</i></h3>
		<h2>Payer Information</h2>
		<table>
			<tr>
				<td><b>First Name:</b></td>
				<td>${payer.firstName}</td>
			</tr>
			<tr>
				<td><b>Last Name:</b></td>
				<td>${payer.lastName}</td>
			</tr>
			<tr>
				<td><b>E-mail:</b></td>
				<td>${payer.email}</td>
			</tr>
		</table>
		<h2>Recipient Name:</h2>
		<table>
			<tr>
				<td><b>Recipient Name:</b></td>
				<td>${recipient.recipientName}</td>
			</tr>
			<tr>
				<td>Address Line 1:</td>
				<td>${recipient.line1}</td>
			</tr>
			<tr>
				<td>Address Line 2:</td>
				<td>${recipient.line2}</td>
			</tr>
			<tr>
				<td>City:</td>
				<td>${recipient.city}</td>
			</tr>
			<tr>
				<td>State:</td>
				<td>${recipient.state}</td>
			</tr>
			<tr>
				<td>Country Code:</td>
				<td>${recipient.countryCode}</td>
			</tr>
			<tr>
				<td>Postal Code:</td>
				<td>${recipient.postalCode}</td>
			</tr>
		</table>
		<h2>Transaction Details:</h2>
		<table>
			<tr>
				<td><b>Description:</b></td>
				<td>${transaction.description}</td>
			</tr>
			<tr>
				<td><b>Item List:</b></td>
			</tr>
			<tr>
				<td colspan="2">
					<table border="1">
						<tr>
							<th>No.</th>
							<th>Name</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
						</tr>
						<c:forEach items="${transaction.itemList.items}" var="item" varStatus="status">
						<tr>
							<td>${var.index +1}</td>
							<td>${item.name}</td>
							<td>${item.quantity}</td>
							<td><fmt:formatNumber value="${item.price}" type="currency" /></td>
							<td><fmt:formatNumber value="${item.price * item.quantity}" type="currency" /></td>
						</tr>
						</c:forEach>
						<tr>
							<td colspan="5" align="right">
								<p>Subtotal: <fmt:formatNumber value="${transaction.amount.details.totalAmount}" type="currency" /></p>
								<p>TAx: <fmt:formatNumber value="${transaction.amount.details.tax}" type="currency" /></p>
								<p>Shipping Fee: <fmt:formatNumber value="${transaction.amount.details.shippingFee}" type="currency" /></p>
								<p>TOTAL: <fmt:formatNumber value="${transaction.amount.details.total}" type="currency" /></p>
							</td>
						</tr>
					</table>
				</td>	
			</tr>
		</table>
		<div>
			<br/>
			<form action="execute_payment" method="post">
				<input type="hidden" name="paymentId" value="${param.paymentId}" />
				<input type="hidden" name="PayerId" value="${param.PayerId}" />
				<input type="submit" value="PAy Now" />
			</form>
				
		</div>
	</div>
</body>
</html>