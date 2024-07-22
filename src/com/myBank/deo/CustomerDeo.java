package com.myBank.deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

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

	public boolean performTransaction(int customerId, int balanceAfterTransaction) throws SQLException {
		boolean transaction = false;
		Connection conn = dataSource.getConnection();
		String sql = "UPDATE customer set balance=? where customer_id=?";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.setInt(1,balanceAfterTransaction);
		prepStmt.setInt(2, customerId);
		int rs=prepStmt.executeUpdate();
		return rs==1?true:false;
	}

}
