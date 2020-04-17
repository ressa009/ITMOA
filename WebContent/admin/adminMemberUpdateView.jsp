<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.FileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="java.util.Enumeration" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>ITMOA ADMIN - MemberUpdateView</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<script src="ckeditor/ckeditor.js"></script>

<style>
input[type="text"] {
		border: 0.5px solid #cccccc;
		background: #hhhhhh;
}
.btn-file {
	padding: 0px 10px;
    overflow: hidden;
}
.btn-file input[type=file] {
    top: 0;
    right: 0;
    max-width: 70px;
    max-height: 0px;
    font-size: 100px;
    text-align: right;
    filter: alpha(opacity=0);
    opacity: 0;
    outline: none;
    background: white;
    cursor: inherit;
    display: block;
}
</style>


<script>
       // form 검증
   	function chkSubmit(){
   		frm = document.forms["frm"];
   		
   		var mb_id = frm["mb_id"].value.trim();
   		var mb_pw = frm["mb_pw"].value.trim();
   		var mb_pwOk = frm["mb_pwOk"].value.trim();
   		var mb_email = frm["mb_email"].value.trim();
   		var mb_zip = frm["mb_zip"].value.trim();
   		var mb_add1 = frm["mb_add1"].value.trim();
   		var mb_add2 = frm["mb_add2"].value.trim();
   		
   		if(mb_pw == ""){ // 혹시 몰라서 남겨둠
   			alert("비밀번호를 입력해주세요.");
   			frm["mb_pw"].focus();
   			return false;
   		} 
   		if(mb_pwOk == ""){ // 혹시 몰라서 남겨둠
   			alert("비밀번호를 입력해주세요.");
   			frm["mb_pwOk"].focus();
   			return false;
   		} 
   		if(mb_email == ""){
   			alert("이메일을 입력해주세요.");
   			frm["mb_email"].focus();
   			return false;
   		}
   		if(mb_zip == ""){
   			alert("우편번호를 입력해주세요.");
   			frm["mb_zip"].focus();
   			return false;
   		} 
   		if(mb_add1 == ""){
   			alert("주소1을 입력해주세요.");
   			frm["mb_add1"].focus();
   			return false;
   		} 
   		if(mb_add2 == ""){
   			alert("주소2를 입력해주세요.");
   			frm["mb_add2"].focus();
   			return false;
   		}
   		
   			return true;
   	}
       
</script>

</head>

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
					<div class="h4 m-0 ml-2 font-weight-bold text-primary">회원 관리</div>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">관리자1</span>
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
									<h6 class="m-0 font-weight-bold text-primary">회원 정보 수정</h6>
								</div>
								<div class="card-body">
									<form method="post" action="/Project_itmoa/admin/adminMemberUpdateOk.do" enctype="multipart/form-data" onsubmit="return chkSubmit()">
									
										<div class="ooo">
										<h6 id="left" class="m-0 font-weight-bold text-primary p-2">아이디</h6>
										<input type="text" name="mb_id" placeholder="${adminMemberUpdateView[0].mb_id }" class="p-2 mb-3 col-xl-12" style="width: 150px; height: 30px;" disabled>
										</div>
										<div class="ooo">
										<h6 id="left" class="m-0 font-weight-bold text-primary p-2">회원번호</h6>
										<input type="text" name="mb_uid" placeholder="${adminMemberUpdateView[0].mb_uid }" class="p-2 mb-3 col-xl-12" style="width: 150px; height: 30px;" disabled>
										</div>
										<div class="ooo">
										<h6 id="left" class="m-0 font-weight-bold text-primary p-2">회원등급</h6>
										<select name="mb_level">
											<option value="1">일반회원</option>
											<option value="2">슈퍼회원</option>
											<option value="3">관리자</option>
										</select>
										</div>
										<div class="ooo">
										<h6 id="left" class="m-0 font-weight-bold text-primary p-2">이메일</h6>
										<input type="text" name="mb_email" value="${adminMemberUpdateView[0].mb_email }" class="p-2 mb-3 col-xl-12" style="width: 250px; height: 30px;">
										</div>
										<div class="ooo">
										<h6 id="left" class="m-0 font-weight-bold text-primary p-2">주소</h6>
										<input type="text" id="sample6_postcode" name="mb_zip" value="${adminMemberUpdateView[0].mb_zip }" style="width: 250px; height: 30px; border-radius: 5px; margin: 5px; margin-left: -1px;">
										<button class="addr-btn, btn btn-info btn-icon-split" type="button" onclick="sample6_execDaumPostcode()" style="margin-left: -5px; margin-top: -3.5px; background-color: #4e73df; border: none;">우편번호 찾기</button><br>
										</div>
										<div style="margin-left: 150px">
										<input class="addr" type="text" id="sample6_address" name="mb_add1" value="${adminMemberUpdateView[0].mb_add1 }" style="width:250px"><br>
										<input class="addr" type="text" id="sample6_detailAddress" name="mb_add2" value="${adminMemberUpdateView[0].mb_add2}" style="width:250px">
										<!--<input style="left: 200px" class="addr" type="text" id="sample6_extraAddress" style="display: none;" placeholder="상세주소">-->
										</div>
										<div class="ooo">   
										<h6 id="left" class="m-0 font-weight-bold text-primary p-2">프로필 이미지</h6>
										<label class="btn-file float-left bg-primary font-weight-bold text-white border-0 rounded" style="margin-top:7px;">
												사진 선택<input type="file" name="mb_img" accept="image/jpeg, image/png" onchange="changeImg()" />
										</label>     
										<img src="/Project_itmoa/user/mypage/img/${adminMemberUpdateView[0].mb_img }" style="width: 150px; height: 150px;"/>
										<input type="hidden" name="mb_uid" value="${adminMemberUpdateView[0].mb_uid }" />
										<input type="hidden" name="ifNew" value="true" />
										</div>
										<button type="submit" class="p-2 mt-3 col-xl-12 bg-primary text-white border-0 rounded">작성 완료</button>
									</form>
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
					<a class="btn btn-primary" href="../user/logoutOk.do">Logout</a>
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
	
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
 	
    function changeImg() {
		var changeP = $("input[name='mb_img']").val().substring(12);
		$("form > p").html(changeP);
	}
 
</script>
	

</body>

</html>