<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.col-xl-6 {
    flex: 0 0 0 0;
    max-width: 100%;
    }
</style>
<div class="col-lg-12" style="margin:0 auto; width:50%;">

	<div class="card" >
		<div class="card-header">${classInfo.pi_nm}
		</div>
		<div class="card-body">
				<div style="margin-left:10px;">
				<table>
					<tr>
						<th class="col-md-5">커버이미지</th>
						<td><img src="/JinDam/piImages/${classInfo.temp_nm}" style="width: 200px;"></td>
					</tr>
					<tr>
						<th class="col-md-5">카테고리</th>
						<td>${classInfo.cate_nm}</td>
					</tr>
					<tr>
						<th class="col-md-5">클래스 소개</th>
						<td>${classInfo.pi_intro}</td>
					</tr>
					<tr>
						<th class="col-md-5">클래스 부가정보</th>
						<td>${classInfo.pi_add_info}</td>
					</tr>
					<tr>
						<th class="col-md-5">클래스 진행기간</th>
						<td>${classInfo.cl_start} ~ ${classInfo.cl_end}</td>
					</tr>
					<tr>
						<th class="col-md-5">클래스 금액</th>
						<td>${classInfo.cl_fee}</td>
					</tr>
				</table>
				</div>


<!-- Grid row -->
<div class="row accordion-gradient-bcg d-flex justify-content-center" >

  <!-- Grid column -->
  <div class="col-md-10 col-xl-6 py-5" style="width:700px;">

    <!--Accordion wrapper-->
    <div class="accordion md-accordion accordion-2" id="accordionEx7" role="tablist"
      aria-multiselectable="true" style="width:700px;">

     
<c:forEach items="${curriList}" var="curri" >
	<c:if test="${empty curri.curri_cd2}">
		      <!-- Accordion card -->
		      <div class="card" style="width:700px;">
		
		        <!-- Card header -->
		        <div class="card-header rgba-stylish-strong z-depth-1 mb-1" role="tab" id="heading3" style="width:700px;">
		          <a class="collapsed" data-toggle="collapse" data-parent="#accordionEx7" href="#collapse3"
		            aria-expanded="false" aria-controls="collapse3">
		            <h5 class="mb-0 white-text text-uppercase font-thin">
		              ${curri.curri_nm} <i class="fas fa-angle-down rotate-icon"></i>
		            </h5>
		          </a>
		        </div>
		        
  	</c:if>
	<c:if test="${not empty curri.curri_cd2}">
		        <!-- Card body -->
		        <div id="collapse3" class="collapse" role="tabpanel" aria-labelledby="heading3"
		          data-parent="#accordionEx7" style="width:700px;">
		          <div class="card-body mb-1 rgba-grey-light white-text" style="width:700px;">
            	<p>${curri.curri_order}. ${curri.curri_nm}  [첨부파일 : ${curri.curri_ori_nm}]</p>
	          </div>
	        </div>
      <!-- Accordion card -->
    </c:if>
 </c:forEach>    
	      </div>
      
    </div>
    <!--/.Accordion wrapper-->

  </div>
  <!-- Grid column -->

</div>
<!-- Grid row -->
							<div></div>
					</div>
				</div>
				<div style="margin-left:40%;">
				<input type="button" class="btn btn-warning goPage" value="바로가기" /> <input type="button" class="btn btn-warning" value="목록"	onclick="location.href='<c:url value="/creatorCenter/classList.do"/>';" />
				<br><br><br>
				</div>
		</div>
	</div>

<form id="detail" action="${cPath}/hobby/hobbyDetail.do" method="get">
<input type="hidden" name="what"/>
</form>
<script>
$(".goPage").on("click",function(){
	var cd = ${classInfo.cl_cd};
	$("input[name='what']").val(cd);
	$("#detail").submit();
})
  
</script>