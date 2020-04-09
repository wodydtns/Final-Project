<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
 <form id="insertForm" action="<c:url value='/member/snsInsert.do'/>" method="post">
	<input type="hidden" name="mem_email"/>
  </form>
  <form id="loginForm" action='<c:url value='/login/snsLogin.do'/>' method="post">
	<input type="hidden" name="email2"/>
  </form>
<c:set var="user" value="${sessionScope.email}"/>
<input type="hidden" name="sessionEmail" value="${user}"/>
</body>
<script type="text/javascript">
  
	var user = $("input[name='sessionEmail']").val();
	$.ajax({
			url : "${pageContext.request.contextPath}/member/checkId.do",
			data : {
				email : user
			},
			method : "post",
			dataType : "json", 
			success : function(resp) { 
				if(resp.status=="PKDUPLICATED"){
					$("input[name='email2']").val(user);
					$("#loginForm").submit();
					//opener.document.location.href="${pageContext.request.contextPath}/login/snsLogin.do"//로그인후 메인폼
					//self.close();
					alert("탄다");
					//alert(resp.message);
					//return;
				}else{//중복되지않을때
					//console.log(user);
					$("input[name='mem_email']").val(user);
					$("#insertForm").submit();//insert
					opener.document.location.href="${pageContext.request.contextPath}/member/nickHpInsert.do"
					self.close();
				}
			},
			error : function(xhr) {
				console.log(xhr.status)
		}
		});
	
</script>
</html>