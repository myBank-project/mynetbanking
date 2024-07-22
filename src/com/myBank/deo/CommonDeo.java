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
import com.myBank.other.TransactionType;

public class CommonDeo {
	private static DataSource dataSource;

	public CommonDeo(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public static List<CustomerPassbookOrViewTransaction> getCustomerPassbook(int customerId) throws SQLException {
		System.out.println("after query"+customerId);
		Connection conn = dataSource.getConnection();
		System.out.println("after query2"+customerId);
		
		String sql = "select * from transactions where customer_id=?";
		PreparedStatement prptStmt = conn.prepareStatement(sql);
		prptStmt.setInt(1, customerId);
		ResultSet rs = prptStmt.executeQuery();
		
		List<CustomerPassbookOrViewTransaction> customerPassbook = new ArrayList<>();
		while (rs.next()) {
			int senderAccountNumber = rs.getInt("sender_account_no");
			int receiverAccountNumber = rs.getInt("reciever_account_no");
			String transactionType = rs.getString("transaction_type");
			int sendAmount = rs.getInt("transfer_amount");
			Date transferDate = rs.getDate("transaction_date");
			CustomerPassbookOrViewTransaction transaction = new CustomerPassbookOrViewTransaction(0,
					senderAccountNumber, receiverAccountNumber, transferDate, transactionType, sendAmount);
			System.out.println("trying.. "+transaction);
			customerPassbook.add(transaction);
		}

		return customerPassbook;
	}

	public static boolean updateProfile(int customerId, String firstName, String lastName, String password,
			String email, String customerAddress) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "UPDATE customer set first_name = COALESCE(?, customer.first_name),\r\n"
				+ "    last_name = COALESCE(?, customer.last_name),\r\n"
				+ "    password = COALESCE(?, customer.password),\r\n"
				+ "    customer_address = COALESCE(?, customer.customer_address),\r\n"
				+ "    email = COALESCE(?, customer.email) where customer_id=?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setString(1, firstName);
		prpStmt.setString(2, lastName);
		prpStmt.setString(3, password);
		prpStmt.setString(4, customerAddress);
		prpStmt.setString(5, email);
		prpStmt.setInt(6, customerId);
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

	public List<CustomerPassbookOrViewTransaction> getTransactionByType(String transactionType)
			throws SQLException {
		Connection conn = dataSource.getConnection();
		String transactiontype = transactionType.toString();
		String sql = "select * from transaction where transaction_type is ?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setString(1, transactiontype);
		ResultSet rs = prpStmt.executeQuery();
		List<CustomerPassbookOrViewTransaction> allTransactionByType = null;
		while (rs.next()) {
			int transactionId = rs.getInt("transaction_no");
			int senderAccountNo = rs.getInt("sender_account_no");
			int receiverAccountNo = rs.getInt("receiver_account_no");
			Date transactiondate = rs.getDate("transaction_date");
			String transactiontype1 = rs.getString("transaction_type");
			int transactionAmount = rs.getInt("transaction_amount");

			CustomerPassbookOrViewTransaction transaction = new CustomerPassbookOrViewTransaction(transactionId,
					senderAccountNo, receiverAccountNo, transactiondate, transactiontype1, transactionAmount);
			allTransactionByType.add(transaction);

		}

		return allTransactionByType;
	}

	public List<CustomerPassbookOrViewTransaction> getTransactionBySenderAccountNo(int senderAccountNo)
			throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select * from transaction where sender_account_no is ?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setInt(1, senderAccountNo);
		ResultSet rs = prpStmt.executeQuery();
		List<CustomerPassbookOrViewTransaction> allTransactionBySenderAccountNo = null;
		while (rs.next()) {
			int transactionId = rs.getInt("transaction_no");
			int senderAccountNumber = rs.getInt("sender_account_no");
			int receiverAccountNo = rs.getInt("receiver_account_no");
			Date transactiondate = rs.getDate("transaction_date");
			String transactionType = rs.getString("transaction_type");
			int transactionAmount = rs.getInt("transaction_amount");

			CustomerPassbookOrViewTransaction transaction = new CustomerPassbookOrViewTransaction(transactionId,
					senderAccountNumber, receiverAccountNo, transactiondate, transactionType, transactionAmount);
			allTransactionBySenderAccountNo.add(transaction);
		}
		return allTransactionBySenderAccountNo;
	}

	public List<CustomerPassbookOrViewTransaction> getTransactionByReceiverAccountNo(int receiverAccountNo) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select * from transaction where receiver_account_no is ?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setInt(1, receiverAccountNo);
		ResultSet rs = prpStmt.executeQuery();
		List<CustomerPassbookOrViewTransaction> allTransactionByReceiverAccountNo = null;
		while (rs.next()) {
			int transactionId = rs.getInt("transaction_no");
			int senderAccountNumber = rs.getInt("sender_account_no");
			int receiverAccountNumber = rs.getInt("receiver_account_no");
			Date transactiondate = rs.getDate("transaction_date");
			String transactionType = rs.getString("transaction_type");
			int transactionAmount = rs.getInt("transaction_amount");

			CustomerPassbookOrViewTransaction transaction = new CustomerPassbookOrViewTransaction(transactionId,
					senderAccountNumber, receiverAccountNumber, transactiondate, transactionType, transactionAmount);
			allTransactionByReceiverAccountNo.add(transaction);
		}
		return allTransactionByReceiverAccountNo;
	}

}
