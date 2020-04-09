<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 11.      최도혁      최초작성
* 2020. 3. 11.		김혜정	  태그수정
* Copyright (c) 2020 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" />
<jsp:useBean id="errors" class="java.util.HashMap" scope="request" /> 
<fmt:setLocale value="ko"/>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>회원가입</title>

<!-- Custom fonts for this template-->
 <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
 <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet"> 

  <!-- Custom styles for this template-->
  <link href="../css/login/sb-admin-2.min.css" rel="stylesheet"> 
</head>
<style>
.bg-register-image, img{
 height:750px;
 width:450px;

}
</style>
<body>

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-5 d-none d-lg-block bg-register-image" >
          	<img src="../images/login/회원가입사진.png" >
          </div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4"><b>환영합니다!</b></h1>
              </div>
             <form action="<c:url value='/member/emailInsert.do'/>"
					method="post" id="insertForm" >	
                <div class="form-group row">
                  <input type="hidden" name="mem_email" />
                  <input type="text" class="form-control form-control-user" id="mem_email" 
                  value="${fn:substringBefore(member.mem_email, '@') }" placeholder="이메일주소 (영문자/숫자)"
                  style="width:270px; margin-right:10px;"/>
                  <select id="emailType" class="form-control form-control-user" style="width:130px; margin-right:10px;">
					<option value="">이메일 선택</option>
					<option value="@naver.com">@naver.com</option>
					<option value="@hotmail.com">@hotmail.com</option>
					<option value="@nate.com">@nate.com</option>
					<option value="@hanmail.net">@hanmail.net</option>
					<option value="@gmail.com">@gmail.com</option>
				</select>
				<input type="button" id="idCheckBtn" class="btn btn-outline-primary mb-2" value="인증번호 전송"/> 
				</div>
                <form:errors path="member.mem_email" element="span" cssClass="error"/>
				
				<div class="form-group row">
					<input type="text" id="auth_num" class="form-control form-control-user" placeholder="인증번호 입력" style="width:300px; margin-right:10px;"/>
					<input type="button" id="authNumCheckBtn" class="btn btn-outline-primary mb-2" value="인증하기"/>
                </div>
                
                <div class="form-group row">
                	<input type="text" name="mem_nick" placeholder="닉네임" class="form-control form-control-user" style="width:300px; margin-right:10px;">
                	<input type="button" id="nickCheckBtn" class="btn btn-outline-primary mb-2" value="중복확인"/>
                	<small id="nickHelp-success" style="display: inline-block;">영문/한글/숫자 5글자이상 10글자이내</small>
                	<small id="nickHelp-danger" style="display: none; color: red; font-weight: bold; ">영문/한글/숫자 5글자이상 10글자이내</small>
                	<form:errors path="member.mem_nick" element="span" cssClass="error" />
                </div>
                <div class="form-group row">
                    <input type="password" class="form-control form-control-user" name="mem_pass" minlength="8" maxlength="20" placeholder="비밀번호" style="width:300px;"/>
                	<small id="passHelp-success" style="display: inline-block;">대소문자 구분, 숫자 · 특수문자 포함, 8~20자리</small>
                	<small id="passHelp-danger" style="display: none; color: red; font-weight: bold; ">대소문자 구분, 숫자 · 특수문자 포함, 8~20자리</small>
                  	<form:errors path="member.mem_pass" element="span" cssClass="error" />
                    <input type="password" class="form-control form-control-user" id="mem_pass_check" minlength="8" maxlength="20" placeholder="비밀번호 확인" style="width:300px;"/> 
					<div style="padding-top:10px; padding-left:10px;">
	               	  	<small id="alert-success" style="display: none; text-align:center;">비밀번호가 일치합니다.</small>
	 					<small id="alert-danger" style="display: none; color: red; font-weight: bold;">비밀번호가 일치하지 않습니다.</small>
 					</div>
                 </div>  
                  <div class="form-group row">
                </div>
                <div class="form-group row">
                	<input type="text" name="mem_hp" placeholder="휴대폰번호" class="form-control form-control-user" style="width:300px; margin-right:10px;">
                	<input type="button" id="hpCheckBtn" class="btn btn-outline-primary mb-2" value="인증번호 전송"/>
                	<input type="text" id="Hp_auth_num" class="form-control form-control-user" placeholder="인증번호 입력" style="width:300px; margin-right:10px;"/>
                	<form:errors path="" element="span" cssClass="error"/>
					<input type="button" id="Hp_authBtn" class="btn btn-outline-primary mb-2" value="인증하기"/>
                </div>
                 <input id="check1" type="checkbox" />
                 <a href="<c:url value='/member/clause.do'/>" 
                 onclick="window.open(this.href,'name','resizebale=no','width=200px','height=500px', 'left=1000', 'top=500');return false">취준진담 이용약관동의</a>
                 <br/>
                 <input id="check2" type="checkbox" />
                 <a href="<c:url value='/member/private.do'/>" 
                 onclick="window.open(this.href,'name','resizebale=no','width=200px','height=500px', 'left=1000', 'top=500');return false">개인정보 수집 및 이용에 대한 안내</a>
                <input type="button" id="joinBtn" class="btn btn-primary btn-user btn-block" class="btn btn-outline-primary mb-2" value="회원가입"
                />
              </form>
              <hr>
              <div class="text-center">
                <a class="small" href="<c:url value='/login/forgotPass.do'/>">비밀번호 찾기</a>
              </div>
              <div class="text-center">
                  <a class="small" href="<c:url value='/login/login.do'/>">로그인</a>
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

  <!-- Core plugin JavaScript -->
  <script src="../js/login/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages -->
  <script src="../js/login/sb-admin-2.min.js"></script>

