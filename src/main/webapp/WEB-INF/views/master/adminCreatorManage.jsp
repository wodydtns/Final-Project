<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty message }">
<script>
	alert("${message }");
	</script>
</c:if>
<style>
.card {
		margin-top : 100px;
		margin-right : 180px;
	}
</style>
<div >
	<div class="card">
		<div class="card-header">크리에이터 리스트</div>
		<div class="card-body">
			<p class="card-title"></p>
			<table class="table table-hover" id="dataTables-example">
				<thead class="thead-dark">
					<tr>
						<th onclick="tableSort(this)" scope="col">아이디</th>
						<th onclick="tableSort(this)" scope="col">닉네임</th>
						<th onclick="tableSort(this)" scope="col">전화번호</th>
						<th onclick="dateTimeTableSort(this,'DateTime')" scope="col">가입일</th>
						<th onclick="tableSort(this)" scope="col">탈퇴 여부</th>
						<th onclick="tableSort(this)" scope="col">탈퇴 요청</th>
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
									<option value="email"
										${"email" eq search.searchType?"selected":"" }>이메일</option>
									<option value="nick"
										${"nick" eq search.searchType?"selected":"" }>닉네임</option>
									<option value="hp" ${"hp" eq search.searchType?"selected":"" }>전화번호</option>
								</select> 
								<input class="form-control mr-2" type="text" id="searchWord" name="searchWord" value="${search.searchWord }" /> 
								<input id="searchBtn" class="btn btn-info mr-2" type="button" value="검색" />
							</div>
							<div id="pagingArea">${pagingVO.pagingHTML }</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<form id="searchForm" action="${cPath }/master/adminCreatorManage.do">
				<input type="hidden" name="searchType" value="${search.searchType}" />
				<input type="hidden" name="searchWord" value="${search.searchWord }" />
				<input type="hidden" name="page" />
				
			</form>
			<form id="deleteForm" action="${cPath }/master/creatorDeleteManage.do" >
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
	var action = $(this).attr("action");
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
				$(dataList).each(function(index,member){
					var eachTr = $("<tr>");
					eachTr.append(
					$("<td>").text(member.mem_email),
					$("<td>").text(member.mem_nick),
					$("<td>").text(member.mem_hp),
					$("<td>").text(member.reg_date),
					$("<td>").text(member.yn_code),
					$("<td>").html(
							$("<button>").attr({
							"type":"button",
							"value": member.mem_email,
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




