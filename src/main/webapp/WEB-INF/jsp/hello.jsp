<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String message = 
		(String) request.getAttribute("message");

%>

<body>
<H1><%= message %></H1>
</body>
</html>