<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="codepixer">
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="shortcut icon" href="img/fav.png">

<title>InganIn</title>
<style type="text/css">
#content {
	padding-top: 200px;
}
</style>
<tiles:insertAttribute name="recruitmain-prescript" />
</head>
<body>
<div id="header">
	<tiles:insertAttribute name="recruitmain-header" />
</div>
<div id="content">
	<tiles:insertAttribute name="recruitmain-content" />
</div>
<div id="footer">
	<tiles:insertAttribute name="recruitmain-footer" />
</div>
</body>
</html>