<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
${adminMemberUpdateOk }
<c:choose>
	<c:when test="${adminMemberUpdateOk == 0 }">
		<script>
			alert("수정 실패");
			history.back();
		</script>  
	</c:when>
	<c:when test="${adminMemberUpdateOk == 1 }">
		<script>
			alert("수정 성공");
			location.href = "/Project_itmoa/admin/adminMemberList.do";
		</script>
	</c:when>
	<c:when test="${adminMemberUpdateOk == 2 }">
		<script>
			alert("이미지 파일만 업로드 할 수 있습니다.");
			history.back();    
		</script>
	</c:when>
	<c:when test="${adminMemberUpdateOk == 3 }">
		<script>
			alert("수정 성공");
			location.href = "/Project_itmoa/admin/adminMemberList.do";
		</script>
	</c:when>
</c:choose>