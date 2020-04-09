<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 4. 2.      최효은      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
<div class="container-fluid">
	
	<!-- 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<div class="table-responsive">
				<form id="pmsProjectForm" action="${cPath }/pmsProject/projectFeedbackInsert.do" method="post">
					<input type="hidden" name="proj_cd" value="${sessionScope.proj_cd }" />
					<!-- 추후 수정 : ${authMember.mem_email} -->
					<input type="hidden" name="sender_email" value="${authMember.mem_email}" />
<%-- 					<input type="hidden" name="pw_cd" value="${pwMember.pw_cdㅊ }" /> --%>
					<input type="hidden" name="feed_target" value="${pm_cd }" />
					
					<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
						<tbody>
							<tr>
								<th>관련된 업무</th>
							</tr>
							<tr>
								<td>
									<!-- 관련된 업무 셀렉트 박스 -->
									<select name="pw_cd" class="pms-input-border" style="color:#858796;">
										<option value="">작업선택</option>
										<c:forEach items="${pwMember }" var="pw">
											<option value="${pw['pw_cd'] }">${pw['pw_nm'] }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th>설명</th>
							</tr>
							<tr>
								<td>
									<!-- 에디터 -->
									<textarea name="feed_cont" required="required" id="pms-feed-text"
									style="width:365px;htight:250px;border:1px solid #e3e6f0;"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<input type="button" id="feedCheck" value="작성하기" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
<%-- 									<a href="<c:url value="/pmsProject/projectMemberList.do"> --%>
<%-- 										<c:param name="who" value="${authMember.mem_email}" /> --%>
<%-- 										<c:param name="what" value="${sessionScope.proj_cd }" /> --%>
<%-- 									</c:url>"> --%>
<!-- 									<input type="button" value="취소" -->
<!-- 									class="d-none d-sm-inline-block btn btn-sm btn-gray-100 shadow-sm"></a> -->
								</td>
							</tr>
						</tfoot>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	
	var feedCheck = $("#feedCheck");
	var pmsProjectForm = $("#pmsProjectForm");
	
	var pw_cdTag = $("input[name='pw_cd']").val();
	var pw_expTag = $("input[name='pw_exp']").val();
	
	feedCheck.on("click", function(){
// 		console.log("들어오니?");
// 		console.log(pw_cdTag);
// 		console.log(pw_expTag);
// 		if(pw_cdTag==null){
// 			alert("작업을 선택해주세요.");
// 			return;
// 		}
// 		if(pw_expTag==null){
// 			alert("내용을 입력해주세요.");
// 			return;
// 		}
		pmsProjectForm.submit();
	});
	
</script>