<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout.jsp</title>
</head>
<body>
<h1>request : <%=request.getAttribute("key") %></h1>
<h1>requestScope : ${requestScope.key }</h1>
<h1>request(el) : ${key }</h1>가장 작은 범위는 앞에 생략 가능
</body>
</html>