<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>buy_form</title>
</head>
<body>
구매 개수 입력<br>
<form action="buy">
	<input type="text" name="num"><br>
	<input type="submit" value="구매">
	<input type="button" value="db결과 확인" onclick="location.href='db_result'"><br>
</form>
</body>
</html>