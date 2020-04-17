<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>ITMOA ADMIN - Member</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
   type="text/css">
<link
   href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
   rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- 페이징 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/common.css"/>
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
<!-- 푸시댔는감? -->
                     <!-- Area Chart -->
                     <div class="card shadow mb-4">
                        <div class="card-header py-3">
                        <form action="adminMemberList.do" method="post" enctype="multipart">
                           <h6 class="m-0 font-weight-bold text-primary">회원구분</h6>
                           <label class="p-2"><input type="radio" name="option_mb_1" value="1" checked="checked" />전체</label>
                           <label class="p-2"><input type="radio" name="option_mb_1" value="2" />일반회원</label>
                           <label class="p-2"><input type="radio" name="option_mb_1" value="3" />슈퍼회원</label>
                           <label class="p-2"><input type="radio" name="option_mb_1" value="4" />관리자</label>
                           <h6 class="m-0 font-weight-bold text-primary">검색조건</h6>
                           <label class="p-2"><select name="option_mb_2" style="height:29.5px;">
                              <option value="1">회원ID</option>
                              <option value="2">회원명</option>
                              <option value="3">회원번호</option>
                              <option value="4">이메일</option>
                           </select>
                           <input type="text" name="option_mb_3" style="height:29.5px; margin-left: -5px"/>
                           <button type="submit" class="btn btn-info btn-icon-split" style="margin-top: -3.5px; margin-left: 1px">검색</button></label>
                           </form>
                        </div>
                        <div class="card-body">
                           <h6 class="m-0 font-weight-bold text-primary">총 ${adminMemberAllCnt }건 중 ${adminMemberCnt }건의 데이터가 조회되었습니다.</h6><br>
                           <div class="table-responsive">
                              <table class="table table-bordered" id="dataTable"
                                 width="100%" cellspacing="0">
                                 <thead>
                                    <tr style="white-space: nowrap;">
                                       <th style="text-align: center;">No</th>
                                       <th style="text-align: center;">회원 번호</th width="5%">
                                       <th style="text-align: center;">이름</th width="5%">
                                       <th style="text-align: center;">아이디</th width="5%">
                                       <th style="text-align: center;">이메일</th width="5%">
                                       <th style="text-align: center;">주소</th width="5%">
                                       <th style="text-align: center;">상세주소</th width="5%">
                                       <th style="text-align: center;">최초생성일</th width="5%">
                                       <th style="text-align: center;">회원삭제</th width="5%">
                                    </tr>
                                 </thead>
                                 
                                 <c:forEach var="dto" items="${adminMemberList }" varStatus="status">
                                    <tr style="font-size: 12px;">
                                       <td>${(page - 1) * pageRows + status.index + 1}</td>
                                       <td style="text-align: center;"><a href="/Project_itmoa/admin/adminMemberUpdateView.do?mb_uid=${dto.mb_uid }">${dto.mb_uid }</a></td>
                                       <td style="text-align: center;">${dto.mb_name }</td>
                                       <td style="text-align: center;">${dto.mb_id }</td>
                                       <td style="text-align: center;">${dto.mb_email }</td>
                                       <td style="text-align: center;">${dto.mb_add1 }</td>
                                       <td style="text-align: center;">${dto.mb_add2 }</td>
                                       <td style="text-align: center;">${dto.mb_regdate }</td>
                                       <td style="text-align: center;">
	                                       <a href="/Project_itmoa/admin/adminMemberDeleteOk.do?mb_uid=${dto.mb_uid }" class="btn btn-danger btn-icon-split" onclick="chk()"> 
												<span class="icon text-white-50"> 
													<i class="fas fa-trash"></i>
												</span>
										   </a>
									   </td>
                                    </tr>
                                 </c:forEach>
                              </table>
                              	<%--페이징 --%>
					         		<div id="pageContainer">
						         		<div id="pageBtn">
											<jsp:include page="adminMemberListPagination.jsp">
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
$(".nav-link").eq(0).children().css("color", "white");
</script>
</html>