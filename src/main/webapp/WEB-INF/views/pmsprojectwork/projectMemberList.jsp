<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 24.      최효은      최초작성
* 2020. 3. 25.      최효은      초대하기 기능 추가
* Copyright (c) 2020 by DDIT All right reserved
 --%>
 <c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
 
 
<!-- 테이블 전체 div -->
<div class="container-fluid">
  
	<!-- 프로젝트 리스트 제목, 내용 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div>
			<h1 class="h3 mb-2 text-gray-800">멤버 관리</h1><br>
			<p class="mb-4">현재 프로젝트에 참여중인 멤버가 표시됩니다.</p>
		</div>
		
		<!-- 초대하기 모달창 출력 부분 -->
		<a href="#" data-toggle="modal" data-target="#exampleModalLong"
		class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" style="color:white;">
		<i class="fas fa-arrow-alt-circle-right fa-sm text-white-50"></i> 초대하기</a>
	</div>

	<!-- 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">참여중인 멤버</h6>
			<!-- 검색조건 추가 -->
			<!-- 닉네임 -->
			<div id="inputForm" class="form-inline justify-content-center mb-3">
				<!-- 이름 검색 -->
				<input type="text" name="" id="searchWord"  class="form-control form-control-sm"
						placeholder="닉네임을 입력해주세요."	value="${search.searchWord }"	/>	
				<!-- 멤버 필터 -->
				<select name="yn_code2" class="pms-input-border pms-align-right" >
					<!-- <spring:message code="proj.proj_cate" /> -->
					<option value="">수락 여부</option>
					<c:forEach items="${agreeList }" var="agree">
						<option value="${agree['yn_code'] }">${agree['yn_name']}</option>
					</c:forEach>
				</select>
				<select name="yn_code" class="pms-input-border pms-align-right" >
					<!-- <spring:message code="proj.proj_cate" /> -->
					<option value="">탈퇴 여부</option>
					<c:forEach items="${deleteList }" var="delete">
						<option value="${delete['yn_code'] }">${delete['yn_name']} </option>
					</c:forEach>
				</select>
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
							<th onclick="tableSort(this)" class="pms-table-center">닉네임</th>
							<th onclick="tableSort(this)" class="pms-table-center">구분</th>
							<th onclick="dateTimeTableSort(this,'DateTime')" class="pms-table-center">초대일자</th>
							<th onclick="tableSort(this)" class="pms-table-center">이메일</th>
							<th class="pms-table-center">피드백</th>
							<th class="pms-table-center">탈퇴</th>
						</tr>
					</thead>
					<tbody id="listBody"></tbody>
					<tr>
						<td colspan="7"><div id="pagingArea"></div></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</div>  
<!-- /.container-fluid -->

<!-- 초대하기 모달창 -->
<div class="modal fade bd-example-modal-lg" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">초대하기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div id="modal-body">
      </div>
    </div>
  </div>
</div>

<!-- 탈퇴 처리 재확인 모달창 -->
<div class="modal fade" id="pmMemberOutModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel2">탈퇴 처리를 진행하시겠습니까?</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<!-- 추후 수정 : ${authMember.mem_email} -->
				<form id="pwDeleteForm"
					action="<c:url value='/pmsProject/projectWorkDelete.do'/>">
					<input type="hidden" name="what" value="${pwList.pw_cd }" />
					<input type="hidden" name="who" value="${authMember.mem_email}" />
				</form>
				<button type="button" id="pmMemberDelBtn" class="btn btn-primary">진행</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<!-- 피드백 인풋 폼 -->
<div class="modal fade" id="feedbackFormModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel2">피드백 작성</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<!-- 추후 수정 : ${authMember.mem_email} -->
				<div class="container-fluid">
				<button type="button" id="feedbackInsertBtn" class="btn btn-primary">전송</button>
<!-- 				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button> -->
			</div>
		</div>
	</div>
</div>
</div>

<!-- 검색, 페이징 처리 -->
<!-- 추후 수정 -->
<form id="searchForm">
	<input type="hidden" name="page" value="${param.page}"  />
	<input type="hidden" name="who" value="${authMember.mem_email}" />
	<input type="hidden" name="what" value="${sessionScope.proj_cd }" />
	<input type="hidden" name="yn_code" value="${param.yn_code }" />
	<input type="hidden" name="yn_code2" value="${param.yn_code2 }" />
	<input type="hidden" name="searchWord" value="${search.searchWord }"/>
