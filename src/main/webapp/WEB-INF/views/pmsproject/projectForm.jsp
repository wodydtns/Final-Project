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

<!--  [[개정이력(Modification Information)]]       -->
<!--  수정일        수정자     수정내용               -->
<!--  ==========   ======    ==============        -->
<!--  2020. 3. 13.     최효은     최초작성               -->
<!--  2020. 3. 19.     최효은     업데이트 기능 추가               -->
<!--  Copyright (c) 2020 by DDIT All right reserved -->

<!-- 테이블 전체 div -->
<div class="container-fluid">

	<!-- 프로젝트 리스트 제목, 내용 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div>
			<h1 class="h3 mb-2 text-gray-800">${empty proj.proj_cd?"프로젝트 생성":"프로젝트 수정"}</h1><br>
		</div>
	</div>

	<!-- 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<div class="table-responsive">
				<form id="pmsProjectForm" action="${cPath }/pmsProject/projectInsert.do" method="post">
					<input type="hidden" name="proj_cd" value="${proj.proj_cd }" />
					<!-- 추후 수정 : ${authMember.mem_email} -->
					<input type="hidden" name="proj_leader" value="${authMember.mem_email}" />
					<input type="hidden" name="who" value="${authMember.mem_email}" />
					<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th class="pms-table-input">프로젝트명
								<form:errors path="proj.proj_nm" element="span" cssClass="error" /></th>
								<td><input type="text" name="proj_nm" value="${proj.proj_nm }" required
								class="pms-input-border" width="auto" height="auto"></td>
									<th class="pms-table-input">카테고리 선택</th>
								<td>
									<select name="cate_cd" class="pms-input-border">
										<!-- <spring:message code="proj.proj_cate" /> -->
<!-- 										<option value="">카테고리 선택</option> -->
										<c:forEach items="${categoryList }" var="cate">
											<option value="${cate['cate_cd'] }">${cate['cate_nm'] }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th class="pms-table-input pms-table-top">프로젝트 설명</th>
								<td colspan="3">
									<textarea id="proj_exp" name="proj_exp" rows="20" cols="100" class="pms-input-border" >${proj.proj_exp }</textarea>
									<form:errors path="proj.proj_exp" element="span" cssClass="error" />
								</td>
							</tr>
							<tr>
								<th class="pms-table-input">프로젝트 기간</th>
								<td colspan="3">
									<% Calendar cal = Calendar.getInstance();
									int year = cal.get(Calendar.YEAR); %>
									<c:set var="date" value="<%= year %>" scope="request" ></c:set>
								    <input
										name="proj_start" id="date1" type="text"
										value="${proj.proj_start }" class="pms-input-border" placeholder="  시작일자"
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
										name="proj_end" id="date2" type="text"
										value="${proj.proj_end }" class="pms-input-border" placeholder="  종료일자"
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
<!-- 									<input id="outward-date" type="text" data-dd-roundtrip="my-trip" /> ~ -->
<!-- 									<input id="return-date" type="text" data-dd-roundtrip="my-trip" />	 -->
<!-- 									<button class="datedropper-init" data-dd-change-value-to="#my-input">Open datepicker</button> -->
									
									<span class="pms-font-error">&nbsp;&nbsp;&nbsp;※ 최대 기간 1년</span>
								</td>
							</tr>
							<tr>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<!-- 프로젝트 생성, Insert Controller 실행 -->
									<c:if test="${empty proj.proj_cd }">
										<input type="submit" value="생성" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
									</c:if>
									<!-- 프로젝트 생성, Update Controller 실행 -->
									<c:if test="${not empty proj.proj_cd }">
										<input type="button" value="수정" id="proj_moidfy" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
									</c:if>
									<!-- 취소, 리스트로 돌아가기 -->
									<!-- 추후 수정 : ${authMember.mem_email} -->
									<a href="<c:url value="/pmsProject/projectList.do">
										<c:param name="what" value="${authMember.mem_email}" /></c:url>">
									<input type="reset" value="취소"
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

<!-- 프로젝트 시작일자, 종료일자 date Script -->
<script type="text/javascript">

	var pmsProjectForm = $("#pmsProjectForm");
	
	var cate_cdTag = $("[name='cate_cd']");
	
   $("input[id='date1'], [id='date2']").dateDropper({ 
     theme: "leaf" ,
     minDate: getTodayType1()
   });	  

   function getTodayType1() {
	   var date = new Date();
	   return  (date.getMonth()+1) + "/" + date.getDate() + "/" + date.getFullYear() ;
	 }
   
   $("#proj_moidfy").on("click", function(){
	   pmsProjectForm.attr("action", "${cPath }/pmsProject/projectUpdate.do");
	   pmsProjectForm.submit();
   });
   
   cate_cdTag.val("${proj['cate_cd']}");
   
   
</script>

