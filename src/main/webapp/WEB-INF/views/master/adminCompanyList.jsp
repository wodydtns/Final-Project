<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
</style>
<div >
	<div class="card">
		<div class="card-header">Basic DataTables Table</div>
		<div class="card-body">
			<p class="card-title"></p>
			<table class="table table-hover" id="dataTables-example">
				<thead>
					<tr>
						<th onclick="numberTableSort(this,true)">번호</th>
						<th onclick="tableSort(this)">회사명</th>
						<th onclick="tableSort(this)">사업자 번호</th>
						<th onclick="tableSort(this)">기업형태</th>
						<th onclick="tableSort(this)">업종</th>
						<th onclick="numberTableSort(this,true)">사원수</th>
						<th onclick="dateTimeTableSort(this,'DateTime')">창립일</th>
						<th onclick="tableSort(this)">CEO</th>
						<th onclick="tableSort(this)">주소</th>
						<th onclick="tableSort(this)">회사 홈페이지</th>
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
									<option value="compnm"
										${"compnm" eq search.searchType?"selected":"" }>회사명</option>
										<option value="comtnm"
										${"comtnm" eq search.searchType?"selected":"" }>기업형태</option>
										<option value="compcate"
										${"compcate" eq search.searchType?"selected":"" }>업종</option>
										<option value="ceonm"
										${"ceonm" eq search.searchType?"selected":"" }>대표자</option>
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
	var action="${cPath }/master/adminCompanyList.do";
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
				$(dataList).each(function(index,company){
					var eachTr = $("<tr>");
					eachTr.append(
						$("<td>").text(company.rnum),
						$("<td>").text(company.comp_nm),
						$("<td>").text(company.comp_lic_no),
						$("<td>").text(company.comt_nm),
						$("<td>").text(company.comp_cate),
						$("<td>").text(company.emp_cnt),
						$("<td>").text(company.comp_open),
						$("<td>").text(company.ceo_nm),
						$("<td>").text(company.address),
						$("<td>").html(
									$("<a>").attr({
										"href":"${company.comp_url}"
									})
								.text(company.comp_url)
								),
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




