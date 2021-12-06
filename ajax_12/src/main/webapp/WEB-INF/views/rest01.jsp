<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script><!-- jquery문법 사용할때 필요 -->
<script type="text/javascript">
	function ajaxGet(){
		$.ajax({ //jquery문법 
			url : "rest", 
			type:"get", 
			dataType:"json", //리턴으로 돌아온 값
			success : function(data){$("#label").text(data.execute)}
		//id가 label인 곳에 / text: text를 넣어라/ data.execute : 서버에서 받아온 데이터
			
		})
	}
	function ajaxPost(){
		$.ajax({ //jquery문법 
			url : "rest", 
			type:"post", 
			dataType:"json", //리턴으로 돌아온 값
			success : function(data){$("#label").text(data.execute)}
			
		})
	}
	function ajaxPut(){
		$.ajax({ //jquery문법 
			url : "rest", 
			type:"put", 
			dataType:"json", //리턴으로 돌아온 값
			success : function(data){$("#label").text(data.execute)}
			
		})
	}
	function ajaxDelete(){
		$.ajax({ //jquery문법 
			url : "rest", 
			type:"delete", 
			dataType:"json", //리턴으로 돌아온 값
			success : function(data){$("#label").text(data.execute)}
			
		})
	}
</script>
</head>
<body>rest01.jsp<br>
<label id="label"></label><hr>
<button type="button" onclick="ajaxGet()">get 호출</button>
<button type="button" onclick="ajaxPost()">post 호출</button>
<button type="button" onclick="ajaxPut()">put 호출</button>
<button type="button" onclick="ajaxDelete()">delete 호출</button>
</body>
</html>