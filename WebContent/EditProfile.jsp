<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
</head>
<body>
	<div class="container mt-5">
        <h2>Edit Profile</h2>
        <form action="updateProfile" method="post">
            <div class="form-group">
                <label for="firstName">Customer First Name:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value="${customer.firstName}">
            </div>
            <div class="form-group">
                <label for="lastName">Customer Last Name:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${customer.lastName}">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" value="${customer.password}">
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
            <button type="reset" class="btn btn-secondary">Cancel</button>
        </form>
        <a href="CustomerHome.jsp" class="btn btn-secondary mt-3">Back to Home</a>
    </div>
</body>
</html>