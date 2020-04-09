<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<!-- 메인 css 추가 -->
<link href="${cPath }/css/pms/pms-style.css" rel="stylesheet" type="text/css">
  
<!-- Custom fonts for this template-->
<link href="${cPath }/css/pms/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  
<!-- Custom styles for this template-->
<link href="${cPath }/css/pms/sb-admin-2.min.css" rel="stylesheet">

<!-- Bootstrap core JavaScript-->
<script src="${cPath }/css/pms/vendor/jquery/jquery.min.js"></script>
<script src="${cPath }/css/pms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${cPath }/css/pms/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${cPath }/js/pms/sb-admin-2.min.js"></script>

<body>

<form method="post">
<div style="padding:20px;">
<h3 style="margin-left:40%;">결제 정보</h3>
<p>클래스 이름  <input type="text" class="form-control" readonly="readonly" value="${pay.pi_nm}" name="pi_nm"/></p>
<p>가격  <input type="text" class="form-control" readonly="readonly" value="${pay.pay_amt}" name="pay_amt"/></p>
<p>내 쿠폰조회</p>
<select class="form-control" name="cp_have_cd" id="cp_box">
	<option value="">쿠폰선택</option>
	<c:forEach items="${mycoupon}" var="coupon" >
	<option value="${coupon.cp_have_cd}" class="${coupon.cp_disc}">${coupon.cp_nm}</option>
	</c:forEach>
</select>

<br>
<br>
<input type="hidden" value="${pay.cl_cd}" name="cl_cd"/>
<input type="hidden" name="mem_email" value="${pay.mem_email}"/>
최종결제금액  <input id="finalamt" class="form-control" readonly="readonly" name="final_pay_amt"></input>
<br>
<br>
<button type="submit" class="btn btn-warning" style="width:100%;">카카오페이로 결제하기</button>
<br><br>
</div>
</form>
</body>



<script type="text/javascript">
	$(document).ready(function(){
		$("#finalamt").val("${pay.pay_amt}");
	});


	$("#cp_box").on("change",function(){
		var disc_amt = $("#cp_box option:selected").attr("class");
		var ori_amt = ${pay.pay_amt};
		var final_amt = parseInt(ori_amt) - parseInt(disc_amt);
		$("#finalamt").val(final_amt);
	});

</script>




</html>