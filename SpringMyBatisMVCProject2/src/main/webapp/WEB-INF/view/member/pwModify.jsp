<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pwModify.jsp</title>
</head>
<body>

<form action = "pwModify1" method="post" name="frm" >
	<input type="hidden" name="userId" value="${userId}" />
	비밀번호 : <input type="password" name = "userPw" id = "pw" /><br />
	<div>${err }</div>
	<br />
	<input type="submit" value="확인" />
</form>
</body>
</html>