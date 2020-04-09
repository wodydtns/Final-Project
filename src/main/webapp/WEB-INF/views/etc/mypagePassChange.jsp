<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/mypageCheck.css">
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap" rel="stylesheet">
<style>
	body {
		font-family: 'Do Hyeon', sans-serif;
	font-size: 18px;
	}
</style>
<div class="elelment">
<div class="element-main">
<h1>비밀번호 변경</h1>
		<p>비밀번호를 입력해주세요</p>
<form id="passForm" method="post">
    <label for="inputPassword" class="col-form-label">새 비밀번호 입력</label>
    <div>
      <input type="password" class="form-control" name="mem_pass" minlength="8" maxlength="20" required placeholder="비밀번호 입력칸">
      <small id="nickHelp" class="form-text text-muted">대소문자 구분, 숫자 · 특수문자 포함, 8~20자리</small>
    </div>
    <label for="inputPassword" class="col-form-label">새 비밀번호 재입력</label>
    <div>
      <input type="password" class="form-control" name="mem_pass2" minlength="8" maxlength="20" required placeholder="비밀번호 재입력칸">
      <small id="nickHelp" class="form-text text-muted">대소문자 구분, 숫자 · 특수문자 포함, 8~20자리</small>
    </div>
    <div>
      <input type="button" class="btn btn-danger" id="btn" value="확인"/>
    </div>
  </form>
  </div>
  </div>
  <script>
  	var passForm = $("#passForm");
  	$("#btn").on("click",function(event){
  		var pass1 = passForm.find("[name='mem_pass']");
  		var pass2 = passForm.find("[name='mem_pass2']");
  		if(pass1.val() === pass2.val()){
  			var action = "${cPath}/etc/mypagePassChange.do";
  			passForm.action = action;
  			passForm.submit();
  			event.preventDefault();
  			window.close();
  			alert("비밀번호가 변경되었습니다");
  		}else{
  			alert("비밀번호를 확인하여주십시오.");
  			pass1.val("");
  			pass2.val("");
  		}
  	});
  </script>