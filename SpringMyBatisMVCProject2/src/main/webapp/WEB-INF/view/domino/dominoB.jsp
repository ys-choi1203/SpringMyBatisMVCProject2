<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<script type="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script>
$(function(){
	$("#bsel").change(function(){
		//location.href="cDomino?num="+$("#bsel").val()+"&a1=1";
		$.ajax({
			type : "POST",
			url : "cDomino",
			data : {"a1" : $("#asel").val(), "num" : $("#bsel").val(), },
			dataType : "html",
			success : function(data1){
				$("#cDTO").html(data1);
			}
		});
	});
});
</script>
<h2>Second Domino</h2>
<select id="bsel" name="bNum">
	<option value="">--------선택하세요--------</option>
	<c:forEach var="bDTO" items="${bb }">
		<option value="${bDTO.b1 }">${bDTO.b2 }</option>
	</c:forEach>
</select>
