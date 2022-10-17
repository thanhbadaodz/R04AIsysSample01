<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	Optional<String> message = 
		Optional.ofNullable((String) request.getAttribute("message"));
Optional<String> string = 
	Optional.ofNullable((String) request.getAttribute("string"));

%>

<body>
<H1>DetectLanguage</H1>
<H3>文章：<%= string.orElse("ERROR") %></H3>
<H3>結果：<%= message.orElse("ERROR") %></H3>
</body>
</html>