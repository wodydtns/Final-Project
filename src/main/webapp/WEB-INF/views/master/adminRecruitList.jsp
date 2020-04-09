<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<div class="card">
		<div class="card-header">Basic DataTables Table</div>
		<div class="card-body">
			<p class="card-title"></p>
			<table class="table table-hover" id="dataTables-example">
				<thead>
					<tr>
						<th onclick="numberTableSort(this,true)">번호</th>
						<th onclick="tableSort(this)">공고 제목</th>
						<th onclick="dateTimeTableSort(this,'DateTime')">공고 시작일</th>
						<th onclick="dateTimeTableSort(this,'DateTime')">공고 마감일</th>
						<th>연봉</th>
						<th onclick="tableSort(this)">직급</th>
						<th onclick="tableSort(this)">직책</th>
						<th onclick="dateTimeTableSort(this,'DateTime')">채용 인원</th>
						<th onclick="dateTimeTableSort(this,'DateTime')">작성일</th>
						<th onclick="tableSort(this)">학력</th>
						<th onclick="tableSort(this)">도</th>
						<th onclick="tableSort(this)">군구</th>
						<th onclick="tableSort(this)">경력</th>
						<th onclick="tableSort(this)">고용형태</th>
					</tr>
				</thead>
				<tbody id="listBody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="14">
							<div id="searchUI"
								class="form-inline justify-content-center mb-10">
								<div></div>
								<select id="searchType" name="searchType" class="form-control mr-2">
									<option value="">전체</option>
									<option value="city"
										${"city" eq search.searchType?"selected":"" }>도시</option>
										<option value="empteypnm"
										${"empteypnm" eq search.searchType?"selected":"" }>고용형태</option>
										<option value="caname"
										${"caname" eq search.searchType?"selected":"" }>경력</option>
										<option value="acanm"
										${"acanm" eq search.searchType?"selected":"" }>학력</option>
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
	var action="${cPath }/master/adminRecruitList.do";
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
				$(dataList).each(function(index,recruit){
					var eachTr = $("<tr>");
					eachTr.append(
						$("<td>").text(recruit.rnum),
						$("<td>").text(recruit.rec_title),
						$("<td>").text(recruit.rec_start),
						$("<td>").text(recruit.rec_end),
						$("<td>").text(recruit.rec_sal),
						$("<td>").text(recruit.rec_position),
						$("<td>").text(recruit.rec_duty),
						$("<td>").text(recruit.hire_cnt),
						$("<td>").text(recruit.rec_write_date),
						$("<td>").text(recruit.aca_nm),
						$("<td>").text(recruit.city),
						$("<td>").text(recruit.si),
						$("<td>").text(recruit.ca_name),
						$("<td>").text(recruit.emp_teyp_nm),
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




