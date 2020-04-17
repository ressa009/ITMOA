<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <script src="js/jquery.js"></script>

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
</head>
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
                                    <h2 class="main-title">IT News</h2>
                                    <hr>
                                    
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
                <div class="row">
                    <div class="col-sm-12">
                        <div class="blog">
                            <div class="blog-item">
                                <div class="blog-featured-image">
                                    <img class="img-responsive img-blog" src="${newsView[0].news_brd_img }" alt="" />
                                </div>
                                <div class="blog-content">
                                    <h3 class="main-title">${newsView[0].news_brd_title }</h3>
                                    <div class="entry-meta">
                                        <span><i class="fa fa-user"></i> <a href="#"> ${newsView[0].news_brd_viewcnt }</a></span>
                                    </div>
                                    <div style="padding: 30px 5px">
                                    	${newsView[0].news_brd_content }
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                          <button type="button" id="return_list" onclick="location.href='/Project_itmoa/user/newsList.do'">
											목록으로
                                        </button>
                    </div><!--/.col-md-8-->
                </div><!--/.row-->
            </div>
        </section><!--/#blog-->
       
    </div>

    
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/init.js"></script>
</body>
</html>