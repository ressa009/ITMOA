<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>ITMOA ADMIN - Review</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/common.css"/>
<style>
.pagination {
	display: inline-block;
	padding-left: 0;
	margin: 20px 0;
	border-radius: 4px
}

.pagination>li {
	display: inline
}

.pagination>li>a, .pagination>li>span {
	position: relative;
	float: left;
	padding: 6px 12px;
	margin-left: -1px;
	line-height: 1.42857143;
	color: #337ab7;
	text-decoration: none;
	background-color: #fff;
	border: 1px solid #ddd;
	left:70%;
}

.pagination>li>a:focus, .pagination>li>a:hover, .pagination>li>span:focus,
	.pagination>li>span:hover {
	z-index: 2;
	color: #23527c;
	background-color: #eee;
	border-color: #ddd
}

.pagination>li:first-child>a, .pagination>li:first-child>span {
	margin-left: 0;
	border-top-left-radius: 4px;
	border-bottom-left-radius: 4px
}

.pagination>li:last-child>a, .pagination>li:last-child>span {
	border-top-right-radius: 4px;
	border-bottom-right-radius: 4px
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover,
	.pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover
	{
	z-index: 3;
	color: #fff;
	cursor: default;
	background-color: #337ab7;
	border-color: #337ab7
}

.pagination>.disabled>a, .pagination>.disabled>a:focus, .pagination>.disabled>a:hover,
	.pagination>.disabled>span, .pagination>.disabled>span:focus,
	.pagination>.disabled>span:hover {
	color: #777;
	cursor: not-allowed;
	background-color: #fff;
	border-color: #ddd
}

.pagination-lg>li>a, .pagination-lg>li>span {
	padding: 10px 16px;
	font-size: 18px;
	line-height: 1.3333333
}

.pagination-lg>li:first-child>a, .pagination-lg>li:first-child>span {
	border-top-left-radius: 6px;
	border-bottom-left-radius: 6px
}

.pagination-lg>li:last-child>a, .pagination-lg>li:last-child>span {
	border-top-right-radius: 6px;
	border-bottom-right-radius: 6px
}

.pagination-sm>li>a, .pagination-sm>li>span {
	padding: 5px 10px;
	font-size: 12px;
	line-height: 1.5
}

.pagination-sm>li:first-child>a, .pagination-sm>li:first-child>span {
	border-top-left-radius: 3px;
	border-bottom-left-radius: 3px
}

.pagination-sm>li:last-child>a, .pagination-sm>li:last-child>span {
	border-top-right-radius: 3px;
	border-bottom-right-radius: 3px
}
</style>
<script src="https://kit.fontawesome.com/bb29575d31.js"></script>

</head>
<c:choose>
<c:when test="${sessionScope.loginLevel != 3 }">
	<jsp:include page="ifNotAdmin.jsp" />
</c:when>
<c:otherwise>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Side Menu -->
		<jsp:include page="sideMenu.jsp"/>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Search -->
					<div class="h4 m-0 ml-2 font-weight-bold text-primary">리뷰 관리</div>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.loginId }</span>
								<img class="img-profile rounded-circle"
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZ1TLNa9cA0CONWCEvzv4l5vSHyLy4GadK0gKlS_PINAzaQjUT&s">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Content Row -->
					<div class="row">

						<div class="col-xl-12 col-lg-7">

							<!-- Area Chart -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<form method="get" action="/Project_itmoa/admin/adminReviewList.do" onsubmit="return chkSubmit()">
										<h6 class="mb-2 font-weight-bold text-primary">검색조건</h6>
										<label class="p-2"><select name="option_review" style="height:29.5px;">
											<option value="1">작성자 아이디</option>
											<option value="2">리뷰 제목</option>
											<option value="3">리뷰 내용</option>
										</select>
										<input type="text" name="keyword" style="height:29.5px; margin-left: -5px" />
										<button type="submit" class="btn btn-info btn-icon-split" style="margin-top: -3.5px; margin-left: 1px">검색</button></label>
									</form>
								</div>
								<div class="card-body">
									<h6 class="font-weight-bold text-primary">총 ${adminReviewAllCnt }건 중 ${adminReviewCnt }건의 데이터가 조회되었습니다.</h6>
									<div class="table-responsive">
										<table class="table table-bordered" id="dataTable"
											width="100%" cellspacing="0">
											<thead>
												<tr style="white-space: nowrap;">
													<th width="5%">No</th>
													<th width="14%">리뷰 번호</th>
													<th width="15%">작성자 아이디</th>
													<th width="15%">학원명</th>
													<th width="25%">리뷰제목</th>
													<th width="25%">리뷰작성일</th>
													<th width="6%" style="text-align: center;">리뷰삭제</th>
												</tr>
											</thead>
				                        	<c:forEach var="dto" items="${adminReviewList }" varStatus="status">											
												<tbody>
													<tr onclick="location.href='/Project_itmoa/user/reviewView.do?review_brd_uid=${dto.review_brd_uid }'" style="font-size: 12px;">
														<td>${(page - 1) * pageRows + status.index + 1}</td>
														<td>${dto.review_brd_uid }</td>
														<td>${dto.mb_id }</td>
														<td>${dto.ins_name }</td>
														<td>${dto.review_brd_title }</td>
														<td>${dto.review_brd_regdate }</td>
														<td style="text-align: center;">
															<a href="/Project_itmoa/admin/adminReviewDeleteOk.do?review_brd_uid=${dto.review_brd_uid }" class="btn btn-danger btn-icon-split" onclick="chk()"> 
																<span class="icon text-white-50"> 
																	<i class="fas fa-trash"></i>
																</span>
															</a>
														</td>
													</tr>
												</tbody>
											</c:forEach>
										</table>
									</div>
									<div>
										<jsp:include page="adminReviewListPagination.jsp">
											<jsp:param value="${writePages }" name="writePages"/>
											<jsp:param value="${totalPage }" name="totalPage"/>
											<jsp:param value="${page }" name="curPage"/>
										</jsp:include>
									</div>
								</div>
							</div>

						</div>

					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2019</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">정말 로그아웃 하시겠습니까?</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="/Project_itmoa/user/logoutOk.do">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/chart-area-demo.js"></script>
	<script src="js/demo/chart-pie-demo.js"></script>
	<script src="js/demo/chart-bar-demo.js"></script>

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
		})
		
		function chkSubmit() {
			var option_news_3 = $(":text[name='keyword']").val().length;   
			if(option_news_3 > 0) {
				return true;
			}
			
			alert("검색어를 입력하세요");
			return false;
		}
		
		function chkSubmit() {
			var option_news_3 = $(":text[name='option_news_3']").val().length;
			if(option_news_3 == 0) {
				alert('검색어를 입력하세요');
				return false;	
			}
			return true;
		}
	</script>

</body>

</c:otherwise>
</c:choose>
<script>
function chk(){
	var delConfirm = confirm('정말로 삭제하시겠습니까?');
	   if (delConfirm) {
	      alert('삭제되었습니다.');
	   }
	   else {
	      alert('삭제가 취소되었습니다.');
	   }
}
</script>
<script>
$(".nav-link").eq(1).children().css("color", "white");
</script>
</html>