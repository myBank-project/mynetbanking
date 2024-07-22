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
import com.myBank.deo.CommonDeo;
import com.myBank.entity.CustomerPassbookOrViewTransaction;

/**
 * Servlet implementation class Customer
 */
@WebServlet("/Customer")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/mybanknetbanking")
	private DataSource dataSource;
	private CustomerDeo customerDeo;
	private CommonDeo commonDeo;

	public void init() throws ServletException {
		super.init();
		customerDeo = new CustomerDeo(dataSource);
		commonDeo = new CommonDeo(dataSource);
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

		default:
			customerHome(request, response);
			break;
		}
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
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String customerAddress = request.getParameter("customer_address");

		boolean checkEdit = commonDeo.updateProfile(customerId, firstName, lastName, password, email, customerAddress);
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
		int currentBalance = customerDeo.getCurrentBalance(customerId);
		int transferAmount = Integer.parseInt(request.getParameter("transferAmount"));
		System.out.println("transferamountis: " + transferAmount);
		if (currentBalance < transferAmount) {
			System.out.println("your balance is less than transfer amount");
//		 create exception of less amount present
			return;
		}
		int balanceAfterTransaction = currentBalance - transferAmount;
		boolean checkTransaction = customerDeo.performTransaction(customerId, balanceAfterTransaction);
		request.setAttribute("checkTransaction", checkTransaction);
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
