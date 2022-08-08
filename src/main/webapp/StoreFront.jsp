<%@ page import="com.emp.repo.Repository"%>
<%@ page import="com.emp.dao.Product"%>
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
	<br /> Here are out items:
	<br />
	<table>
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Score</th>
		</tr>
		<%
		session.setAttribute("from", "StoreFront.jsp");
		for (Product p : Repository.PRODUCTS.values()) {
			out.println("<tr><td>" + p.name() + "</td><td>" + p.price() + "</td><td>" + p.stars()
			+ "</td><td><a href=\"addItem?item=" + p.name() + "\">+</a></td></tr>");
		}
		%>
	</table>

</body>
</html>