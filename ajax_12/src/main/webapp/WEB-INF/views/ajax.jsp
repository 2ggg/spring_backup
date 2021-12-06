<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function ajaxTest(){
		//jquery 문법
		$.ajax({
			url : "ajax_result",
			type : "GET",
			success : function(data){//data는 통신 성공하면 전달받는 값
				$("#count").text(data) //id : count
				console.log("성공")
				},
			error : function(){alert('통신 실패')}
		})
		//컨트롤러에서 get매핑 해준 이름,
		//get 방식,
		//성공하면 이 기능 실행,
		//에러나면 이 기능 실행
	}
</script>

</head>
<body>
비동기식이기 때문에 특정 부분만 서버와 연동됨
	
	
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<h1>일</h1>
	<button type="button" onclick="ajaxTest()">클릭</button>
	<label id="count">0</label><!-- 여기서 0이 data. 버튼을 클릭하면 숫자가 카운트됨 -->
	
</body>
</html>