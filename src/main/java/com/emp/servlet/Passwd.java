package com.emp.servlet;

import java.io.IOException;

import com.emp.dao.User;
import com.emp.repo.Repository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Passwd
 */
@WebServlet("/passwd")
public class Passwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uname = (String) request.getSession().getAttribute("user");
		if (uname == null) {
			response.sendError(401);
			return;
		}
		final String newPass = request.getParameter("pass");
		if (newPass == null) {
			response.sendRedirect("profile.jsp");
		}
		final User user = Repository.USERS.get(uname);
		user.setPassword(newPass);
		response.sendRedirect("logout");
	}

}
