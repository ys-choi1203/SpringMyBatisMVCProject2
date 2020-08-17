<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
	src="http://code.jquery.com/jquery-latest.js" ></script>
</head>
<body>
<form:form action ="pwModifyPro" method="post" name="frm" 
		id="frm" commandName="memberCommand">
	현재비밀번호 : <input type="password" name = "oldPw" 
	             id ="pw" />
	             <form:errors path="oldPw"/>
	             <br />
	새 비밀번호 : <input type="password" name = "userPw" 
	             id ="newPw" />
	             <form:errors path="userPw"/>
	             <br />
	새 비밀번호 확인 : <input type="password" name = "userPwCon" 
	             id ="reNewPw" />
	             <form:errors path="userPwCon"/>
	             <br />
	<input type="submit" value = "비밀번호변경" id ="sbm"/>
</form:form>	             
</body>
</html>