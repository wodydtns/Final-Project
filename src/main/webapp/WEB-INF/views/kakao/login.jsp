<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 11.      김혜정      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<title>Login Demo - Kakao JavaScript SDK</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
<a id="kakao-login-btn"></a>  
<a href="http://developers.kakao.com/logout"></a>
<form id="checkForm3" >
</form>
<form id="insertForm3" action="<c:url value='/member/snsInsert.do'/>" method="post">
<input type="hidden"  name="mem_email" />
</form>
<form id="loginForm3" action='<c:url value='/login/snsLogin.do'/>' method="post">
<input type="hidden" name="email2"/>
</form>
<%-- <form id="loginForm" action="<c:url value='/login/snsLogin.do'/>" method="post"> --%>
<!-- </form> -->


</body>
<script type='text/javascript'>
   var email=null;
   var change=null;
   
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
   Kakao.init("71cdcff806d494b7e44e48541fd2e1ac");
   // 카카오 로그인 버튼을 생성합니다.
   Kakao.Auth.createLoginButton({
   container: '#kakao-login-btn',
   success: function(authObj) { //로그인 성공시 인증토큰을 가져옴
	 //alert(JSON.stringify(authObj)); //토큰들어있음
     Kakao.API.request({ //카카오홈페이지로 request요청을 보냄
       url: '/v2/user/me', //request보낼 주소
       success: function(res) { // request요청 성공시 json 객체 반환
             //alert(JSON.stringify(res)); 
             console.log(res.id);
             console.log(res.kakao_account.email);
             //change = JSON.stringify(res);
             email = res.kakao_account.email;
             console.log(email);
              $("input[name='mem_email']").val(email);
              $("input[name='mem_email']").val(res.kakao_account.email);
             console.log($("input[name='mem_email']").val());
             console.log(res.properties['nickname']);
             console.log(authObj.access_token);
             console.log(authObj.refresh_token); 
//             $("#checkForm").submit();
			checkFormFunction();
             
           }
         })
       },
       fail: function(error) {
         alert(JSON.stringify(error));
       }
     });
   
   
   
	function checkFormFunction(){
	   event.preventDefault(); 
		$.ajax({
			url : "${pageContext.request.contextPath}/member/checkId.do",
			data : {
				"email" : email
			},
			method : "post",
			dataType : "json", 
			success : function(resp) { 
				if(resp.status=="PKDUPLICATED"){
					$("input[name='email2']").val(email);
 					$("#loginForm3").submit();//로그인후 메인폼
					//location.href="${pageContext.request.contextPath}/login/snsLogin.do";
				}else{//중복되지않을때
					console.log("타냐?");
					$("input[name='mem_email']").val(email);
					$("#insertForm3").submit();
				}
			},
			error : function(xhr) {
				console.log(xhr.status)
		},
		complete : function(){
			insertFormMethod();
		}
		});
	}
	
// 	function insertFormMethod(){
// 		$("#insertForm").submit();
// 	}
	
</script>
</html>