<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
</c:if>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/mypageCheck.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap" rel="stylesheet">
<style>
	body {
		font-family: 'Do Hyeon', sans-serif;
	font-size: 18px;
	}
</style>
<body>
<div class="elelment">
<div class="element-main">
<h1>비밀번호 변경</h1>
		<p>비밀번호를 입력해주세요</p>
<form id="passForm" method="post" >
    <label for="inputPassword" class="col-form-label">비밀번호</label>
    <div >
      <input type="password" class="form-control" id="inputPassword" name="mem_pass" placeholder="비밀번호 입력칸">
    </div>
    <div >
      <input type="submit" class="btn btn-danger" id="btn" value="확인"/>
    </div>
  </form>
  </div>
  </div>
	<script>
		var action = "${cPath}/etc/mypageUpdate.do";
		var message = "비밀번호를 변경하시겠습니까?";
		var form = $("#passForm");
		$("#btn").on("click",function(event){
			var confirm = window.confirm(message);
			
			if(confirm){
				$(this).action = action;
				$(this).submit();
			}else{
				return;
			}
		});
		
	</script>
</body>
</html>