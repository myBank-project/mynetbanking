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
<title>Add New Customer</title>
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
	max-width: 600px;
}

.btn-custom {
	background-color: #ff5722;
	border: none;
	color: white;
}

.btn-custom:hover {
	background-color: #e64a19;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Add New Customer</h2>
		<form action="Admin" method="get">
			<input name="command" value=addCustomer type="hidden" />
			<div class="form-group">
				<label for="firstName">First Name:</label> <input type="text"
					class="form-control" id="firstName" name="first_name" required>
			</div>
			<div class="form-group">
				<label for="lastName">Last Name:</label> <input type="text"
					class="form-control" id="lastName" name="last_name" required>
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email"
					class="form-control" id="email" name="email" required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>



			<div class="form-group">
				<label>Account Type:</label><br>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="account_type"
						id="SAVINGS" value="SAVINGS" required> <label
						class="form-check-label" for="admin">SAVINGS</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="account_type"
						id="CURRENT" value="CURRENT" required> <label
						class="form-check-label" for="admin">CURRENT</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="account_type"
						id="LOAN" value="LOAN" required> <label
						class="form-check-label" for="admin">LOAN</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="account_type"
						id="SALARY" value="SALARY" required> <label
						class="form-check-label" for="admin">SALARY</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="account_type"
						value="BUSINESS" required> <label class="form-check-label"
						for="admin">BUSINESS</label>
				</div>
			</div>





			<div class="form-group">
				<label for="CustomerAddres">CustomerAddres:</label> <input
					type="text" class="form-control" id="CustomerAddres"
					name="customer_addres" required>
			</div>


			<div class="form-group">
				<label for="NomineeName">Nominee Name:</label> <input type="text"
					class="form-control" id="NomineeName" name="nominee_name" required>
			</div>


			<button type="submit" class="btn btn-custom btn-block">Add
				Customer</button>
			<button type="reset" class="btn btn-secondary">Cancel</button>
		</form>
	</div>
</body>
</html>