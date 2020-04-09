<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 27.      최효은      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>

<!-- 작업 리스트 제목, 내용 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div>
			<!-- 추후 수정 : ${authMember.mem_email} -->
				<h1 class="h3 mb-2 text-gray-800">작업 상세 화면</h1><br>
				<p class="mb-4">해당 작업의 상세화면이 표시됩니다.</p>
		</div>
		<!-- 작업 테이블 -->
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<!-- 추후 수정 : ${authMember.mem_email} -->
				<a href="<c:url value="/pmsProject/projectWorkUpdate.do">
					<c:param name="what" value="${pwList.pw_cd }" />
				</c:url>"
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" style="color:white;">
				<i class="fas fa-download fa-sm text-white-50"></i>  작업 수정</a>
			<c:if test="${projectMemberList.get(0).proj_leader eq authMember.mem_email }">
				<a href="#" id="deleteBtn"><i class="fas fa-download fa-sm text-white-50">
				<input type="button" value=" 작업 삭제"
				class="d-none d-sm-inline-block btn btn-sm btn-danger shadow-sm" 
				data-toggle="modal" data-target="#pwDeleteModal" /></i></a>
			</c:if>
		</div>
	</div>

<!-- 작업 삭제 재확인 모달창 -->
<div class="modal fade" id="pwDeleteModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">정말 삭제하시겠습니까?</h5>
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
					<input type="hidden" name="proj_cd" value="${sessionScope.proj_cd }" />
					<input type="hidden" name="who" value="${authMember.mem_email}" />
				</form>
				<button type="button" id="pwModalDelBtn" class="btn btn-primary">삭제</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<!-- 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<div class="table-responsive">
				<form id="pmsProjectForm" method="post">
					<input type="hidden" name="proj_cd" value="${proj.proj_cd }" />
					<!-- 추후 수정 : ${authMember.mem_email} -->
					<input type="hidden" name="proj_leader" value="${authMember.mem_email}" />
					<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th class="pms-table-input">작업명
								<td>${pwList.pw_nm }</td>
									<th class="pms-table-input">담당자</th>
								<td>${pwList.mem_nick }
								</td>
							</tr>
							<tr>
								<th class="pms-table-input pms-table-top">설명</th>
								<td colspan="3">
									<!-- 에디터 -->
<%-- 									<textarea name="pw_exp"  required="required" id="summernote">${center.cs_cont }</textarea> --%>
<!-- 									<div id="summernote_result"></div> -->
									${pwList.pw_exp }
								</td>
							</tr>
							<tr>
								<th class="pms-table-input">진행상황</th>
								<td>${pwList.prog_nm }</td>
								<th class="pms-table-input">우선순위</th>
								<td>${pwList.prior_nm }</td>
							</tr>
							<tr>
								<th class="pms-table-input">작업 시간</th>
								<td>${pwList.pw_ext_time }  시간</td>
								<th>진척도</th>
								<td>${pwList.pw_level }  %</td>
							</tr>
							<tr>
								<th class="pms-table-input">작업 기간</th>
								<td colspan="3">
									${pwList.pw_start } ~ ${pwList.pw_end }
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="4"><c:forEach items="${pwList.attatchList }" var="pm" varStatus="vs" >
									<c:url value="/pmsProject/PWDownload.do" var="downloadURL">
										<c:param name="what" value="${pm.st_cd }" />
									</c:url>
									<span data-attno="${pm.st_cd }"><a href="${downloadURL }">${pm.ori_nm } </a>
									<c:if test="${not vs.last}">
										<br>
									</c:if>
									</span>
								</c:forEach></td>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
								<!-- 취소, 리스트로 돌아가기 -->
									<!-- 추후 수정 : ${authMember.mem_email} -->
									<a href="<c:url value="/pmsProject/projectWorkList.do">
										<c:param name="who" value="${authMember.mem_email}" />
										<c:param name="what" value="${sessionScope.proj_cd }" />
										</c:url>">
									<input type="button" value="돌아가기"
									class="d-none d-sm-inline-block btn btn-sm btn-gray-100 shadow-sm"></a>
								</td>
							</tr>
						</tfoot>
					</table>
				</form>
			</div>
		</div>
	</div>

	<!-- 댓글 -->
	<!-- 추후 수정 : 전부  -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">댓글</h6>
		</div>
		<div class="card-body">
		
		<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			<tbody id="replyListBody">
			</tbody>
		</table>
			<!-- 댓글 처리를 위한 form -->
			<form id="replyForm" action="${cPath }/pmsProject/projectWorkReplyInsert.do" method="post" >
				<input type="hidden" name="proj_comm_cd" />
				<input type="hidden" name="pm_cd" />
				<input type="hidden" name="proj_cd" />
				<input type="hidden" name="mem_email" value="${authMember.mem_email}" />
				<input type="hidden" name="pw_cd" value="${pwList.pw_cd }" />
