package com.myBank.deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.myBank.other.TransactionType;

public class CustomerDeo {

	private DataSource dataSource;

	public CustomerDeo(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		// TODO Auto-generated constructor stub
	}

	public int getCurrentBalance(int customerId) throws SQLException {
		int currentBalance = 0;
		Connection conn = dataSource.getConnection();
		String sql = "select balance from customer where customer_id=?";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.setInt(1, customerId);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			currentBalance = rs.getInt("balance");

		}
		return currentBalance;
	}

	public boolean reduceSenderBalance(int customerId, int balanceAfterTransaction) throws SQLException {
		boolean transaction = false;
		Connection conn = dataSource.getConnection();
		String sql = "UPDATE customer set balance=? where customer_id=?";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.setInt(1, balanceAfterTransaction);
		prepStmt.setInt(2, customerId);
		int rs = prepStmt.executeUpdate();
		return rs == 1 ? true : false;
	}

	public int getCustomerAccountNo(int customerId) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "select account_number from customer where customer_id=?";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setString(1, Integer.toString(customerId));
		ResultSet rs = prpStmt.executeQuery();
		int accountNumber = 0;
		while (rs.next()) {
			accountNumber = rs.getInt(1);
		}
		return accountNumber;
	}

	public boolean addTransactionRecord(int customerId, int transferAmount, int receiverAccountNumber,
			int currentUserAccNo, TransactionType transactionType) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "insert into transactions(sender_account_no,receiver_account_no,transaction_type,transaction_amount,customer_id) values(?,?,?,?,?)";
		PreparedStatement prpStmt = conn.prepareStatement(sql);
		prpStmt.setInt(1, currentUserAccNo);
		prpStmt.setInt(2, receiverAccountNumber);
		prpStmt.setString(3, transactionType.name());
		prpStmt.setInt(4, transferAmount);
		prpStmt.setInt(5, customerId);

		int rs = prpStmt.executeUpdate();

		return rs == 1 ? true : false;
	}

}
