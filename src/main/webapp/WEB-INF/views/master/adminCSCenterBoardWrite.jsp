<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty message }">
	<script  type="text/javascript">
		alert(${message});
	</script>
</c:if>
<!-- 폼 스타일 css -->
<style>
form {
	width: 600px;
}


.custom-select {
	margin: 10px;
	width: 150px;
}
#outline{
	width : 700px;
	height : 500px;
}
#buttons{
	float : right;
	margin-right: 5px;
	margin-top : 5px;
}

label[for="center_file"] { display: inline-block; padding: .5em .75em; color: white; font-size: inherit; line-height: normal; vertical-align: middle; background-color: #5232a8; cursor: pointer; border: 1px solid #ebebeb; border-bottom-color: #e2e2e2; border-radius: .25em; margin:5px }

#center_file { /* 파일 필드 숨기기 */ position: absolute; width: 1px; height: 1px; padding: 0; margin: -1px; overflow: hidden; clip:rect(0,0,0,0); border: 0; }

</style>

<div id="outline">
	<form id="insertForm"  method="post" enctype="multipart/form-data" class="was-validated">
		<div class="form-check form-check-inline">
			<label class="col-sm-3 col-form-label" for="inlineFormTitle"
				style="padding-left: 0px;">게시판</label> 
				<select class="custom-select" name="cs_bo_cd" >
				<option value="C001" selected>공지사항</option>
				<option value="C002">FAQ</option>
			</select> 
			<select class="custom-select" name="header_cd">
				<option value="H001" selected>공지</option>
				<option value="H002">중요</option>
			</select>
		</div>
		<hr />
		<div class="form-group row" style="margin-top: 5px;">
			<label for="inputTitle" class="col-sm-2 col-form-label" >제목</label>
			
			<div class="col-sm-10">
				<input type="text" name="cs_title" class="form-control" id="inputTitle" required="required" aria-describedby="titleHelpBlock" value=${center.cs_title }  > 
				
					<small id="titleHelpBlock" class="form-text text-muted"> 제목은 반드시 작성해야합니다. </small>
			</div>
		</div>
		<div class="mb-3">
		<textarea name="cs_cont"  required="required" id="summernote">${center.cs_cont }</textarea>
		 <div class="invalid-feedback">
         게시글 내용을 작성해야합니다.
      </div>
    </div>
		<div id="buttons">		
			<input type="button" value="목록으로" class="btn btn-info" 
					onclick="location.href='<c:url value="/master/adminCSCenter.do"/>';"
				/>
			<label for="center_file">업로드</label><input type="file" name="center_file" id="center_file" >
			<button type="submit" id="confirm" class="btn btn-success">작성</button>
		</div>
		<!-- 
			<input type="hidden" name="관리자 아이디 넣어야함 => 세션에서 받아?"/>
		 -->
	</form>
	<script>
		  $('#summernote').summernote({
			  height: 300,                 // set editor height
			  minHeight: null,             // set minimum height of editor
			  maxHeight: null,
			  lang : "ko-KR",
			  placeholder: "최대 2048자까지 쓸 수 있습니다.",
			  callbacks:{
				  onImageUpload: function (files) {
					  console.log(files);
					uploadSummernoteImageFile(files[0],this);
				}
			  }
		  });
		  
		  function uploadSummernoteImageFile(file,editor){
			  data = new FormData();
				data.append("file", file);
				$.ajax({
					data : data,
					type : "POST",
					url : "${cPath }/master/centerImageUpload.do",
					enctype: 'multipart/form-data',
					contentType : false,
					processData : false,
					success : function(data) {
						console.log(data);
		            	//항상 업로드된 파일의 url이 있어야 한다.
						$(editor).summernote('insertImage', data.url);
					},
					error : function(error){
						alert('에러임');
					}
				});
			}
</script>
</div>
<script>
	var confirm = $("#confirm");
	var modify = $("#modify");
	var insertForm = $("#insertForm");
	
	// 버튼 클릭 시 loading 버튼으로 변경하는 코드 구현
	$(function() {
		$("#confirm").on("click",	function() {
			confirm.empty();
			confirm.append('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>');
			confirm.append('<span class="sr-only">Loading...</span>');
		});
						
		$("#modify").on("click",function() {
			modify.empty();
			modify.append('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>');
			modify.append('<span class="sr-only">Loading...</span>');
		});
	});
</script>

