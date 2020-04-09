<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>클래스 상세보기</title>
<style>

</style>
<tiles:insertAttribute name="preScript" />
</head>
<body id="page-top">
	<div id="header">
		<tiles:insertAttribute name="hobbydetail-header" />
	</div>
	<div id="content">
		<tiles:insertAttribute name="hobbydetail-content" />
	</div>
	<div id="footer">
		<tiles:insertAttribute name="hobbydetail-footer" />
	</div>
	
	  	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>
</body>
</html>