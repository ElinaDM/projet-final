<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login page</title>
</head>

<body>
	<form action="/login" method="post">
		<input type="text"  name="username" placeholder="Enter Username">
		<input type="password" name="password" placeholder="Enter Password">
		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
		<input type="submit" value="Log in">
	</form>
</body>
</html>