<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 30.      김혜정      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
  
<!-- 테이블 전체 div -->
<style>
#pagingArea{
	width: 100px;
	margin : 0 auto;
}
</style>
<div class="container-fluid">

	<!-- 수강생 리스트 제목, 내용 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div>
			<h1 class="h3 mb-2 text-gray-800">수강생 리스트</h1><br>
			<p class="mb-4">해당 클래스의 수강생 리스트가 표시됩니다.</p>
		</div>
	</div>

	<!-- 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<span class="m-0 font-weight-bold text-warning">누적 수강생</span>
			<div id="inputForm" class="form-inline justify-content-center mb-3">
				<select name="mem_nick" class="pms-input-border pms-align-right" >
					<option value="">닉네임</option>
				</select>
				<input type="text" name="" id="searchWord"  class="form-control form-control-sm"
						placeholder="닉네임을 입력해주세요."	 value="${search.searchWord }"	/>	
				<input type="button" value="검색" id="searchBtn"	width="100"
				class="d-none d-sm-inline-block btn btn-sm btn-warning shadow-sm"/>
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
							<th onclick="tableSort(this)" class="pms-table-center">닉네임</th>
							<th onclick="tableSort(this)" class="pms-table-center">휴대폰번호</th>
							<th onclick="numberTableSort(this,true)" class="pms-table-center">쿠폰할인금액</th>
							<th onclick="numberTableSort(this,true)" class="pms-table-center">최종결제금액</th>
							<th onclick="dateTimeTableSort(this,'DateTime')" class="pms-table-center">결제일</th>
						</tr>
					</thead>
					<tbody id="listBody"></tbody>
					<tr>
						<td colspan="6">
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
<form id="searchForm">
	<input type="hidden" name="page" value="${param.page}" />
	<input type="hidden" name="what" value="${cl_cd}" />
	<input type="hidden" name="searchWord" value="${search.searchWord }"/>
</form>

<script type="text/javascript">
var listBody = $("#listBody");
var pagingArea = $("#pagingArea");
var inputForm = $("#inputForm");

var searchWordTag = $("#searchWord");

var searchForm = $("#searchForm").on("submit", function(event){
	event.preventDefault();
	let queryString = $(this).serialize();
	let method = $(this).attr("method");
		// 비동기 처리 요청
		$.ajax({
			url: "<c:url value='/creatorCenter/stuList.do' />",
			method: method,
			data : queryString,
			dataType : "json", 
			success : function(resp) {
				let dataList = resp.dataList;
				console.log(dataList);
				let trTags = [];
				if(dataList.length>0){
					$(dataList).each(function(index, stuList){
						let eachTr = $("<tr>");
						eachTr.append(
							$("<td>").text(stuList.rnum),		//rownum
							$("<td>").text(stuList.mem_nick),	//아이디
							$("<td>").text(stuList.mem_hp),	//휴대폰번호
							$("<td>").text(stuList.cp_disc),		//할인금액
							$("<td>").text(stuList.final_pay_amt),	//최종결제금액
							$("<td>").text(stuList.pay_date)	//결제일
						);
						trTags.push(eachTr);
					});
				}else{
					trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"6"
							}).html("해당 클래스에 수강생이 없습니다.")		
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

	// 검색 버튼을 눌렀을 때의 처리
	var searchBtn = $("#searchBtn").on("click", function(){
		let searchWord = searchWordTag.val();
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
 
 
 