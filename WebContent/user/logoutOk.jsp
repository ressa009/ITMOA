<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	int uid = (int)session.getAttribute("loginUid"); 
	
	if (uid > 0) {
		session.removeAttribute("loginUid"); 
		session.removeAttribute("loginId"); 
		session.removeAttribute("loginLevel"); 
		session.removeAttribute("loginImg"); 		
	}

%>

 	<script>
 		location.href="logoutIndex.do";
 	</script>
