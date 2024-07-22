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
<title>Passbook</title>
   
</head>
<body>
	 <div class="container mt-5">
        <h2>Passbook</h2>
         <!-- Sort option for date -->
        <div class="text-end mb-3">
            <a href="TransactionSortServlet?sortBy=date" class="btn btn-secondary">Sort by Date</a>
        </div>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Sender Acc No</th>
                    <th>Receiver Acc No</th>
                    <th>Type of Transaction</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customerPassbook" items="${customerPassbook}">
                    <tr>
                        <td>${customerPassbook.senderAccountNumber}</td>
                        <td>${customerPassbook.receiverAccountNumber}</td>
                        <td>${customerPassbook.transactionDate}</td>
                        <td>${customerPassbook.transactionAmount}</td>
                        <td>${customerPassbook.transactionDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="CustomerHome.jsp" class="btn btn-secondary">Back to Home</a>
    </div>
    
</body>
</html>