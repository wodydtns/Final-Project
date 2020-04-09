<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>관리자 페이지</title>
<tiles:insertAttribute name="preScript"/>
</head>
<style type="text/css">
#header, #content, #footer, #left{
	font-family: 'Do Hyeon', sans-serif;
	font-size: 18px;
}
</style>
<body>

<div id="header">
	<tiles:insertAttribute name="header"/>
</div>
<div id="left">
	<tiles:insertAttribute name="left"/>
</div>
<div id="content">
	<tiles:insertAttribute name="content"/>
	
</div>
<div id="footer">
	<tiles:insertAttribute name="footer"/>
</div>

</body>
</html>