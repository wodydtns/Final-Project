<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 23.      김혜정      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.myclass{
		margin-top :25px;
		margin-bottom :25px;
		margin-left: 300px;
		width : 500px;
		height : 200px;
	}
	
	img{
		width:100%; 
		height:200px;
	}
	#classListDIV{
	margin-left:300px;
	width:48%;
	float:left;
}

/*플로팅바 css*/
#floatdiv {
    position: fixed;
   width:400px; height:auto;
    overflow: hidden;
    right: 350px;
    top: 200px;
    margin: 0;
    padding: 20px;
}
#floatdiv ul {
  list-style: none;
}
#floatdiv li {
  margin-bottom: 2px;
  text-align: center;
}
#floatdiv a {
  color: #5d5d5d;
  border: 0px;
  text-decoration: none;
  display: block;
}
#floatdiv a:hover,
#floatdiv .menu {
  color: #5d5d5d;
  color: #fff;
}
#floatdiv .menu,
#floatdiv .last {
  margin-bottom: 0px;
}
</style>
				<div id="classListDIV" class="d-sm-flex align-items-center justify-content-between mb-4 inline-block">
				<div>
				<h4 style="color:black; font-weight:bold;">사전조사 대기 클래스</h4>
				<small>관리자가 수락한 사전조사는 사전조사진행리스트에서 조회가능합니다.</small>
				</div>
				</div>
				<c:forEach items="${dataList }" var="pi">
				
        			<div class="myclass">
  					<div class="row" >
                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <h5 class="card-header"> ${pi['pi_nm'] }</h5>
                                        <div class="card-body">
                                        		<p>카테고리 : ${pi['cate_nm'] }</p>
                                                <img src="/JinDam/piImages/${pi.temp_nm}" />  
                                            <h5 class="card-title"></h5>
                                             	
                                            	 
                                            	<c:choose>
														<c:when test="${ pi['yn_code'] eq 'YN_C05'}"><p class="card-text" style="color:red; font-weight:bold;">사전조사 수락여부 : 반려</p></c:when>
														<c:when test="${ pi['yn_code'] eq 'YN_C06'}"><p class="card-text" style="font-weight:bold;">사전조사 수락여부 : 대기 </p></c:when>
												</c:choose>
                                           
                                            <c:if test="${ pi['yn_code'] eq 'YN_C06'}">
                                            <a href="<c:url value='/creatorCenter/piUpdate.do?what=${pi.pi_cd}'/>" class="btn btn-warning">수정하기</a>
                                            <a href="<c:url value='/creatorCenter/piDelete.do?what=${pi.pi_cd}'/>"
                                             data-toggle="modal" data-target="#DelModal"  class="btn btn-warning delBtn">삭제하기</a>
                                            </c:if>
                                            <c:if test="${ pi['yn_code'] eq 'YN_C05'}">
                                            <a href="<c:url value='/creatorCenter/piDelete.do?what=${pi.pi_cd}'/>"
                                             data-toggle="modal" data-target="#DelModal"  class="btn btn-warning delBtn">삭제하기</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
					</div>
					</div>	
                       <br><br><br><br><br>
                       <br><br><br><br><br>
                       <br><br><br><br><br>
                       <br><br><br><br><br>
		</c:forEach>
		
		
		
 <div class="modal fade" id="DelModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">정말로 삭제 하시겠습니까?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">삭제 버튼을 클릭하시면 대기중이던 사전조사가 삭제됩니다.</div>
        <div class="modal-footer">
          <a class="btn btn-primary" id="delLink" href="#">삭제</a>
          <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
        </div>
      </div>
    </div>
  </div>
		
		<!-- 플로팅바 -->
<div id="floatdiv" class="card">
      <div class="single-widget category-widget">
			클래스를 만들기 위해서는 사전조사가 먼저 시행된답니다!<br>
			사전조사 생성 후 관리자의 수락이 이루어지면 <br>
			본격적으로 예비수강생들에게 조사가 진행되고,<br>
			좋아요 100개가 넘으면 클래스 생성 자격이 주어집니다! <br>
			<br>
			<br>
			<br>
	  </div>
	  
	  <div>
	  	<img src="../images/new-service-point-02.1d12891c.png"/>
	 	데이터로 수요 파악, 무료 홍보 효과까지<br>
		온라인 광고가 무료로 집행됩니다.<br>
		매일 N만명의 사람들에게 도달하고<br>
		사진 선택까지도 데이터로 결정하세요.<br>
	  </div>
    </div>
		
<form id="DelForm" method="get" action="<c:url value='/creatorCenter/piDelete.do?'/>">
<input type="hidden" name="what"/>
</form>		
		
<script type="text/javascript">

var href=null;

$(".delBtn").on("click",function(e){
	e.preventDefault();
	href = $(this).attr("href");
	var out = href.substring(href.indexOf("=", 0)+1);
	console.log(out);
	$("input[name='what']").val(out);
})

$("#delLink").on("click",function(e){
	e.preventDefault();
	$("#DelForm").submit();
})

	
</script>

