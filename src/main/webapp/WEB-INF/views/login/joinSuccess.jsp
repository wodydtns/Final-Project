<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 12.      김혜정      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
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
<title>추가정보입력</title>

<!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="../css/login/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4"><b>가입을 축하합니다!!</b></h1>
              </div>
             <form action="<c:url value='/member/emailInsert.do'/>"
					method="post" id="insertForm" >	
                <div class="form-group">
                  <input type="text" class="form-control form-control-user" name="mem_nick" placeholder="닉네임"/>
                <form:errors path="member.mem_email" element="span" cssClass="error"/>
				<input type="button" id="nickCheckBtn" value="중복확인"/> 
                </div>
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <input type="text" class="form-control form-control-user" name="mem_hp" minlength="11" maxlength="11" placeholder="휴대폰번호"/>
                  	<form:errors path="member.mem_pass" element="span" cssClass="error" />
                  	<input type="button" id="hpCheckBtn" value="인증번호 전송"/> 
					<div id="auth"></div>
                  </div>
                <input type="button" id="joinBtn" class="btn btn-primary btn-user btn-block" value="확인"/>
                </div>
              </form>
              <hr>
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

$('#joinBtn').on("click",function(){
	insertForm.submit();
});

var insertForm = $("#insertForm").on("submit",function(){
	
	let idChecked = $(this).data("idchecked");
	let authNumChecked = $(this).data("authNumChecked");

	if(!idChecked){
		alert("이메일 인증 해주세요.");
		return false;
	}else if(!authNumChecked){
		alert("인증번호 확인버튼을 눌러주세요.");
		return false;
	}else if($('input:checkbox[id="check"]').is(":checked") == false){
		alert("약관 동의 체크 해주세요.")
	}else{
		return true;
	}
})

var idInputTag = $("[name='mem_email']").on("change",function(){//타이핑 이벤트 발생시마다 idchecked를 false로 해줌
	insertForm.data("idchecked",false);//외부에서 수정할 수 없다.
});


$("#idCheckBtn").on("click", function(){//중복확인 및 인증번호발송 
	//다른 항목들을 유지한 채로 중복여부 확인 ==> 비동기
	
	let inputId = idInputTag.val();
	let emailType = $("#emailType").val(); //이메일 종류
	if(!inputId){//null일 때
		alert("이메일을 입력해주세요.");
		idInputTag.focus();
		return;
	}
	
	if(emailType==""){ //이메일종류 선택 안했을 때
		alert("이메일 종류를 선택해주세요.");
		return;
	}
	
	$("#auth").html(""); // 태그 초기화
	$("#auth").append("<input type="text" id="auth_num" /><input type="button" id="authNumCheckBtn" value="인증하기"/>"); // 태그 추가
	
	$.ajax({
		url : "${pageContext.request.contextPath}/member/checkId.do",
		data : {
			email : inputId+emailType
		},
		method : "post",
		dataType : "json", //Accept헤더,Content-Type 두가지 헤더 결정
		success : function(resp) { //resp = json데이터가 javascript객체로 언마샬링
			if(resp.status=="PKDUPLICATED"){
				idInputTag.val("");
				idInputTag.focus();
			}else{//중복확인까지 끝내고 submit버튼 기능이 되어야함
				insertForm.data("idchecked",true);	
			}
			idInputTag.after(
					$("<span>").addClass("message")
							.text(resp.message)
				);
		},
		error : function(xhr) {
			console.log(xhr.status)
	}
	});
	

	
});	

var authInputTag = $("#auth_num").on("change",function(){//타이핑 이벤트 발생시마다 authNumChecked를 false로 해줌
	insertForm.data("authNumChecked",false);//외부에서 수정할 수 없다.
});

$("#authNumCheckBtn").on("click", function(){//인증하기버튼 눌렀을 때
	//다른 항목들을 유지한 채로 중복여부 확인 ==> 비동기
	
	let inputAuth = authInputTag.val();
	if(!inputAuth){//null일 때
		alert("인증번호를 입력해주세요.");
		inputAuth.focus();
		return;
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/member/checkAuthNum.do",
		data : {
			num : inputAuth
		},
		method : "post",
		dataType : "json", //Accept헤더,Content-Type 두가지 헤더 결정
		success : function(resp) { //resp = json데이터가 javascript객체로 언마샬링
			if(resp.status=="FAIL"){
				inputAuth.val("");
				inputAuth.focus();
			}else{//중복확인까지 끝내고 submit버튼 기능이 되어야함
				insertForm.data("authNumChecked",true);	
			}
			inputAuth.after(
					$("<span>").addClass("message")
							.text(resp.message)
				);
		},
		error : function(xhr) {
			console.log(xhr.status)
	}
	});
});	

$('#mem_pass_check').focusout(function () {
    var pwd1 = $("input[name='mem_pass']").val();
    var pwd2 = $("#mem_pass_check").val();
    if (pwd1 != "" || pwd2 != "") {
        if (pwd1 == pwd2) {
            $("#alert-success").css('display', 'inline-block');
            $("#alert-danger").css('display', 'none');
        } else {
            $("#alert-success").css('display', 'none');
            $("#alert-danger").css('display', 'inline-block');
            alert("비밀번호 불일치");
        }
    }
});


</script>
</html>