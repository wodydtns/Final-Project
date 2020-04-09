<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 4. 1.      최효은      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
 
<!-- 테이블 전체 div -->
<div class="container-fluid">

	<!-- 프로젝트 리스트 제목, 내용 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div>
			<!-- 추후 수정 : ${authMember.mem_email} -->
			<h1 class="h3 mb-2 text-gray-800">${empty pbList.stu_post_cd?"작업 등록":"작업 수정"}</h1><br>
		</div>
	</div>
	
	<!-- 테이블 -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<div class="table-responsive">
				<form id="pmsProjectForm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="proj_cd" value="${sessionScope.proj_cd }" />
					<!-- 추후 수정 : ${authMember.mem_email} -->
					<input type="hidden" name="mem_email" value="${authMember.mem_email}" />
					<input type="hidden" name="stu_post_cd" value="${pbList.stu_post_cd }" />
					<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
						<thead>
						<tr>
							<th class="pms-table-input">제목
							<td><input type="text" name="pw_title" value="${pbList.pw_title }" class="pms-input-border" width="auto" height="auto"></td>
						</tr>
						<tr>
							<th class="pms-table-input pms-table-top">설명</th>
							<td>
								<!-- 에디터 -->
								<textarea name="pw_cont" required="required" id="summernote">${pbList.pw_cont }</textarea>
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="4"><c:forEach items="${pbList.attatchList }" var="pb" varStatus="vs" >
									<c:url value="/pmsProject/PWDownload.do" var="downloadURL">
										<c:param name="what" value="${pm.st_cd }" />
									</c:url>
									<span data-attno="${pb.st_cd }"><a href="${downloadURL }">${pb.ori_nm } </a>
									<button type="button" class="pms-white-line d-sm-inline-block btn btn-sm btn-primary delBtn">삭제</button>
									<c:if test="${not vs.last}">
										<br>
									</c:if>
									</span>
							</c:forEach></td>
						</tr>
						<tr>
							<td colspan="4">
								<input type="file" name="pb_file" multiple />
							</td>
						</tr>
					</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<!-- 프로젝트 생성, Insert Controller 실행 -->
									<c:if test="${empty pbList.stu_post_cd }">
										<input type="submit" value="생성" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
									</c:if>
									<!-- 프로젝트 생성, Update Controller 실행 -->
									<c:if test="${not empty pbList.stu_post_cd }">
										<input type="submit" value="수정" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
									</c:if>
									<!-- 취소, 리스트로 돌아가기 -->
									<!-- 추후 수정 : ${authMember.mem_email} -->
									<a href="<c:url value="/pmsProject/projectBoardList.do">
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

var pmsProjectForm = $("#pmsProjectForm");

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


//첨부파일 삭제 기능
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



</script>



