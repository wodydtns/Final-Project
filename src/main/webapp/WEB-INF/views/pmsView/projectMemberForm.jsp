<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 25.      최효은      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
 
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">멤버 초대</h6>
		<!-- 검색조건 추가 -->
		<!-- 닉네임 -->
		<div id="inputForm" class="form-inline justify-content-center mb-3">
			<!-- 이름 검색 -->
			<input type="text" name="mem_email_input" id="searchWord1"
				class="form-control form-control-sm pms-input-width"
				placeholder=" 추가할 멤버의 이메일을 입력해주세요." value="" />
			<!-- 멤버 필터 -->
			<input type="button" value="추가" id="searchBtn" width="100" onclick="add_tr()"
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" />
		</div>

	</div>
	<form id="emailForm1" action="${cPath }/pmsProject/projectMember.do" method="post">
	<div class="card-body">
		<div class="table-responsive">
				<!-- 추후 수정 : ${authMember.mem_email} -->
				<input type="hidden" name="proj_cd" value="${sessionScope.proj_cd }" />
				<table class="table table-bordered" id="dataTable1" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th class="pms-table-center">No</th>
							<th class="pms-table-center" colspan="2">이메일</th>
						</tr>
					</thead>
					<tbody id="listBody"></tbody>
				</table>
		</div>
		<div>
			<input type="button" name="inv" value="초대" onclick="email_submit()"
					class="pms-inv-right d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" >
			<input type="reset" name="reset" value="취소"
					class="pms-inv-right d-none d-sm-inline-block btn btn-sm btn-secondary shadow-sm"  data-dismiss="modal">
		</div>
	</div>
	</form>
</div>


<script type="text/javascript">


var dataTable1 = $("#dataTable1");
var emailForm1 = $("#emailForm1");
var rnum = 1;
var idx = 0;

function add_tr(){
	let searchWord1 = $("#searchWord1").val();
	let mem_email_input = $("[name='mem_email_input']")
	console.log(searchWord1);
	if(searchWord1==null || searchWord1.length==0){
		alert("초대할 이메일을 입력해주세요.");
		return;
	}
	let result = isEmail(searchWord1);
	if(result){
		var eachTr = "<tr>";
			eachTr += "<td>"+ rnum +"</td>";
			eachTr += "<td colspan='2'><input type='text' name='mem_email_list' class='mem_email_list' value='" + searchWord1
					+ "' class='pms-search-invi'style='border:none' readonly onfocus='this.blur()'><button type='button' class='pms-width-right d-none d-sm-inline-block btn btn-sm btn-secondary shadow-sm'> 삭제 </button> </td>";
			eachTr += "</tr>";
			dataTable1.append(eachTr)
			rnum += 1;
			idx += 1;
			mem_email_input.val("");
			mem_email_input.focus();
	}else{
		alert("이메일 형식이 올바르지 않습니다.");
	}
}

// 방금 생긴 행(tr) 삭제
dataTable1.on("click", "button", function() {
	// dataTable1 테이블의 버튼(삭제)을 누르면, 해당 버튼의 부모(tr)와 자식테이블을 한꺼번에 삭제 
    $(this).closest("tr").remove();
    rnum -= 1;
    idx -= 1;
});
	
// 이메일 체크 정규식
// 한글X, 아이디 1글자 이상 / @ 들어가야함 / 주소 1글자 이상 / .이후 2글자 이상
function isEmail(asValue) {
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	return regExp.test(asValue); // 형식에 맞는 경우 true 리턴	
}

function email_submit() {
	var check = $("input[name='mem_email_list']").val();
// 	console.log("idx:"+idx);
// 	console.log($("input[name='mem_email_list["+idx+"]']").val());
// 	if(check == null){
// 		alert("이메일을 입력해주세요.")
// 		return;
// 	}
	emailForm1.submit();
}


</script>




