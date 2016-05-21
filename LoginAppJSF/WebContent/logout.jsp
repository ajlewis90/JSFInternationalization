<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ResourceBundle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Logout</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style type="text/css">
		.centerText {
			position: absolute;
			top: 50%;
			left: 50%;
		}
	</style>
</head>

<body>
	
	<div class="centerText">
		<%
			ResourceBundle rb = (ResourceBundle) session.getAttribute("languageTag");
			session.invalidate();				
		%>
		<%=rb.getString("LOGGEDOUT")%>

		<br>
		<a href="login.xhtml">Login</a>
	</div>


</body>
</html>