</form>

<!-- 프로젝트 회원 탈퇴 폼 -->
<!-- 추후 수정 : ${authMember.mem_email} -->
<form id="pmMemberDeleteForm" action="projectWorkMemberDelete.do" method="post">
	<input type="hidden" name="mem_email" value="${authMember.mem_email}" />
	<input type="hidden" name="proj_cd" value="${sessionScope.proj_cd }" />
	<input type="hidden" name="pm_cd" />
</form>

<script type="text/javascript">

var listBody = $("#listBody");
var pagingArea = $("#pagingArea");

// 폼
var inputForm = $("#inputForm");
var pmMemberDeleteForm = $("#pmMemberDeleteForm");
var feedbackInsertForm = $("#feedbackInsertForm");

// 검색조건
var searchWordTag = $("#searchWord");
var yn_codeTag = $("[name='yn_code']");
var yn_code2Tag = $("[name='yn_code2']");

var dataTable = $("#dataTable");

// 모달
var pmMemberOutModal = $("#pmMemberOutModal");
var feedbackFormModal = $("#feedbackFormModal");

// 초대하기 모달창 출력
$("#exampleModalLong").on("show.bs.modal", function(event){
// 	let pm_cd = $("#pm_cd").val(pwData.['pm_cd']); 
	// 추후 수정 : ${authMember.mem_email}
	let aTag = $(event.relatedTarget);
	let modalBody = $(this).find("#modal-body")
	$.ajax({
		url : "<c:url value='/pmsProject/projectMember.do'/>",
		data : {
			who: "${authMember.mem_email}",
			what: ${sessionScope.proj_cd}
		},
		method : "get",
		dataType : "html",
		success : function(resp) {
			modalBody.html(resp);
		},
		error : function(xhr) {
			console.log(xhr.status);
		}
	});
});

// 피드백 보내기 비동기 데이터
$("#feedbackFormModal").on("show.bs.modal", function(event){
	console.log("찍히나?");
	let aTag = $(event.relatedTarget);
	let modalBody = $(this).find(".modal-body")
	$.ajax({
		url : "<c:url value='/pmsProject/projectFeedbackInsert.do'/>",
		data : {
			pm_cd: pm_cd,
			proj_cd: ${sessionScope.proj_cd},
		},
		method : "get",
		dataType : "html",
		success : function(resp) {
			modalBody.html(resp);
// 			if(resp>0){
// 				resp.each(function(index, pwMember){
// 					var html;
// 					html.append("<option>").val(resp.pw_cd).text(resp.pw_nm);
// 				})
// 				$("#pw_select").append(html);
// 			}
		},
		error : function(xhr) {
			console.log(xhr.status+","+xhr.responseText);
		}
	});
});

let pwData = null;
let pm_cd = null;
let target_email = null;

listBody = $("#listBody");

// 회원 탈퇴 모달창
listBody.on("click", ".pmDeleteCheck", function(){
	pwData = $(this).closest("tr").data("pmList");
	pmMemberOutModal.modal("show");
});

// 피드백 버튼
listBody.on("click", ".feedbackBtn", function(){
	pwData = $(this).closest("tr").data("pmList");
// 	$("#pm_cd").val(pwData['pm_cd']); 
	pm_cd = pwData['pm_cd'];
	feedbackFormModal.modal("show");
});

// 모달창 확인 이후 탈퇴 처리
$("#pmMemberDelBtn").on("click", function(){
	pmMemberDeleteForm.find("[name='pm_cd']").val(pwData['pm_cd']);
	pmMemberDeleteForm.submit();
});

// 모달창 확인 이후 피드백 전송 처리
$("#feedbackInsertBtn").on("click", function(){
	feedbackInsertForm.find("[name='feed_target']").val(pwData['pm_cd']);
	feedbackInsertForm.submit();
});

// 삭제할 회원의 이메일을 가져오는 방법
function projectDeleteBtn(a){
	// td 버튼을 눌렀을 시, 회원이메일을 선택
	var b = a.closest("td");
	var b_prev = $(b).prev();
	var delete_mem_email = b_prev.text();
}

