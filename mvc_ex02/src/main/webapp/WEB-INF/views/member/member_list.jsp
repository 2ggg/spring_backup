<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register_list</title>
</head>
<body>register_list<br>
<table>
	<tr>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
	</tr>
	<c:choose>
		<c:when test="${list.size() !=0}">
			<c:forEach var="mem" items="${list }">
				<tr>
					<td>${mem.id }</td>
					<td>${mem.pwd }</td>
					<td>${mem.name }</td>
				</tr>
			</c:forEach>
			
		</c:when>
		<c:otherwise>
			<tr><th colspan="3">데이터 존재</th></tr>
		<tr><th colspan="3">
			데이터 없음
			</th>
		</tr>
		</c:otherwise>
	</c:choose>
	<tr><td colspan="3"><a href="index">index이동</a></td></tr>
</table>
</body>
</html>