<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript">
	function modifyboard(){
		modifyform.submit();
	}
	
	function delBoard(){
		/*
		location.href = "qnaDelete?boardNum="+$("#boardNum").val()+
								 "&boardPass="+$("#boardPass").val()
		*/
		$.ajax({
			type : "POST",
			url : "qnaDelete",
			dataType : "text",
			data : {"boardNum" : $("#boardNum").val(), 
					"boardPass" : $("#boardPass").val()},
			success : okDel,
			beforeSubmit : function(){
				if($("#boardPass").val == ""){
					alert("비밀번호를 입력해주세요.");
					return false;
				}
			}
			error : function(){
				alert('에러가 발생했습니다.');
				return;
			}
		});
	}
	
	function okDel(responseText, statusText, xhr, $form){
		if(statusText == "success"){
			if(responseText.trim() == "1"){
				location.href = "qnaList";
			}else{
				alert('삭제 되지 않았습니다.');
			}
		}
	}
	</script>
</head>
<body>
<form:form action="qnaBoardModifyPro" method="post" name="modifyform" 
	commandName="boardCommand">
<form:hidden path="boardNum" id="boardNum"/>
<form:hidden path="boardName" />
<form:hidden path="ipAddr" />
<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	<tr align="left" valign="middle">
		<td colspan="2">글쓴이 : ${boardCommand.boardName }
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${boardCommand.ipAddr }</td>
	</tr>
	<tr>
		<td height="16" style="font-family:돋음; font-size:12">
			<div align="center">제 목</div>
		</td>
		<td>
			<form:input path="boardSubject" size="50" maxlength="100" />
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td>
			<form:textarea path="boardContent" cols="67" rows="15"></form:textarea>
		</td>
	</tr>
	<tr>
		<td height="16" style="font-family:돋음; font-size:12">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input name="boardPass" type="password" id="boardPass">
			<div>${err }</div>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
			<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:delBoard()">
			[삭제]
			</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>&nbsp;&nbsp;
			
			</font>
		</td>
	</tr>
</table>
</form:form>
</body>
</html>