// 피드백 받는 사람의 회원의 이메일을 가져오는 방법
function feedback(a){
	var b = a.closest("td");
	var b_prev = $(b).prev();
	var feedback_mem_email = b_prev.text();
}

// 비동기 처리 요청
var searchForm = $("#searchForm").on("submit", function(event){
	event.preventDefault();
	let queryString = $(this).serialize();
	let method = $(this).attr("method");
	$.ajax({
		url: "<c:url value='/pmsProject/projectMemberList.do' />",
		method: method,
		data : queryString,
		dataType : "json", 
		success : function(resp) {
			console.log(resp);
			let dataList = resp.dataList;
			let trTags = [];
			if(dataList.length>0){ 
				$(dataList).each(function(index, pmList){
					let eachTr = $("<tr>");
					eachTr.append(
						$("<td>").text(pmList.rnum),	// 순번
						$("<td>").text(pmList.mem_nick)	// 닉네임
					);
					if(pmList.mem_email == pmList.proj_leader){	// 리더구분
						eachTr.append($("<td>").text("리더"));
					}else if(pmList.yn_code2 == "YN_E03"){
						eachTr.append($("<td>").text("수락대기"));
					}else if(pmList.yn_code2 == "YN_E02"){
						eachTr.append($("<td>").text("미수락"));
					}else if(pmList.yn_code == "YN_A01"){
						eachTr.append($("<td>").text("탈퇴"));
					}else{
						eachTr.append($("<td>"));
					}
					eachTr.append($("<td>").text(pmList.pm_date));	// 초대일자
					eachTr.append($("<td>").text(pmList.mem_email));
					if(pmList.yn_code2 == "YN_E03" || pmList.yn_code2 == "YN_E02"){
						eachTr.append($("<td>"));
					}else if(pmList.mem_email == '${authMember.mem_email}' || pmList.yn_code == "YN_A01"){
						eachTr.append("<td>");
					}else{
						eachTr.append(
								"<td><button type='button' class='pms-width-right d-none d-sm-inline-block btn btn-success btn-secondary shadow-sm feedbackBtn' onclick='feedback(this);'> 피드백 </button></td>"
							);
					}
					// 추후 수정 : ${authMember.mem_email}
					if(pmList.proj_leader == '${authMember.mem_email}'){	// 프로젝트 리더이면,
						if(pmList.mem_email == '${authMember.mem_email}'){	// 프로젝트 리더 탈퇴 버튼 삭제
							eachTr.append(
									$("<td>")
								);
						}else{
							if(pmList.yn_code == "YN_A01" || pmList.yn_code2 == "YN_E03" || pmList.yn_code2 == "YN_E02"){
								eachTr.append($("<td>")).text();
							}else{
								eachTr.append("<td><button type='button' class='pms-width-right d-none d-sm-inline-block btn btn-sm btn-secondary shadow-sm pmDeleteCheck' onclick='projectDeleteBtn(this);'> 탈퇴 </button></td>");
							}
						}
					}else if(pmList.mem_email == '${authMember.mem_email}'){	// 본인이면,
						if(pmList.yn_code == "YN_A01"){
							eachTr.append($("<td>")).text();
						}else{
							eachTr.append("<td><button type='button' class='pms-width-right d-none d-sm-inline-block btn btn-sm btn-secondary shadow-sm pmDeleteCheck' onclick='projectDeleteBtn(this);'> 탈퇴 </button></td>");
						}
					}else{
						eachTr.append(		// 일반 회원이면,
								"<td>"
						).text();
					}
					eachTr.append().data("pmList", pmList);
					console.log(eachTr);
					trTags.push(eachTr);
				});
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").attr({
							colspan:"5"
						}).text("회원 목록이 없습니다.")		
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

	//검색 버튼을 눌렀을 때의 처리
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

	//검색 조건에 값 채워주는 부분
	yn_codeTag.val("${param['yn_code']}");
	yn_code2Tag.val("${param['yn_code2']}");

	// 페이징 처리
	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		console.log(page);
		searchForm.find("input[name='page']").val(page);
		searchForm.submit();
		return false;
	});
	
	// 검색 버튼을 누르지 않아도 submit 할 수 있도록
	searchForm.submit();
	
</script>
 
 
 
 
 