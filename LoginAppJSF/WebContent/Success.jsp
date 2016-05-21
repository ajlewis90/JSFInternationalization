<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="org.arthur.mvcdemo.model.User"%>
<%@page import="java.util.ResourceBundle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
.topcorner {
	position: absolute;
	top: 0;
	right: 0;
}
</style>

<title>Success</title>
</head>
<body>

	<div class="topcorner">
		<a href="logout.jsp">Logout</a>
	</div>
	
	<jsp:useBean id="user" class="org.arthur.mvcdemo.model.User" scope="session">
		<jsp:setProperty property="userName" name="user"/>
	</jsp:useBean>
	
	<%
		ResourceBundle rb = (ResourceBundle) session.getAttribute("languageTag");
		response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
		response.addHeader("Pragma", "no-cache"); 
		response.addDateHeader ("Expires", 0);
	%>
	
	<%=rb.getString("HELLO")%>, <jsp:getProperty property="userName" name="user"/>, <%=session.getAttribute("userName")%>
	<h3><%=rb.getString("SUCCESS")%></h3>

	<br><%=rb.getString("WELCOME")%>

</body>
</html>