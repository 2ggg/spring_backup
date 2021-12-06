<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getuser</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script><!-- jquery문법 사용할때 필요 -->
<script type="text/javascript">
	function getUsers(){
		$.ajax({
			url : "users", type : "get", dataType:"json",
			success : function(list){
				//$("#users").text(list) <object, Object> 이런 형태로 보여지게 됨
				/*
				console.log(list)
				let html = "<b>이름 : </b>"+list[0].name+"님<br>"
				html += "<b>나이 : </b>"+list[0].age+"살<br>"
				
				html += "<b>이름 : </b>"+list[1].name+"님<br>"
				html += "<b>나이 : </b>"+list[1].age+"살<br>"
				
				let html = ""
				for(var i=0; i<list.length; i++){
					html += "<b>이름 : </b>"+list[i].name+"님<br>"
					html += "<b>나이 : </b>"+list[i].age+"살<hr>"
				}*/
				let html = ""
				$.each(list, function(index, item){
					html += "<b>이름 : </b>"+item.name+"님<br>"
					html += "<b>나이 : </b>"+item.age+"살<hr>"
				})
				//$("#users").html(html) //html : 현재 있는 내용을 무시하고 덮어쓰기
				$("#users").append(html) //append : 현재 데이터에서 마지막에 추가함. 더보기 기능
			}
		})
	}
	
	function userInfo(){
		var userId = $("#userId").val()
		//location.href = "경로?id="+userId
		$.ajax({//id로 정보 찾기
			//url : "user?id="+userId,	//경로 : user?id=홍길동1
			url : "user/"+userId,		//경로 : user/홍길동1
			//둘 중 골라쓰기
			type : "get",
			dataType : "json",
			success : function(dto){
				let html = "<b>이름 : </b>"+dto.name+"님<br>"
				html += "<b>나이 : </b>"+dto.age+"살<hr>"
				$("#users").html(html)
			}
		})
	}
	function modify(){
		let name = $("#name").val();
		let age = $("#age").val();
		let form = {name:name, age:age}
		$.ajax({
			url : "modify",
			type : "put",
			dataType : "json",
			data : JSON.stringify(form),
			contentType : "application/json; charset=utf=8",
			success : function(dto){
				let html = "<b>이름 : </b>"+dto.name+"님<br>"
				html += "<b>나이 : </b>"+dto.age+"살<hr>"
				$("#users").html(html)
			}
		})
	}
	/*
	function membership(){
		let uId = $("#uId").val();
		let uName = $("#uName").val();
		let uAge = $("#uAge").val();
		let uAddr = $("#uAddr").val();
		let uPhone = $("#uPhone").val();
		
		let form = {uId:uId, uName:uName, uAge:uAge, uAddr:uAddr, uPhone:uPhone}
		$.ajax({
			url:"membership", type:"post", dataType:"json",
			data : JSON.stringify(form),
			contentType:"application/json;  charset=utf-8",
			success:function(){
				alert('저장 성공')
			}
		})
	}*/
	
	function membership(){
		/*
		let uId = $("#uId").val();
		let uName = $("#uName").val();
		let uAge = $("#uAge").val();
		let uAddr = $("#uAddr").val();
		let uPhone = $("#uPhone").val();
		
		let form = {uId:uId, uName:uName, uAge:uAge, uAddr:uAddr, uPhone:uPhone}
		*/
		let form = {}
		let arr = $("#fo").serializeArray()//키, 값 쌍으로 된 array
		fonsole.log(arr)
		for(var i=0; i<arr.length; i++){//위에걸 반복문으로 바꿔줌
			form[arr[i].name] = arr[i].value
		}
		
		$.ajax({
			url:"membership", type:"post", 
			dataType:"json",
			data : JSON.stringify(form),
			contentType:"application/json; charset=utf-8",
			success:function(){
				alert('저장 성공')
			}
		})
	}
</script>
</head>
<body>
getuser.jsp<br>
	<span id="users"></span>
	<hr>
	<button type="button" onclick="getUsers()">사용자 목록 보기</button>
	<hr>
	<input type="text" id="userId">
	<button type="button" onclick="userInfo()">개인 정보 보기</button>
	<hr>
	<input type="text" id="name" placeholder="수정할 이름 입력"><br>
	<input type="text" id="age" placeholder="수정할 나이 입력"><br>
	<button onclick="modify()">수정</button>
	
	<hr>
	<h3>회원가입</h3>
	<form id="fo">
		<input type="text" id="uId" name="uId" placeholder="id"><br>
		<input type="text" id="uName" name="uName" placeholder="uName"><br>
		<input type="text" id="uAge" name="uAge" placeholder="uAge"><br>
		<input type="text" id="uAddr" name="uAddr" placeholder="uAddr"><br>
		<input type="text" id="uPhone" name="uPhone" placeholder="uPhone"><br>
		<input type="button" onclick="membership()" value="회원가입"><br>
	</form>
	
</body>
</html>