<%@ page import="java.util.Map"%>
<%@ page import="com.emp.repo.Repository"%>
<%@ page import="com.emp.dao.Product"%>
<%@ page import="com.emp.dao.User"%>
<%@ page import="com.emp.dao.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World</title>
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
	<br /> Here are your previous purchases:
	<br />
	<table>
		<tr>
			<th>Order #</th>
			<th>Name</th>
			<th>Unit Price</th>
			<th>Qty</th>
			<th>Total Price</th>
		</tr>
		<%
		final User user = Repository.USERS.get(session.getAttribute("user"));
		if (user == null)
			response.sendError(401);
		session.setAttribute("from", "history.jsp");
		for (Order order : user.getPastOrders()) {
			for (Map.Entry<Product, Integer> entry : order.products().entrySet()) {
				Product p = entry.getKey();
				int qty = entry.getValue();
				out.println("<tr><td>" + order.id() + "</td><td>" + p.name() + "</td><td>" + p.price() + "</td><td>" + qty
				+ "</td><td>" + p.price() * qty + "</td></tr>");
			}
		}
		%>
	</table>

</body>
</html>