<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
#header1 {
	margin-top: -100px;
}

#section{
	background: white;
}
#inganin {
	width:80px;
	height:60px;
}
</style>
    
<!--================ Start header Top Area =================-->
<section class="header-top" id="header">
	<div class="container box_1170" id="header1">
		<div class="row align-items-center justify-content-between" id="header2">
			<div class="col-lg-6 col-md-6 col-sm-6" id="headerleft">
				<a href="index.html" class="logo">
					<img id="inganin" src="${pageContext.request.contextPath }/images/hobby/inganin.png" alt="">
				</a>
				<a>
					<img src="${pageContext.request.contextPath }/images/hobby/취미아이콘.png" id="logo">
				</a>
			</div>
			
			<div class="col-lg-6 col-md-6 col-sm-6 search-trigger">
				<a href="#" class="search"> 검색
					<img src="${pageContext.request.contextPath }/buttons/main/search.png"id="search">
				</a> &nbsp;  
				
				<a href="${pageContext.request.contextPath }/login/login.jsp" class="login"> 로그인
					<img id="login" src="${pageContext.request.contextPath }/buttons/main/login.png">
				</a> &nbsp;
				
				<a href="${pageContext.request.contextPath }/pms/pms.jsp">PMS
					<img src="${pageContext.request.contextPath }/buttons/main/pms.png">
				</a> &nbsp;
				
			</div>
		</div>
	</div>
		
</section>
	<!--================ End header top Area =================-->

	<!-- Start header Area -->
	<header id="header">
		<div class="container box_1170 main-menu">
			<div class="row align-items-center justify-content-center d-flex">
				<nav id="nav-menu-container">
					<ul class="nav-menu" id="maincategory">
						<li class="nav-item"><a href="${pageContext.request.contextPath }/recruit/recruitArea.do">지역별</a></li>
						<li><a href="${pageContext.request.contextPath }/recruit/recruitJob.do">직종별</a></li>
						<li><a href="archive.html">공채달력</a></li>
						<li class="menu-has-children"><a href="">인적성검사</a></li>
						<li class="menu-has-children"><a href="">추천</a></li>
						<li><a href="contact.html">취준생커뮤니티</a></li>
					</ul>
				</nav>
			</div>
		</div>
		
		<!-- 서치바  -->
		<div class="search_input" id="search_input_box"> 
			<div class="container box_1170">
				<form class="d-flex justify-content-between">
					<input type="text" class="form-control" id="search_input" placeholder="검색어 입력">
					<button type="submit" class="btn" id="searchBtn">
						<img src="${pageContext.request.contextPath }/buttons/main/search.png">
					</button>
					<span id="close_search" title="Close Search"><use xlink:href="#lnr-cross"></use></span>
				</form>
			</div>
		</div>
	</header>
	<!-- End header Area -->

	<!-- Top Stories Area 상단메뉴 밑 사진들(6)-->
	<section class="top-stories-area" id="section">
		<div class="container-fluid">
			<div class="row"></div>
		</div>
	</section>
	<!-- End Stories Area -->