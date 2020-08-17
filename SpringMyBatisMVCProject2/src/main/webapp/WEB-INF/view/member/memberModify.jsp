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
<script type="text/javascript">
$(function(){
	$("#memPw").click(function(){
		location.href="changePassword/${dto.userId}";
	})
});
</script>
</head>
<body>
<form:form name ="frm" id ="frm" method = "post" 
	action ="memberModifyPro" commandName="memberCommand" >
<input type="hidden" name="urlPath" value="${urlPath }"/>
<form:hidden path="userId" />
<form:hidden path="userName" />
<form:hidden path="userBirth" />
<form:hidden path="userGender" />
<table border = 1  width = 600 align = "center" cellpadding = 3 >
	<tr><td colspan=2> 회원정보 수정 </td></tr>
	<tr><td >아이디와 비번</td>
		<td >&nbsp;</td></tr>
	<tr><td>사용자 ID</td><td>${memberCommand.userId}</td></tr>
	<tr><td>비밀번호</td>
		<td><input type="password" id="pw" name ="userPw" />
			<form:errors path="userPw"/>
		  <div></div>
		  </td>
	</tr>
	<tr><td >기본정보 입력</td>
		<td >&nbsp;</td></tr>
	<tr><td>사용자 이름</td>
		<td>${memberCommand.userName }</td>
	</tr>
	<tr><td> 생년월일 및 성별</td>
		<td><fmt:formatDate value="${memberCommand.userBirth }" type="date" pattern="yyyy-MM-dd"/>
		 / ${memberCommand.userGender }</td>
	</tr>
	<tr><td>사용자 이메일</td>
	    <td><form:input id ="email" path ="userEmail" />
	    	<form:errors path="userEmail"/>
	    </td></tr>
	<tr><td>사용자 주소</td>
	    <td>
	    <form:input id ="addr" path ="userAddr" />
	    <form:errors path = "userAddr"/>
	    </td>
	</tr>
	<tr><td>연락처 1</td>
	    <td>
	    <form:input  id ="memberPh1" path ="userPh1" />
	    <form:errors path = "userPh1"/>
	    </td>
	</tr>
	<tr><td>연락처 2</td>
	    <td>
	    <for:input id ="memberPh2" path ="userPh2" />
	    </td>
	</tr>
	<tr>
		<td colspan=2>
		<input type="submit" name="modify" id ="modify" 
				value="수   정" >
		<input type="button" value="취  소" 
			onclick = "javascript:history.back();" />
		<!-- onclick = "javascript:location.href='main.jsp';" -->
		</td>
	</tr>
</table>

</form:form>
</body>
</html>