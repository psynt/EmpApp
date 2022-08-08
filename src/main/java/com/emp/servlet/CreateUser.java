package com.emp.servlet;

import java.io.IOException;

import com.emp.dao.User;
import com.emp.dao.User.UserType;
import com.emp.repo.Repository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/createUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		final String un = request.getParameter("user");
		final String pw = request.getParameter("pass");
		
		if (Repository.USERS.containsKey(un)) {
			response.sendRedirect("register.html");
			return;
		}
		
		if (un == null || pw == null || un.length() == 0 || pw.length() == 0) {
			response.sendRedirect("register.html");
			return;
		}
		
		final String type = request.getParameter("role");
		final User user = new User(un, pw, type.equals("Admin") ? UserType.Admin : UserType.Standard);
		Repository.USERS.put(un, user);
		request.getSession().setAttribute("user", un);
		response.sendRedirect(user.getRedirectUrl());
		
	}

}
