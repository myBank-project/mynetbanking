package com.myBank.deo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.myBank.entity.Customer;
import com.myBank.entity.CustomerPassbookOrViewTransaction;
import com.myBank.entity.Transaction;
import com.myBank.entity.ViewCustomer;
import com.myBank.other.AccountType;
import com.myBank.other.Role;

public class AdminDeo {

	private static DataSource dataSource;

	public AdminDeo(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public int createCustomer(String firstName, String lastName, String email, String password, AccountType accountType,
			String customerAddress, String noineeName) throws SQLException {
//		System.out.println("before execution: ");
		Connection conn = dataSource.getConnection();
		String sql = "INSERT INTO customer ( first_name,last_name, email, account_type, customer_address, nominee_name)\r\n"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement prpStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		prpStmt.setString(1, firstName);
		prpStmt.setString(2, lastName);
		prpStmt.setString(3, email);
		prpStmt.setString(4, accountType.name());
		prpStmt.setString(5, customerAddress);
		prpStmt.setString(6, noineeName);

		int rs = prpStmt.executeUpdate();
		ResultSet generatedKeys = prpStmt.getGeneratedKeys();
		int customerId = 0;
		while (generatedKeys.next()) {
			customerId = generatedKeys.getInt(1);
		}

		return customerId;
	}

	public boolean isCustomerIdAreadyUsedByAnotherAccount(int customerId) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select account_number from customer where customer_id=?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setInt(1, customerId);
		ResultSet rs = prpStmt.executeQuery();
		int accountNumber = 0;
		while (rs.next()) {
			accountNumber = rs.getInt("account_number");

		}
		return accountNumber != 0 ? true : false;
	}

	public int addBankAccountWithCustomerId(int customerId) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "insert into accountnumber(customer_id) values(?)";
		PreparedStatement prpStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		prpStmt.setInt(1, customerId);
		int rs = prpStmt.executeUpdate();
		ResultSet generatedKeys = prpStmt.getGeneratedKeys();
		int accountNumber = -1;
		while (generatedKeys.next()) {
			accountNumber = generatedKeys.getInt(1);
		}
		return accountNumber;
	}

	public List<ViewCustomer> viewAllCustomers() throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select first_name,last_name,account_number,balance from customer order by account_open_date DESC";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		ResultSet rs = prpStmt.executeQuery();
		List<ViewCustomer> customers = new ArrayList<>();
		while (rs.next()) {
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			int accountNumber = rs.getInt("account_number");
			int balance = rs.getInt("balance");
			ViewCustomer customer1 = new ViewCustomer(firstName, lastName, accountNumber, balance);
			System.out.println("trying.. " + firstName);
			customers.add(customer1);
		}
		return customers;
	}

	public static List<Transaction> getAllTransactions() throws SQLException {
		System.out.println("after query");
		Connection conn = dataSource.getConnection();

		String sql = "select sender_account_no,receiver_account_no,transaction_type,transaction_amount,transaction_date from transactions";
		PreparedStatement prptStmt = conn.prepareStatement(sql);
		ResultSet rs = prptStmt.executeQuery();

		List<Transaction> allTransactions = new ArrayList<>();
		while (rs.next()) {
			int senderAccountNumber = rs.getInt("sender_account_no");
			int receiverAccountNumber = rs.getInt("receiver_account_no");
			String transactionType = rs.getString("transaction_type");
			int sendAmount = rs.getInt("transaction_amount");
			Date transferDate = rs.getDate("transaction_date");
			Transaction transaction = new Transaction(senderAccountNumber, receiverAccountNumber, transferDate,
					transactionType, sendAmount);
			allTransactions.add(transaction);
		}
		return allTransactions;
	}

	public int getAccountNumber(int customerId) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select account_number from accountnumber where customer_id=?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setInt(1, customerId);
		ResultSet rs = prpStmt.executeQuery();
		int accountNumber = 0;
		while (rs.next()) {

			accountNumber = rs.getInt("account_number");
		}
		if (accountNumber == 0)
			return 0;
		return accountNumber;
	}

	public boolean setAccountWithCustomer(int customerId, int accountNumber) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "UPDATE customer SET account_number = ? WHERE customer_id = ?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setInt(1, accountNumber);
		prpStmt.setInt(2, customerId);
		int rs = prpStmt.executeUpdate();
		return rs == 1 ? true : false;
	}

	public boolean createCredentials(int customerId,Role role) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql="insert into users(username,password_hash,role) values(?,?,?)";
		PreparedStatement prpStmt=conn.prepareStatement(sql);
		prpStmt.setString(1,String.valueOf(customerId));
		prpStmt.setString(2,String.valueOf(customerId));
		prpStmt.setString(3, role.name());
		
		
		return false;
	}

}