</body>
<script type="text/javascript">

var insertForm = $("#insertForm");

var idInputTag = $("#mem_email").on("change",function(){//타이핑 이벤트 발생시마다 idchecked를 false로 해줌
	insertForm.data("idchecked",false);//외부에서 수정할 수 없다.
});
var nickInputTag = $("input[name='mem_nick']").on("change",function(){//타이핑 이벤트 발생시마다 idchecked를 false로 해줌
	insertForm.data("nickchecked",false);//외부에서 수정할 수 없다.
});
var hpInputTag = $("input[name='mem_hp']").on("change",function(){//타이핑 이벤트 발생시마다 idchecked를 false로 해줌
	insertForm.data("hpchecked",false);//외부에서 수정할 수 없다.
});



var email;

// 아이디 중복확인
$("#idCheckBtn").on("click", function(){
	//다른 항목들을 유지한 채로 중복여부 확인 ==> 비동기
	
	
	//이메일 정규표현식 적용 (@ . 제한)
	let inputId = idInputTag.val();
	var idPattern = /^[A-Za-z0-9\_+]*$/;
	if(!idPattern.test(inputId)) {
		$("#mem_email").val("");
		$("#mem_email").focus();
        return;
    }
	
	
	let emailType = $("#emailType").val(); //이메일 종류
	if(!inputId){//null일 때
		idInputTag.focus();
		return;
	}
	
	if(emailType==""){ //이메일종류 선택 안했을 때
		$("#emailType").focus();
		return;
	}
	
	email = inputId + emailType;
	//$("#auth").html(""); // 태그 초기화
	//$("#auth").append("<input type='text' id='auth_num' /><input type='button' id='authNumCheckBtn' value='인증하기'/>"); // 태그 추가
	
	$.ajax({
		url : "${pageContext.request.contextPath}/member/checkId.do",
		data : {
			email : email
		},
		method : "post",
		dataType : "json", //Accept헤더,Content-Type 두가지 헤더 결정
		success : function(resp) { //resp = json데이터가 javascript객체로 언마샬링
			if(resp.status=="PKDUPLICATED"){
				idInputTag.val("");
				idInputTag.focus();
			}else{//중복확인까지 끝내고 submit버튼 기능이 되어야함
				sendMail(email);
				insertForm.data("idchecked",true);	
			}
		
			$(".message").remove();
			if(resp.message == "사용 가능"){
				$("#idCheckBtn").after(
						$("<small style='display: inline-block;'>").addClass("message")
								.text(resp.message)
					);
			}else if(resp.message == "이미 존재하는 이메일입니다."){ //얘안됨; (resp.message == "이미 존재하는 이메일입니다.")
				$("#idCheckBtn").after(
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

//인증메일보내기
function sendMail(email) {
	$.ajax({
		url : "${pageContext.request.contextPath}/member/sendMail.do",
		data : {
			email : email
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
}
var authInputTag = $("#auth_num").on("change",function(){//타이핑 이벤트 발생시마다 authNumChecked를 false로 해줌
	insertForm.data("authNumChecked",false);//외부에서 수정할 수 없다.
});


//이메일인증하기버튼 눌렀을 때
$("#authNumCheckBtn").on("click", function(){
	
	let inputAuth = authInputTag.val();
	if(!inputAuth){//null일 때
		authInputTag.focus();
		return;
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
				insertForm.data("authNumChecked",true);	
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

//닉네임중복확인버튼 눌렀을 때
$("#nickCheckBtn").on("click", function(){
	let inputNick = nickInputTag.val();
	
	var nickPattern = /^[가-힣A-Za-z0-9]{5,10}$/;
	 
	
	if(!inputNick){//null일 때
		nickInputTag.focus();
		return;
	}else if(!nickPattern.test(inputNick)) {
	 $("#nickHelp-success").css('display', 'none');
   	 $("#nickHelp-danger").css('display', 'inline-block');
		nickInputTag.focus();
        return;
    }else{
    	 $("#nickHelp-success").css('display', 'inline-block');
       	 $("#nickHelp-danger").css('display', 'none');	
    }


	
	$.ajax({
		url : "${pageContext.request.contextPath}/member/checkNick.do",
		data : {
			nick : inputNick
		},
		method : "post",
		dataType : "json", 
		success : function(resp) { 
			if(resp.status=="PKDUPLICATED"){
				nickInputTag.val("");
				nickInputTag.focus();
			}else{
				insertForm.data("nickChecked",true);	
				
			}
			$(".message").remove();
			if(resp.message=="사용가능"){
				$("#nickCheckBtn").after(
						$("<small style='display: inline-block; margin-left:10px;'>").addClass("message")
								.text(resp.message)
					);
			}else{
				$("#nickCheckBtn").after(
						$("<small style='display: inline-block; color: red; font-weight: bold; margin-left:10px; maring-top:5px;'>").addClass("message")
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



//휴대폰 인증번호 보내기 버튼
$("#hpCheckBtn").on("click",function(){
	
	var hpNum = hpInputTag.val();
    var regExp = /[ㄱ-ㅎ가-힣a-zA-Z\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;

	    if(regExp.test(hpNum)){
	    	alert("숫자만입력하세요.");
	    	hpInputTag.focus();
	        return;
	    }else if(!regExp.test(hpNum)){
	    	hpNum = hpNum.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3");
	    	hpInputTag.val(hpNum);
			sendMessage(hpNum); //나중에 풀어!!
	    	insertForm.data("hpChecked",true);
	    }
});

//휴대폰인증보내기
function sendMessage(hpNum) {
	$.ajax({
		url : "${pageContext.request.contextPath}/member/sendMessage.do",
		data : {
			hpNum : hpNum
		},
		method : "post",
		dataType : "json", //Accept헤더,Content-Type 두가지 헤더 결정
		success : function(resp) { //resp = json데이터가 javascript객체로 언마샬링
			alert("문자를 정상적으로 보냈습니다.");
			insertForm.data("hpChecked",true);	
		},
		error : function(xhr) {
			console.log(xhr.status)
		}
	});
}

//휴대폰번호 인증번호 태그
var Hp_authInputTag = $("#Hp_auth_num").on("change",function(){//타이핑 이벤트 발생시마다 authNumChecked를 false로 해줌
	insertForm.data("Hp_authNumChecked",false);//외부에서 수정할 수 없다.
});



//휴대폰인증하기버튼 눌렀을 때
$("#Hp_authBtn").on("click", function(){
// 	insertForm.data("Hp_authNumChecked",true);	 //나중에 지워
 	let Hp_inputAuth = Hp_authInputTag.val();
	if(!Hp_inputAuth){//null일 때
	//	alert("인증번호를 입력해주세요.");
		Hp_authInputTag.focus();
		return;
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/member/checkHpAuthNum.do",
		data : {
			hp_authNum : Hp_inputAuth
		},
		method : "post",
		dataType : "json", 
		success : function(resp) { 
			if(resp.status=="FAIL"){
				Hp_authInputTag.val("");
				Hp_authInputTag.focus();
			}else{
				insertForm.data("Hp_authNumChecked",true);	
			}

			$(".message").remove();
			if(resp.message=="인증 성공"){
				$("#Hp_authBtn").after(
						$("<small style='display: inline-block;'>").addClass("message")
								.text(resp.message)
					);
			}else{
				$("#Hp_authBtn").after(
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


//가입 눌렀을때
$('#joinBtn').on("click",function(){
	insertForm.submit();
});
insertForm.on("submit",function(event){
	$("input[name='mem_email']").val(email);
	
	let idChecked = $(this).data("idchecked");
	let authNumChecked = $(this).data("authNumChecked");
	let nickChecked = $(this).data("nickChecked");
	let hpChecked = $(this).data("hpChecked");
	let Hp_authNumChecked = $(this).data("Hp_authNumChecked");
	let samePassChecked = $(this).data("samePassChecked");
	
	if(!idChecked){
		alert("이메일 인증 해주세요.");
		return false;
	}else if(!authNumChecked){
		alert("인증번호 확인버튼을 눌러주세요.");
		return false;
	}else if(!nickChecked){
		alert("닉네임 중복확인버튼을 눌러주세요.");
		return false;
	}else if(!hpChecked){
		alert("휴대폰 인증버튼을 눌러주세요.");
		return false;
	}else if(!Hp_authNumChecked){
		alert("인증번호 확인버튼을 눌러주세요.");
		return false;
	}else if($('input:checkbox[id="check1"]').is(":checked") == false){
		alert("약관 동의 체크 해주세요.");
		return false;
	}else if($('input:checkbox[id="check2"]').is(":checked") == false){
		alert("약관 동의 체크 해주세요.");
		return false;
	}else if(!samePassChecked){
		return false;
	}
	
	
})
</script>
</html>