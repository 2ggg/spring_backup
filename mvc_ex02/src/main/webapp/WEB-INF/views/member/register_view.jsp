<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register view</title>
</head>
<body>
	<h1>회원가입 페이지</h1>
	<form action="${contextPath }/member/register" method="post"></form>
		<input type="text" name="id" placeholder="input id"><br>
		<input type="password" name="pwd" placeholder="input pwd"><br>
		<input type="text" name="name" placeholder="input name"><br>
		<input type="submit" value="가입">
</body>
</html>