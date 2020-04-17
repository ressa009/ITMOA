<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
${adminInsUpdateOk }
<c:choose>
	<c:when test="${adminInsUpdateOk == 0 }">
		<script>
			alert("수정 실패");
			history.back();    
		</script>  
	</c:when>
	<c:when test="${adminInsUpdateOk == 1 }">
		<script>
			alert("수정 성공");
			location.href = "/Project_itmoa/admin/adminInsList.do";
		</script>
	</c:when>
	<c:when test="${adminInsUpdateOk == 2 }">
		<script>
			alert("이미지 파일만 업로드 할 수 있습니다.");
			history.back();    
		</script>
	</c:when>
	<c:when test="${adminInsUpdateOk == 3 }">
		<script>
			alert("수정 성공");
			location.href = "/Project_itmoa/admin/adminInsList.do";
		</script>
	</c:when>
</c:choose>