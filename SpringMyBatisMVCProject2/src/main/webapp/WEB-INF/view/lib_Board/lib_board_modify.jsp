<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script>
function fileDel(strFile, orgFile, fileSize, btn){
	$.ajax({
		type : "POST",
		url : "fileDel",
		dataType : "text",
		data : {"storeFileName":strFile, "originalFileName":orgFile, "fileSize":fileSize},
		error : function(){
			$(btn).text("value","삭제 취소");
			alert('에러가 발생했습니다.');
			return;
		},
		success : function(result){
			if(result.trim() == "1"){
				$(btn).text("삭제 취소");
			}else{
				$(btn).text("삭제");
			}
		}
	})
}
</script>
</head>
<body>
<form:form action="libBoardModifyPro" method="post" name="frm" 
			commandName="libraryCommand" 
			enctype="multipart/form-data">
<form:hidden path="boardNum" />
<form:hidden path="ipAddr"/>
<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			글쓴이 : ${libraryCommand.boardName } &nbsp;&nbsp;&nbsp;&nbsp;
			접속주소 : ${libraryCommand.ipAddr }
		</td>
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
			<form:textarea path="boardContent" cols="67" rows="15" />
		</td>
	</tr>
	
	<tr>
		<td>파일</td>
		<td>
		<c:set var="idx" value="0"/>
			<c:forTokens items="${libraryCommand.originalFileName }" varStatus="cnt" delims="`" var="org">
				<a href='<c:url value="/lib_Board/upload/${storeFileName[idx] }" />' >
				${org } / ${fileSize[cnt.index] }</a>
				
				<button type="button" id = "btn" onclick="fileDel('${storeFileName[idx]}', '${org }',
											'${fileSize[cnt.index] }', this)" >삭제</button>
							<br />
				<c:set var="idx"  value="${idx = idx + 1 }"/>							
			</c:forTokens>
		<c:remove var="idx"/>
		</td>
	</tr>
	<tr>
		<td>파일추가</td>
		<td><input type="file" name="report" multiple="multiple" /></td>
	</tr>
	<tr>
		<td height="16" style="font-family:돋음; font-size:12">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input name="boardPass" type="password">
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
			<input type="submit" value="수정" />&nbsp;&nbsp;
			<input type="button" value="뒤로" 
				onclick="javascript:history.back();" />
			</font>
		</td>
	</tr>
</table>
</form:form>
</body>
</html>