<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
<style>
	div#memberTab {width:610px;margin:0 auto;}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.8.1.min.js"></script>
</head>
<body>
<form:form action='memberJoin' method='POST' name='frm' id="frm" 
		commandName="memberCommand">
<div id='memberTab'>
<fieldset>
	<legend>회원가입 정보</legend>
<table width = '600' align = 'center' border = '1' >
	<caption><strong>회원 가입</strong></caption>
	<colgroup>
	    <col width="25%" />
		<col width="75%"  />
    </colgroup>
	<tfoot>
	<tr>
		<th colspan = 2>
			<input type='submit' value='가입완료' />&nbsp;&nbsp;
			<input type='reset' value='다시 입력' />&nbsp;&nbsp;
			<input type='button' value='가입 안함' /></th>
	</tr>
	</tfoot>
	<tbody>
	<tr>
		<th colspan= '2'>사용자 ID와 비밀번호 입력</th>
	</tr>
	<tr>
		<th ><label for='userId'>사용자 ID</label></th><!-- id:#userId1, .userId2 -->
		<td ><form:input path='userId' id='userId' class='userId' size='15' 
		maxlength='10' autofocus="autofocus" />
		<form:errors  path='userId'/></td>
	</tr>
	<tr>
		<th ><label for='userPw'>비밀번호</label></th>
		<td ><form:password path='userPw' id='userPw' 
		size='15' maxlength='12' />
		<form:errors  path='userPw'/></td>
	</tr>
	<tr>
		<th><label for='userPwCon'>비밀번호확인</label></th>
		<td><form:password path='userPwCon' id='userPwCon' 
		size='15' maxlength='12'/>
		<form:errors  path='userPwCon'/></td>
	</tr>
	<tr>
		<th colspan= '2'>사용자 기본 정보</th>
	</tr>
	<tr>
		<th><label for='userName'>사용자 이름</label></th>
		<td><form:input path='userName' id='userName' size='15'
		 maxlength='12' /><form:errors  path='userName'/></td>
	</tr>
 	<tr>
		<th><label for='userBirth'>생년원일</label></th>
		<td><input type="date" name='userBirth' id='userBirth' 
		placeholder='1999-12-12' value="${memberCommand.userBirth }">
		<form:errors  path='userBirth'/>
		</td>
	</tr>
 	<tr>
		<th>성별</th>	
		<td><form:radiobutton path='userGender' id='userGender' value='M' /> 남자 &nbsp;&nbsp;&nbsp;&nbsp; 
		<form:radiobutton path='userGender' id='userGender' value='F' />여자 
		</td>
	</tr>
	<tr>
		<th colspan= '2'>사용자 연락처</th>
	</tr>
 	<tr>
		<th><label for='userEmail'>사용자 이메일</label></th>
		<td><input type='email' name='userEmail' id='userEmail'/>
		<form:errors path="userEmail"/></td>
	</tr>
	<tr>
		<th><label for='userAddr'>사용자 주소</label></th>
		<td><form:input path='userAddr' id ='userAddr' />
		<form:errors path="userAddr"/></td>
	</tr>
	<tr>
		<th ><label for='userPh1'>연락처 1</label></th>
		<td><form:input path='userPh1' id ='userPh1' 
		placeholder='123-123-1234 ,123-1234-1234' size= '26' 
		maxlength='13' /><form:errors path="userPh1"/></td>
	</tr>
	<tr>
		<th><label for='userPh2'>연락처 2</label></th>
		<td><form:input path='userPh2' id ='userPh2' placeholder='123-123-1234 ,123-1234-1234' size= '26' maxlength='13' /></td>
	</tr>
	<tr>
		<th>관심 분야</th>
		<td>
			<form:checkbox value="HTML" path="interest" />HTML
			<form:checkbox value="CSS" path="interest" />CSS
			<form:button value="Javascript" path="interest" />Javascript
		</td>
	</tr>
	</tbody>
</table>
<fieldset>
</div>
</form:form>
</body>
</html>