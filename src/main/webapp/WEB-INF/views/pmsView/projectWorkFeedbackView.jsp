<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid">

	<!-- 프로젝트 리스트 제목, 내용 -->
	<!-- 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<div class="table-responsive">
					<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
						<tbody>
							<tr>
								<th>피드백 보낸 멤버</th>
							</tr>
							<tr>
								<td>${feedList.target_nick }</td>
							</tr>
							<tr>
								<th>피드백 받는 멤버</th>
							</tr>
							<tr>
								<td>
									${feedList.sender_nick }
								</td>
							</tr>
							<tr>
								<th>관련된 업무</th>
							</tr>
							<tr>
								<td>
									${feedList.pw_nm }
								</td>
							</tr>
							<tr>
								<th>설명</th>
							</tr>
							<tr>
								<td>
									${feedList.feed_cont }
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<!-- 추후 수정 : ${authMember.mem_email} -->
									<a href="<c:url value="/pmsProject/projectFeedbackList.do">
										<c:param name="mem_email" value="${authMember.mem_email}" />
									</c:url>">
									<input type="button" value="확인"
									class="d-none d-sm-inline-block btn btn-sm btn-gray-100 shadow-sm"></a>
								</td>
							</tr>
						</tfoot>
					</table>
			</div>
		</div>
	</div>
</div>