package com.emp.servlet;

import java.io.IOException;

import com.emp.dao.Product;
import com.emp.dao.User;
import com.emp.repo.Repository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddItem
 */
@WebServlet("/addItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddItem() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String uname = (String) request.getSession().getAttribute("user");
		if (uname == null) {
			response.sendError(401);
			return;
		}
		
		final String productName = request.getParameter("item");
		if (!Repository.PRODUCTS.containsKey(productName)) {
			response.sendError(404);
			return;
		}
		
		final Product product = Repository.PRODUCTS.get(productName);
		final User user = Repository.USERS.get(uname);
		try {
			user.addToCart(product, Integer.parseInt(request.getParameter("qty")));
		} catch (NumberFormatException e) {
			user.addToCart(product);
		}
		response.sendRedirect((String) request.getSession().getAttribute("from"));
	}

}
