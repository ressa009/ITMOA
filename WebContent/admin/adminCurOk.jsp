<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${adminCurOk == 0 }">
		<script>
			alert("추가 실패");
			history.back();
		</script>
	</c:when>
	<c:when test="${adminCurOk == 1 }">
		<script>
			alert("추가 성공");
			location.href = "/Project_itmoa/admin/adminInsList.do";
		</script>
	</c:when>
</c:choose>