<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.18.3/bootstrap-table.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.18.3/bootstrap-table.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Customers</title>
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
        <h2>View Customers</h2>
        
        <!-- Search form for account number -->
        <form action="Admin" method="get" class="mb-3 text-center">
            <label for="accountNumber">Search by Account Number:</label>
            <input type="text" id="accountNumber" name="accountNumber">
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
        
        <!-- Sort option for name -->
        <div class="text-end mb-3">
            <a href="CustomerSortServlet?sortBy=name" class="btn btn-secondary">Sort by Name</a>
        </div>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Account Number</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Balance</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cutomersDetails" items="${cutomersDetails}">
                    <tr>
                        <td>${cutomersDetails.accountNumber}</td>
                        <td>${cutomersDetails.customerfirstName}</td>
                        <td>${cutomersDetails.customerlastName}</td>
                        <td>${cutomersDetails.balance}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>