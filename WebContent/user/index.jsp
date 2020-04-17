<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lec.beans.*" %>
<% String dto = (String)session.getAttribute("loginId"); %>
<% if (dto != null ) { %>
<jsp:include page="loginIndex.jsp" />
<% } %>

<% if (dto == null ) { %>
<jsp:include page="logoutIndex.jsp" />
<% } %>