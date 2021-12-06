<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>index.jsp<br>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<img src="resources/삐진탱이.png" width="100px" height="100px">
<img src="${contextPath }/resources/삐진탱이.png" width="100px" height="100px">
<img src="<c:url value='/resources/삐진탱이.png'/>" width="100px" height="100px">
<hr>
<img src="resources/test/뼈대1.jfif" width="100px" height="100px">
<img src="css/다운로드.jfif" width="100px" height="100px">


	<form action="result">
		<input type="text" name="id"><br>
		<input type="submit" value="전송">
	</form>
</body>
</html>