<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 16.      최효은      최초작성
* 2020. 3. 17. 		최효은	프로젝트 상세보기 출력
* Copyright (c) 2020 by DDIT All right reserved
 --%>
<!-- content 제목 -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<div>
		<h1 class="h3 mb-2 text-gray-800">${projList.proj.proj_nm }</h1><br>
		<p class="mb-4">${projList.proj.proj_exp }</p>
	</div>
	<!-- 추후 수정 : ${authMember.mem_email} -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<c:if test="${projectMemberList.get(0).proj_leader eq authMember.mem_email}">
			<a href="<c:url value="/pmsProject/projectUpdate.do">
			<c:param name="what" value="${projList.proj.proj_cd }" />
			<c:param name="who" value="${projList.proj.mem_email }" />
			</c:url>"
			class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" style="color:white;">
			<i class="fas fa-download fa-sm text-white-50"></i>프로젝트 수정</a>
<%-- 			<a href="<c:url value="pmsproject/projectDelete.do"> --%>
<%-- 			<c:param name="what" value="${projList.proj.proj_cd }" /></c:url>" --%>
<!-- 			class="d-none d-sm-inline-block btn btn-sm btn-danger shadow-sm"> -->
<!-- 			<i class="fas fa-download fa-sm text-white-50"></i>프로젝트 삭제</a><br> -->
		</c:if>
	</div>
</div>
 
<div class="row">
	<div class="col-lg-6">
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">진행 작업 현황</h6>
			</div>
			<div id="workListArea" class="card-body">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th class="pms-table-center">진행</th>
							<th class="pms-table-center">완료</th>
							<th class="pms-table-center">합계</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<!-- 추후 수정 : 하이퍼링크 추가 -->
							<td class="pms-table-center">${projList.progCount }</td>
							<td class="pms-table-center">${projList.compCount }</td>
							<td class="pms-table-center">${projList.progCount+projList.compCount}</td>
						</tr>
					</tbody>
				</table>
				<a href="<c:url value='/pmsProject/projectWorkList.do'>
			        	<c:param name="who" value="${authMember.mem_email}" />
			        	<c:param name="what" value="${sessionScope.proj_cd }" />
			        </c:url>">작업내역 확인하기</a> |
				<a href="<c:url value='/pmsProject/projectGanttChart.do'>
			        	<c:param name="who" value="${authMember.mem_email}" />
			        	<c:param name="what" value="${sessionScope.proj_cd }" />
			        </c:url>">Gantt차트</a>	
			</div>
		</div>
		<!-- Basic Card Example -->
		<!-- 추후 수정 : 소요시간? 보류 필요 -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">작업시간</h6>
			</div>
			<div id="timeArea" class="card-body">
				${projList.projWorkTime } 시간
			</div>
		</div>
	</div>
	
	<div class="col-lg-6">
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">프로젝트 회원</h6>
			</div>
			<div id="memberListArea" class="card-body">
				<!-- 추후 수정 : 해당 이메일을 누르면 마이페이지로 가게끔? -->
				<c:forEach items="${projList.projMemberList }" var="member">
					<c:if test="${member['proj_leader'] eq member['mem_email'] }" >
						${member['mem_nick'] }<span id="pms-leader-font">(리더)</span>  (${member['mem_email'] })<br>
					</c:if>
					<c:if test="${member['proj_leader'] ne member['mem_email'] }" >
						${member['mem_nick'] } (${member['mem_email'] })<br>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
// var workListArea = $("#workListArea");
// var timeArea = $("#timeArea");
// var memberListArea = $("#memberListArea");

// $.ajax({
// 	url: "<c:url value='/pmsProject/projectView.do'/>",
// 	method: "get",
// 	data : {"who":"${authMember.mem_email}", "what":${proj.proj_cd}},
// 	data : {"who":"${authMember.mem_email}", "what":"1"},
// 	dataType : "json", 
// 	success : function(resp) {
// 		console.log(resp);
// 		console.log(resp.proj)
// 		let eachTr = $("<tr>");
// 		eachTr.append(
// 			$("<td>").text(proj.rnum),
// 			$("<td>").html(
// 				$("<a>").attr({
// 					"href" : "<c:url value='/pmsProject/projectView.do'/>?what="+proj.proj_cd+"&who=${authMember.mem_email}"
// 				}).text(proj.proj_nm)
// 			),	
// 			$("<td>").text(proj.proj_start),
// 			$("<td>").text(proj.proj_end)
// 		);
// 		trTags.push(eachTr);
// 		workListArea.empty();
// 		workListArea.append(trTags);
// 	},
// 	error : function(xhr) {
// 		console.log(xhr.status);
// 	}
// });

</script>