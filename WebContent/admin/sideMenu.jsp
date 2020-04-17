<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
   type="text/css">
<link
   href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
   rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<script src="vendor/jquery/jquery.min.js"></script>



</head>
<body>

   <!-- Sidebar -->
      <ul
         class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
         id="accordionSidebar">

         <!-- Sidebar - Brand -->
         <a
            class="sidebar-brand d-flex align-items-center justify-content-center"
            href="#">
            <div class="sidebar-brand-icon rotate-n-15">
               <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">
               ITMOA <sup>ADMIN</sup>
            </div>
         </a>

         <!-- Divider -->
         <hr class="sidebar-divider my-0">     

         <!-- Nav Item - Dashboard -->
         <li class="nav-item"><a class="nav-link" href="adminMemberList.do">
               <i class="fas fa-fw fa-wrench"></i> <span>Member</span>
         </a> <a class="nav-link" href="adminReviewList.do"> <i
               class="fas fa-fw fa-table"></i> <span>Review</span>
         </a> <a class="nav-link" href="adminNewsList.do"> <i
               class="fas fa-fw fa-table"></i> <span>IT News</span>
         </a> <a class="nav-link" href="adminInsList.do"> <i
               class="fas fa-fw fa-table"></i> <span>Class</span>
         </a></li>

         <!-- Divider -->
         <hr class="sidebar-divider d-none d-md-block">

         <!-- Sidebar Toggler (Sidebar) -->
         <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
         </div>

      </ul>
      <!-- End of Sidebar -->

</body>
</html>