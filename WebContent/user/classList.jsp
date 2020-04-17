<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL core 라이브러리 포함 --%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<script src="js/jquery.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript">
    jQuery(document).ready(function($){
    'use strict';
        jQuery('body').backstretch([
        	"images/bg/bg0.jpg",
 	        "images/bg/bg1.png",
 	        "images/bg/bg2.png"
        ], {duration: 5000, fade: 500});

        $("#mapwrapper").gMap({ controls: false,  scrollwheel: false, markers: [{ latitude:40.7566, longitude: -73.9863, icon: { image: "images/marker.png", iconsize: [44,44], iconanchor: [12,46], infowindowanchor: [12, 0] } }], icon: {image: "images/marker.png",iconsize: [26, 46], iconanchor: [12, 46],  infowindowanchor: [12, 0] }, latitude:40.7566, longitude: -73.9863, zoom: 14 });
    });
    
    </script>
</head>
<!--/head-->
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


	<section id="single-page-slider" class="no-margin">
		<div class="carousel slide" data-ride="carousel">
			<div class="carousel-inner">
				<div class="item active">
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<div class="center gap fade-down section-heading">
									<h2 class="main-title">학원검색</h2>
									<hr>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--/.item-->
			</div>
			<!--/.carousel-inner-->
		</div>
		<!--/.carousel-->
	</section>
	<!--/#main-slider-->

	<div id="content-wrapper">
		<section id="portfolio" class="white">
			<div class="container">
			<!-- 상단영역 -->
			<div id="ins_top">
				<!-- 지역 선택을 위한 select문 -->
				<div class="ins center">
				<form>
				<hr>
				<div class="ins_label"><span class="pe-7s-gleam bounce-in"></span>&nbsp;지역</div>
				<hr>
				<select name="option_location" id= "option_location" size="1" onchange="changeBranchSelect()" >
					<option value="전체" selected>전체</option>
					<option value="서울" >서울</option>
					<option value="경기" >경기</option>
					<option value="인천" >인천</option>
					<option value="대전" >대전</option>
					<option value="대구" >대구</option>
					<option value="부산" >부산</option>
					<option value="광주" >광주</option>
					<option value="울산" >울산</option>
					<option value="기타" >기타</option>
				</select>
				</div>
				
				<!-- 지점 선택을 위한 select문 -->
				<div class="ins center">
				<hr>
				<div class="ins_label"><span class="pe-7s-gleam bounce-in"></span>&nbsp;지점</div>
				<hr>
				<select name="option_branch" id= "option_branch" size="1">
					<option value ="전체" id="test" selected>전체</option>
				</select>
				</div>
				</form>
				
				
				<div class="ins center">
				<hr>
				<div class="ins_label"><span class="pe-7s-gleam bounce-in"></span>&nbsp;과정</div>
				<hr>
				<ul class="portfolio-filter fade-down center">
					<li><a class="curName btn btn-outlined btn-primary active" href="#"
						onclick="show()">전체과정</a></li>
					<li><a class="curName btn btn-outlined btn-primary btn-color" href="#"
						onclick="show()">웹앱</a></li>
					<li><a class="curName btn btn-outlined btn-primary btn-color" href="#"
						onclick="show()">보안</a></li>
					<li><a class="curName btn btn-outlined btn-primary btn-color" href="#"
						onclick="show()">네트워크</a></li>
					<li><a class="curName btn btn-outlined btn-primary btn-color" href="#"
						onclick="show()">AI</a></li>
					<li><a class="curName btn btn-outlined btn-primary btn-color" href="#"
						onclick="show()">디자인</a></li>
					<li><a class="curName btn btn-outlined btn-primary btn-color" href="#"
						onclick="show()">영상</a></li>
					<li><a class="curName btn btn-outlined btn-primary btn-color" href="#"
						onclick="show()">빅데이터</a></li>
					<li><a class="curName btn btn-outlined btn-primary btn-color" href="#"
						onclick="show()">게임</a></li>

				</ul>
				</div>
			</div> 
				<!--/#portfolio-filter-->


				<ul class="portfolio-items col-3 isotope fade-up">
				<c:choose>	
					<c:when test="${not empty sessionScope.loginUid }">				
					    <c:forEach var="dto" items="${classList }">
					    	<!-- portfolio-item  -->
							<li class="portfolio-item apps isotope-item">
								<div class="item-inner">
								<div onclick="location.href='/Project_itmoa/user/classView.do?class_uid=${dto.class_uid }&mb_uid=${sessionScope.loginUid }'">
									<img src="${dto.ins_img}" alt="" style="height: 200px;">
								</div>
								<div id="h5_border">
								<div id="like_pane">
									<span style="color:red;">
										<i id="heart" class="fa fa-heart" style="color:red"></i>
									</span>
									<span>찜 ${dto.class_zzimcnt }</span>
								</div>	
								<div onclick="location.href='/Project_itmoa/user/classView.do?class_uid=${dto.class_uid }&mb_uid=${sessionScope.loginUid }'">
									<h5>${dto.ins_name }</h5>
									<h5>${dto.cur_name }</h5>
									<h5>${dto.ins_branch }</h5>
								</div>
								</div>
								</div>
							</li>
							<!--/.portfolio-item-->
						</c:forEach>
					</c:when>
						
						<c:otherwise>
							    <c:forEach var="dto" items="${classList }">
					    	<!-- portfolio-item  -->
							<li class="portfolio-item apps isotope-item">
								<div class="item-inner">
								<div onclick="location.href='/Project_itmoa/user/classView.do?class_uid=${dto.class_uid }'">
									<img src="${dto.ins_img}" alt="" style="height: 200px;">
								</div>
								<div id="h5_border">
								<div id="like_pane">
									<span style="color:red;">
										<i id="heart" class="fa fa-heart" style="color:red"></i>
									</span>
									<span>찜 ${dto.class_zzimcnt }</span>
								</div>	
								<div onclick="location.href='/Project_itmoa/user/classView.do?class_uid=${dto.class_uid }'">
									<h5>${dto.ins_name }</h5>
									<h5>${dto.cur_name }</h5>
									<h5>${dto.ins_branch }</h5>
								</div>
								</div>
								</div>
							</li>
							<!--/.portfolio-item-->
						</c:forEach>
						</c:otherwise>
				</c:choose>
				</ul>
			</div>
		</section>
	</div>


	</div>

	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/init.js"></script>
	
	  
    
    <!-- 지역에 따른 지점 출력 함수 -->
	<script>

	$(document).ready(function() {
		$('.item-inner').hover(function() {
			$(this).css({
				"cursor": "pointer"
			})
		}, function() {
			$(this).css({
				"cursor": "default"
			})
		})
	})
	


	$(document).ready(function() {
	   $('#option_location').on('change', function(){
		
		   var select = document.getElementById("option_location");
		   var location = select.options[select.selectedIndex].value;
		   var selected_location;
		   
		   if(location == "전체"){
			   selected_location = "<select name='option_branch' size='1'><option value='전체' selected>전체</option></select>";
		   }else if( location == "서울" ){
			   selected_location = "<select name='option_branch' size='1'> <option value='전체' selected>전체</option> <option value='강남' >강남</option><option value='신촌' >신촌</option><option value='종로' >종로</option><option value='사당' >사당</option><option value='노량진' >노량진</option><option value='기타' >기타</option></select>";
		   }else if( location == "경기"){
			   selected_location= "<select name='ins_branch' size='1'><option value='전체' selected>전체</option><option value='고양' >고양</option><option value='안양' >안양</option><option value='부천' >부천</option><option value='성남' >성남</option><option value='구리' >구리</option><option value='광명' >광명</option></select>";   
		   }else if( location == "인천"){
			   selected_location = "<select name='ins_branch' size='1'><option value='전체' selected>전체</option><option value='부평' >부평</option><option value='송도' >송도</option><option value='기타' >기타</option></select>";
		   }else if(location == "대전"){
			   selected_location = "<select name='ins_branch' size='1'><option value='전체' selected>전체</option><option value='서구' >서구</option><option value='중구' >중구</option><option value='기타' >기타</option></select>";
		   }else if(location == "대구"){
			   selected_location= "<select name='ins_branch' size='1'><option value='전체' selected>전체</option><option value='중구' >중구</option>	option value='수성' >수성</option><option value='기타' >기타</option>	</select>";
		   }else if(location == "부산"){
			   selected_location= "<select name='ins_branch' size='1'><option value='전체' selected>전체</option><option value='진구' >진구</option>	<option value='연제구' >연제구</option><option value='해운대구' >해운대구</option><option value='기타' >기타</option></select>";
		   }else if(location == "광주"){
			   selected_location = "<select name='ins_branch' size='1'><option value='전체' selected>전체</option><option value='서구' >서구</option><option value='북구' >북구</option><option value='기타' >기타</option></select>";
		   }else if(location == "울산"){
			   selected_location = "<select name='ins_branch' size='1'>	<option value='전체' selected>전체</option><option value='남구' >남구</option><option value='기타' >기타</option></select>";
		   }else{
			   selected_location= "<select name='ins_branch' size='1'><option value='기타'>기타</option></select>";
		   }

		   $('#option_branch').html(selected_location);
		 
	   });
	})

	
	 function show(){
		
		  $('.curName').on('click', function(){
			  
			  var option_location = document.getElementById("option_location");
			  option_location = option_location.options[option_location.selectedIndex].value;
			  
			  var option_branch = document.getElementById('option_branch');
			  option_branch = option_branch.options[option_branch.selectedIndex].value;
			  
			  var option_curName = $(this).text(); 
			  
			  //alert(option_location+ " " + option_branch + " " +option_curName);
			  
			  var url = 'classList.do?option_location=' + encodeURI(option_location) + '&option_branch=' + encodeURI(option_branch)+ '&option_curName=' + encodeURI(option_curName);
			  
			  window.location.href = url;

		  });

	  };
  
	</script>
</body>
</html>
