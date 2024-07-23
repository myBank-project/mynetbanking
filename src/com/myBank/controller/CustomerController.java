package com.myBank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.myBank.deo.CustomerDeo;
import com.myBank.deo.AdminDeo;
import com.myBank.deo.CommonDeo;
import com.myBank.entity.CustomerPassbookOrViewTransaction;
import com.myBank.other.TransactionType;

/**
 * Servlet implementation class Customer
 */
@WebServlet("/Customer")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/mybanknetbanking")
	private DataSource dataSource;
	private CustomerDeo customerDeo;
	private AdminDeo adminDeo;
	private CommonDeo commonDeo;

	public void init() throws ServletException {
		super.init();
		customerDeo = new CustomerDeo(dataSource);
		commonDeo = new CommonDeo(dataSource);
		adminDeo = new AdminDeo(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command;
		command = request.getParameter("command");
		if (command == null) {
			command = "default";

		}

		switch (command) {
		case "customerHome":
			customerHome(request, response);
			break;
		case "passbook":
			try {
				openPassbook(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "newTransactionClick":
			newTransactionClick(request, response);
			break;
		case "newTransaction":
			try {
				newTransaction(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "editProfile":
			try {
				editProfile(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "editProfileClick":
			editProfileClick(request, response);
			break;
			
		case "SortByOldestOneInPassBook":
			try {
				SortByOldestOneInPassBook(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "searchByAccNo":
			try {
				searchByAccNo(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		default:
			customerHome(request, response);
			break;
		}
	}

	private void searchByAccNo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		int customerId = Integer.parseInt((String) session.getAttribute("username"));
		int receiverAccountNumber=Integer.parseInt(request.getParameter("searchByAccNo"));
		List<CustomerPassbookOrViewTransaction> customerPassbook = commonDeo.SearchByReceiverAccount(customerId,receiverAccountNumber);
		request.setAttribute("customerPassbook", customerPassbook);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/PassBook.jsp");
		requestDispatcher.forward(request, response);
		
	}

	private void SortByOldestOneInPassBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		HttpSession session = request.getSession();
		int customerId = Integer.parseInt((String) session.getAttribute("username"));
		List<CustomerPassbookOrViewTransaction> customerPassbook = commonDeo.SortByOldestOneInPassBook(customerId);
		request.setAttribute("customerPassbook", customerPassbook);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/PassBook.jsp");
		requestDispatcher.forward(request, response);
		
	}

	private void editProfileClick(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/EditProfile.jsp");
		requestDispatcher.forward(request, response);

	}

	private void newTransactionClick(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/NewTransaction.jsp");
		requestDispatcher.forward(request, response);

	}

	private void editProfile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null) {
//			create exception
			return;
		}
		String username = (String) (session.getAttribute("username"));
		int customerId = Integer.parseInt(username);
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String customerAddress = request.getParameter("address");

		boolean checkEdit = commonDeo.updateProfile(customerId, firstName, lastName, customerAddress);
		System.out.println("update profile part:"+checkEdit);
		if(checkEdit==false) {
//			.create exception of profile not updated
			return;
		}
		
		boolean updatePassword=commonDeo.updatePassword(customerId,password);
		System.out.println("update password part:"+updatePassword);
		request.setAttribute("updateprofilesuccess", checkEdit);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/CustomerHome.jsp");
		requestDispatcher.forward(request, response);
	}

	private void newTransaction(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null) {
//			create exception
			return;
		}
		String username = (String) (session.getAttribute("username"));
		int customerId = Integer.parseInt(username);
		int ReceiverAccountNumber = Integer.parseInt(request.getParameter("ReceiverAccountNumber"));
		boolean checkReceiverDetails = adminDeo.findAccountNumber(ReceiverAccountNumber);
		int transferAmount = Integer.parseInt(request.getParameter("transferAmount"));
		String transferingAmount = request.getParameter("transactionType");
		TransactionType transactionType=TransactionType.valueOf(transferingAmount);
		System.out.println("transferamountis: " + transferAmount);

		if (checkReceiverDetails == false) {
//			create exception of accountnubmer not found;
			return;
		}

		int currentBalance = customerDeo.getCurrentBalance(customerId);
		if (currentBalance < transferAmount) {
			System.out.println("your balance is less than transfer amount");
//		 create exception of less amount present
			return;
		}

		int balanceAfterTransaction = currentBalance - transferAmount;
		boolean checkMoneyReduced = customerDeo.reduceSenderBalance(customerId, balanceAfterTransaction);
		if (checkMoneyReduced == false) {
//			create exception of transaction failed;
			return;
		}
		int currentUserAccNo = customerDeo.getCustomerAccountNo(customerId);
		boolean addTrasaction = customerDeo.addTransactionRecord(customerId, transferAmount, ReceiverAccountNumber,
				currentUserAccNo, transactionType);
		if (addTrasaction == false) {
//			create exception of transacion not added to db
			return;
		}
		request.setAttribute("checkTransaction", addTrasaction);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/transactionSuccess.jsp");
		requestDispatcher.forward(request, response);
	}

	private void customerHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/CustomerHome.jsp");
		requestDispatcher.forward(request, response);

	}

	private void openPassbook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		int customerId = Integer.parseInt((String) session.getAttribute("username"));
		List<CustomerPassbookOrViewTransaction> customerPassbook = commonDeo.getCustomerPassbook(customerId);
		request.setAttribute("customerPassbook", customerPassbook);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/PassBook.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
