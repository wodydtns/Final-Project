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
	background: #7c32ff 0%;
}
#bestTable{
	width:330px;
	height:300px;
	margin:20px;
	float:left;
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
	width:1200px;
}
#container{
	display:flex;
}
#pagingArea{
	margin-left: 500px;
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

</section>
<!-- Start Post Silder Area -->

<!-- Start main body Area 메인좌측 (클래스 들어갈 부분) -->
<div class="row">
<div class="col-lg-8 post-list">
	<section class="post-area">
	<form action="<c:url value='hobby/hobbyDetail.do'/>" method="get">
	<input type="hidden" name="cl_cd" />
		<div>
			<h3>사전조사 진행중</h3>
	        	<div id="listBody">
	        	
	        	</div>
		</div>
 </form>
 </section>
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

<script>

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
	if(!action) action = "<c:url value='/hobby/hobbyPI.do' />";
	if(!method) method = "get";
		
	let addr = "<c:url value='/hobby/hobbyDetail.do' />";
	$.ajax({
		url: "<c:url value='/hobby/hobbyPI.do'/>",
		method: "get",
		data: queryString,
		dataType: "json",
		success: function(resp) {
			let dataList = resp.dataList; 
			console.log(dataList) ;
			let trTags = [];
			if(dataList.length>0){
				$(dataList).each(function(index, best){
					let eachTr = $("<table class='table table-bordered' id='mainTable'>")
					eachTr.append(
							$("<tr>").html(
								$("<td>").html(
									$("<img class='coverimg'>").attr({
										"src":"${cPath}/piImages/"+best.temp_nm
										})
								)
							),
							$("<tr>").html(
									$("<td>").html(
									$("<a id='image'>").attr({
											"href": addr + '?what=' + best.cl_cd 
									})
											 .text(best.pi_nm )
									 )
							),
							$("<tr>").html(
									$("<td>").text(
											"클래스 시작일 : "+best.cl_start)	
							),
							$("<tr>").html(
									$("<td>").text(
											"클래스 종료일 : "+best.cl_end )	
							),
							$("<tr>").html(
								$("<td>").text(
										"클래스 가격 : "+best.cl_fee)		
							),
							$("<tr>").html(
								$("<td>").text(
										"좋아요 수 : "+best.cl_like)	
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

var searchBtn = $("#searchBtn").on("click", function(){
	let searchType = searchTypeTag.val();
	let searchWord = searchWordTag.val();
	searchForm.find("[name='searchType']").val(searchType); 
	searchForm.find("[name='searchWord']").val(searchWord); 
	searchForm.find("[name='page']").val(1);
	searchForm.submit();
});

pagingArea.on("click","a",function(event){
	event.preventDefault();
	let page = $(this).data("page");
	searchForm.find("[name='page']").val(page);
	searchForm.submit();
	return false;
});
searchForm.submit();
</script>
	

	
	
	
	
