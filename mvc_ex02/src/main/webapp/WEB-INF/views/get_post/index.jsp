<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>get_post index</title>
</head>
<body>
	<h1>get 방식 전송</h1>
	<form action="${contextPath }/my/result" method="get">
		<input type="text" name="name"><br>
		<input type="text" name="age"><br>
		<input type="submit" value="전송"><br>
	</form>
	
	<h1>post 방식 전송</h1>
	<form action="${contextPath }/my/result" method="post">
		<input type="text" name="name"><br>
		<input type="text" name="age"><br>
		<input type="submit" value="전송"><br>
	</form>
</body>
</html>