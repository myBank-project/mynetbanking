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
<meta charset="UTF-8">
<title>Customer Home</title>
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
</style>
</head>
<body>

	<div class="container text-center">
		<h2>Customer Home</h2>
		<ul class="list-group">
			<li class="list-group-item"><a href="Customer?command=passbook"
				class="btn btn-custom btn-block">My Passbook</a></li>
			<li class="list-group-item"><a
				href="Customer?command=newTransactionClick"
				class="btn btn-custom btn-block">New Transaction</a></li>
			<li class="list-group-item"><a
				href="Customer?command=editProfileClick" class="btn btn-custom btn-block">Edit
					Profile</a></li>
		</ul>
	</div>

</body>
</html>