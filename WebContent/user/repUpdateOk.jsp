<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${repUpdateOk == 0 }">
		<script>
			alert("작성 실패");
			history.back();
		</script>
	</c:when>
	<c:when test="${repUpdateOk == 1 }">
		<script>
			alert("작성 성공");
			location.href = "/Project_itmoa/user/reviewView.do?review_brd_uid=${location }";
		</script>
	</c:when>
	<c:when test="${repUpdateOk == 2 }">
		<script>
			alert("수정 실패");
			history.back();
		</script>
	</c:when>
	<c:when test="${repUpdateOk == 3 }">
		<script>
			alert("수정 성공");
			location.href = "/Project_itmoa/user/reviewView.do?review_brd_uid=${location }";
		</script>
	</c:when>
</c:choose>