package com.myBank.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.myBank.deo.AdminDeo;
import com.myBank.deo.LoginDeo;
import com.myBank.other.Role;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/mybanknetbanking")
	private DataSource dataSource;
	private LoginDeo loginDeo;

	public void init() throws ServletException {
		super.init();
		loginDeo = new LoginDeo(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");
		if (command == null)
			command = "loginpage";
		switch (command) {
		case "login":
			dologin(request, response);
			break;

		default:
			System.out.println("going to login page");
			loginPage(request, response);
		}

	}

	private void loginPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatacher = request.getRequestDispatcher("/LoginPage.jsp");
		requestDispatacher.forward(request, response);

	}

	private void dologin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String roleinstr = request.getParameter("role");
		if (roleinstr == null) {
			return;
		}
		Role role = Role.valueOf(roleinstr.toUpperCase());
		boolean isExist = false;
		try {
			isExist = loginDeo.checkLogin(username, password, role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isExist == false) {
//			create exception of invalid username or password or role
			return;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		session.setAttribute("role", role);
		System.out.println("session is : " + session);
		String path;
		System.out.println(role);
		String usertype = "Admin";
		if (role.name().equals("ADMIN")) {
			path = "AdminHome";
		} else {
			usertype = "Customer";
			path = "customerHome";
		}
		System.out.println("usertype:" + usertype);
		response.sendRedirect("/mybanknetbanking/" + usertype + "?command=" + path);
//		RequestDispatcher requestDispatacher=request.getRequestDispatcher(path);
//		requestDispatacher.sendRedirect(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
