<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 4. 1.      최효은      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
 
 
 <!-- 테이블 전체 div -->
<div class="container-fluid">

	<!-- 프로젝트 리스트 제목, 내용 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div>
			<h1 class="h3 mb-2 text-gray-800">프로젝트 게시판</h1><br>
			<p class="mb-4">프로젝트 게시판이 표시됩니다.</p>
		</div>
		<a href="<c:url value='/pmsProject/projectBoardInsert.do'>
        	<c:param name="what" value="${sessionScope.proj_cd }" />
        </c:url>"
		class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" style="color:white;">
		<i class="fas fa-arrow-alt-circle-right fa-sm text-white-50"></i> 새 게시글 등록</a>
	</div>

	<!-- 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<span class="m-0 font-weight-bold text-primary">게시글 목록</span>
			
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th onclick="numberTableSort(this,true)" class="pms-table-center">No</th>
							<th onclick="tableSort(this)" class="pms-table-center">제목</th>
							<th onclick="tableSort(this)" class="pms-table-center">작성자</th>
							<th onclick="dateTimeTableSort(this,'DateTime')" class="pms-table-center">작성일자</th>
							<th onclick="numberTableSort(this,true)" class="pms-table-center">조회수</th>
						</tr>
					</thead>
					<tbody id="listBody"></tbody>
					<tr>
						<td colspan="6">
							<!-- 검색 -->
							<div class="form-inline mb-3 justify-content-center">
								<select id="searchType" class="form-control mr-2">
									<option value="">전체</option>
									<option value="title">제목</option>
									<option value="content">내용</option>
									<option value="nickName">닉네임</option>
								</select>
								<input type="text" id="searchWord"  class="form-control mr-2"
									value="${search.searchWord }"
								/>
								<input type="button" value="검색" id="searchBtn" class="btn btn-info"/>
							</div>
							<div id="pagingArea"></div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

<!-- 검색, 페이징 처리 -->
<!-- 추후 수정 : ${authMember.mem_email} -->
<!-- <form id="searchForm"> -->
<%-- 	<input type="hidden" name="page" value="${param.page}" /> --%>
<%-- 	<input type="hidden" name="what" value="${sessionScope.proj_cd}" /> --%>
<!-- 	<input type="hidden" name="who" value="${authMember.mem_email}" /> -->
<%-- 	<input type="hidden" name="prog_cd" value="${param.prog_cd }"/> --%>
<%-- 	<input type="hidden" name="prior_cd"  value="${param.prior_cd }"/> --%>
<%-- 	<input type="hidden" name="mem_nick"  value="${param.mem_nick }"/> --%>
<%-- 	<input type="hidden" name="searchWord" value="${search.searchWord }"/> --%>
<!-- </form> -->



<!-- 검색, 페이징 처리 -->
<!-- 추후 수정 : ${authMember.mem_email} -->
<form id="searchForm">
	<input type="hidden" name="page" value="${param.page}" />
	<input type="hidden" name="what" value="${sessionScope.proj_cd}" />
	<input type="hidden" name="searchType" value="${param.searchType }"/>
	<input type="hidden" name="searchWord" value="${param.searchWord }"/>
</form>

<script type="text/javascript">
var listBody = $("#listBody");
var pagingArea = $("#pagingArea");

var searchTypeTag = $("#searchType");
var searchWordTag = $("#searchWord");
searchTypeTag.val("${search.searchType }");	// 검색 이후에도 값 채워주는 부분

var searchForm = $("#searchForm").on("submit", function(event){
	event.preventDefault();
	let queryString = $(this).serialize();
	let method = $(this).attr("method");
		// 비동기 처리 요청
		$.ajax({
			url: "<c:url value='/pmsProject/projectBoardList.do' />",
			method: method,
			data : queryString,
			dataType : "json", 
			success : function(resp) {
				let dataList = resp.dataList;
				console.log(dataList);
				let trTags = [];
				if(dataList.length>0){
					$(dataList).each(function(index, pbList){
						let eachTr = $("<tr>");
						eachTr.append(
							$("<td>").text(pbList.rnum),		// 작업코드
							$("<td>").html(
								$("<a>").attr({
									"href" : "<c:url value='/pmsProject/projectBoardView.do'/>?what="+pbList.stu_post_cd
								}).text(pbList.pw_title)			// 작업명
							),	
							$("<td>").text(pbList.mem_nick),			// 담당자
							$("<td>").text(pbList.pw_date),	// 작성일자
							$("<td>").text(pbList.pw_hit)	// 조회수
						);
						trTags.push(eachTr);
					});
				}else{
					trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"6"
							}).html("작성된 게시글이 없습니다.")		
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
				console.log(xhr.status + "," + xhr.responseText);
			}
		});
});
	
	// 검색 조건에 값 채워주는 부분
	var searchBtn = $("#searchBtn").on("click", function(){
		let searchType = searchTypeTag.val();
		let searchWord = searchWordTag.val();
		searchForm.find("[name='searchType']").val(searchType); 
		searchForm.find("[name='searchWord']").val(searchWord); 
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
	});
	
	// 페이징 처리
	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	});
	
	// 검색 버튼을 누르지 않아도 submit 할 수 있도록
	searchForm.submit();	
	
</script>
 
 
 