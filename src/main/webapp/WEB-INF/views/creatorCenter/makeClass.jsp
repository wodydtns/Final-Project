<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <JSP:USEBEAN ID="NOW" CLASS="JAVA.UTIL.DATE" /> --%>
<%-- <FMT:FORMATDATE VALUE="${NOW}" PATTERN="YYYY-MM-DD" VAR="TODAY" /> --%>
<%-- <C:SET VAR="END" VALUE="<%=NEW DATE(NEW DATE().GETTIME() + (60*60*24*7))%>"/> --%>
<%-- <FMT:FORMATDATE VALUE="${END}" PATTERN="YYYY-MM-DD" VAR="END"/> --%>
<style>
tbody , .col-lg-6 {
    /* flex: 0 0 50%; */
    max-width: 90%;
}
</style>

<div class="col-lg-8" style="width:100%; margin:0 auto;">
	<h3 style="color:black; font-weight:bold;">사전조사에 통과하신 것을 축하해요!</h3>
	<br><br>
	<div class="card">
		<div class="card-header">클래스 커리큘럼 작성</div>
		<div class="card-body">
			<h5 class="card-title"></h5>
			<form class="form-horizontal" id="classForm" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="crea_email"
					value="${authMember.mem_email}"> <input type="hidden"
					name="pre_cd" value="${pi_cd}">

				<table>
					<tr>
						<th>클래스 진행기간</th>
						<td>
							<% Calendar cal = Calendar.getInstance();
							int year = cal.get(Calendar.YEAR); %> <c:set var="date"
								value="<%= year %>" scope="request"></c:set> <input
							name="cl_start" id="date1" type="text" value="${class.cl_start}"
							class="form-control" placeholder="  시작일자" data-dd-format="Y-m-d"
							data-dd-lang="en" data-dd-large="true"
							data-dd-large-default="true" hideDay="true"
							data-dd-max-year="2021" data-dd-min-year="${year }"
							data-dd-jump="3" data-dd-default-date="<%= new Date() %>"
							data-dd-disabledDays="<%= new Date() %>"
							data-dd-show-only-enabled-days="true" />  
							<form:errors
								path="proj.proj_start" element="span" cssClass="error" />
						</td>
						<td>~</td>
						<td><input name="cl_end" id="date2" type="text"
							value="${class.cl_end}" class="form-control" placeholder="  종료일자"
							data-dd-format="Y-m-d" data-dd-lang="en" data-dd-large="true"
							data-dd-large-default="true" hideDay="true"
							data-dd-max-year="2021" data-dd-min-year="${year }"
							data-dd-jump="3" data-dd-default-date="<%= new Date() %>"
							data-dd-disabledDays="<%= new Date() %>"
							data-dd-show-only-enabled-days="true" /> 
							<form:errors
								path="proj.proj_end" element="span" cssClass="error" /></td>
					</tr>
					<tr>
						<th>클래스 금액</th>
						<td><input name="cl_fee" value="${class.cl_fee}"
							type="number" class="form-control"></td>
					</tr>
				</table>
<br><br>
				<p style="font-weight:bold;">클래스 커리큘럼</p>

				<div id="parentDIV">
					<div class="col-lg-6" name="curriDiv">
						<div class="card">
							<div class="card-header">
								<input type="text" name="curriculumList[0].curri_nm" class="form-control"
									placeholder="목차명" /> 
									<input type="hidden" name="curriculumList[0].curri_cd" value="256"/>	
									<input type="hidden" name="curriculumList[0].curri_order" value="1"/>	
								<input type="button" name="curriAddBtn"
									value="커리큘럼 추가" />
							</div>
							<div class="card-body">
								<table name="tableId">
									<tbody>
										<tr>
											<td class="order" style="width:60%;"><input name="curriculumList[1].curri_nm" type="text"
												placeholder="세부목차명" class="form-control">
													
												<input type="hidden" name="curriculumList[1].curri_cd2" value="256"/>	
												<input type="hidden" name="curriculumList[1].curri_order" value="1" />	
											</td>
											<td style="width:20%;"><input type="file" accept="video/*"
												name="curriculumList[1].class_video"></td>
											<td><input type="button" name="rowAddBtn"
												class="btn btn-warning" value="추가"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<br><br>
				<input type="submit" class="btn btn-warning mb-2" value="등록" /> <input
					type="button" class="btn btn-secondary mb-2" value="취소"
					onclick="location.href='<c:url value="/creatorCenter/pi_end_List.do"/>';" />
			</form>
		</div>
	</div>
</div>



<script>

