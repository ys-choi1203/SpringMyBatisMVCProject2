<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<h2>Third Domino</h2>
<select id="csel" name="cNum">
	<option value="">--------선택하세요--------</option>
	<c:forEach var="cdto" items="${cc }">
		<option value="${cdto.c1 }">${cdto.c2 }</option>
	</c:forEach>
</select>