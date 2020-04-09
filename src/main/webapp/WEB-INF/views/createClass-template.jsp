<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
	.cc-input-border{
		border : 1px solid #e3e6f0;	
		padding : 5px;
	}
	.cc-font-error{
		color : red;
		font-size : 10px;
		font-weight: bold;
	}
	#cc-leader-font{
		color : #5cabff;
		font-size : 8px;
		font-weight: bold;
	}
	
		#content{
		background-color : white;
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
		<div id="ccl-left">
			<tiles:insertAttribute name="ccl-left" />
		</div>
	    <!-- Content Wrapper -->
	    <div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<div id="cc-header">
					<tiles:insertAttribute name="cc-header" />
				</div>
			<!-- content -->
			<div class="container-fluid">
				<div id="ccl-content">
					<tiles:insertAttribute name="ccl-content" ignore="true" />
				</div>
	     	</div>
			</div>
     	
			<div id="cc-footer">
				<tiles:insertAttribute name="cc-footer" />
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