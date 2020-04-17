<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${zzimOk == 0}">
		<script>
			alert("이미 삭제 되었거나 존재하지 않는 목록입니다.");
			history.back();
		</script>
	</c:when>
	
	<c:otherwise>
		<script>
			alert("해당 목록을 삭제하였습니다");
			location.href="myPage.do?mb_uid=${sessionScope.loginUid }";
		</script>
	</c:otherwise>
</c:choose>
