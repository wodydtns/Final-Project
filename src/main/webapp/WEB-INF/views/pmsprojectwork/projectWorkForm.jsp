<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 27.      최효은      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>

<!-- 테이블 전체 div -->
<div class="container-fluid">

	<!-- 프로젝트 리스트 제목, 내용 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div>
			<!-- 추후 수정 : ${authMember.mem_email} -->
			<h1 class="h3 mb-2 text-gray-800">${empty pwList.pw_cd?"작업 등록":"작업 수정"}</h1><br>
		</div>
	</div>
	
	<!-- 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<div class="table-responsive">
				<form id="pmsProjectForm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="proj_cd" value="${sessionScope.proj_cd }" />
					<!-- 추후 수정 : ${authMember.mem_email} -->
					<input type="hidden" name="proj_leader" value="${authMember.mem_email}" />
					<input type="hidden" name="pw_cd" value="${pwList.pw_cd }" />
					<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th class="pms-table-input">작업명
<%-- 								<form:errors path="proj.proj_nm" element="span" cssClass="error" /></th> --%>
								<td><input type="text" name="pw_nm" value="${pwList.pw_nm }" class="pms-input-border" width="auto" height="auto"></td>
								<th class="pms-table-input">담당자 선택</th>
								<td>
									<select name="pm_cd" class="pms-input-border">
										<!-- <spring:message code="proj.proj_cate" /> -->
<%-- 										<option value="${pwList['pm_cd']}">${not empty pwList['mem_nick']?pwList['mem_nick']:"담당자 선택"}</option> --%>
										<c:forEach items="${projectMemberList }" var="pmList">
											<option value="${pmList['pm_cd'] }">${pmList['mem_nick'] }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th class="pms-table-input pms-table-top">설명</th>
								<td colspan="3">
									<!-- 에디터 -->
									<textarea name="pw_exp" required="required" id="summernote">${pwList.pw_exp }</textarea>
								</td>
							</tr>
							<tr>
								<th class="pms-table-input">진행상황</th>
								<td>
									<select name="prog_cd" class="pms-input-border pms-align-right" >
									<!-- <spring:message code="proj.proj_cate" /> -->
<!-- 									<option value="">진행상황 선택</option> -->
									<c:forEach items="${progressList }" var="prog">
										<option value="${prog['prog_cd'] }">${prog['prog_nm'] }</option>
									</c:forEach>
									</select>
								</td>
								<th class="pms-table-input">우선순위</th>
								<td>
									<select name="prior_cd" class="pms-input-border pms-align-right" >
										<!-- <spring:message code="proj.proj_cate" /> -->
