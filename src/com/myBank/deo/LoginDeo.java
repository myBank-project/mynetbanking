package com.myBank.deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.myBank.other.Role;

public class LoginDeo {
	private DataSource dataSource;
	
	
	public LoginDeo(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		// TODO Auto-generated constructor stub
	}


	public boolean checkLogin(String username, String password, Role role) throws SQLException {
		Connection conn=dataSource.getConnection();
		String sql="select * from users where username=? and password_hash=? and role=?";
		PreparedStatement prpStmt=conn.prepareStatement(sql);
		prpStmt.setString(1, username);
		prpStmt.setString(2, password);
		prpStmt.setString(3, role.name());
		ResultSet rs=prpStmt.executeQuery();
		String userName=null;
		while(rs.next()) {
			userName=rs.getString("username");
		}
		return userName!=null?true:false;
	}

}
