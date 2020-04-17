<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="css/style2.css" rel="stylesheet">
    <link href="css/style3.css" rel="stylesheet">
    
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <script src="js/jquery.js"></script>

</head><!--/head-->  
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
    <div class="a" id="div_find-id">
        <div class="find-id">
        	<h3>비밀번호 찾기</h3>
        </div>
        <div id="find-id" class="find-id" style="border: none;">
        	<div id="find_id-info" class="find-id">
        		<div name="mb_name" class="find-id-info" type="text" required="required">고객님의 이메일로<br>비밀번호가 발송되었습니다.</div><br>
        	</div>
        	<button id="login-btn" onclick="location.href='login.do'" style="background: #eb2b63; color: #ffffff;">로그인</button>
        </div>
    </div>
    
		
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/init.js"></script>
</body>
</html>
