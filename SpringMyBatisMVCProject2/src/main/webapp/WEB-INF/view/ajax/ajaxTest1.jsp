<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxTest1.jsp</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script>
	function testDiv(n) {
		//location.href="AjaxTest2?num="+n // 동기식
		$.ajax({
			type : "post",
			url : "AjaxTest2",
			dataType : "html",
			data : "num=" + n,
			success : function(result) {
				$("#notice_content").html(result);
			},
			error : function() {
				alert('에러가 나왔다 홀홀홀~');
				return;
			}
		});
	}
	function testDiv1(n) {
		aaa = { 
 	 			type : "post",	
 	 			url : "AjaxTest2",
 	 			dataType : "html",
				data: "num="+n,
				success : function(result){
					$("#notice_content").html(result);
				},
				error : function(){
					alert('에러가 나왔다 홀홀홀~');
					return;
				}
			}
		$.ajax(aaa);
	}

	$(function() {
		$("#btn3").click(function() {
			/* 		if($("#n").val()==""){
			 alert("입력하세요.")
			 return false;
			 }
			 $("#frm").submit(); */
			$("#frm").ajaxSubmit({
				type : "post",
				url : "AjaxTest2",
				dataType : "html",
				beforeSubmit : function() {
					if ($("#n").val() == "") {
						alert("값을 입력해주세요.");
						$("#n").focus();
						return false;
					}
				},
				success : function(result) {
					$("#notice_content").html(result);
				},
				error : function() {
					alert('에러가 나왔다 홀홀홀~');
					return;
				}
			});
		});
		$("#btn4").click(function() {
			$("#frm").ajaxSubmit({
				type : "post",
				url : "AjaxTest2",
				dataType : "html",
				beforeSubmit : beforeTest, // beforeTest()함수
				success : okTest, // okTest()함수
				error : errorTest
			// errorTest()함수
			});
		});
		$("#btn5").click(function() {
			option = {
				type : "post",
				url : "AjaxTest2",
				dataType : "html",
				beforeSubmit : beforeTest, // beforeTest()함수
				success : okTest, // okTest()함수
				error : errorTest
			}
			$("#frm").ajaxSubmit(option);
		});
	});
	function beforeTest() {
		if ($("#n").val() == "") {
			alert("(btn4)값을 입력해주세요.");
			$("#n").focus();
			return false;
		} else {
			alert("ajax가 서버에 전송하기 전에 실행되는 이벤트")
		}
	}

	function okTest(responseText, statusText, xhr, $form) {
		if (statusText == "success") {
			$("notice_content").html(responseText);
			$form.css('background', 'red');
		}
	}

	function errorTest() {
		alert('에러가 발생했습니다');
	}
</script>
</head>
<body>
	<form id="frm" action="AjaxTest2">
		<!-- submit -->
		<input type="text" id="n" name="num" />
	</form>

	<button id="btn1" onclick="javascript:testDiv1(1)">버튼</button>
	<button id="btn2" onclick="javascript:testDiv(1)">버튼1</button>
	<button id="btn3">버튼2</button>
	<button id="btn4">버튼3</button>
	<button id="btn5">버튼4</button>
	<div id="notice_content"></div>
</body>
</html>