<!-- 										<option value="">우선순위 선택</option> -->
										<c:forEach items="${priorityList }" var="prio">
											<option value="${prio['prior_cd'] }">${prio['prior_nm'] }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th class="pms-table-input">작업 시간</th>
								<td><input type="number" name="pw_ext_time" value="${pwList.pw_ext_time }"
								class="pms-input-border" width="70" height="auto" max="8760" min="1" />  시간</td>
								<th>진척도</th>
								<td><input type="number" name="pw_level" value="${pwList.pw_level }"
								class="pms-input-border" width="70" height="auto" max="100" min="0" />  %</td>
							</tr>
							<tr>
								<th class="pms-table-input">작업 기간</th>
								<td colspan="3">
									<% Calendar cal = Calendar.getInstance();
									int year = cal.get(Calendar.YEAR); %>
									<c:set var="date" value="<%= year %>" scope="request" ></c:set>
								    <input
										name="pw_start" id="date1" type="text"
										value="${pwList.pw_start }" class="pms-input-border" placeholder="  시작일자"
										data-dd-format="Y-m-d"
										data-dd-lang="ko"
										data-dd-large="true"
										data-dd-large-default="true"
										hideDay="true"
										data-dd-max-year="2021"
										data-dd-min-year="${year }"
										data-dd-jump="3"
										data-dd-default-date="<%= new Date() %>"
										data-dd-disabledDays="<%= new Date() %>"
										data-dd-show-only-enabled-days="true"
										required
								    /> ~
									<form:errors path="proj.proj_start" element="span" cssClass="error" />
									<input
										name="pw_end" id="date2" type="text"
										value="${pwList.pw_end }" class="pms-input-border" placeholder="  종료일자"
										data-dd-format="Y-m-d"
										data-dd-lang="ko"
										data-dd-large="true"
										data-dd-large-default="true"
										hideDay="true"
										data-dd-max-year="2021"
										data-dd-min-year="${year }"
										data-dd-jump="3"
										data-dd-default-date="<%= new Date() %>"
										data-dd-disabledDays="<%= new Date() %>"
										data-dd-show-only-enabled-days="true"
										required
								    />
									<form:errors path="proj.proj_end" element="span" cssClass="error" />
								</td>
							</tr>
							<tr>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="4"><c:forEach items="${pwList.attatchList }" var="pm" varStatus="vs" >
										<c:url value="/pmsProject/PWDownload.do" var="downloadURL">
											<c:param name="what" value="${pm.st_cd }" />
										</c:url>
										<span data-attno="${pm.st_cd }"><a href="${downloadURL }">${pm.ori_nm } </a>
										<button type="button" class="pms-white-line d-sm-inline-block btn btn-sm btn-primary delBtn">삭제</button>
										<c:if test="${not vs.last}">
											<br>
										</c:if>
										</span>
								</c:forEach></td>
							</tr>
							<tr>
								<td colspan="4">
									<input type="file" name="pw_file" multiple />
								</td>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<!-- 프로젝트 생성, Insert Controller 실행 -->
									<c:if test="${empty pwList.pw_cd }">
										<input type="submit" value="생성" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
									</c:if>
									<!-- 프로젝트 생성, Update Controller 실행 -->
									<c:if test="${not empty pwList.pw_cd }">
										<input type="submit" value="수정" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
									</c:if>
									<!-- 취소, 리스트로 돌아가기 -->
									<!-- 추후 수정 : ${authMember.mem_email} -->
									<a href="<c:url value="/pmsProject/projectWorkList.do">
										<c:param name="who" value="${authMember.mem_email}" />
										<c:param name="what" value="${sessionScope.proj_cd }" />
										</c:url>">
									<input type="button" value="취소"
									class="d-none d-sm-inline-block btn btn-sm btn-gray-100 shadow-sm"></a>
								</td>
							</tr>
						</tfoot>
					</table>
				</form>
			</div>
		</div>
	</div>
  
</div>
<!-- /.container-fluid -->
 
<script type="text/javascript">

	var pm_cdTag = $("[name='pm_cd']");
	var prog_cdTag = $("[name='prog_cd']");
	var prior_cdTag = $("[name='prior_cd']");
	
	var pmsProjectForm = $("#pmsProjectForm");

	// 데이터 피커
	$("input[id='date1'], [id='date2']").dateDropper({ 
	    theme: "leaf" ,
	    minDate: getTodayType1()
	  });	  

	// 오늘 날짜 이전 지정 불가능
	function getTodayType1() {
	   var date = new Date();
	   return  (date.getMonth()+1) + "/" + date.getDate() + "/" + date.getFullYear() ;
	}

	// 작업 내용을 작성하기 위한 에디터
	$('#summernote').summernote({
		  height: 300,                 // 에디터의 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,
		  lang : "ko-KR",
		  placeholder: "최대 2048자까지 쓸 수 있습니다.",
		  callbacks:{
			  onImageUpload: function (files) {
				  console.log(files);
				uploadSummernoteImageFile(files[0], this);
			}
		  }
	});
	
	// 에디터의 이미지 추가 기능
	function uploadSummernoteImageFile(file,editor){
	    data = new FormData();
	     data.append("file", file);
	     $.ajax({
	        data : data,
	        type : "POST",
	        url : "${cPath}/pmsProject/projectWorkImageInsert.do",
	        enctype: "multipart/form-data",
	        contentType : false,
	        processData : false,
	        success : function(data) {
	           console.log(data);
	              //항상 업로드된 파일의 url이 있어야 한다.
	           $(editor).summernote("insertImage", data.url);
	        },
	        error : function(error){
	           alert('error');
	        }
	     });
	  }
	
	
	// 첨부파일 삭제 기능
	$(".delBtn").on("click", function(){
		let span = $(this).parent("span");
		let attNo = span.data("attno");
		span.hide();
		let input = $("<input>").attr({
						"type":"hidden",
						"name":"delAttNos"
					}).val(attNo);
		pmsProjectForm.append(input);
	});
	
	
	// 검색 조건에 값 채워주는 부분
	pm_cdTag.val("${pwList['pm_cd']}");
	prog_cdTag.val("${pwList['prog_cd']}");
	prior_cdTag.val("${pwList['prior_cd']}");
	
	
	
	
	
	
	
</script>







