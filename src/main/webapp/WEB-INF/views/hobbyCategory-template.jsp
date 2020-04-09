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
<meta name="author" content="codepixer">
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="shortcut icon" href="img/fav.png">
<title>카테고리별</title>
<style>
#header, #content, #footer{
	font-family: 'Do Hyeon', sans-serif;
	font-size: 18px;
}
#content {
	padding-top: 245px;
	margin-left: 200px;
}
</style>
</head>
<tiles:insertAttribute name="preScript" />
<body>
<div id="header">
	<tiles:insertAttribute name="hobbyCategory-header" />
</div>
<div id="content">
	<tiles:insertAttribute name="hobbyCategory-content" ignore="true"/>
</div>
<div id="footer">
	<tiles:insertAttribute name="hobbyCategory-footer" ignore="true"/>
</div>
</body>
</html>