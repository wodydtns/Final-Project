<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="codepixer">
<meta name="description" content="">
<meta name="keywords" content="">

<title>취춘진담</title>
<style type="text/css">
#header, #content, #footer{
	font-family: 'Do Hyeon', sans-serif;
	font-size: 18px;
}
	
#content {
	padding-top: 245px;
}
	
</style>
<tiles:insertAttribute name="hobbymain-prescript" />
</head>
<body>
<div id="header">
	<tiles:insertAttribute name="hobbymain-header" />
</div>
<div id="content">
	<tiles:insertAttribute name="hobbymain-content" />
</div>
<div id="footer">
	<tiles:insertAttribute name="hobbymain-footer" />
</div>
</body>
</html>