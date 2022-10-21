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
Optional<String> positive = Optional.ofNullable((String)request.getAttribute("positive"));
Optional<String> neutral = Optional.ofNullable((String)request.getAttribute("neutral"));
Optional<String> negative = Optional.ofNullable((String)request.getAttribute("negative"));

%>

<body>
<H1>Result</H1>
<H3>文章：<%= string.orElse("ERROR") %></H3>
<H3>結果：<%= message.orElse("ERROR") %></H3>
<H3>Sentiment:</H3>
<H3>		Positive: <%= positive.orElse("ERROR") %></H3>
<H3>		Neutral: <%= neutral.orElse("ERROR") %></H3>
<H3>		Negative: <%= negative.orElse("ERROR") %></H3>
</body>
</html>