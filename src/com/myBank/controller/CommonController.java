package com.myBank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.myBank.deo.CommonDeo;
import com.myBank.entity.CustomerPassbookOrViewTransaction;
import com.myBank.deo.CustomerDeo;

@WebServlet("/CommonController")
public class CommonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Resource(name = "jdbc/mybanknetbanking")
	private DataSource dataSource;
	private CommonDeo commonDeo;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


	public void init() throws ServletException {
		super.init();
		commonDeo = new CommonDeo(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		switch (command) {
		case "searchTransactionByDate":
			try {
				searchTransactionByDate(request, response);
			} catch (ServletException | IOException | SQLException | ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case "searchTransactionByType":
			try {
				searchTransactionByType(request, response);
			} catch (ServletException | IOException | SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case "searchTransactionBySenderAccountNo":
			try {
				searchTransactionBySenderAccountNo(request, response);
			} catch (ServletException | IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "searchTransactionByReceiverAccountNo":
			try {
				searchTransactionByReceiverAccountNo(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}

	private void searchTransactionByReceiverAccountNo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int receiverAccountNo = Integer.parseInt(request.getParameter("receiver_account_no"));
		List<CustomerPassbookOrViewTransaction> transactionByReceiverAccountNo = commonDeo
				.getTransactionByReceiverAccountNo(receiverAccountNo);
		request.setAttribute("viewTransaction", transactionByReceiverAccountNo);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view-transaction.jsp");
		requestDispatcher.forward(request, response);

	}

	private void searchTransactionBySenderAccountNo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int senderAccountNo = Integer.parseInt(request.getParameter("sender_account_no"));
		List<CustomerPassbookOrViewTransaction> transactionBySenderAccountNo = commonDeo
				.getTransactionBySenderAccountNo(senderAccountNo);
		request.setAttribute("viewTransaction", transactionBySenderAccountNo);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view-transaction.jsp");
		requestDispatcher.forward(request, response);

	}

	private void searchTransactionByType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String transactionType=request.getParameter("transaction-type");
		List<CustomerPassbookOrViewTransaction> transactionByType = commonDeo.getTransactionByType(transactionType);
		request.setAttribute("viewTransaction", transactionByType);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view-transaction.jsp");
		requestDispatcher.forward(request, response);

	}

	private void searchTransactionByDate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ParseException {
		String dateInString=request.getParameter("transaction_date");
		Date transactionDate = formatter.parse(dateInString);
		List<CustomerPassbookOrViewTransaction> transactionByDate = commonDeo.getTransactionByDate((java.sql.Date) transactionDate);
		request.setAttribute("viewTransaction", transactionByDate);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view-transaction.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
