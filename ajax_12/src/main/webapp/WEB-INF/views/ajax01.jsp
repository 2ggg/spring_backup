<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax01</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function ajax(){
		var nam = $("#name").val(); //jquery 방식
		var ag = document.getElementById("age").value; //javascript 방식
		var adr = $("#addr").val();
		var msg = $("#msg").val();
		var form = {name:nam, age:ag, addr:adr} //javascript의 오브젝트 
		//json 방식 :문자열 방식으로 주고받아서 통신이 빠름
		//키 : 값 (map같은 역할)
		console.log(form)
		$.ajax({
			url : "ajax_result01", type: "POST",
			data : JSON.stringify(form), //JavaScript 값이나 객체를 JSON 문자열로 변환(디펜던시 추가해야 사용 가능)
			//서버로 보낼 데이터 : {name:홍길동, age:20, addr:어딘가}
			dataType : "json", //서버로 리턴받는 값의 타입 지정 : json
			//서버로 보낼 데이터 설정
			contentType : "application/json; charset=utf-8",
			success : function(result){
				$("#label").text(result.name+" : "+result.age+" : "+result.addr+"	해당 아이디는 사용 가능합니다.")
			},error : function(){alert("문제 발생")}
		})
	}
</script>
</head>
<body>
	<input type="text" id="name" placeholder="이름"><br>
	<input type="text" id="age" placeholder="나이"><br>
	<input type="text" id="addr" placeholder="주소"><br>
	<input type="button" onclick="ajax()" value="저장"><br>
	<hr> 
	결과 >> <label id="label"></label>
</body>
</html>