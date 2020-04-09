<%@page import="kr.or.ddit.hobby.service.HobbyClassServiceImpl"%>
<%@page import="kr.or.ddit.vo.ClassVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<style type="text/css">
#mainclass {
	padding-top: 30px;
}

#gongo, #gongo div {
	width: 365px;
}

#right {
	padding-right: 50px; 
}

thead tr th {
	text-align: center;
}

#searchWord {
	width:400px;
	float: left;
}

#pagingArea {
	padding-left: 550px;
	
}

#card-body {
	board: 1px solid lightgray;
}

#topTable { 
	border: 1px solid lightgray; 
	width: 101%; 
	height:700px; 
	padding: 15px;
} 

#title {
	font-size: 18px;
}

#jimIcon img {
	width:20px;
	height:20px;
}

#jim {
	margin-top: 30px;
}

#header.header-scrolled {
   background: white;
   transition: all 0.5s;
   margin-top: 0;
}

#header.header-scrolled .nav-menu a {
    color: #415094;
}
	
</style>

	<!-- Start main body Area 테이블 전체 -->
	<div class="main-body section-gap" id="mainclass">
		<div class="container box_1170">
		
		<div id="firstArea">
			<div id="h3"><h3>최근 등록된 공고</h3></div>
			<table id="topTable" border="1" width="100%">
<!-- 				<thead> -->
<!-- 					<tr> -->
<!-- 						<th colspan="4"></th> -->
<!-- 					</tr> -->
<!-- 				</thead> -->
				<tr>
				<c:forEach items="${recruitList2 }" begin="0" end="3" var="recruit2">
					<td>
						<div><a href="#" id="title">${recruit2.rec_title }</a></div>
						<div>${recruit2.rec_end } 마감</div>
						<div id="jim"><a id="jimIcon" href="#"><img id="image" src="${cPath}/buttons/star1.png" /></a>스크랩</div>
						<input type="hidden" value="${recruit2.rec_cd }">
					</td>
				</c:forEach>
				</tr>
				<tr>
				<c:forEach items="${recruitList2 }" begin="4" end="7" var="recruit2">
					<td>
						<div><a href="#" id="title">${recruit2.rec_title }</a></div>
						<div>${recruit2.rec_end } 마감</div>
						<div id="jim"><a id="jimIcon" href="#"><img src="${cPath}/buttons/star1.png" /></a>스크랩</div>
					</td>
				</c:forEach>
				</tr>
				<tr>
				<c:forEach items="${recruitList2 }" begin="8" end="11" var="recruit2">
					<td>
						<div><a href="#" id="title">${recruit2.rec_title }</a></div>
						<div>${recruit2.rec_end } 마감</div>
						<div id="jim"><a id="jimIcon" href="#"><img src="${cPath}/buttons/star1.png" /></a>스크랩</div>
					</td>
				</c:forEach>
				</tr>
				<tr>
				<c:forEach items="${recruitList2 }" begin="12" end="15" var="recruit2">
					<td>
						<div><a href="#" id="title">${recruit2.rec_title }</a></div>
						<div>${recruit2.rec_end } 마감</div>
						<div id="jim"><a id="jimIcon" href="#"><img src="${cPath}/buttons/star1.png" /></a>스크랩</div>
					</td>
				</c:forEach>
				</tr>
				<tr>
				<c:forEach items="${recruitList2 }" begin="16" end="19" var="recruit2">
					<td>
						<div><a href="#" id="title">${recruit2.rec_title }</a></div>
						<div>${recruit2.rec_end } 마감</div>
						<div id="jim"><a id="jimIcon" href="#"><img src="${cPath}/buttons/star1.png" /></a>스크랩</div>
					</td>
				</c:forEach>
				</tr>
			</table>
		</div>
		<br><br><br><hr><br><br><br>
		
		
		
			<!-- 테이블 -->
			<div class="row" id="secondArea">
				<h3>전체 공고</h3>
				<table class="table table-bordered" id="recruitTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>직급</th>
							<th>시작일자</th>
							<th>종료일자</th>
						</tr>
					</thead>
					<tbody id="listBody">
					
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<input type="text" id="searchWord"  class="form-control mr-2"
								value="${search.searchWord }"
								/>							
								<input type="button" value="검색" id="searchBtn" class="btn btn-info"/>
								<div id="pagingArea">
									${pagingVO.pagingHTML }
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	
<!-- 검색, 페이징 처리 -->
<form id="searchForm">
	<input type="text" name="page" />
	<input type="text" name="searchType" value="${param.searchType }"/>
	<input type="text" name="searchWord" value="${param.searchWord }"/>
</form>
				
	
<script type="text/javascript">
var pagingArea = $("#pagingArea");
var listBody = $("#listBody");
var searchForm = $("#searchForm").on("submit", function(event){
	event.preventDefault();
	let queryString = $(this).serialize();
	let action = $(this).attr("action");
	let method = $(this).attr("method");
	if(!action) action = "<c:url value='/recruit/recruitMain.do' />"; <%-- "<%=request.getContextPath()%>/member/memberList.do"; --%>
	if(!method) method = "get";
		
	$.ajax({
		url: "<c:url value='/recruit/recruitMain.do' />",
		method: "get",
		data: queryString,
		dataType: "json",
		success: function(resp) {
			let dataList = resp.dataList; 
			console.log(dataList) ;
			let trTags = [];
			if(dataList.length>0){
				$(dataList).each(function(index, recruit){
					let eachTr = $("<tr>")
					eachTr.append(
							$("<td>").text(recruit.rnum),	
							$("<td>").html(
								$("<a>").attr({
											"href":"#"
										})
										.text(recruit.rec_title)
							),	
							$("<td>").text(recruit.rec_position),	
							$("<td>").text(recruit.rec_start),	
							$("<td>").text(recruit.rec_end)	
					);
					trTags.push(eachTr);
				});
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").attr({
							colspan:"5"
						}).text("채용공고 목록이 없음.")		
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

})
		
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
pagingArea.on("click","a",function(event){
	event.preventDefault();
	let page = $(this).data("page");
	searchForm.find("[name='page']").val(page);
	searchForm.submit();
	return false;
});
searchForm.submit();
			
$(function(){
	$("#jimIcon").click(function(){
		$("#image").attr("src", "${pageContext.request.contextPath }/buttons/star2.png")
	})
})


</script>
	
