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
<title>View Transactions</title>
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
        <h2>View Transactions</h2>
         <!-- Sort option for date -->
        
        <div class="row">
			<form action="CommonController" method="get" class="mb-3 text-center">


				<div class="col-md-6">
					<input name="command" value="searchTransactionBySenderAccountNo" type="hidden" /> <input
						type="number" id="firstName" name="searchTransactionBySenderAccountNo"
						class="form-control" placeholder="sender account number">
				</div>
			</form>
			
			<form action="CommonController" method="get" class="mb-3 text-center">


				<div class="col-md-6">
					<input name="command" value="searchTransactionByReceiverAccountNo" type="hidden" /> <input
						type="number" id="firstName" name="searchTransactionByReceiverAccountNo"
						class="form-control" placeholder="receiver account number">
				</div>
			</form>
		</div>
        
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Sender Account No.</th>
                    <th>Receiver Account NO.</th>
                    <th>Type of Transaction</th>
                     <th>Amount</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="viewAllTransactions" items="${viewAllTransactions}">
                    <tr>
                        <td>${viewAllTransactions.senderAccountNumber}</td>
                        <td>${viewAllTransactions.receiverAccountNumber}</td>
                        <td>${viewAllTransactions.transactionType}</td>
                        <td>${viewAllTransactions.transactionAmount}</td>
                        <td>${viewAllTransactions.transactionDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>