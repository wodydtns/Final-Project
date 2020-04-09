<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.card {
		margin-top : 100px;
		margin-right : 180px;
	}
</style>
<div >
	<div class="card">
		<div class="card-header">공지사항</div>
		<div class="card-body">
			<p class="card-title"></p>
			
			<table class="table table-hover" id="dataTables-example">
				<thead class="thead-dark">
					<tr>
						<th onclick="numberTableSort(this,true)" scope="col">게시글 번호</th>
						<th onclick="tableSort(this)" scope="col">제목</th>
						<th onclick="tableSort(this)" scope="col">작성자</th>
						<th onclick="dateTimeTableSort(this,'DateTime')" scope="col">작성일자</th>
						<th onclick="numberTableSort(this,true)" scope="col">조회 수</th>
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
								<a href="${cPath }/master/adminCSCenterBoardWrite.do"><input type="button" class="btn btn-success" value="글 작성"></a>
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
								"href":"<c:url value='/master/adminCSBoardDetail.do'/>?cs_cd="+center.cs_cd
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




