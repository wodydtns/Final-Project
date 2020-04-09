<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="kr.or.ddit.hobby.service.HobbyClassServiceImpl"%>
<%@page import="kr.or.ddit.vo.ClassVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:useBean id="coupon" class="kr.or.ddit.vo.CouponVO" scope="request" />

<style>
#mainclass {
	padding-top: 50px;
}
#header.header-scrolled {
    background: white;
    transition: all 0.5s;
    margin-top: 0;
}

#header.header-scrolled .nav-menu a {
    color: #415094;
}
#classpic {
	height:350px;
}
#mainbackground{
	background: rgb(255, 80, 79);
}
#mainTable{
	width:330px;
	height:300px;
	margin:20px;
	float:left;
	overflow:hidden;
}
#mainTable img{
	width:320px;
	height:240px;
}
#mainTable img:hover{
	transform:scale(1.1); 
}
#mainTable tr, #mainTable tr td{
	border: none;
}
#listBody{
	width:760px;
}
#container{
	display:flex;
}
#pagingArea{
	margin-left: 300px;
}
#ceomail h4{
	text-align: center;
}
#ceo{
    width: 220px;
    margin-left: 35px;
    margin-top: 15px;
}
.p-text{
	color: white;
}
#couponHeader{
	text-align: center;
	color: white;
	font-size: 20px;
}
#fee{
	color:white;
}
/*
.coverimg{
	width:100%;
    height:200px;
}
*/
</style>
<%
	String mem_email = new CookieUtils(request).getCookieValue("idCookie");
	String message = (String)session.getAttribute("message");
%>

<c:if test="${not empty message }">
<script>
	alert("${message}");
</script>
</c:if>



<!-- Start Post Silder Area 컨텐츠 상단-->
<section class="post-slider-area">
	<div class="container-fluid">
		<div class="row justify-content-center" id="mainbackground">

		 <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          </ol>
          
          <!-- 광고/베스트 클래스 -->
          <div class="carousel-inner" role="listbox" id="ol"> 
          
            <div class="carousel-item active">
              <img class="d-block img-fluid" src="${cPath }/images/hobby/classpic/olPic1.png" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="${cPath }/images/hobby/classpic/olPic2.png" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="${cPath }/images/hobby/classpic/olPic3.png" alt="Third slide">
            </div>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev" id="qq">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next" id="qq">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
		</div>
	</div>
</section>

<!-- Start main body Area 메인좌측 (클래스 들어갈 부분) -->
<div class="main-body section-gap" id="mainclass">
	<div class="container box_1170" id="container">
		<div class="row">
			<div class="col-lg-8 post-list">
				<section class="post-area">
				<form action="<c:url value='hobby/hobbyDetail.do'/>" method="get">
				<input type="hidden" name="cl_cd" />
					<div>
						<h3>신규 클래스</h3>
				        	<div id="listBody">
				        	
				        	</div>
					</div>
			 </form>
			 </section>
		 </div>
	  </div>
		
	
			<!-- Start Post Area 메인우측-->
			<div class="col-lg-4 sidebar">
				<div class="single-widget protfolio-widget">
					<img class="img-fluid" id="ceo" src="${pageContext.request.contextPath }/images/hobby/대표님.png" alt="">
					<a href="#" id="ceomail">
						<h4>(주)취준진담 박재욱대표</h4>
					</a>
					<p class="p-text">
						취미와 취업 두마리 토끼를 한꺼번에 잡을 수 있는 취준진담이 되겠습니다.
					</p>
					<img src="../images/hobby/sign.png" alt="">
				</div>
				
				<!-- 쿠폰메뉴 -->
				<form action="<c:url value='hobby/hobbyMainCheck.do'/>" method="get" id="couponForm" >
				<input type="hidden" name="cp_cd" />
				<div class="single-widget popular-posts-widget">
					<div class="jq-tab-wrapper" id="horizontalTab">
						<div class="jq-tab-menu">
							<div class="jq-tab-title active" data-tab="1" id="couponHeader">쿠폰</div>
						</div>
						<div class="jq-tab-content-wrapper">
							<div class="jq-tab-content active" data-tab="1" id="couponDiv">
								<c:forEach items="${couponList }" var="coupon">
								<div class="single-popular-post d-flex flex-row">
									<div class="popular-thumb" id="couponImg">
										<img class="img-fluid" src="${pageContext.request.contextPath }/images/hobby/coupon${coupon.cp_cd }.png" alt="">
									</div>
									<div class="popular-details">
										<h6><a href="<c:url value='/hobby/hobbyMainCheck.do?what=${coupon.cp_cd }'/>" id="getCoupon${coupon.cp_cd }" >발급받기</a></h6>
										<p id="fee">${coupon.cp_nm }</p>
									</div>
								</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div><br><br>
				</form>
				


				<div class="single-widget tags-widget">
					<h4 class="title">Post Tags</h4>
					<ul>
						<li><a href="#">Lifestyle</a></li>
						<li><a href="#">Art</a></li>
						<li><a href="#">Adventure</a></li>
						<li><a href="#">Food</a></li>
						<li><a href="#">Techlology</a></li>
						<li><a href="#">Fashion</a></li>
						<li><a href="#">Architecture</a></li>
						<li><a href="#">Food</a></li>
						<li><a href="#">Technology</a></li>
					</ul>
				</div>
			</div>
			
		</div>
	</div>
	
	 <div id="pagingArea">
			${pagingVO.pagingHTML }
	 </div>
 <form id="searchForm">
