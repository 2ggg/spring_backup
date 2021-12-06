<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
index.jsp
기본 페이지입니다.
<a href="http://localhost:8085/root/login">로그인</a><br>
<a href="login">로그인</a>상대경로<br>
<a href="${contextPath }/logout">로그아웃</a>절대경로<br>

</body>
</html>