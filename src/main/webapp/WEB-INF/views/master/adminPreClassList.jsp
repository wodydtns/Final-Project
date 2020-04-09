<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty message }">
	<script>
		confirm("${message}");
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
		<div class="card-header">사전조사 중인 리스트</div>
		<div class="card-body">
			<p class="card-title"></p>
			<table class="table table-hover" id="classTable">
				<thead class="thead-dark">
					<tr>
						<th onclick="numberTableSort(this,true)" scope="col">번호</th>
						<th onclick="tableSort(this)" scope="col">크리에이터 명</th>
						<th onclick="tableSort(this)" scope="col">클래스 명</th>
						<th onclick="tableSort(this)" scope="col">클래스 부가정보</th>
						<th onclick="dateTimeTableSort(this,'DateTime')" scope="col">시작일</th>
						<th onclick="dateTimeTableSort(this,'DateTime')" scope="col">마지막 일</th>
						<th onclick="dateTimeTableSort(this,'DateTime')" scope="col">예정 출시일</th>
						<th onclick="tableSort(this)" scope="col">난이도</th>
						<th onclick="tableSort(this)" scope="col">카테고리명</th>
					</tr>
				</thead>
				<tbody id="listBody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="9">
							<div id="searchUI"
								class="form-inline justify-content-center mb-3">
								<select id="searchType" name="searchType" class="form-control mr-2">
									<option value="">전체</option>
									<option value="pinm"
										${"pinm" eq search.searchType?"selected":"" }>클래스 이름</option>
										<option value="lodnm"
										${"lodnm" eq search.searchType?"selected":"" }>난이도</option>
										<option value="catenm"
										${"catenm" eq search.searchType?"selected":"" }>카테고리</option>
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
			<form id="permitForm" >
				<input type="hidden" name="permit" value=""/>
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
	var action="${cPath }/master/adminPreClassList.do";
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
				$(dataList).each(function(index,pi){
					var eachTr = $("<tr>");
					eachTr.append(
						$("<td>").text(pi.rnum),
						$("<td>").text(pi.crea_email),
						$("<td>").text(pi.pi_nm),
						$("<td>").text(pi.pi_add_info),
						$("<td>").text(pi.pi_start),
						$("<td>").text(pi.pi_end),
						$("<td>").text(pi.pi_open),
						$("<td>").text(pi.lod_nm),
						$("<td>").text(pi.cate_nm),
						$("</tr>")
					);
					trTags.push(eachTr);
				});
				
			}else{
				trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"9"
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




