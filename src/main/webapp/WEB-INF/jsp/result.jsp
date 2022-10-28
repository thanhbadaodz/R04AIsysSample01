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
	Optional<String> message = Optional.ofNullable((String) request.getAttribute("message"));
Optional<String> string = Optional.ofNullable((String) request.getAttribute("string"));
Optional<String> sentiment_text = Optional.ofNullable((String)request.getAttribute("sentiment_text"));

%>

<body>
<H1>Result</H1>
<H3>文章：<%= string.orElse("") %></H3>
<H3><%= message.orElse("") %></H3>
<H3><%= sentiment_text.orElse("") %></H3>
</body>
</html>