<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
${findPwOk }
<c:choose>  
	<c:when test="${findPwOk == 0 }">
		<script>
			alert("비밀번호 찾기 실패");
			history.back();
		</script>  
	</c:when>
	<c:when test="${findPwOk == 1 }">
		<script>
			alert("비밀번호 찾기 성공");
			location.href = "/Project_itmoa/user/findPwView.do";
		</script>
	</c:when>
</c:choose>