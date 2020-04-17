<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lec.beans.*" %>

<c:choose>
	<c:when test="${reserveOk == 0}">
		<script>
			alert("다시 시도해 주세요.");
		</script>
	</c:when>
	
	<c:otherwise>
		<script>
			alert("예약이 완료 되었습니다.");
			history.back();
		</script>

	</c:otherwise>
</c:choose>
