<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
<style>
body {
	background:
		url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_-qAlSh3Fi2vCW42Zr3lPIP_pFcV0IvTKlg&s')
		no-repeat center center fixed;
	background-size: cover;
}

.container {
	margin-top: 50px;
	background-color: rgba(255, 255, 255, 0.9);
	padding: 30px;
	border-radius: 10px;
}

.btn-custom {
	background-color: #ff5722;
	border: none;
	color: white;
}

.btn-custom:hover {
	background-color: #e64a19;
}

.spacer {
	margin-top: 20px;
}
</style>
</head>
<body>
	<div class="container text-center">
		<h2>Admin Home</h2>
		<div class="spacer"></div>
		<form action="Admin" method="post">
			<ul class="list-group">
				<li class="list-group-item"><a
					href="Admin?command=addNewCustomerClick"
					class="btn btn-custom btn-block">Add New Customer</a></li>
				<li class="list-group-item"><a
					href="Admin?command=addBankAccount"
					class="btn btn-custom btn-block">Add Bank Account</a></li>
				<li class="list-group-item"><a
					href="Admin?command=viewCustomers" class="btn btn-custom btn-block">View
						Customers</a></li>
				<li class="list-group-item"><a
					href="Admin?command=viewTransactions"
					class="btn btn-custom btn-block">View Transactions</a></li>
			</ul>
		</form>
	</div>
</body>
</html>