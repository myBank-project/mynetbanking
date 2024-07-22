<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Transaction</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container mt-5">
		<h2>New Transaction</h2>
		<form action="Customer" method="get">
			<input name="command" value="newTransaction" type="hidden" />
			<div class="form-group">
				<label for="type">Type of Transaction:</label> <select
					class="form-control" id="type" name="type">
					<option value="Credit">Credit</option>
					<option value="Debit">Debit</option>
					<option value="Transfer">Transfer</option>
				</select>
			</div>
			<div class="form-group">
				<label for="amount">Amount:</label> <input type="number"
					class="form-control" id="amount" name="transferAmount">
			</div>
			<div class="form-group">
				<label for="toAccount">To Account Number (in case of
					transfer):</label> <input type="text" class="form-control" id="toAccount"
					name="toAccount">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
			<button type="reset" class="btn btn-secondary">Cancel</button>
		</form>
		<a href="CustomerHome.jsp" class="btn btn-secondary mt-3">Back to
			Home</a>
	</div>
</body>
</html>