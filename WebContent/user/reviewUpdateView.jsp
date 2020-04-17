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
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<script src="ckeditor/ckeditor.js"></script>
<script src="js/jquery.js"></script>


<script type="text/javascript">
    jQuery(document).ready(function($){
    'use strict';
        jQuery('body').backstretch([
            "http://placehold.it/800x600",
            "http://placehold.it/800x600",
            "http://placehold.it/800x600"
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
									<h2 class="main-title">학원 후기</h2>
									<hr>
									<p>리뷰 리뷰 리뷰 리뷰 리뷰 리뷰 리뷰 리뷰</p>
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
		<section id="blog" class="white">
			<div class="container">
				<div class="gap"></div>
				<div class="row">
					<div class="col-sm-12">
						<div class="blog">
							<div class="blog-item">
								<div class="blog-content">
									<form method="post" action="/Project_itmoa/user/reviewUpdateOk.do" enctype=”multipart/form-data” onSubmit="return chkSubmit()">
										<h3 class="main-title">
											<input name="review_brd_title" value="${reviewUpdateView[0].review_brd_title }" style="width: 100%; padding: 10px;" />
										</h3>
										<div style="padding: 30px 10px">
											<textarea name="review_brd_content" id="editor1">${reviewUpdateView[0].review_brd_content }</textarea>
											<script>
											CKEDITOR.replace('editor1', {
												allowedContent: true,
												height: '700px',
												filebrowserUploadUrl: '${pageContext.request.contextPath }/user/reviewFileUplaod.do'
											});
											</script>
										</div>
										<input type="hidden" name="mb_uid" value="1" />  <!-- mb_uid -->
										<input type="hidden" name="review_brd_uid" value="${reviewUpdateView[0].review_brd_uid }" />
										<input type="hidden" name="ifNew" value="false" />
										<button class="col-sm-12" style="background-color: #343a40; color: white; border: 0px; padding: 10px 0px;" type="submit">수정 완료</button>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!--/.col-md-8-->
				</div>
				<!--/.row-->
			</div>
		</section>
		<!--/#blog-->
	</div>


	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/init.js"></script>
	
	<script>
		function chkSubmit() {
			if ($(":text[name='review_brd_title']").val() == null) {
				alert("제목을 입력하세요");
				return false;
			}
			if ($("textarea[name='review_brd_content']").val() == null) {
				alert("내용을 입력하세요");
				return false;
			}
		}
	</script>
</body>
</html>