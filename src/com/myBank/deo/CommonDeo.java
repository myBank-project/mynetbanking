package com.myBank.deo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.myBank.entity.CustomerPassbookOrViewTransaction;
import com.myBank.entity.Transaction;
import com.myBank.entity.ViewCustomer;
import com.myBank.other.TransactionType;

public class CommonDeo {
	private static DataSource dataSource;

	public CommonDeo(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public static List<CustomerPassbookOrViewTransaction> getCustomerPassbook(int customerId) throws SQLException {
		System.out.println("after query" + customerId);
		Connection conn = dataSource.getConnection();
		System.out.println("after query2" + customerId);

		String sql = "select * from transactions where customer_id=? order by transaction_date asc";
		PreparedStatement prptStmt = conn.prepareStatement(sql);
		prptStmt.setInt(1, customerId);
		ResultSet rs = prptStmt.executeQuery();

		List<CustomerPassbookOrViewTransaction> customerPassbook = new ArrayList<>();
		while (rs.next()) {
			int senderAccountNumber = rs.getInt("sender_account_no");
			int receiverAccountNumber = rs.getInt("receiver_account_no");
			String transactionType = rs.getString("transaction_type");
			int sendAmount = rs.getInt("transaction_amount");
			Date transferDate = rs.getDate("transaction_date");
			CustomerPassbookOrViewTransaction transaction = new CustomerPassbookOrViewTransaction(0,
					senderAccountNumber, receiverAccountNumber, transferDate, transactionType, sendAmount);
			System.out.println("trying.. " + transaction);
			customerPassbook.add(transaction);
		}

		return customerPassbook;
	}

	public static boolean updateProfile(int customerId, String firstName, String lastName, String customerAddress)
			throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "UPDATE customer set first_name =?,last_name = ?,customer_address = ? WHERE customer_id = ?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setString(1, firstName);
		prpStmt.setString(2, lastName);
		prpStmt.setString(3, customerAddress);
		prpStmt.setInt(4, customerId);
		int rs = prpStmt.executeUpdate();
		return rs == 1 ? true : false;

	}

	public List<CustomerPassbookOrViewTransaction> getTransactionByDate(Date transactionDate) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select * from transaction where transaction_date is ?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setDate(1, transactionDate);
		ResultSet rs = prpStmt.executeQuery();
		List<CustomerPassbookOrViewTransaction> allTransactionByDate = null;
		while (rs.next()) {
			int transactionId = rs.getInt("transaction_no");
			int senderAccountNo = rs.getInt("sender_account_no");
			int receiverAccountNo = rs.getInt("receiver_account_no");
			Date transactiondate = rs.getDate("transaction_date");
			String transactionType = rs.getString("transaction_type");
			int transactionAmount = rs.getInt("transaction_amount");

			CustomerPassbookOrViewTransaction transaction = new CustomerPassbookOrViewTransaction(transactionId,
					senderAccountNo, receiverAccountNo, transactiondate, transactionType, transactionAmount);
			allTransactionByDate.add(transaction);

		}

