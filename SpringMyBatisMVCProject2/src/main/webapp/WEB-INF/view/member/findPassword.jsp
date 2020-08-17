<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findPassword.jsp</title>
</head>
<body>
<form action="findPasswordPro" method="poat" name="frm">
	아이디 : <input type="text" name="userId"><br />
	이메일 : <input type="text" name="userEmail"><br />
	<div>${err }</div>
	<input type="submit" value="전송">	
</form>
</body>
</html>