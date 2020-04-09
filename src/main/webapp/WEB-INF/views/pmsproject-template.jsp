<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap" rel="stylesheet">
<title>프로젝트</title>
<style type="text/css">
#pmsproject-header, #pmsproject-content, #pmsproject-footer{
	font-family: 'Do Hyeon', sans-serif;
	font-size: 18px;
}
#pmsproject-left{
	font-family: 'Do Hyeon', sans-serif;
	font-size: 24px;
}
</style>

<tiles:insertAttribute name="preScript" />
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</head>
<body id="page-top">
 	<div id="wrapper">
		<div id="pmsproject-left">
			<tiles:insertAttribute name="pmsproject-left" />
		</div>
	    <!-- Content Wrapper -->
	    <div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<div id="pmsproject-header">
					<tiles:insertAttribute name="pmsproject-header" />
				</div>
			<!-- content -->
			<div class="container-fluid">
				<div id="pmsproject-content">
					<tiles:insertAttribute name="pmsproject-content" />
				</div>
	     	</div>
			</div>
     	
			<div id="pmsproject-footer">
				<tiles:insertAttribute name="pmsproject-footer" />
			</div>
	    </div>
	    <!-- End of Content Wrapper -->
	</div>
  	
  	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>
</body>
</html>