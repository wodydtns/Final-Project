<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 17.		김혜정	  최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setLocale value="ko" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>개인정보입력</title>

<!-- Custom fonts for this template-->
<!--  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css"> -->
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../css/login/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">

	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image">
						<img src="../images/login/회원가입사진.png">
					</div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">
									<b>추가정보입력</b>
								</h1>
							</div>
							<form action="<c:url value='/member/nickHpInsert.do'/>"
								method="post" id="insertForm">
								<c:set var="user" value="${sessionScope.email}"/>
								<input type="hidden" name="mem_email" value="${user}"/>
								<div class="form-group row">
									<input type="text" name="mem_nick" placeholder="닉네임"> <small
										id="nickHelp" class="form-text text-muted">영문,한글,숫자포함
										5글자이상 10글자이내</small>
									<form:errors path="member.mem_nick" element="span"
										cssClass="error" />
									<input type="button" id="nickCheckBtn" value="중복확인" />
								</div>
								<div class="form-group row">
									<input type="text" name="mem_hp" placeholder="휴대폰번호 숫자만 입력"> <input
										type="button" id="hpCheckBtn" value="인증번호 전송" /> 
										<br>
										<input
										type="text" id="Hp_auth_num" />
									<%-- <form:errors path="" element="span" cssClass="error" /> 나중에 풀기 --%>
									<input type="button" id="Hp_authInputTag" value="인증하기" />
								</div>
								<input type="button" id="insertBtn" value="등록하기" />
							</form>
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
var insertForm = $("#insertForm");

var nickInputTag = $("input[name='mem_nick']").on("change", function() {//타이핑 이벤트 발생시마다 idchecked를 false로 해줌
	insertForm.data("nickchecked", false);//외부에서 수정할 수 없다.
});
var hpInputTag = $("input[name='mem_hp']").on("change", function() {//타이핑 이벤트 발생시마다 idchecked를 false로 해줌
	insertForm.data("hpchecked", false);//외부에서 수정할 수 없다.
});

var Hp_authInputTag = $("#Hp_auth_num").on("change",function(){//타이핑 이벤트 발생시마다 authNumChecked를 false로 해줌
	insertForm.data("Hp_authNumChecked",false);//외부에서 수정할 수 없다.
});

//닉네임중복확인버튼 눌렀을 때
$("#nickCheckBtn").on("click", function(){
	let inputNick = nickInputTag.val();
	
	var nickPattern = /^[가-힣A-Za-z0-9]{5,10}$/;
	 
	
	if(!inputNick){//null일 때
		alert("닉네임을 입력해주세요.");
		nickInputTag.focus();
		return;
	}else if(!nickPattern.test(inputNick)) {
		nickInputTag.val("");
		nickInputTag.focus();
        return;
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
			nickInputTag.after(
					$("<span>").addClass("message")
							.text(resp.message)
				);
		},
		error : function(xhr) {
			console.log(xhr.status)
	}
	});
});	


$("#hpCheckBtn").on("click",function(){
	
	var hpNum = hpInputTag.val();
	
	    var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
	    var regExp2 = /[ㄱ-ㅎ가-힣a-zA-Z]/;

	    if(regExp.test(hpNum) || regExp2.test(hpNum)){
	    	alert("숫자만입력하세요.")
	    	hpInputTag.val("");
	    	hpInputTag.focus();
	        return;
	    }else if(!regExp.test(hpNum)){
	    	hpNum = hpNum.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3");
			sendMessage(hpNum);
	    	//insertForm.data("hpChecked",true);	//나중에지우기
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




//휴대폰인증번호확인버튼 눌렀을 때
$("#Hp_authInputTag").on("click", function(){
	
 	let Hp_inputAuth = Hp_authInputTag.val();
	if(!Hp_inputAuth){//null일 때
		alert("인증번호를 입력해주세요.");
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
			Hp_authInputTag.after(
					$("<span>").addClass("message")
							.text(resp.message)
				);
		},
		error : function(xhr) {
			console.log(xhr.status)
	}
	}); 
	//insertForm.data("Hp_authNumChecked",true); //나중에지우기	
});	


//submit
$('#insertBtn').on("click", function() {
	insertForm.submit();
});

var insertForm = $("#insertForm").on("submit", function(event) {
	let nickChecked = $(this).data("nickChecked");
	let hpChecked = $(this).data("hpChecked");
	let Hp_authNumChecked = $(this).data("Hp_authNumChecked");

	if (!nickChecked) {
		alert("닉네임 중복확인버튼을 눌러주세요.");
		return false;
	} else if (!hpChecked) {
		alert("휴대폰 인증버튼을 눌러주세요.");
		return false;
	} else if (!Hp_authNumChecked) {
		alert("인증번호 확인버튼을 눌러주세요.");
		return false;
	}
})
</script>
</html>