		return allTransactionByDate;
	}

	public List<Transaction> getTransactionByType(String transactionType) throws SQLException {
		Connection conn = dataSource.getConnection();
		String transactiontype = transactionType.toString();
		String sql = "select * from transaction where transaction_type is ?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setString(1, transactiontype);
		ResultSet rs = prpStmt.executeQuery();
		List<Transaction> allTransactionByType = null;
		while (rs.next()) {
			int transactionId = rs.getInt("transaction_no");
			int senderAccountNo = rs.getInt("sender_account_no");
			int receiverAccountNo = rs.getInt("receiver_account_no");
			Date transactiondate = rs.getDate("transaction_date");
			String transactiontype1 = rs.getString("transaction_type");
			int transactionAmount = rs.getInt("transaction_amount");

			Transaction transaction = new Transaction(transactionId,
					senderAccountNo, receiverAccountNo, transactiondate, transactiontype1, transactionAmount);
			allTransactionByType.add(transaction);

		}

		return allTransactionByType;
	}

	public List<Transaction> getTransactionBySenderAccountNo(int senderAccountNo)
			throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select sender_account_no,receiver_account_no,transaction_type,transaction_amount,transaction_date from transactions where sender_account_no = ?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setInt(1, senderAccountNo);
		ResultSet rs = prpStmt.executeQuery();
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

	public List<Transaction> getTransactionByReceiverAccountNo(int receiverAccountNo)
			throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select sender_account_no,receiver_account_no,transaction_type,transaction_amount,transaction_date from transactions where receiver_account_no = ?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setInt(1, receiverAccountNo);
		ResultSet rs = prpStmt.executeQuery();
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

	public boolean updatePassword(int customerId, String password) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "UPDATE users set password_hash=? where username=?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		System.out.println("username is: "+customerId);
		prpStmt.setString(1, password);
		
		prpStmt.setString(2, Integer.toString(customerId));
		int rs = prpStmt.executeUpdate();

		return rs == 1 ? true : false;
	}

	public List<CustomerPassbookOrViewTransaction> SortByOldestOneInPassBook(int customerId) throws SQLException {
		System.out.println("after query" + customerId);
		Connection conn = dataSource.getConnection();
		System.out.println("after query2" + customerId);

		String sql = "select * from transactions where customer_id=? order by transaction_date desc";
		PreparedStatement prptStmt = conn.prepareStatement(sql);
		prptStmt.setInt(1, customerId);
		ResultSet rs = prptStmt.executeQuery();

		List<CustomerPassbookOrViewTransaction> customerPassbook = new ArrayList<>();
		while (rs.next()) {
			int senderAccountNumber = rs.getInt("sender_account_no");
			int receiverAccountNumber = rs.getInt("receiver_account_no");
			String transactionType = rs.getString("transaction_type");
			int sendAmount = rs.getInt("transaction_amount");
			Date transferDate = rs.getDate("transaction_date");
			CustomerPassbookOrViewTransaction transaction = new CustomerPassbookOrViewTransaction(0,
					senderAccountNumber, receiverAccountNumber, transferDate, transactionType, sendAmount);
			System.out.println("trying.. " + transaction);
			customerPassbook.add(transaction);
		}

		return customerPassbook;
	}

	public List<CustomerPassbookOrViewTransaction> SearchByReceiverAccount(int customerId, int receiverAccountNumber2) throws SQLException {
		Connection conn = dataSource.getConnection();

		String sql = "select * from transactions where customer_id=? AND receiver_account_no=?";
		PreparedStatement prptStmt = conn.prepareStatement(sql);
		prptStmt.setInt(1, customerId);
		prptStmt.setInt(2, receiverAccountNumber2);
		ResultSet rs = prptStmt.executeQuery();

		List<CustomerPassbookOrViewTransaction> customerPassbook = new ArrayList<>();
		while (rs.next()) {
			int senderAccountNumber = rs.getInt("sender_account_no");
			int receiverAccountNumber = rs.getInt("receiver_account_no");
			String transactionType = rs.getString("transaction_type");
			int sendAmount = rs.getInt("transaction_amount");
			Date transferDate = rs.getDate("transaction_date");
			CustomerPassbookOrViewTransaction transaction = new CustomerPassbookOrViewTransaction(0,
					senderAccountNumber, receiverAccountNumber, transferDate, transactionType, sendAmount);
			System.out.println("trying.. " + transaction);
			customerPassbook.add(transaction);
		}

		return customerPassbook;
	}

	public List<ViewCustomer> searchCustomerByAccountNumber(int accountNo) throws SQLException {
		
		Connection conn = dataSource.getConnection();

		String sql = "select first_name,last_name,account_number,balance from customer where account_number=?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setInt(1, accountNo);
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

	public List<ViewCustomer> searchByFirstName(String firstName) throws SQLException {
		Connection conn = dataSource.getConnection();

		String sql = "select first_name,last_name,account_number,balance from customer where first_name=?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setString(1, firstName);
		ResultSet rs = prpStmt.executeQuery();
		List<ViewCustomer> customers = new ArrayList<>();
		while (rs.next()) {
			String firstName2 = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			int accountNumber = rs.getInt("account_number");
			int balance = rs.getInt("balance");
			ViewCustomer customer1 = new ViewCustomer(firstName2, lastName, accountNumber, balance);
			System.out.println("trying.. " + firstName);
			customers.add(customer1);
		}
		return customers;
	}

}
