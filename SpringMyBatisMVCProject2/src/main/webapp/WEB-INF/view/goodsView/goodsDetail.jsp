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
function goodsCartAdd(goodsNum){
	$.ajax({
		type : "POST",
		url : "<c:url value='/cart/goodsCartAdd' />",
		dataType : "text",
		data : {"goodsNum":goodsNum},	// "goodsNum=" + goodsNum
		success : function(result){
			if(result.trim() == "1"){
				if(confirm("계속 쇼핑하시려면 '아니오'를 누르시오")){
					location.href = "<c:url value='/cart/goodsCartList' />";
				}
			}else {
				alert("장바구니에 담기지 않았습니다.\n다시 시도해주세요")
			}
		},
		error : function(){
			alert("에러가 발생했습니다.")
			return;
		}
	});
}
</script>
</head>
<body>
<table align="center" width="600" border="1">
	<tr bgcolor="orange">
		<td align="right">조회수 : ${goods.readCount }
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:goodsCartAdd(${goods.goodsNum });">장바구니 담기</a>
	</tr>
</table>

<table align="center" width="600"  >
	<tr align="center">
		<td rowspan="4">
		<c:forTokens items="${goods.goodsImage }" delims="`"
					 var="img" begin="0" end="0">
		<img src="../goodsView/upload/${img }" width="50" height="50"/><br/>
		</c:forTokens>
		</td>
		<td>상품명 : ${goods.goodsName } </td>
	</tr>
	<tr>
	 <td>가격: ${goods.goodsPrice }</td>
	</tr>
</table>

<table align="center" width="600" border="1">
	<tr>
	 <td>상품 설명: ${fn:replace(goods.goodsContent,cn,br) }<br/>
	 <div align="center">
		 <c:forTokens items="${goods.goodsImage }" delims="`"
					 var="img" begin="0" end="10">
		<img src="../goodsView/upload/${img }" width="100" height="100"/><br/>
		</c:forTokens>
	</div>
	 </td>
	</tr>
    <tr><td colspan="2" align="center">
    		<a href="goodsList.gd">목록보기</a> |
    		<a href=
    		"goodsDelete?goodsNum=${goods.goodsNum }&image=${goods.goodsImage }">
    		상품 삭제</a> |
    		<a href="goodsModify?goodsNum=${goods.goodsNum }">상품 수정</a>
    	</td>
    </tr>
</table>
</body>
</html>