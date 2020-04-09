<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="codepixer">
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="shortcut icon" href="img/fav.png">

<title>취춘진담에 오신것을 환영합니다.</title>

<link href="https://fonts.googleapis.com/css?family=Roboto:400,500|Rubik:500" rel="stylesheet">
	<!--
			CSS
			============================================= -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/hobby/linearicons.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/hobby/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/hobby/magnific-popup.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/hobby/nice-select.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/hobby/animate.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/hobby/owl.carousel.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/hobby/main.css">
</head>

<body>
<!--================ Start header Top Area =================-->
	<section class="header-top">
		<div class="container box_1170" id="header1">
			<div class="row align-items-center justify-content-between" id="header2">
				<div class="col-lg-6 col-md-6 col-sm-6" id="headerleft">
					<a href="index.html" class="logo">
						<img id="inganin" src="../images/hobby/inganin.png" alt="">
					</a>
					<a>
						<img src="${pageContext.request.contextPath }/images/hobby/로고.png" id="logo">
					</a>
				</div>
				
				<div class="col-lg-6 col-md-6 col-sm-6 search-trigger"  id="headerright">
					<a href="#" class="search"> 검색
						<img src="../buttons/main/search.png"id="search">
					</a> &nbsp;  
					
					<a href="../login/login.jsp" class="login"> 로그인
						<img id="login" src="../buttons/main/login.png">
					</a> &nbsp;
					<a href="../pms/pms.jsp">PMS
						<img src="../buttons/main/pms.png">
					</a> &nbsp;
					
					<a href="../master/masterView.jsp">관리자
						<img src="../buttons/main/관리자.png">
					</a>
				</div>
			</div>
		</div>
			
	</section> <hr id="hr"><br><br>
	<!--================ End header top Area =================-->

	

	<!-- Start Post Silder Area 컨텐츠 상단-->
	<span id="choice">이용할 서비스를 선택해 주세요.</span><br><br>
	<section class="post-slider-area" >
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="owl-carousel active-post-carusel" id="imgg1">
						
						<!-- 취미생활 이미지-->
						<div class="single-post-carousel" id="img1"> 
							<div class="post-thumb">
								<img class="img-fluid" src="${pageContext.request.contextPath }/images/취미.png" alt="">
							</div>
							<div class="post-details"> 
								<h2><a href="${pageContext.request.contextPath }/hobby/hobbyMain.do">취미 볼래요</a></h2> <!-- 클릭시 해당 페이지로 이동 -->
								<div class="post-content d-flex justify-content-between">
									<div class="details"> 
										<p>취미생활을 즐기고 싶다면 클릭하세요</p>
									</div>
								</div>
							</div>
						</div>
						
						
						<!-- 취업연계 이미지-->
						<div class="single-post-carousel" id="img2">
							<div class="post-thumb">
								<img class="img-fluid" src="${pageContext.request.contextPath }/images/취업.png" alt="">
							</div>
							<div class="post-details">
								<h2><a href="${pageContext.request.contextPath }/hobby/hobbyMain.do">취업 볼래요</a></h2> <!-- 클릭시 해당 페이지로 이동 -->
								<div class="post-content d-flex justify-content-between">
									<div class="details">
										<p>취업연계를 이용하고 싶다면 클릭하세요.</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br><br>
	</section>
	
	<!-- Start Post Silder Area -->

	
	<!-- start footer Area -->
	<footer class="footer-area section-gap" id="footer1">
		<div class="container box_1170" id="footer">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6 class="footer_title">About Us</h6>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore dolore
							magna aliqua.</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6 class="footer_title">Newsletter</h6>
						<p>Stay updated with our latest trends</p>
						<div id="mc_embed_signup">
							<form target="_blank" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
							 method="get" class="subscribe_form relative">
								<div class="input-group d-flex flex-row">
									<input name="EMAIL" placeholder="Email Address" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email Address '"
									 required="" type="email">
									<button class="btn sub-btn"><span class="lnr lnr-arrow-right"></span></button>
								</div>
								<div class="mt-10 info"></div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6">
					<div class="single-footer-widget instafeed">
						<h6 class="footer_title">Instagram Feed</h6>
						<ul class="list instafeed d-flex flex-wrap">
							<li><img src="../images/hobby/i1.jpg" alt=""></li>
							<li><img src="../images/hobby/i2.jpg" alt=""></li>
							<li><img src="../images/hobby/i3.jpg" alt=""></li>
							<li><img src="../images/hobby/i4.jpg" alt=""></li>
							<li><img src="../images/hobby/i5.jpg" alt=""></li>
							<li><img src="../images/hobby/i6.jpg" alt=""></li>
							<li><img src="../images/hobby/i7.jpg" alt=""></li>
							<li><img src="../images/hobby/i8.jpg" alt=""></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2 col-md-6 col-sm-6">
					<div class="single-footer-widget f_social_wd">
						<h6 class="footer_title">Follow Us</h6>
						<p>Let us be social</p>
						<div class="f_social">
							<a href="#"><i class="fa fa-facebook"></i></a>
							<a href="#"><i class="fa fa-twitter"></i></a>
							<a href="#"><i class="fa fa-dribbble"></i></a>
							<a href="#"><i class="fa fa-behance"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row footer-bottom d-flex justify-content-between align-items-center">
				<p class="col-lg-12 footer-text text-center"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
			</div>
		</div>
	</footer>
	<!-- End footer Area -->

	<script src="${pageContext.request.contextPath }/js/hobby/vendor/jquery-2.2.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	 crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/vendor/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/easing.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/hoverIntent.js"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/superfish.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/jquery.ajaxchimp.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/jquery.magnific-popup.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/owl.carousel.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/jquery.tabs.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/jquery.nice-select.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/waypoints.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/mail-script.js"></script>
	<script src="${pageContext.request.contextPath }/js/hobby/main.js"></script>
	<script src="https://cdn.linearicons.com/free/1.0.0/svgembedder.min.js"></script>
	
</body>
</html>