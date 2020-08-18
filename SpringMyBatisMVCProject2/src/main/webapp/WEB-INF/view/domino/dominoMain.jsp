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
<script type="text/javascript">
$(function(){
	op = "<h2>Third Domino</h2>"
		+"<select>"
		+"	<option>--------선택하세요--------</option>"
		+"</select>"
	$("#asel").change(function(){
		//location.href="bDomino?num="+$("#asel").val();
		$.ajax({
            type : "POST",
            url : "bDomino",
            data : "num=" + $("#asel").val(),
            datatype : "html",
            success : function(data1) {
               $("#bDTO").html(data1);
               $("#cDTO").html(op);
            }
            
          });
	});
});
</script>
</head>
<body>
	<h2>첫번째 Domino</h2>
	<select id="asel" name="aNum">
		<option value="">--------선택하세요--------</option>
		<c:forEach items="${lists }" var="i">
			<option value="${i.a1 }">${i.a2 }</option>
		</c:forEach>
	</select>

	<div id="bDTO">
		<h2>Second Domino</h2>
		<select>
			<option value="">--------선택하세요--------</option>
		</select>
	</div>

	<div id="cDTO">
		
	</div>

</body>
</html>