<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${user}'s profile</title>
</head>
<body>

<h3>Change password</h3>
	<a href="cart.jsp">view cart</a> <br/>
	<a href="place">place order</a> <br/>
	<a href="history.jsp">view purchase history</a> <br/>
	<a href="StoreFront.jsp">return to store</a><br/>
	<a href="profile.jsp">view my profile</a> <br/>
	<a href="userManagement.jsp">admin panel</a> <br/>
	<a href="logout">logout</a> <br/>
<form action="passwd" method="post">
<label>New Password:</label><input type="text" name="pass">
<br/>
<input type="submit" value="change password">
</form>

</body>
</html>