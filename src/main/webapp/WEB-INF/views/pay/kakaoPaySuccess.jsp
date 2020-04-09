<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
 
<img src="${cPath }/images/hobby/취준진담.png" style="margin-left: 80px;"><br><br>
<div style="font-size: 18px; margin: 20px;">
카카오페이 결제가 정상적으로 완료되었습니다.<br/>
 
결제일시:     ${info.approved_at}<br/>
주문번호:    ${info.partner_order_id}<br/>
상품명:    ${info.item_name}<br/>
상품수량:    ${info.quantity}<br/>
결제금액:    ${info.amount.total}<br/>
결제방법:    ${info.payment_method_type}<br/>
</div>

</body>
</html>
