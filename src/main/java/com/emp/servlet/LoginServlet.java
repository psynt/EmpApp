package com.emp.servlet;

import static com.emp.repo.Repository.USERS;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		final String un = request.getParameter("user");
		final String pw = request.getParameter("pass");

		if (!USERS.containsKey(un) || !USERS.get(un).getPassword().equals(pw)) {
			response.sendError(401);
			return;
		}
		
		request.getSession().setAttribute("user", un);
		
		final String redirectUrl = USERS.get(un).getRedirectUrl();
		response.sendRedirect(redirectUrl);
	}

}
