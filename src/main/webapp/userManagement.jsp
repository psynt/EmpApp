<%@page import="com.emp.dao.User.UserType"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="com.emp.repo.Repository"%>
<%@ page import="com.emp.dao.Product"%>
<%@ page import="com.emp.dao.Order"%>
<%@ page import="com.emp.dao.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>User Management</title>
</head>
<body>


	<%
	if (session.getAttribute("user") == null)
		response.sendError(401);
	final User user = Repository.USERS.get(session.getAttribute("user"));
	if (user == null)
		response.sendError(401);
	if (user.getType() != UserType.Admin)
		response.sendError(403);
	%>

	<h3>Hello Admin ${user}</h3>
	<a href="cart.jsp">view cart</a> <br/>
	<a href="place">place order</a> <br/>
	<a href="history.jsp">view purchase history</a> <br/>
	<a href="StoreFront.jsp">return to store</a><br/>
	<a href="profile.jsp">view my profile</a> <br/>
	<a href="userManagement.jsp">admin panel</a> <br/>
	<a href="logout">logout</a> <br/>
	<br/>
	<br /> Here is our list of users:
	<table>
		<tr>
			<th>Name:</th>
			<th>Last order:</th>
		</tr>
		<%
		final List<User> users = Repository.getUsersByOrderDateDesc();
		for ( User u : users ){
			out.println("<tr><td>" + u.getUsername() + "</td><td>" + u.getSnippet() + "</td></tr>");
		}
		%>
	</table>
</body>
</html>