<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title><spring:message code="member.register"/></title>
 </head>
 <body>
  회원가입을 하시려면.........
<form action='regist'  method='post'>
	<input type='checkbox' name="agree" required/> 동의
	<input type='submit' value='다음' />
</form>
 </body>
</html>
