<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>비밀번호 찾기</title>

<!-- Custom fonts for this template-->
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="../css/login/sb-admin-2.min.css" rel="stylesheet">
</head>
<style>
</style>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<body >

  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
              <div class="col-lg-6">
                <div class="p-5" >
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-2">비밀번호를 잊어버리셨나요?</h1>
                  </div>
                  <form id="forgotPassForm" action="<c:url value='/member/memberUpdatePass.do'/>"
					method="post" >
                    <div class="form-group row">
                      <input type="text" class="form-control form-control-user" name="mem_email" aria-describedby="emailHelp" style="width:220px; margin-right:10px;" placeholder="이메일을 입력해주세요">
	                  <input type="button" id="sendEmailBtn" class="btn btn-outline-primary mb-2" value="인증번호 전송"/>
                    </div>
                    <div class="form-group row">
                 		<input type="text" class="form-control form-control-user" id="auth_num" placeholder="인증번호" style="width:170px; margin-right:10px;"/>
                 		<input type="button" id="authNumCheckBtn" class="btn btn-outline-primary mb-2" value="인증하기"/>
                 	</div>
                    <div class="form-group row">
                    <input type="password" class="form-control form-control-user" name="mem_pass" minlength="8" maxlength="20" placeholder="비밀번호" style="width:300px;"/>
                	<small id="passHelp-success" style="display: inline-block;">대소문자 구분, 숫자 · 특수문자 포함, 8~20자리</small>
                	<small id="passHelp-danger" style="display: none; color: red; font-weight: bold; ">대소문자 구분, 숫자 · 특수문자 포함, 8~20자리</small>
                  	<form:errors path="member.mem_pass" element="span" cssClass="error" />
                 </div>  
                  <div class="form-group row">
                    <input type="password" class="form-control form-control-user" id="mem_pass_check" minlength="8" maxlength="20" placeholder="비밀번호 확인" style="width:300px;"/> 
               	  	<small id="alert-success" style="display: none;">비밀번호가 일치합니다.</small>
 					<small id="alert-danger" style="display: none; color: red; font-weight: bold; ">비밀번호가 일치하지 않습니다.</small>
                </div>
                  </form>
                  <div class="form-group row">
                  <input type="button" id="changePassBtn" class="btn btn-primary btn-user btn-block" value="변경하기"/> 
                   </div>
                  <hr>
                  <div class="text-center">
                <a class="small" href="<c:url value='/login/login.do'/>">회원가입/로그인</a>
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
  <script src="../js/kendoUi/js/jquery.min.js"></script>
  <script src="../bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="../js/login/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="../js/login/sb-admin-2.min.js"></script>

</body>
<script type="text/javascript">

var forgotPassForm = $("#forgotPassForm");


$(document).on("click","#changePassBtn",function(){
	forgotPassForm.submit();
});



forgotPassForm.on("submit",function(){
	
	let sendEmailChecked = $(this).data("sendEmailChecked");
	let authNumChecked = $(this).data("authNumChecked");
	let samePassChecked = $(this).data("samePassChecked");

	if(!sendEmailChecked){
		alert("인증번호 전송버튼을 눌러주세요.");
		return false;
	}else if(!authNumChecked){
		alert("인증하기 버튼을 눌러주세요.");
		return false;
	}else if(!samePassChecked){
		return false;
	}else{
		return true;
	}
})


var idInputTag = $("[name='mem_email']").on("change",function(){//타이핑 이벤트 발생시마다 idchecked를 false로 해줌
	forgotPassForm.data("sendEmailChecked",false);//외부에서 수정할 수 없다.
});


$("#sendEmailBtn").on("click", function(){//이메일 발송 
	forgotPassForm.data("sendEmailChecked",true);
	let inputId = idInputTag.val();
	if(!inputId){//null일 때
// 		alert("이메일을 입력해주세요.");
		idInputTag.focus();
		return;
	}
	//인증메일보내기
	$.ajax({
		url : "${pageContext.request.contextPath}/member/sendMail.do",
		data : {
			email : inputId
		},
		method : "post",
		dataType : "json", //Accept헤더,Content-Type 두가지 헤더 결정
		success : function(resp) { //resp = json데이터가 javascript객체로 언마샬링
			alert("메일을 정상적으로 보냈습니다.");
		},
		error : function(xhr) {
			console.log(xhr.status)
	}
	});
	
});	


var authInputTag = $("#auth_num").on("change",function(){//타이핑 이벤트 발생시마다 authNumChecked를 false로 해줌
	forgotPassForm.data("authNumChecked",false);//외부에서 수정할 수 없다.
});



//인증하기버튼 눌렀을 때
var authNumCheckBtn = $("#authNumCheckBtn").on("click", function(){
	
	let sendEmailChecked = forgotPassForm.data("sendEmailChecked");
	
	let inputAuth = authInputTag.val();
	if(!inputAuth){//null일 때
// 		alert("인증번호를 입력해주세요.");
		authInputTag.focus();
		return;
	}else if(!sendEmailChecked){
		alert("인증번호를 전송해주세요.");
		return false;
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/member/checkAuthNum.do",
		data : {
			authNum : inputAuth
		},
		method : "post",
		dataType : "json", 
		success : function(resp) { 
			if(resp.status=="FAIL"){
				authInputTag.val("");
				authInputTag.focus();
			}else{
				forgotPassForm.data("authNumChecked",true);	
				

			}
			$(".message").remove();	
			if(resp.message=="인증 성공"){ 
				$("#authNumCheckBtn").after(
						$("<small style='display: inline-block;'>").addClass("message")
								.text(resp.message)
					);
			}else{
				$("#authNumCheckBtn").after(
						$("<small style='display: inline-block; color: red; font-weight: bold;'>").addClass("message")
								.text(resp.message)
					);
				
			}
			
		},
		error : function(xhr) {
			console.log(xhr.status)
	}
	});
});	

var pw1InputTag =  $("input[name='mem_pass']");
var pw2InputTag = 	$("#mem_pass_check");//비밀번호 확인칸

//비밀번호 입력시
pw2InputTag.focusin(function () {
	var pwPattern = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/;
	if(pw1InputTag.val()=="") {
		$("input[name='mem_pass']").focus();
        return;
	}else if(!pwPattern.test(pw1InputTag.val())) {
		 $("#passHelp-success").css('display', 'none');
        $("#passHelp-danger").css('display', 'inline-block');
		$("input[name='mem_pass']").val("");
		$("input[name='mem_pass']").focus();
        return;
    }else{//정규식 통과시
    	$("#passHelp-success").css('display', 'inline-block'); 
    	 $("#passHelp-danger").css('display', 'none');
    }
});

pw2InputTag.focusout(function () {
    if (pw1InputTag.val() != "" || pw2InputTag.val() != "") {
        if (pw1InputTag.val() == pw2InputTag.val()) {
            $("#alert-success").css('display', 'inline-block');
            $("#alert-danger").css('display', 'none');
            insertForm.data("samePassChecked",true);	
        } else {
            $("#alert-success").css('display', 'none');
            $("#alert-danger").css('display', 'inline-block');
            insertForm.data("samePassChecked",false);	
            return;
        }
    }
});


</script>
</html>