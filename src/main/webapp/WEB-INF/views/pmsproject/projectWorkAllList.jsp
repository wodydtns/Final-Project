<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 16.      최효은      최초작성
* 2020. 3. 19.      최효은      검색을 위한 PROGRESS, PRIORITY 추가
* Copyright (c) 2020 by DDIT All right reserved
 --%>
 
  
<!-- 테이블 전체 div -->
<div class="container-fluid">

	<!-- 프로젝트 리스트 제목, 내용 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div>
			<h1 class="h3 mb-2 text-gray-800">전체 작업 리스트</h1><br>
			<p class="mb-4">모든 프로젝트의 작업 리스트가 표시됩니다.</p>
		</div>
<%-- 		<a href="<c:url value='/pmsProject/pmsProjectInsert.do' />" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"> --%>
<!-- 		<i class="fas fa-arrow-alt-circle-right fa-sm text-white-50"></i> 새 프로젝트 생성</a> -->
	</div>

	<!-- 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<span class="m-0 font-weight-bold text-primary">진행중인 작업</span>
			<div id="inputForm" class="form-inline justify-content-center mb-3">
				<select name="prog_cd" class="pms-input-border pms-align-right" >
					<!-- <spring:message code="proj.proj_cate" /> -->
					<option value="">진행상황 선택</option>
					<c:forEach items="${progressList }" var="prog">
						<option value="${prog['prog_cd'] }">${prog['prog_nm'] }</option>
					</c:forEach>
				</select>
				<select name="prior_cd" class="pms-input-border pms-align-right" >
					<!-- <spring:message code="proj.proj_cate" /> -->
					<option value="">우선순위 선택</option>
					<c:forEach items="${priorityList }" var="prio">
						<option value="${prio['prior_cd'] }">${prio['prior_nm'] }</option>
					</c:forEach>
				</select>
				<input type="text" name="" id="searchWord"  class="form-control form-control-sm"
						placeholder="작업명을 입력해주세요."	value="${search.searchWord }"	/>	
				<input type="button" value="검색" id="searchBtn"	width="100"
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"/>
			</div>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<p class="pms-table-sort-exp">※ 테이블의 제목 행을 클릭하면 정렬이 가능합니다. (오름차순/내림차순)</p>
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th onclick="numberTableSort(this,true)" class="pms-table-center">No</th>
							<th onclick="tableSort(this)" class="pms-table-center">프로젝트명</th>
							<th onclick="tableSort(this)" class="pms-table-center">상태</th>
							<th onclick="tableSort(this)" class="pms-table-center">우선순위</th>
							<th onclick="tableSort(this)" class="pms-table-center">작업명</th>
							<th onclick="tableSort(this)" class="pms-table-center">담당자</th>
							<th onclick="dateTimeTableSort(this,'DateTime')" class="pms-table-center">변경일자</th>
						</tr>
					</thead>
					<tbody id="listBody"></tbody>
					<tr>
						<td colspan="7">
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
<form id="searchForm">
	<input type="hidden" name="page" value="${param.page}" />
	<input type="hidden" name="who" value="${authMember.mem_email}" />
	<input type="hidden" name="prog_cd" value="${param.prog_cd }"/>
	<input type="hidden" name="prior_cd"  value="${param.prior_cd }"/>
	<input type="hidden" name="searchWord" value="${search.searchWord }"/>
</form>

<script type="text/javascript">
var listBody = $("#listBody");
var pagingArea = $("#pagingArea");
var inputForm = $("#inputForm");

var searchWordTag = $("#searchWord");
var prog_cdTag = $("[name='prog_cd']");
var prior_cdTag = $("[name='prior_cd']");

var searchForm = $("#searchForm").on("submit", function(event){
	event.preventDefault();
	let queryString = $(this).serialize();
	let method = $(this).attr("method");
		// 비동기 처리 요청
		$.ajax({
			url: "<c:url value='/pmsProject/projectWorkAllList.do' />",
			method: method,
// 			data : {"who":"${authMember.mem_email}", "page" : "${param.page}"},
			data : queryString,
			dataType : "json", 
			success : function(resp) {
				let dataList = resp.dataList;
				let trTags = [];
				if(dataList.length>0){
					$(dataList).each(function(index, pwList){
						let eachTr = $("<tr>");
						eachTr.append(
							$("<td>").text(pwList.rnum),		// 작업코드
							$("<td>").text(pwList.proj_nm),		// 프로젝트명
							$("<td>").text(pwList.prog_nm),		// 상태
							$("<td>").text(pwList.prior_nm),	// 우선순위
							$("<td>").html(
								$("<a>").attr({
									"href" : "<c:url value='/pmsProject/projectWorkAllView.do'/>?what="+pwList.pw_cd
								}).text(pwList.pw_nm)			// 작업명
							),	
							$("<td>").text(pwList.mem_nick),	// 담당자
							$("<td>").text(pwList.pw_modi_date)	// 변경일자
						);
						trTags.push(eachTr);
					});
				}else{
					trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"7"
							}).text("진행중인 작업 목록이 없습니다.")		
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
});

	// 검색 버튼을 눌렀을 때의 처리
	var searchBtn = $("#searchBtn").on("click", function(){
		let searchWord = searchWordTag.val();
		var inputTags = inputForm.find(":input[name]");
		$(inputTags).each(function(index, element){
			let name = $(this).attr("name");
			let value = $(this).val();
			let searchFormInput = searchForm.find("input[name='"+name+"']");
			searchFormInput.val(value);
		});
		searchForm.find("[name='searchWord']").val(searchWord); 
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
	});
	
	
	// 검색 조건에 값 채워주는 부분
	prog_cdTag.val("${param['prog_cd']}");
	prior_cdTag.val("${param['prior_cd']}");
	
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
 
 
 
 
 
 