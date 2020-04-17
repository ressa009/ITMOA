<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lec.beans.*" %>

<c:choose>
	<c:when test="${myPageUpdateOk == 0}">
		<script>
			alert("변경된 정보가 없습니다.");
			history.back();
		</script>
	</c:when>
	
	<c:when test="${myPageUpdateOk == 1}">
		<script>
			alert("정보가 변경되었습니다.");
			location.href="myPage.do?mb_uid=${sessionScope.loginUid }";
		</script>
	</c:when>
	
	<c:when test="${myPageUpdateOk == 2}">
		<script>
			alert("비밀번호를 확인해주세요.");
			history.back();
		</script>
	</c:when>
	
	<c:when test="${myPageUpdateOk == 3}">
		<script>
			alert("이메일을 확인해주세요.");
			history.back();
		</script>
	</c:when>
</c:choose>
