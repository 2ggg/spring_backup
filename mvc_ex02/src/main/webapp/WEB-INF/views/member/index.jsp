<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
index<br>

<img src="resources/cat.png" width="100px" height="auto">
<img src="${contextPath }/resources/cat.png" width="100px" height="auto">
<img src="<c:url value='/resources/cat.png'>" width="100px" height="auto">

<a href="${contextPath }/member/register_view">회원가입</a>
<a href="${contextPath }/member/member_list">모든 회원 보기</a>
</body>
</html>