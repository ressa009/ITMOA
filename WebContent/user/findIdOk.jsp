<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
${findIdOk } <%-- 혹시 몰라 남겨두는 파일 --%>
<c:choose>
	<c:when test="${fn:length(findIdOk) == 0 }">
		<script>
			alert("아이디 찾기 실패");
			history.back();
		</script>  
	</c:when>
	<c:when test="${fn:length(findIdOk) == 1 }">
		<script>
			alert("아이디 찾기 성공");
			location.href = "/Project_itmoa/user/findIdView.do";
		</script>
	</c:when>
</c:choose>