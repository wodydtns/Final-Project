<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--  [[개정이력(Modification Information)]]       -->
<!--  수정일        수정자     수정내용               -->
<!--  ==========   ======    ==============        -->
<!--  2020. 3. 13.     최효은     최초작성               -->
<!--  Copyright (c) 2020 by DDIT All right reserved -->
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
<style>
	.btn-primary{
		color: white;
	}
</style>

<!-- 테이블 전체 div -->
<div class="container-fluid">
  
	<!-- 프로젝트 리스트 제목, 내용 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div>
			<h1 class="h3 mb-2 text-gray-800">프로젝트 리스트</h1><br>
			<p class="mb-4">현재 진행중인 프로젝트가 표시됩니다.</p>
		</div>
		
		<a href="<c:url value='/pmsProject/projectInsert.do' />" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" style="color:white;">
		<i class="fas fa-arrow-alt-circle-right fa-sm text-white-50"></i> 새 프로젝트 생성</a>
	</div>
	<!-- 초대 대기중인 프로젝트 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">초대받은 프로젝트</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable2" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th class="pms-table-center">No</th>
							<th class="pms-table-center">프로젝트명</th>
							<th class="pms-table-center">프로젝트 설명</th>
							<th class="pms-table-center">수락여부</th>
						</tr>
					</thead>
					<tbody id="listBody2"></tbody>
					<tr>
						<td colspan="4">
							<div id="pagingArea2"></div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<!-- 진행 중인 프로젝트 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">진행중인 프로젝트</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th onclick="numberTableSort(this,true)" class="pms-table-center">No</th>
							<th onclick="tableSort(this)" class="pms-table-center">프로젝트명</th>
							<th onclick="dateTimeTableSort(this,'DateTime')" class="pms-table-center">시작일자</th>
							<th onclick="dateTimeTableSort(this,'DateTime')" class="pms-table-center">종료일자</th>
						</tr>
					</thead>
					<tbody id="listBody"></tbody>
					<tr>
						<td colspan="4">
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
	<input type="hidden" name="page" value="${param.page}"  />
	<input type="hidden" name="who" value="${authMember.mem_email}" />
</form>
<form id="searchForm2">
	<input type="hidden" name="page" value="${param.page}"  />
	<input type="hidden" name="who" value="${authMember.mem_email}" />
</form>
<form id="searchForm3" action="<c:url value='/pmsProject/pmsUpdateAgree.do'/>" method="post" >
	<input type="hidden" name="proj_cd" value="${sessionSope.proj_cd}"  />
	<input type="hidden" name="mem_email" value="${authMember.mem_email}" />
	<input type="hidden" name="yn_code2" value="" />
</form>

<script type="text/javascript">
var listBody = $("#listBody");
var pagingArea = $("#pagingArea");
var searchForm = $("#searchForm");

var listBody2 = $("#listBody2");
var pagingArea2 = $("#pagingArea2");
var searchForm2 = $("#searchForm2");
var searchForm3 = $("#searchForm3");

var dataTable2 = $("#dataTable2");

// 비동기 처리 요청(진행중인 프로젝트)
var searchForm = $("#searchForm").on("submit", function(event){
	event.preventDefault();
	let queryString = $(this).serialize();
	$.ajax({
		url: "<c:url value='/pmsProject/projectList.do' />",
		method: "get",
		data : queryString,
		dataType : "json", 
		success : function(resp) {
			let dataList = resp.dataList;
			let trTags = [];
			if(dataList.length>0){ 
				$(dataList).each(function(index, proj){
					let eachTr = $("<tr>");
					eachTr.append(
						$("<td>").text(proj.rnum),
						$("<td>").html(
							$("<a>").attr({
								// 추후 수정
								"href" : "<c:url value='/pmsProject/projectView.do'/>?what="+proj.proj_cd+"&who=${authMember.mem_email}"
							}).text(proj.proj_nm)
						),	
						$("<td>").text(proj.proj_start),
						$("<td>").text(proj.proj_end)
					);
					trTags.push(eachTr);
				});
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").attr({
							colspan:"4"
						}).text("진행중인 프로젝트 목록이 없습니다.")		
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
	// 페이징 처리(진행중인 프로젝트)
	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		console.log(page);
		searchForm.find("input[name='page']").val(page);
		searchForm.submit();
		return false;
	});

dataTable2.on("click", "button", function() {
	// dataTable2 테이블의 버튼(수락/거부)을 누르면, 해당 버튼의 값을 form submit으로 데이터 보내기
	let ynTag = searchForm3.find("input[name='yn_code2']");
	let projTag = searchForm3.find("input[name='proj_cd']");
	let ynVal = $(this).val();
	let projVal = $(this).data("projcd");
	ynTag.val(ynVal);
	projTag.val(projVal);
	searchForm3.submit();
});
	
// 비동기 처리 요청(초대 대기중 프로젝트)
var searchForm2 = $("#searchForm").on("submit", function(event){
	event.preventDefault();
	let queryString = $(this).serialize();
	$.ajax({
		url: "<c:url value='/pmsProject/projectList2.do' />",
		method: "get",
		data : queryString,
		dataType : "json", 
		success : function(resp) {
			let dataList = resp.dataList;
			let trTags = [];
			if(dataList.length>0){ 
				$(dataList).each(function(index, proj){
					let eachTr = "<tr>";
						eachTr += "<td>"+proj.rnum+"</td>";
						eachTr += "<td>"+proj.proj_nm+"</td>";
						eachTr += "<td class='pms-proj-exp-width' >"+proj.proj_exp+"</td>";
						eachTr += "<td><input type='hidden' name='yn_code2' "
						+ " class='pms-search-invi' readonly onfocus='this.blur()'><button type='button' value='YN_E01' data-projcd='"+proj.proj_cd+"' class='pms-width-right d-none d-sm-inline-block btn btn-primary shadow-sm'> 수락 </button>";
						eachTr += "<input type='hidden' name='yn_code2' "
						+ " class='pms-search-invi' readonly onfocus='this.blur()'><button type='button' value='YN_E02' data-projcd='"+proj.proj_cd+"' class='pms-width-right d-none d-sm-inline-block btn btn-danger shadow-sm' style='margin-left:10px'> 거절 </button> </td>";
						eachTr += "</tr>";
					trTags.push(eachTr);
				});
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").attr({
							colspan:"4"
						}).text("초대받은 프로젝트 목록이 없습니다.")		
					)
				);
			}
			listBody2.empty();
			listBody2.append(trTags);	
			let pagingHTML = resp.pagingHTML;
			pagingArea2.empty(); //**
			pagingArea2.html(pagingHTML);
		},
		error : function(xhr) {
			console.log(xhr.status);
		}
	});
	
});
	// 페이징 처리(초대 대기중 프로젝트)
	pagingArea2.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		console.log(page);
		searchForm2.find("input[name='page']").val(page);
		searchForm2.submit();
		return false;
	});
	
	searchForm.submit();
	searchForm2.submit();
	
</script>