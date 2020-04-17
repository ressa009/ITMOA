<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
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
	<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <script src="js/jquery.js"></script>
	<style>
	
       /* Set the size of the div element that contains the map */

      #map {
        height: 400px;  /* The height is 400 pixels */
        width: 100%;  /* The width is the width of the web page */
       }
       
       body{
       }

    </style>
    <script type="text/javascript">
    jQuery(document).ready(function($){
    'use strict';
        jQuery('body').backstretch([
        	"images/bg/bg0.jpg",
            "images/bg/bg1.png",
            "images/bg/bg2.png"
        ], {duration: 5000, fade: 500});

        $("#mapwrapper").gMap({ controls: false,
            scrollwheel: false,
            markers: [{
                latitude:40.7566,
                longitude: -73.9863,
            icon: { image: "images/marker.png",
                iconsize: [44,44],
                iconanchor: [12,46],
                infowindowanchor: [12, 0] } }],
            icon: {
                image: "images/marker.png",
                iconsize: [26, 46],
                iconanchor: [12, 46],
                infowindowanchor: [12, 0] },
            latitude:40.7566,
            longitude: -73.9863,
            zoom: 14 });
    });
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDT7sSTMO5sgyqu_1l0KuaIK_QAyv0U44c&callback=initMap" async defer></script>
	<script>
	    var map;
			function initMap() {
			     map = new google.maps.Map(document.getElementById('map'), {
			            center: {lat: ${classView[0].ins_x }, lng: ${classView[0].ins_y }},
			            zoom: 15
			    });
			
			    var marker = new google.maps.Marker({
			            position: {lat: ${classView[0].ins_x }, lng: ${classView[0].ins_y }},
			            map: map
			    }
		    );
		}
	</script>
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


    <section id="single-page-slider" class="no-margin">
        <div class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="center gap fade-down section-heading">
                                    <h2 class="main-title">학원 상세 정보</h2>
                                    <hr>
                      
                                    	<p>${classView[0].ins_name }</p>
	                                    <form method="post" action="/Project_itmoa/user/reserveOk.do" onSubmit="return chkLogin()">
	                                 		<input type="hidden" name="mb_uid" value="${sessionScope.loginUid }">
		                                    <button type="submit" style="color: white" class="btn btn-info btn-icon-split">
												<span class="text"><span class="pe-7s-gleam bounce-in"></span>상담예약</span>
											</button>
	                                    </form>
                                 
                                    <c:choose>
										<c:when test="${sessionScope.loginUid != null }">                                    
		                                    <form method="post" action="/Project_itmoa/user/zzimOk.do">
		                             			<input type="hidden" name="goBack" value="${classView[0].class_uid }">
		                                 		<input type="hidden" name="mb_uid" value="${sessionScope.loginUid }">
			                                    <c:choose>
			                                    	<c:when test="${zzimView[0].mb_uid == sessionScope.loginUid }">
		                                 				<input type="hidden" name="ifZZim" value="true">
		                                    			<input type="hidden" name="zzim_uid" value="${zzimView[0].zzim_uid }">
					                                    <button type="submit" class="btn btn-danger btn-icon-split"> 
															<span class="icon text-white-50"> 
																<i class="fas fa-heart"></i>
															</span>
															<span class="text zzim_btn">찜취소</span>
														</button>
			                                    	</c:when>
			                                    	<c:otherwise>
		                                 				<input type="hidden" name="ifZZim" value="false">
		                                    			<input type="hidden" name="class_uid" value="${classView[0].class_uid }">
					                                    <button type="submit" class="btn btn-success btn-icon-split"> 
															<span class="icon text-white-50"> 
																<i class="fas fa-heart"></i>
															</span>
															<span class="text zzim_btn">찜하기</span>
														</button>
			                                    	</c:otherwise>
			                                    </c:choose>
		                                    </form>
	                                    </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--/.item-->
            </div><!--/.carousel-inner-->
        </div><!--/.carousel-->
    </section><!--/#main-slider-->

    <div id="content-wrapper">
        <section id="blog" class="white">
            <div class="container">
            <div class="gap"></div>
             							<div class="col-md-5 class_label center">
			                                <h3><span class="pe-7s-gleam bounce-in"></span>교육 과정</h3>
			                                <div id="class_name"><span>${classView[0].cur_name }</span></div>
										</div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="blog">
                            <div class="blog-item">
                                <div class="blog-content">
                                <div class="row">
                                	<div class="col-sm-5"><img src="${classView[0].ins_img }" style="width: 100%"/></div>
									
									
										<div class="col-md-7" id="dataTable_warp">
										<table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0">
											<thead>
												<tr>
													<th width="35%">교육 기간</th>
													<td width="65%">${classView[0].cur_months }개월</td>
												</tr>
												<tr>
													<th>교육 시수</th>
													<td>${classView[0].cur_hours }시간</td>
												</tr>
											</thead>
										</table>
								
									
									
					                        <div class="tile-progress tile-pink bounce-in">
					                            <div class="tile-header">
					                            	<p>교육과정의 주차수를 선택하여 세부과정을 볼 수 있습니다.</p>
													<c:if test="${fn:length(classView[0].cur_month1) != 0 }">
			                            				<button onClick="changeMonth(1)">1개월 차</button>
													</c:if>
													<c:if test="${fn:length(classView[0].cur_month2) != 0 }">
			                            				<button onClick="changeMonth(2)">2개월 차</button>
													</c:if>
													<c:if test="${fn:length(classView[0].cur_month3) != 0 }">
			                            				<button onClick="changeMonth(3)">3개월 차</button>
													</c:if>
													<c:if test="${fn:length(classView[0].cur_month4) != 0 }">
			                            				<button onClick="changeMonth(4)">4개월 차</button>
													</c:if>
													<c:if test="${fn:length(classView[0].cur_month5) != 0 }">
			                            				<button onClick="changeMonth(5)">5개월 차</button>
													</c:if>
													<c:if test="${fn:length(classView[0].cur_month6) != 0 }">
			                            				<button onClick="changeMonth(6)">6개월 차</button>
													</c:if>
					                            </div>
					                            
					                 
					                            <c:if test="${fn:length(classView[0].cur_month1) != 0 }">
					                            	<div>
					                            		<h3>1개월 차</h3>
						                                <span>${classView[0].cur_month1 }</span>
							                            <div class="tile-progressbar" style="margin-top: 50px">
							                                <span data-fill="10%" style="background-color:#eb2b63"></span>
							                            </div>
							                            <div class="tile-footer">
							                                <h4>
							                                    <span class="pct-counter counter">10</span>%
							                                </h4>
							                            </div>
					                            	</div>
												</c:if>
												<c:if test="${fn:length(classView[0].cur_month2) != 0 }">
						                            <div>
					                            		<h3>2개월 차</h3>
						                                <span>${classView[0].cur_month2 }</span>
							                            <div class="tile-progressbar" style="margin-top: 50px">
							                                <span data-fill="10%"></span>
							                            </div>
							                            <div class="tile-footer">
							                                <h4>
							                                    <span class="pct-counter counter">10</span>%
							                                </h4>
							                            </div>
					                            	</div>
												</c:if>
												<c:if test="${fn:length(classView[0].cur_month3) != 0 }">
						                            <div>
					                            		<h3>3개월 차</h3>
						                                <span>${classView[0].cur_month3 }</span>
							                            <div class="tile-progressbar" style="margin-top: 50px">
							                                <span data-fill="10%"></span>
							                            </div>
							                            <div class="tile-footer">
							                                <h4>
							                                    <span class="pct-counter counter">10</span>%
							                                </h4>
							                            </div>
					                            	</div>
												</c:if>
												<c:if test="${fn:length(classView[0].cur_month4) != 0 }">
						                            <div>
					                            		<h3>4개월 차</h3>
						                                <span>${classView[0].cur_month4 }</span>
							                            <div class="tile-progressbar" style="margin-top: 50px">
							                                <span data-fill="10%"></span>
							                            </div>
							                            <div class="tile-footer">
							                                <h4>
							                                    <span class="pct-counter counter">10</span>%
							                                </h4>
							                            </div>
					                            	</div>
												</c:if>
												<c:if test="${fn:length(classView[0].cur_month5) != 0 }">
						                            <div>
					                            		<h3>5개월 차</h3>
						                                <span>${classView[0].cur_month5 }</span>
							                            <div class="tile-progressbar" style="margin-top: 50px">
							                                <span data-fill="10%"></span>
							                            </div>
							                            <div class="tile-footer">
							                                <h4>
							                                    <span class="pct-counter counter">10</span>%
							                                </h4>
							                            </div>
					                            	</div>
												</c:if>
												<c:if test="${fn:length(classView[0].cur_month6) != 0 }">
						                            <div>
					                            		<h3>6개월 차</h3>
						                                <span>${classView[0].cur_month6 }</span>
							                            <div class="tile-progressbar" style="margin-top: 50px">
							                                <span data-fill="10%"></span>
							                            </div>
							                            <div class="tile-footer">
							                                <h4>
							                                    <span class="pct-counter counter">10</span>%
							                                </h4>
							                            </div>
					                            	</div>
					                            	
												</c:if>
						
					                        </div>
					                    </div>
                                    </div>
                                    <div id="map" style="margin-top: 100px"></div>
                                    <button id="goback" type="button" onclick="location.href='/Project_itmoa/user/classList.do'">
										목록으로
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div><!--/.col-md-8-->
                </div><!--/.row-->
            </div>
        </section><!--/#blog-->
    </div>

   

    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/init.js"></script>
    
    <script>
    	$(document).ready(function() {
    		// 개월 선택 버튼
    		$('.tile-header > button').css({
    			"background-color": "#f0f0f0",
    			"border": "0",
    			//"padding": "5px",
    			//"margin": "5px"
    		});
    		$('.tile-header > button').eq(0).css("background-color", "#eb2b63");
    		$('.tile-header > button').eq(0).css("color", "#fff");
    		
    		$(".tile-progress > div").css("display", "none");
    		$(".tile-progress > div").eq(0).css("display", "block");
    		$(".tile-progress > div").eq(1).css("display", "block");
    		
    		if ($(".tile-progress > div").length == 2) {
    			$(".tile-progress > div > .tile-progressbar > span").eq(0).css("width", "100%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(0).text("100");
    		} else if ($(".tile-progress > div").length == 3) {
    			$(".tile-progress > div > .tile-progressbar > span").eq(0).css("width", "50%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(0).text("50");
    			$(".tile-progress > div > .tile-progressbar > span").eq(1).css("width", "100%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(1).text("100");
    		} else if ($(".tile-progress > div").length == 4) {
    			$(".tile-progress > div > .tile-progressbar > span").eq(0).css("width", "33%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(0).text("33");
    			$(".tile-progress > div > .tile-progressbar > span").eq(1).css("width", "66%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(1).text("66");
    			$(".tile-progress > div > .tile-progressbar > span").eq(2).css("width", "100%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(2).text("100");
    		} else if ($(".tile-progress > div").length == 5) {
    			$(".tile-progress > div > .tile-progressbar > span").eq(0).css("width", "25%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(0).text("25");
    			$(".tile-progress > div > .tile-progressbar > span").eq(1).css("width", "50%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(1).text("50");
    			$(".tile-progress > div > .tile-progressbar > span").eq(2).css("width", "75%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(2).text("75");
    			$(".tile-progress > div > .tile-progressbar > span").eq(3).css("width", "100%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(3).text("100");
    		} else if ($(".tile-progress > div").length == 6) {
    			$(".tile-progress > div > .tile-progressbar > span").eq(0).css("width", "20%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(0).text("20");
    			$(".tile-progress > div > .tile-progressbar > span").eq(1).css("width", "40%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(1).text("40");
    			$(".tile-progress > div > .tile-progressbar > span").eq(2).css("width", "60%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(2).text("60");
    			$(".tile-progress > div > .tile-progressbar > span").eq(3).css("width", "80%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(3).text("80");
    			$(".tile-progress > div > .tile-progressbar > span").eq(4).css("width", "100%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(4).text("100");
    		} else if ($(".tile-progress > div").length == 7) {
    			$(".tile-progress > div > .tile-progressbar > span").eq(0).css("width", "16%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(0).text("16");
    			$(".tile-progress > div > .tile-progressbar > span").eq(1).css("width", "32%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(1).text("32");
    			$(".tile-progress > div > .tile-progressbar > span").eq(2).css("width", "48%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(2).text("48");
    			$(".tile-progress > div > .tile-progressbar > span").eq(3).css("width", "64%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(3).text("64");
    			$(".tile-progress > div > .tile-progressbar > span").eq(4).css("width", "80%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(4).text("80");
    			$(".tile-progress > div > .tile-progressbar > span").eq(5).css("width", "100%");
       			$(".tile-progress > div > .tile-footer > h4 > span").eq(5).text("100");
    		}
    	})
    	
    	function changeMonth(i) {
    		$(".tile-progress > div").css("display", "none");
    		$(".tile-progress > div").eq(0).css("display", "block");
       		$(".tile-progress > div").eq(i).css("display", "block");
    		$('.tile-header > button').css("background-color", "#f0f0f0");
    		$('.tile-header > button').css("color", "black");
    		$('.tile-header > button').eq(i-1).css("background-color", "#eb2b63");
    		$('.tile-header > button').eq(i-1).css("color", "#fff");
    	}
    	
    	function chkLogin() {
    		if (${empty sessionScope.loginUid }) {
    			alert("상담 예약은 로그인 시 가능합니다");
    			return false;
    		}
    	}
    </script>
    
</body>
</html>