<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap" rel="stylesheet">
<title>마이페이지</title>

<style>
#mypagemain-left{
	position:fixed;
	padding-top:50px;
}
#mypagemain-content{
	padding-top:100px;
	padding-left:300px;
}
#mypagemain-footer{
	position:fixed;
	padding-top:500px;
	padding-left:200px;
}
mypagemain-header, #mypagemain-content, #mypagemain-footer, #mypagemain-left{
	font-family: 'Do Hyeon', sans-serif;
	font-size: 18px;
}
</style>

<tiles:insertAttribute name="preScript" />
</head>
<body>
<div id="mypagemain-header">
	<tiles:insertAttribute name="mypagemain-header" />
</div>
<div id="mypagemain-left">
	<tiles:insertAttribute name="mypagemain-left" />
</div>
<div id="mypagemain-content">
	<tiles:insertAttribute name="mypagemain-content" />
</div>
<div id="mypagemain-footer">
	<tiles:insertAttribute name="mypagemain-footer" />
</div>
</body>
</html>