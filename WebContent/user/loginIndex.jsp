<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link href="css/style2.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

   <style>
      body{
         overflow-y: hidden; 
         overflow-x: hidden;
      }
   </style>

    <script type="text/javascript">
    jQuery(document).ready(function($){
   'use strict';
         jQuery('body').backstretch([
           "images/bg/bg0.jpg",
           "images/bg/bg1.png",
           "images/bg/bg2.png"
       ], {duration: 5000, fade: 500, centeredY: true });

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
</head><!--/head-->
<body>
<div id="preloader"></div>
    <header class="navbar navbar-inverse navbar-fixed-top " role="banner">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <i class="fa fa-bars"></i>
                </button>
                 <a class="navbar-brand" href="index.do"><h1><span class="pe-7s-gleam bounce-in"></span> ITMOA</h1></a>
            </div>
               <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
               <li><a href="index.do">메인</a></li>
               <li><a href="/Project_itmoa/user/classList.do">학원 찾기</a></li>
               <li><a href="/Project_itmoa/user/reviewList.do">학원 후기</a></li>
               <li><a href="/Project_itmoa/user/newsList.do">IT News</a></li>
               <li><a href="/Project_itmoa/user/myPageAction.do">마이페이지</a></li>
               <li><a href="/Project_itmoa/user/logoutOk.do">로그아웃</a></li>
            </ul>
         </div>
        </div>
    </header><!--/header-->

    <section id="main-slider" class="no-margin">
        <div class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="center centered" style="padding-top: 12%;">
                                   <span class="home-icon hero-rocket"><img src="images/bg/rocket.png" alt=""></span>
                                    <div id="main_text"><img src ="images/bg/main_text.png" ></div>
                                    <div id="main_btn"><a class="btn btn-md animation bounce-in" href="/Project_itmoa/user/classList.do">학원찾기</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--/.item-->
            </div><!--/.carousel-inner-->
        </div><!--/.carousel-->
    </section><!--/#main-slider-->

  


    <script src="js/plugins.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWDPCiH080dNCTYC-uprmLOn2mt2BMSUk&amp;sensor=true"></script>
    <script src="js/init.js"></script>
</body>
</html>