<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${zzimOk == 0 }">
		<script>
			alert("찜 삭제 실패");
			history.back();
		</script>
	</c:when>
	<c:when test="${zzimOk == 1 }">
		<script>
			alert("찜 삭제 성공");
			location.href = "/Project_itmoa/user/classView.do?class_uid=${goBack }&mb_uid=${sessionScope.loginUid }";
		</script>
	</c:when>
	<c:when test="${zzimOk == 2 }">
		<script>
			alert("찜 실패");
			history.back();
		</script>
	</c:when>
	<c:when test="${zzimOk == 3 }">
		<script>
			alert("찜 성공");;
			location.href = "/Project_itmoa/user/classView.do?class_uid=${goBack }&mb_uid=${sessionScope.loginUid }";
		</script>
	</c:when>
</c:choose>