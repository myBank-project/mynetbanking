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
 <title>Add Bank Account</title>
    <style>
        body {
            background: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_-qAlSh3Fi2vCW42Zr3lPIP_pFcV0IvTKlg&s') no-repeat center center fixed;
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
	 <div class="container text-center">
        <h2 class="bg-custom text-dark p-2">Add Bank Account</h2>
        <form action="searchCustomer" method="post" class="bg-custom p-4">
            <div class="form-group">
                <label for="customerId" class="text-dark">Search by -> Customer ID</label>
                <input type="text" class="form-control" id="customerId" name="customerId" required>
            </div>
            <button type="submit" class="btn btn-dark">Submit</button>
        </form>
        <div class="bg-custom p-4 my-4">
            <h4>Customer Details</h4>
            <!-- Customer details will be dynamically populated here -->
        </div>
        <button class="btn btn-custom">Generate Account Number</button>
    </div>
</body>
</html>