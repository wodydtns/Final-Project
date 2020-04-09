<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>

<link href="../css/master/assets/vendor/bootstrap4/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/master/assets/css/master.css" rel="stylesheet">
<link href="../css/master/assets/vendor/chartsjs/Chart.min.css" rel="stylesheet">
<link href="../css/master/assets/vendor/flagiconcss3/css/flag-icon.min.css" rel="stylesheet">
 <script src="../css/master/assets/vendor/jquery3/jquery-3.4.1.min.js"></script>
<script src="../css/master/assets/vendor/bootstrap4/js/bootstrap.bundle.min.js"></script>
<script src="../css/master/assets/vendor/fontawesome5/js/solid.min.js"></script>
<script src="../css/master/assets/vendor/fontawesome5/js/fontawesome.min.js"></script>
 <link href="../css/main.css" rel="stylesheet">
<!-- date dropper -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/datedropper.pro.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- bootstrap library -->
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>

<!-- D3 javascript library -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/d3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/d3.min.js"></script>

<script src="https://d3js.org/d3.v4.js"></script>

<!-- Load d3-cloud -->
<script src="https://cdn.jsdelivr.net/gh/holtzy/D3-graph-gallery@master/LIB/d3.layout.cloud.js"></script>

<!-- chart js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Chart.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/dist/summernote-lite.min.js"></script>
<link href="${cPath }/js/dist/summernote-lite.min.css" rel="stylesheet">

<!-- table sorting -->
<script src="${cPath }/js/IntlTableSort.js"></script>
<script src="${cPath }/js/IntlTableSort.DateTime.js"></script>
<script src="${cPath }/js/IntlTableSort.Number.js"></script>

<!-- video js -->
<link href="https://vjs.zencdn.net/7.6.6/video-js.css" rel="stylesheet" />

 <!-- If you'd like to support IE8 (for Video.js versions prior to v7) -->
<script src="https://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>


</head>
<style>
.card {
		margin-top : 100px;
		margin-right : 180px;
}
.card-header{
	text-align: center;
}
</style>
<body>
<%
	String mem_email = new CookieUtils(request).getCookieValue("idCookie");
	String message = (String)session.getAttribute("message");
%>
<c:if test="${not empty message }">
<script>
	alert("${message}");
</script>
</c:if>

<div >
	<div class="card">
		<div class="card-header">공지사항</div>
		<div class="card-body">
			<p class="card-title"></p>
			
			<table class="table table-hover" id="dataTables-example">
				<thead>
					<tr>
						<th onclick="numberTableSort(this,true)">게시글 번호</th>
						<th onclick="tableSort(this)">제목</th>
						<th onclick="tableSort(this)">작성자</th>
						<th onclick="dateTimeTableSort(this,'DateTime')">작성일자</th>
						<th onclick="numberTableSort(this,true)">조회 수</th>
					</tr>
				</thead>
				<tbody id="listBody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="5">
							<div id="searchUI"
								class="form-inline justify-content-center mb-3">
								<select id="searchType" name="searchType" class="form-control mr-2">
									<option value="">전체</option>
									<option value="content"
										${"content" eq search.searchType?"selected":"" }>내용</option>
										<option value="title"
										${"title" eq search.searchType?"selected":"" }>제목</option>
								</select> 
								<input class="form-control mr-2" type="text" id="searchWord" name="searchWord" value="${search.searchWord }" /> 
								<input id="searchBtn" class="btn btn-info mr-2" type="button" value="검색" />
								<form action="<c:url value='hobby/noticeIdCheck.do'/>" method="get">
								<a href="<c:url value='/hobby/noticeIdCheck.do?mem_email=${member.mem_email }'/>"><input type="button" class="btn btn-success" value="글 작성"></a>
								</form>
							</div>
							<div id="pagingArea">${pagingVO.pagingHTML }</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<form id="searchForm" >
				<input type="hidden" name="searchType" value="${search.searchType}" />
				<input type="hidden" name="searchWord" value="${search.searchWord }" />
				<input type="hidden" name="page" />
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">

var searchTypeTag = $("#searchType");
var searchWordTag = $("#searchWord");
var listBody = $("#listBody");
var pagingArea = $("#pagingArea");

var searchForm = $("#searchForm").on("submit", function(event){
	event.preventDefault();
	var queryString = $(this).serialize();
	var action="${cPath }/master/adminCSCenter.do";
	$.ajax({
		url:  action,
		method : "get",
		data : queryString,
		dataType : "json",
		success: function(resp){
			var dataList = resp.dataList;
			let trTags = [];
			if(dataList.length>0){
				$(dataList).each(function(index,center){
					var eachTr = $("<tr>");
					eachTr.append(
						$("<td>").text(center.rn),
						$("<td>").html(
							$("<a>").attr({
								"href":"<c:url value='/hobby/hobbyNoticeDetail.do'/>?cs_cd="+center.cs_cd
							})
						.text(center.cs_title)
						),
						$("<td>").text(center.admin_id),
						$("<td>").text(center.cs_date),
						$("<td>").text(center.cs_hit),
						$("</tr>")
					);
					trTags.push(eachTr);
				});
				
			}else{
				trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"5"
							}).text("게시글 목록이 없음.")		
						)
					);
			}
			listBody.empty();
			listBody.append(trTags);
			let pagingHTML = resp.pagingHTML;
			pagingArea.empty(); //**
			pagingArea.html(pagingHTML);
		},
		error : function(error){
			console.log(error.status);
		}
	});
});
var searchBtn = $("#searchBtn").on("click",function(){
	let searchType = searchTypeTag.val();
	let searchWord = searchWordTag.val();
	console.log(searchWord);
	searchForm.find("[name='searchType']").val(searchType); 
	searchForm.find("[name='searchWord']").val(searchWord); 
	searchForm.find("[name='page']").val(1);
	searchForm.submit();
});
pagingArea.on("click", "a", function(event){
	event.preventDefault();
	let page = $(this).data("page");
	searchForm.find("[name='page']").val(page);
	searchForm.submit();
	return false;
});
searchForm.submit();
</script>

</body>
</html>