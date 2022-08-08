<%@ page import="java.util.Map"%>
<%@ page import="com.emp.repo.Repository"%>
<%@ page import="com.emp.dao.Product"%>
<%@ page import="com.emp.dao.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Viewing Cart</title>
</head>
<body>

	<%
	if (session.getAttribute("user") == null)
		response.sendError(401);
	%>

	<h3>
		Hello ${user}
	</h3>
	<a href="cart.jsp">view cart</a> <br/>
	<a href="place">place order</a> <br/>
	<a href="history.jsp">view purchase history</a> <br/>
	<a href="StoreFront.jsp">return to store</a><br/>
	<a href="profile.jsp">view my profile</a> <br/>
	<a href="userManagement.jsp">admin panel</a> <br/>
	<a href="logout">logout</a> <br/>
	<br/>
	<br /> Here is your cart:
	<br />
	<table>
		<tr>
			<th>Name</th>
			<th>Unit Price</th>
			<th>Qty</th>
			<th>Total Price</th>
		</tr>
		<%
		final User user = Repository.USERS.get(session.getAttribute("user"));
		if (user == null)
			response.sendError(401);
		session.setAttribute("from", "cart.jsp");
		for (Map.Entry<Product, Integer> entry : user.getCart().entrySet()) {
			Product p = entry.getKey();
			int qty = entry.getValue();
			out.println("<tr><td>" + p.name() + "</td><td>" + p.price() + "</td><td>" + qty + "</td><td>" + p.price() * qty
			+ "</td><td><a href=\"addItem?item=" + p.name() + "\">+</a></td><td><a href=\"addItem?item=" + p.name() + "&qty=-1\">-</a></td></tr>");
		}
		%>
	</table>
</body>
</html>