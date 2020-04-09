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
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
				<i class="fas fa-download fa-sm text-white-50"></i>  작업 수정</a>
			<c:if test="${projectMemberList.get(0).proj_leader eq '${authMember.mem_email}' }">
				<a href="#" id="deleteBtn"><i class="fas fa-download fa-sm text-white-50">
				<input type="button" value=" 작업 삭제"
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" 
				data-toggle="modal" data-target="#pwDeleteModal" /></i></a>
			</c:if>
		</div>
	</div>

<!-- 삭제 재확인 모달창 -->
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
									<a href="<c:url value="/pmsProject/projectWorkAllList.do">
										<c:param name="who" value="${authMember.mem_email}" />
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



<script type="text/javascript">

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
	
</script>












