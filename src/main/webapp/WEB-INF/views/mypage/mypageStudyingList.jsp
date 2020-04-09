<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
</style>
<div>
	<div class="card">
		<div class="card-header">Basic DataTables Table</div>
		<div class="card-body">
			<p class="card-title"></p>
			<table class="table table-hover" id="dataTables-example">
				<thead>
					<tr>
						<th onclick="numberTableSort(this,true)">번호</th>
						<th onclick="tableSort(this)">클래스 이름</th>
						<th onclick="tableSort(this)">강사 아이디</th>
						<th onclick="numberTableSort(this,true)">가격</th>
						<th onclick="numberTableSort(this,true)">좋아요 수</th>
						<th onclick="dateTimeTableSort(this,'DateTime')">클래스 시작일</th>
						<th onclick="dateTimeTableSort(this,'DateTime')">클래스 마감일</th>
					</tr>
				</thead>
				<tbody id="listBody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div id="searchUI"
								class="form-inline justify-content-center mb-3">
								<select id="searchType" name="searchType" class="form-control mr-2">
										<option value="">전체</option>
									<option value="creator"
										${"creator" eq search.searchType?"selected":"" }>강사</option>
										<option value="className"
										${"className" eq search.searchType?"selected":"" }>클래스 이름</option>
								</select> 
								<input class="form-control mr-2" type="text" id="searchWord" name="searchWord" value="${search.searchWord }" /> 
								<input id="searchBtn" class="btn btn-info mr-2" type="button" value="검색" />
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
	var action="${cPath }/mypage/mypageStudyingList.do";
	$.ajax({
		url:  action,
		method : "get",
		data : queryString,
		dataType : "json",
		success: function(resp){
			var dataList = resp.dataList;
			console.log(dataList);
			let trTags = [];
			if(dataList.length>0){
				$(dataList).each(function(index,payment){
					var eachTr = $("<tr>");
					eachTr.append(
						$("<td>").text(payment.classVO.rnum),
						$("<td>").text(payment.piVO.pi_nm),
						$("<td>").text(payment.piVO.crea_email),
						$("<td>").text(payment.classVO.cl_fee),
						$("<td>").text(payment.classVO.cl_like),
						$("<td>").text(payment.classVO.cl_start),
						$("<td>").text(payment.classVO.cl_end),
						$("</tr>")
					);
					trTags.push(eachTr);
				});
				
			}else{
				trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"7"
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




