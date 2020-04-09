<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 11.      최도혁      최초작성
* 2020. 3. 11.      김혜정      태그수정
* Copyright (c) 2020 by DDIT All right reserved
 --%>
<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>로그인</title>

<!-- Custom fonts for this template-->
  <link href="../css/login/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="../css/login/sb-admin-2.min.css" rel="stylesheet">
</head>
<style>
#snslogin{
	width:220px;
	margin: 0 auto;
}
</style>
<body>
<%
	String mem_email = new CookieUtils(request).getCookieValue("idCookie");
%> 

<!-- 얘왜안됨 -->
  <div class="container">
	
    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block bg-login-image">
              	<img src="../images/login/로그인사진.png">
              </div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4"><b>취준진담 로그인</b></h1>
                  </div>
                  <form action="<c:url value='/login/login.do'/>" method="post" id="loginForm">
                    <div class="form-group">
                    <input type="text" class="form-control form-control-user" name="mem_email" 
				value="<%=Objects.toString(mem_email,"") %>" placeholder="이메일 입력"/>
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" name="mem_pass" placeholder="비밀번호">
                    </div>
                    ${message}
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                       <label>
							<input type="checkbox" name="idSave" value="save"
								<%=StringUtils.isNotBlank(mem_email)?"checked":""%>
							/>
							아이디 저장
						</label>
                      </div>
                    </div>
                    <input type="button" id="loginBtn" class="btn btn-primary btn-user btn-block" value="로그인"/>
                    <hr> <!-- sns로그인 jsp 연결 -->
                    <div id="snslogin">
                    <jsp:include page="/WEB-INF/views/kakao/login.jsp"/>
					<jsp:include page="/WEB-INF/views/facebook/facebookLogin.jsp"/>
					<jsp:include page="/WEB-INF/views/naver/login.jsp"/>
					</div>
                  </form>
                  <hr>
                  <div class="text-center">
                <a class="small" href="<c:url value='/login/forgotPass.do'/>">비밀번호 찾기</a>
           		   </div>
                  <div class="text-center">
                <a class="small" href="<c:url value='/member/emailInsert.do'/>">이메일로 가입하기</a>
           		   </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

</body>
<script type="text/javascript">
$("#loginBtn").on("click",function(){
	
	let inputId = $("input[name='mem_email']").val();
	let inputPass = $("input[name='mem_pass']").val();
	if(!inputId){//null일 때
		$("input[name='mem_email']").focus();
		return;
	}else if(!inputPass){
		$("input[name='mem_pass']").focus();
		return;
	}else{
		$("#loginForm").submit();
	}
	
});


</script>

</html>