<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<script>
console.log(${csCenterBoard });
</script>
<style>
span {
	margin: 3px;
}

#list,#modify, #remove {
	float: right;
	margin-right: 5px;
	margin-top: 5px;
}

#inline {
	width: 700px;
	height: 500px;
}
</style>

<div id="inline">
	<div class="d-flex bd-highlight mb-3">
		<div class="p-2 bd-highlight">${csCenterBoard.cs_bo_nm }</div>
		<div style="border-right: 1px solid red; margin-top: 5px;"></div>
		<div class="p-2 bd-highlight">${csCenterBoard.cs_title }</div>
	</div>
	<hr />
	<div>
		<div class="d-flex bd-highlight mb-3">
			<div class="p-2 bd-highlight">${csCenterBoard.admin_id }</div>
			<div class="ml-auto p-2 bd-highlight">${csCenterBoard.cs_date }</div>
		</div>
		<div>
			<c:forEach items="${csCenterBoard.attachList }" var="attatch" varStatus="vs">
				<c:url value="/master/download.do" var= "downloadURL">
					<c:param name="cs_cd" value="${attatch.cs_cd }"></c:param>
				</c:url>
				<a href="${downloadURL }">${attatch.ori_nm } ${not vs.last?"|":""}</a>
			</c:forEach>					
		</div>
		<hr />
	</div>
	<div class="mb-3">
		<p class="text-center">${csCenterBoard.cs_cont }</p>
	</div>


<c:url value="/master/adminCSCenterBoardModify.do" var="centerUpdateURL">
	<c:param name="cs_cd" value="${csCenterBoard.cs_cd }"></c:param>
</c:url>
<input type="button" value="수정" id="modify" class="btn btn-success"
	onclick="location.href='${centerUpdateURL}';"  />
	<input type="button" value="목록으로" class="btn btn-info" id="list"
					onclick="location.href='<c:url value="/master/adminCSCenter.do"/>';"	/>
	<input type="button" value="삭제" class="btn btn-danger" id="remove"/>
	</div>
	<form id="deleteForm" action="${cPath }/master/centerboardDelete.do" >
	    <input type="hidden" name="cs_cd" value="${csCenterBoard.cs_cd }" />
	</form>
<script>
      var remove = $("#remove");
      var list = $("#list");
      // 버튼 클릭 시 loading 버튼으로 변경하는 코드 구현
      $(function() {
        $("#remove").on("click", function() {
          remove.empty();
          remove.append(
            '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>'
          );
          remove.append('<span class="sr-only">Loading...</span>');
          var confirm = confirm("정말 삭제하시겠습니까?");
          
        });
        $("#list").on("click", function() {
          list.empty();
          list.append(
            '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>'
          );
          list.append('<span class="sr-only">Loading...</span>');
        });
      });
     // 삭제 버튼 클릭 시 form submit
     $("#remove").on("click",function(){
    	$("#deleteForm").submit(); 
     });
    </script>

