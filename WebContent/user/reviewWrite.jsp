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
										<div class="widget search">
				                        	<form method="post" action="/Project_itmoa/user/reviewWrite.do" onSubmit="chkSubmit()">
												<h3>학원 검색</h3>
				                            	<div class="input-group">
					                                <input type="text" name="ins_name" placeholder="학원명을 입력하세요" style="width: 100%; height: 42px; padding: 10px;" />
					                                <input type="hidden" name="review_brd_title" value="" />
					                                <input type="hidden" name="review_brd_content" value="" />
					                                <span class="input-group-btn">
				                                    	<button class="btn btn-primary btn-outlined" type="submit"><i class="fa fa-search"></i></button>
					                                </span>
				                            	</div>
		                        			</form>
			                    		</div><!--/.search-->
				                    	<table class="table">
					                    	<thead>
					                    		<tr>
													<th>학원 선택</th>
												</tr>
											</thead>
					                    	<c:forEach var="dto" items="${reviewWrite }" varStatus="status">
												<tbody>
													<tr class="${dto.class_uid }">
														<td>${dto.ins_name } - ${dto.cur_name }</td>
													</tr>
												</tbody>
					                    	</c:forEach>
				                    	</table>
			                    		<h4></h4>
										<form method="post" action="/Project_itmoa/user/reviewUpdateOk.do" enctype=”multipart/form-data” onSubmit="return chkSubmit2()">
										<h3 class="ins"></h3>
										<h3 class="main-title">
											<input name="review_brd_title" placeholder="제목을 입력하세요" value="${reviewBrdTitle }" style="width: 100%; padding: 10px;" />
										</h3>
										<div style="padding: 30px 10px">
											<textarea name="review_brd_content" id="editor1">${reviewBrdContent }</textarea>
											<script>
												CKEDITOR.replace('editor1', {
													allowedContent: true,
													height: '700px',
													filebrowserUploadUrl: '${pageContext.request.contextPath }/user/reviewFileUplaod.do'
												});
											</script>
										</div>
										<input type="hidden" name="mb_uid" value="${sessionScope.loginUid }" />  <!-- mb_uid -->
										<input type="hidden" name="class_uid" value="0" />  <!-- class_uid -->
										<input type="hidden" name="ifNew" value="true" />
										<button class="col-sm-12" style="background-color: #eb2b63; color: white; border: 0px; 
										border-radius: 10px; padding: 10px 0px;" type="submit">작성 완료</button>
									</form>
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
			$('tbody tr').hover(function() {
				$(this).css({
					"cursor": "pointer",
					"background-color": "#F2F2F2"
				})
			}, function() {
				$(this).css({
					"cursor": "default",
					"background-color": "#fff"
				})
			})
			$('tbody tr').click(function() {
				$(":hidden[name='class_uid']").val($(this).attr('class'));
				$('h4').html($(this).children());
			})
		})
		
		function chkSubmit() {
			$(":hidden[name='review_brd_title']").val($(":text[name='review_brd_title']").val());
			$(":hidden[name='review_brd_content']").val(CKEDITOR.instances['editor1'].getData());
		}
		
		function chkSubmit2() {
			if ($(":hidden[name='class_uid']").val() == 0) {
				alert("과정을 선택해주세요");
				return false;
			}
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