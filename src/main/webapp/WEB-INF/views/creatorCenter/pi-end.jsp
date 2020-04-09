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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="YYYY-MM-dd" var="today" />
<style>
#classListDIV{
	margin-left:300px;
	width:48%;
	float:left;
}
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
				<h4 style="color:black; font-weight:bold;">사전조사 종료 클래스</h4>
				</div>
				</div>

<c:forEach items="${dataList }" var="pi">
        	<div class="myclass">
  					<div class="row">
                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <h5 class="card-header"> <a href="<c:url value="/creatorCenter/pi_view.do?what=${pi['pi_cd']}"/>">${pi['pi_nm'] }</a></h5>
                                        <div class="card-body">
                                        		<p>카테고리 : ${pi['cate_nm'] }</p>
                                                 <img src="/JinDam/piImages/${pi.temp_nm}" />  
                                           
                                            <h5 class="card-title"></h5>
                                        		  좋아요 수 합계 : <span class="number">${pi['pi_cnt'] }</span>
                                            <p class="card-text"> 	사전조사 기간 :  ${pi['pi_start'] }~${pi['pi_end'] } </p>
                                            <h5 class="card-text" >	오픈기한 : ${pi['pi_open'] }
<%--                                             <fmt:parseDate value="${pi['pi_open'] }" var="open" pattern="yyyy-MM-dd"/> --%>
<%-- 											<fmt:formatDate value="${open}" pattern="yyyy-MM-dd" var="openday"/> --%>
											</h5>
											<script>
												console.log("${pi['pi_open'] }");
											</script>	
                                            <c:choose>
	                                            <c:when test="${today le pi['pi_open'] }">
	                                            	<a href="<c:url value="/creatorCenter/classInsert.do?what=${pi['pi_cd']}"/>" class="btn btn-warning">클래스 생성하기</a> 
	                                            </c:when>
	                                            
	                                            <c:when test="${today gt pi['pi_open'] }">
	                              				    <p class="card-text" style="color:red; font-weight:bold;">클래스 오픈기한이 경과되었습니다.</p>
	                                            </c:when>
                                            </c:choose>
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
		</c:forEach>

<!-- 플로팅바 -->
<div id="floatdiv" class="card">
      <div class="single-widget category-widget">
		사전조사가 종료된 클래스들이에요!<br>
		사전조사에 통과했다면 생성기한 내에 커리큘럼을 올려야지만 클래스가 생성된답니다.<br>
		약관 특성상 한 번 등록된 커리큘럼은 수정될 수 없으니 아주아주 신중히 만들어주세요.<br>
		또한, 생성기한을 놓치면 다시 사전조사부터 진행해야 하니 기간을 놓치지 않도록 주의하세요!<br>
      	<br>
			<br>
			<br>
	  </div>
	  
	  <div>
	  	<img src="../images/new-service-point-03.bf45c4b9.png"/>
	 	작가로서의 발판<br>
		클래스 콘텐츠는 새로운 포트폴리오입니다.<br>
		클래스 하나로 출판과 강연 제안을 받아보세요.<br>
	  </div>
    </div>