<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lec.beans.*" %>	
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>ITMOA</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/pe-icons.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style3.css" rel="stylesheet">
    
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <script src="js/jquery.js"></script>
	<style>
	input[type="text"] {
		border: 0.5px solid #cccccc;
		background: #hhhhhh;
		padding: 0px 10px;
	}
	button[type="submit"]{
		border: none;
		color: white;
	}
	body{
		background: white;
	}
	</style>
	
</head><!--/head-->
<script>
	// form 검증
	function chkSubmit(){
		frm = document.forms["frm"];
		
		var mb_id = frm["mb_id"].value.trim();
		var mb_pw = frm["mb_pw"].value.trim();
		var mb_name = frm["mb_name"].value.trim();
		var mb_email = frm["mb_email"].value.trim();
		
		if(mb_id == ""){
			alert("아이디를 입력해주세요");
			frm["mb_id"].focus();
			return false;
		}
		if(mb_pw == ""){
			alert("비밀번호를 입력해주세요.");
			frm["mb_pw"].focus();
			return false;
		}
		
		return true;
	}
</script>
<body>
<c:choose>
	<c:when test="${not empty sessionScope.loginUid }">
	<!-- 로그인 탑메뉴 -->
	<jsp:include page="loginTopMenu.jsp" />
	</c:when>
	<c:otherwise>
	<!-- 비회원 탑메뉴 -->
	<jsp:include page="topMenu.jsp" />
	</c:otherwise>
</c:choose> 
        
    <div class="a" id="div-find-id">
	 <form name="frm" id="find-id-content" method="get" action="findIdView.do" onsubmit="return chkSubmit()">
        <div class="find-id">
        	<h4>아이디 찾기</h4>
        </div>
        <div id="find-id" class="find-id">
        	<div id="find-id-info" class="find-id">
        		<input name="mb_name" class="find-id-info" type="text" placeholder="이름" required="required"><br>
        		<input name="mb_email" class="find-id-info" type="text" placeholder="ex)  *****@example.com" required="required">
        	</div>
        	<button type="submit" id="find-id-btn" style="background: #eb2b63; color: #ffffff;">아이디 찾기</button>
        </div>
    </form>
    </div>
    
    <div class="a" id="div-find-pw" style="margin-top: 125px;">
    <form name="frm" id="find-pw-content" method="get" action="findPwOk.do" onsubmit="return chkSubmit()" >
        <div class="find_pw">
        	<h4>비밀번호 찾기</h4>
        </div>
        <div id="find-pw" class="find-pw">
        	<div id="find-pw-info" class="find-pw">
        		<input name="mb_name" class="find-pw-info" type="text" placeholder="이름" required="required"><br>
        		<input name="mb_id" class="find-pw-info" type="text" placeholder="아이디" required="required"><br>
        		<input name="mb_email" class="find-pw-info" type="text" placeholder="ex)  *****@example.com" required="required">
        	</div>
        	<button type="submit" id="find-pw-btn" style="background: #eb2b63; color: #ffffff;">비밀번호 찾기</button>
        </div>
    </form>
	</div>
		
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/init.js"></script>
</body>
</html>
