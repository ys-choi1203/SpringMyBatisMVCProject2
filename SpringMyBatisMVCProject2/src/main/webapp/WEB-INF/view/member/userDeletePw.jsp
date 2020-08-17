<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
	src="http://code.jquery.com/jquery-latest.js" ></script>
<script type="text/javascript">
$(function(){
	$("#bts").click(function(){
		if(confirm("정말 진짜 사실 real 탈퇴?")){
			$("#frm").submit();
		}
	});
});
</script>
</head>
<body>
<form action = "memberUserDelPro" method="post" name="frm" id ="frm">
	비밀번호 : <input type="password" name = "userPw" id = "pw" /><br />
	<div>${err }</div>
	<br />
	<input type="button" value="확인" id ="bts" />
</form>
</body>
</html>