<input type="hidden" name="page" />
<input type="hidden" name="searchType" value="${param.searchType }"/>
<input type="hidden" name="searchWord" value="${param.searchWord }"/>
</form>				
		

<div id="frogue-container" class="position-right-bottom"
      data-chatbot="070f97d2-8139-4d46-ab8f-a6b55161d2e7"
      data-user="wodydtns@gmail.com"
      data-init-key="value"
      ></div>
<!-- data-init-식별키=값 으로 셋팅하면 챗플로우에 파라미터와 연동가능. 식별키는 소문자만 가능 -->
<script>
(function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement(s); js.id = id;
    js.src = "https:\/\/danbee.ai/js/plugins/frogue-embed/frogue-embed.min.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'frogue-embed'));
</script>

<script type="text/javascript">

//페이징 처리
var searchTypeTag = $("#searchType");
searchTypeTag.val("${param.searchType }");
var searchWordTag = $("#searchWord");
var pagingArea = $("#pagingArea");
var listBody = $("#listBody");
var searchForm = $("#searchForm").on("submit", function(event){
	event.preventDefault();
	let queryString = $(this).serialize();
	let action = $(this).attr("action");
	let method = $(this).attr("method");
	if(!action) action = "<c:url value='/hobby/hobbyMain.do' />"; <%-- "<%=request.getContextPath()%>/member/memberList.do"; --%>
	if(!method) method = "get";
		
	let addr = "<c:url value='/hobby/hobbyDetail.do' />";
	$.ajax({
		url: "<c:url value='/hobby/hobbyMain.do' />",
		method: "get",
		data: queryString,
		dataType: "json",
		success: function(resp) {
			let dataList = resp.dataList; 
			console.log(dataList) ;
			let trTags = [];
			if(dataList.length>0){
				$(dataList).each(function(index, hobby){
					let eachTr = $("<table class='table table-bordered' id='mainTable'>")
					eachTr.append(
							$("<tr>").html(
								$("<td>").html(
									$("<img class='coverimg'>").attr({
										"src":"${cPath}/piImages/"+hobby.temp_nm
										})
								)
							),
							$("<tr>").html(
									$("<td>").html(
									$("<a id='image'>").attr({
											"href": addr + '?what=' + hobby.cl_cd 
									})
											 .text(hobby.pi_nm )
									 )
							),
							$("<tr>").html(
									$("<td>").text(
											"클래스 시작일 : "+hobby.cl_start),	
							),
							$("<tr>").html(
									$("<td>").text(
											"클래스 종료일 : "+hobby.cl_end ),	
							),
							$("<tr>").html(
								$("<td>").text(
										"클래스 가격 : "+hobby.cl_fee),		
							),
							$("<tr>").html(
								$("<td>").text(
										"좋아요 수 : "+hobby.cl_like)	
							)
					);
					trTags.push(eachTr);
				});
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").attr({
							colspan:"8"
						}).text("클래스 목록이 없음.")		
					)
				);
			}
			listBody.empty();
			listBody.append(trTags);
			let pagingHTML = resp.pagingHTML;
			pagingArea.empty(); //**
			pagingArea.html(pagingHTML);
		},
		error : function(xhr) {
			console.log(xhr.status);
		}
		
	});

});

//검색
var searchBtn = $("#searchBtn").on("click", function(){
		let searchType = searchTypeTag.val();
		let searchWord = searchWordTag.val();
		searchForm.find("[name='searchType']").val(searchType); 
		searchForm.find("[name='searchWord']").val(searchWord); 
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
	});
	
//페이징 처리
pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	});
searchForm.submit();

</script>
	

	
	
	
	
