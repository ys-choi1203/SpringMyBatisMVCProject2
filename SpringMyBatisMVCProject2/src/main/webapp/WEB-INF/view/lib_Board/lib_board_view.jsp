<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="2">QNA 게시판</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			글쓴이 : ${dto.boardName } &nbsp;&nbsp;&nbsp;&nbsp;
			접속주소 : ${dto.ipAddr }
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목&nbsp;&nbsp;</div>
		</td>
		
		<td style="font-family:돋음; font-size:12">
		${dto.boardSubject }
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td style="font-family:돋음; font-size:12">
			<table border=0 width=490 height=250 style="table-layout:fixed">
				<tr>
					<td valign=top style="font-family:돋음; font-size:12">
					${fn:replace(dto.boardContent,cn,br)}
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>파일</td>
		<td>
		<c:set var="idx" value="0"/>
			<c:forTokens items="${dto.originalFileName }" varStatus="cnt" delims="`" var="org">
				<a href='<c:url value="/lib_Board/upload/${storeFileName[idx] }" />' >
				${org } / ${fileSize[cnt.index] }</a>
				<c:set var="idx"  value="${idx = idx + 1 }"/>
			</c:forTokens>
		<c:remove var="idx"/>
		</td>
	</tr>
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;"></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
			<a href="../libBoardModify?boardNum=${dto.boardNum }">
			[수정]
			</a>&nbsp;&nbsp;
			<a href="libBoardDel?boardNum=${dto.boardNum }">
			[삭제]
			</a>&nbsp;&nbsp;
			<a href="<c:url value='/lib/libBoard' />">[목록]</a>&nbsp;&nbsp;
			</font>
		</td>
	</tr>
</table>
</body>
</html>