<!-- 프로젝트 시작일자, 종료일자 date Script -->
var classForm = $("#classForm");
$("input[id='date1'], [id='date2']").dateDropper({ 
    theme: "leaf" ,
    minDate: getTodayType1()
  });	  

  function getTodayType1() {
	   var date = new Date();
	   return  (date.getMonth()+1) + "/" + date.getDate() + "/" + date.getFullYear() ;
	 }

 //세부목차명 추가
  $(document).on("click","input[name='rowAddBtn']",function(){
	  $(this).attr('disabled','disabled'); //추가 버튼 비활성화
	  var leng1 = $("input[name$='curri_nm']").length; //curri_nm으로 끝나는 name값 길이 확인후 리스트 인덱스로 사용
// 	  console.log("leng1:"+leng1);
	 
	  var myparent = $(this).parents(".card-body").prev().find("input[name$='curri_cd']").val();
	  //나의 상위커리큘럼 cd값 구하기
	  console.log("나의상위cd 태그:",myparent);
	  

	var order = $(this).parents("tr").children().first().find("input[name$='curri_order']").val();
	//나의 앞에 형제태그들 중에 curri_order의 값
	  console.log("order:",order);
	$(this.parentElement.parentElement.parentElement).append("<tr><td><input name='curriculumList["+(leng1)+"].curri_nm' type='text'placeholder='세부목차명' class='form-control'><input type='hidden' name='curriculumList["+(leng1)+"].curri_cd2' value=" + (parseInt(myparent)) + "></input><input type='hidden' name='curriculumList["+(leng1)+"].curri_order' value=" + (parseInt(order)+1) + "></input></td><td><input type='file' accept='video/*' name='curriculumList["+(leng1)+"].class_video'></td><td><input type='button' name='rowAddBtn'class='btn btn-warning' value='추가'></td><td data-curricd='${curri.curri_cd}'><input type='button' name='rowDelBtn'class='btn btn-warning' value='삭제'></td></tr>");
	});
  
 //세부목차 삭제
  $(document).on("click","input[name='rowDelBtn']",function(){
	  //바로위에있는 추가버튼 활성화하기
	  $(this).parents("tr").prev().find("input[name='rowAddBtn']").removeAttr("disabled");
	  
	  let td = $(this).parent("td");
		let curriCd = td.data("curricd");
		td.hide();
		let input = $("<input>").attr({
						"type":"hidden",
						"name":"delAttNos"	
					}).val(curriCd);
		classForm.append(input);
	   var p = this.parentElement.parentElement.parentElement; // 부모 HTML 태그 요소
       p.removeChild(this.parentElement.parentElement);
});
  
 //새커리큘럼 추가
  $(document).on("click","input[name='curriAddBtn']",function(){
	  var leng2 = $("input[name$='curri_nm']").length; //curri_nm으로 끝나는 name값 길이 확인
	  console.log("leng2 :"+leng2);
	var myparent = $("input[name$='curri_cd']:last").val(); //curri_cd로 끝나는 마지막값  
	  console.log("다음 myparent:"+ (parseInt(myparent)+1) );
	 $(this.parentElement.parentElement.parentElement).append("<div class='card'><div class='card-header'><input type='text' name='curriculumList["+ leng2 +"].curri_nm' class='form-control' placeholder='목차명' /><input type='hidden' name='curriculumList["+ leng2 +"].curri_cd' value="+ (parseInt(myparent)+1) + "></input><input type='hidden' name='curriculumList["+ leng2 +"].curri_order' value='1'/><input type='button' name='curriAddBtn'value='커리큘럼 추가' /><input type='button' name='curriDelBtn' value='커리큘럼 삭제' /></div><div class='card-body'><table name='tableId'><tbody><tr><td><input name='curriculumList["+ (parseInt(leng2)+1) +"].curri_nm' type='text'placeholder='세부목차명' class='form-control'><input type='hidden' name='curriculumList["+ (parseInt(leng2)+1) +"].curri_cd2' value="+ (parseInt(myparent)+1) + "></input><input type='hidden' name='curriculumList["+ (parseInt(leng2)+1) +"].curri_order' value='1'/></td><td><input type='file' accept='video/*' name='curriculumList["+ (parseInt(leng2)+1) +"].class_video'></td><td><input type='button' name='rowAddBtn'class='btn btn-warning' value='추가'></td></tr></tbody></table></div></div>");
});
 
  //커리큘럼 삭제
  $(document).on("click","input[name='curriDelBtn']",function(){
	  
	  $(this.parentElement.parentElement).remove();
  });
  
  
</script>