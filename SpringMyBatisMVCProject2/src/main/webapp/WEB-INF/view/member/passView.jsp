<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>passView.jsp</title>
</head>
<body>
${member.userName }님 임시비밀번호를 이메일${member.userEmail }로 보내드렸습니다.
<p><a href="<c:url value='/'/>">메인으로</a>
</body>
</html>