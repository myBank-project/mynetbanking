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
import javax.sql.DataSource;

import com.myBank.deo.AdminDeo;
import com.myBank.deo.CustomerDeo;
import com.myBank.deo.CommonDeo;
import com.myBank.entity.Customer;
import com.myBank.entity.CustomerPassbookOrViewTransaction;
import com.myBank.entity.Transaction;
import com.myBank.entity.ViewCustomer;
import com.myBank.other.AccountType;
import com.myBank.other.Role;

@WebServlet("/Admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/mybanknetbanking")
	private DataSource dataSource;
	private AdminDeo adminDeo;

	public void init() throws ServletException {
		super.init();
		adminDeo = new AdminDeo(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");
		if (command == null)
			command = "default";

		switch (command) {
		case "addCustomer":
			try {
				addCustomer(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "generateAccountNumber":
			try {
				addBankAccount(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "viewCustomers":
			try {
				viewCustomers(request, response);
			} catch (NullPointerException | SQLException | ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case "addNewCustomerClick":
			addNewCustomerClick(request, response);
			break;
		case "viewTransactions":
			try {
				viewTransactions(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			try {
				adminHome(request, response);
			} catch (NullPointerException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	private void addNewCustomerClick(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("entering");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AddCustomer.jsp");
		requestDispatcher.forward(request, response);
	}

	private void adminHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminHome.jsp");
		requestDispatcher.forward(request, response);
	}

	private void viewTransactions(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		System.out.println("before query");
		List<Transaction> viewAllTransactions = adminDeo.getAllTransactions();
		request.setAttribute("viewAllTransactions", viewAllTransactions);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewTransactions.jsp");
		requestDispatcher.forward(request, response);
	}

	private void viewCustomers(HttpServletRequest request, HttpServletResponse response)
			throws NullPointerException, SQLException, ServletException, IOException {

		List<ViewCustomer> customers = adminDeo.viewAllCustomers();
		request.setAttribute("cutomersDetails", customers);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewCustomers.jsp");
		System.out.println("all customers in view in admin" + customers);
		requestDispatcher.forward(request, response);

	}

	private void addBankAccount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("customer_id"));
		boolean isAlreadyExist = adminDeo.isCustomerIdAreadyUsedByAnotherAccount(customerId);
		System.out.println("isAlreadyExist: " + isAlreadyExist);
		if (isAlreadyExist == true) {
//			create execption regarding customerid has already bankaccount created;
			return;
		}
		int createdAccountNumber = adminDeo.addBankAccountWithCustomerId(customerId);
		System.out.println("createdAccountNumber is : " + createdAccountNumber);
		if (createdAccountNumber == -1) {
//			exception
			return;
		}
//		ṣet account number with customer table
		boolean isAddedToCustomer = adminDeo.setAccountWithCustomer(customerId, createdAccountNumber);

//		create default username(customerId) and password
		Role role = Role.CUSTOMER;

		boolean isCredentialsCreated = adminDeo.createCredentials(customerId, role);

		request.setAttribute("createdAccountNumber", createdAccountNumber);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AdminHome.jsp");
		requestDispatcher.forward(request, response);

	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String accounts = request.getParameter("account_type");
		String customerAddress = request.getParameter("customer_address");
		String noineeName = request.getParameter("nominee_name");
		AccountType accountType = AccountType.valueOf(accounts.toUpperCase());
		int customerId = adminDeo.createCustomer(firstName, lastName, email, password, accountType, customerAddress,
				noineeName);
		response.sendRedirect("/mybanknetbanking/Admin?command=generateAccountNumber&customer_id=" + customerId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
