<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>non ajax</title>
<script type="text/javascript">
	function test(){
		location.href="non_ajax";
	}
</script>
</head>
<body>
동기식이기 때문에 전체 문서가 다시 로드됨
	
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<button type="button" onclick="test()">클릭</button>
</body>
</html>