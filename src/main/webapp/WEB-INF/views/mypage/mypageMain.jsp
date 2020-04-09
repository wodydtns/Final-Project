<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
</c:if>
<style>
#wrap {
	width: 700px;
	height: 700px;
	text-align: center;
}
</style>

<div id="wrap">
	<form id="updateForm" method="post" action="${cPath }/mypage/mypageMain.do">
		<div class="form-group row">
			<label for="mem_email" class="col-sm-2 col-form-label">아이디</label>
			<div class="col-sm-5">
				<input type="email" class="form-control" readonly="readonly"
					value="${mypage.mem_email }" name="mem_email" id="mem_email">
			</div>
		</div>
		<hr />
		<div class="form-group row">
			<label for="mem_nick" class="col-sm-2 col-form-label">닉네임</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" value="${mypage.mem_nick }"
					min="5" maxlength="10"  name="mem_nick" id="mem_nick">
			</div>
		</div>
		<hr />
		<div class="form-group row">
			<label for="mem_hp" class="col-sm-2 col-form-label">전화번호</label>
			<div class="col-sm-5">
				<input type="tel" class="form-control" value="${mypage.mem_hp }"
					min="11" max="11" id="mem_hp" name="mem_hp">
			</div>
		</div>
		<hr />
		<div class="form-group row">
			<label for="reg_date" class="col-sm-2 col-form-label">가입일</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" readonly="readonly"
					value="${mypage.reg_date }" name="reg_date" id="reg_date">
			</div>
		</div>
	</form>

	<hr />
	<div class="float-right">
		<button id="infoChange" type="button" class="btn btn-success"
			style="width: 90px">저장</button>
		<button type="reset" class="btn btn-warning" style="width: 90px">초기화</button>
		<button data-toggle="modal" data-target="#exampleModal"
			data-whatever="@mdo" type="button" class="btn btn-danger"
			style="width: 90px">탈퇴</button>
	</div>
</div>
<!-- 모달 창
 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">정말 떠나실건가요?<br/>
				정말 클래스204을 떠나실 건가요? 계정을 삭제하시면 기존 수강권, 쿠폰, 클래스 기록 등 모든 활동정보가
					삭제됩니다.</h5>
				
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="deleteForm" action="${cPath }/etc/mypageEscapeReason.do" method="post">
					<div class="form-group">
						<label for="message-text" class="col-form-label">떠나시기전에
							이유를 알려주세요</label>
						<textarea class="form-control" name="es_reason" id="message-text"></textarea>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-secondary" data-dismiss="modal">클래스 204에 남기</button>
				<button type="button" id="delete" class="btn btn-danger">탈퇴 확인</button>
			</div>
		</div>
	</div>
</div>
<script>

var message = "저장 하시겠습니까?";
var updateForm = $("#updateForm");
	$("#infoChange").on("click",function(){
		var confirm = window.confirm(message);
		if(confirm){
			updateForm.submit();
		}else{
			return;
		}	
	});
$("#delete").on("click",function(){
	var deleteForm = $("#deleteForm");
	deleteForm.submit();
});
	
	
</script>