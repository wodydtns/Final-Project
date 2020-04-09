<%@page import="java.util.Enumeration"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
웰컴 페이지 ${pageContext.session.id } 
<br />
<%-- <c:if test="${not empty authMember }"> --%>
<%-- 	<a href="${cPath }/mypage.do">${authMember.mem_name}님</a>  --%>
<%-- 	<a href="${cPath }/login/logout.do">로그아웃</a> --%>
<!-- 	<ul> -->
<%-- 		<c:forEach items="${applicationScope.userList }" var="user"> --%>
<%-- 			<li>${user.mem_id }[${user.mem_name}]</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<%-- <c:if test="${empty authMember }"> --%>
<%-- 	<a href="${cPath }/login/login.do">로그인</a> --%>
<%-- 	<a href="${cPath }/member/memberInsert.do">회원가입</a> --%>

<%-- </c:if> --%>
<div>
	현재 접속자 : ${applicationScope.currentCount }
	누적 접속자 : ${applicationScope.savedCount }
</div>



</body>
</html>










