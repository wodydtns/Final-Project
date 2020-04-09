<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${cPath }/js/slider/carousel.min.js"></script>
<link rel="stylesheet" href="${cPath }/js/slider/carousel.css">
<div class="row">
	<div class="col-md-5">
		<div class="card">
			<div class="content">
				<div class="head">
					<h4 class="mb-0">나의 쿠폰 보유 현황</h4>
				</div>
				<div class="card">
					<div class="card-body">
					<c:forEach items="${couponList }" var="coupon">
					<div class="card-body" style="border:1px solid black;margin:3px; width:300px;">
						<div class="float-left">
							<span>${coupon.cp_nm }</span>
						</div>
						<br />
						<div class="float-left">
							<span> ${coupon.cp_disc } 할인 금액</span>
							<span style="color:orange;font-size: 8pt"> ${coupon.cp_end_date } 까지</span>
						</div>
						</div>
					</c:forEach>	
					</div>					
				</div>
			</div>
		</div>
	</div>
</div>