<%-- 				<input type="hidden" name="proj_comm_cd" value="${pwList.pw_cd }"/> --%>
<!-- 				<input type="hidden" name="rep_no" /> -->
				
			<table class="table table-bordered">
				<tr>
					<th>작성자 : ${authMember.mem_nick }</th>
				</tr>
				<tr>
					<td colspan="4">
						<textarea name="proj_comm_cont" placeholder="내용 200자 미만" class="form-control"></textarea>
						<input type="submit" value="등록" class="btn btn-info mr-2"/>
						<input type="reset" value="취소" class="btn btn-info"/>
					</td>
				</tr>
				<tfoot id="replyPagingArea"></tfoot>
			</table>
			</form>	
		</div>
	</div>
	
<!-- 페이징 처리를 위한 form -->
<form id="searchForm" action="<c:url value='/reply/replyList.do'/>">
	<input type="hidden" name="page" />
	<input type="hidden" name="what" value="${pwList.pw_cd }" />
</form>

<!-- 댓글 삭제 재확인 모달창 -->
<div class="modal fade" id="replyDeleteModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel2">정말 삭제하시겠습니까?</h5>
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
				<button type="button" id="replyModalDelBtn" class="btn btn-primary">삭제</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>
	
<script type="text/javascript">

	$(document).scrollTop(0);

	$(function(){
		var pmsHtmlStr = $('#summernote').summernote('code');
		var summernote_result = $("summernote-result");
		pmsHtmlStr.html(pmsHtmlStr);
	});	
	
	//  게시글 삭제
	let pwDeleteForm = $("#pwDeleteForm");
	let pwDeleteModal = $("#pwDeleteModal").on("hidden.bs.modal", function(){
		pwDeleteForm.get(0).reset();
	});
	$("#pwModalDelBtn").on("click", function(){
		pwDeleteForm.submit();
		pwDeleteModal.modal("hide");
	});
	
	
	// 댓글 처리
	
	// 버튼 등록 / 수정 / 삭제 변경
	const INSERTURL = "${cPath}/pmsProject/projectWorkReplyInsert.do";
	const UPDATEURL = "${cPath}/pmsProject/projectWorkReplyUpdate.do";
	const DELETEURL = "${cPath}/pmsProject/projectWorkReplyDelete.do";
	
	var pcVO = null;
	let replyDeleteModal = $("#replyDeleteModal");
	
	// 삭제 확인 모달
	let replyModalDelBtn = $("#replyModalDelBtn").on("click", function(){
		let proj_comm_cd = pcVO.proj_comm_cd; // 글번호
		let pm_cd = pcVO.pm_cd; // 프로젝트 회원번호
		replyForm.find("[name='proj_comm_cd']").val(proj_comm_cd);
		replyForm.find("[name='pm_cd']").val(pm_cd);
		replyForm.attr("action", DELETEURL);
		replyForm.submit();
		replyForm.get(0).reset();
		replyDeleteModal.modal("hide");
	});
	
	// 댓글 삭제
	let replyListBody = $("#replyListBody")
	.on("click", ".delReplyBtn" ,function(){
		pcVO = $(this).closest("tr").data("pcVO");	// 가까운 부모의 댓글 번호를 찾는다
		replyDeleteModal.modal("show");
	}).on("click", ".uptReplyBtn", function(){	// 댓글 수정
		let pcVO = $(this).closest("tr").data("pcVO");
		let proj_comm_cont = $("[name='proj_comm_cont']");
		for(let prop in pcVO){	// 반복문. 향상된 for문과 똑같다
			console.log("prop: "+prop);
			replyForm.find("[name='"+prop+"']").val(pcVO[prop]);
		}
		replyForm.attr("action", UPDATEURL);
		let screenHeight = $(window).height();
		let totalHeight = $(document).height();
		proj_comm_cont.focus();
		$(document).scrollTop(screenHeight+500);
	});
	
	let replyForm = $("#replyForm");
	
	// 댓글 등록 / 수정 / 삭제 처리
	replyForm.on("submit", function(event){
		event.preventDefault();
		replyForm.find("[name='proj_cd']").val(${sessionScope.proj_cd});
		let action = $(this).attr("action");
		var formData = $(this).serialize();
		$.ajax({
	        method: "POST",
	        url: action,
	        data: formData,
	        dataType:"json",
//  			processData:false,
// 	        	enctype: 'multipart/form-data',
//  			contentType:false,
 			success:function(resp){
 				if(resp.success){		// 성공했으면,
//  					alert("댓글이 등록되었습니다.");	// 성공 메세지 출력
 					replyForm.get(0).reset();	// 댓글 창 리셋 
 					searchForm.submit();		// 새 페이징을 위한 submit
 				}else if(resp.message){			// 메세지가 있으면,
 					alert("오류 메세지");		 
 					alert(resp.message);		// 메세지창 보여주기 
 				}else{							// 그 외의 상황이면,
 					alert("else");
 					alert(JSON.stringify(resp.errors));	// 에러 난 코드 보여주기
 				}
 			},
 			error:function(errorResp){
 				alert(errorResp.status+", "+errorResp.responseText);
 			}
	    });
		return false;
	}).on("reset", function(){
		replyForm.attr("action", INSERTURL);
	});
	
	// 페이징 처리
	let replyPagingArea = $("#replyPagingArea").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	});
	
	// 댓글 
	let searchForm = $("#searchForm").ajaxForm({
		url : "${cPath}/pmsProject/projectWorkReplyList.do",
		dataType:"json",
		success:function(resp){
			let dataList = resp.dataList;
			let trTags = [];
			if(dataList.length>0){
				$(dataList).each(function(index, pcVO){
					let writer = pcVO.mem_nick+"("+pcVO.mem_email+")";
					let imgSrc = null;
					trTags.push(
						$("<tr>").append(	
							$("<td>").text(writer),			
							$("<td>").attr("colspan", "2")
									.text(pcVO.proj_comm_date)
							)
						);
					if("${authMember.mem_email}"==pcVO.mem_email){
						trTags.push(
							$("<tr>").append(
								$("<td>").attr("colspan", "2")
										 .text(pcVO.proj_comm_cont),
								$("<td>").append(
									$("<button>").attr("type", "button")
												 .addClass("btn btn-info uptReplyBtn mr-2")
												 .text("수정"),
									$("<button>").attr("type", "button")
												 .addClass("btn btn-warning delReplyBtn")
												 .text("삭제")
								)		 
							).data("pcVO", pcVO)
						);
					}else{
						trTags.push(
							$("<tr>").append(
								$("<td>").attr("colspan", "3")
										 .text(pcVO.proj_comm_cont)
							).data("pcVO", pcVO)
						);
					}
				});
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").attr({
							colspan:"1"
						}).text("등록된 댓글이 없습니다.")		
					)
				);
			}
			replyListBody.empty();
			replyListBody.append(trTags);
			let pagingHTML = resp.pagingHTML;
			replyPagingArea.empty(); //**
			replyPagingArea.html(pagingHTML);
			let screenHeight = $(window).height();
			let totalHeight = $(document).height();
			$(document).scrollTop(totalHeight - screenHeight);
		},
		error:function(errorResp){
			alert(errorResp.status+", "+errorResp.responseText);
		}
	});
	
	// 변동 사항이 있을 때 submit이 자동적으로 되도록
	searchForm.submit();
	
	
</script>












