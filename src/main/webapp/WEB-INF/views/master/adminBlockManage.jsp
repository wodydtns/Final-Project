<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.card {
		margin-top : 100px;
		margin-right : 180px;
	}
</style>
<div>
	<div class="card">
		<div class="card-header">신고리스트</div>
		<div class="card-body">
			<p class="card-title"></p>
			<table class="table table-hover" id="dataTables-example">
				<thead class="thead-dark">
					<tr>
						<th onclick="numberTableSort(this,true)" scope="col">번호</th>
						<th onclick="tableSort(this)" scope="col">신고회원 회원</th>
						<th onclick="tableSort(this)" scope="col">신고당한 회원</th>
						<th onclick="tableSort(this)" scope="col">신고 URL</th>
						<th onclick="tableSort(this)" scope="col">신고사유 기타</th>
						<th onclick="dateTimeTableSort(this,'DateTime')" scope="col">가입일</th>
						<th>탈퇴버튼</th>
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
									<option value="report"
										${"report" eq search.searchType?"selected":"" }>신고자</option>
										<option value="reported"
										${"reported" eq search.searchType?"selected":"" }>피신고자</option>
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
			<form id="deleteForm" action="${cPath }/master/memberDeleteManage.do" >
				<input type="hidden" name="mem_email" />
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
	var action="${cPath }/master/adminBlockManage.do";
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
				$(dataList).each(function(index,report){
					var eachTr = $("<tr>");
					eachTr.append(
						$("<td>").text(report.rn),
						$("<td>").text(report.report_email),
						$("<td>").text(report.reported_email),
						$("<td>").html(
							$("<a>").attr({
								"href":"<c:url value='${report.rep_url}'/>"
							})
						.text(report.rep_url)
						),
						$("<td>").text(report.rep_reason_ecp),
						$("<td>").text(report.rep_date),
						$("<td>").html(
								$("<button>").attr({
								"type":"button",
								"value": "${member.mem_email}",
								"name":"delete",
								"class":"btn btn-danger"
								}).text("탈퇴")
							),
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
var deleteForm = $("#deleteForm");
$(document).on("click","[name='delete']",function(){
	var message = confirm("정말로 탈퇴 시키시나요?");
	if(message){
		var email = $(this).val();
		console.log(deleteForm);
		deleteForm.find("[name='mem_email']").val(email);
		deleteForm.submit();
	};
	
});
</script>




