<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 19.      김혜정      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>

#classListDIV{
	margin-left:300px;
	width:48%;
	float:left;
}
.myclass{
	width:35%;
	margin-left:300px;
	margin-right:50px;
	float:left;
}
.guide{
	width:15%;
	float:left;
}
</style>

<!-- 	<div id="listBody" class="d-sm-flex align-items-center justify-content-between mb-4"> -->
      
       	 <c:choose>
       	  	<c:when test="${empty dataList}">
       	  	
       	  	<div style="width:600px; margin:0 auto; text-align:center;">
	       	  	<h4>이제부터 나도 크리에이터! 지금 클래스를 생성해보세요!</h4>
	       	  	<br/><br/>
			</div>
			<div class="inline-block" style=" width:100%; height:500px; background-image: url('../images/크리에이터센터.png');">
	   	  		<a href="<c:url value='/creatorCenter/piInsert.do' />" class="d-none d-sm-inline-block btn btn-sm btn-warning shadow-sm"
	   	  		style="height:60px; width:200px; padding:15px; margin-left:300px; margin-top:360px; font-size:20px;">
				<i id="icon" class="fas fa-arrow-alt-circle-right fa-sm text-white-50"></i> 새 클래스 생성</a>
			</div>
			
			
       	  	</c:when>
       	  	<c:otherwise>
       	  		<div>
	       	  	<div id="classListDIV" class="d-sm-flex align-items-center justify-content-between mb-4 inline-block">
	       	  		<h4 style="color:black; font-weight:bold;">나의 클래스</h4>
	       	  		<a href="<c:url value='/creatorCenter/piInsert.do' />" class="d-none d-sm-inline-block btn btn-sm btn-warning shadow-sm"
	       	  		style="height:40px; padding-top:7px; margin-left: 230px;">
					<i id="icon" class="fas fa-arrow-alt-circle-right fa-sm text-white-50"></i> 새 클래스 생성</a>
					<h4 style="color:black; font-weight:bold;">맞춤 가이드</h4>
				</div>
				</div>
				
					<div class="myclass inline-block" >
       	  		<c:forEach items="${dataList }" var="cl">
			  					<div class="row" style="margin-top:20px;">
			                                <div class="col-md-12">
			                                    <div class="card">
			                                        <div class="card-body">
			                                        	<div style="width:42%; float:left;">
		                                        		<img src="/JinDam/piImages/${cl['temp_nm']}" style="width:230px; height:170px; "/>
		                                        		</div>
		                                        		<div style="width:58%; float:right; padding-left:20px;">
			                                     	   <h5 class="card-title">${cl['pi_nm'] }</h5>
			                                            <p class="card-text">진행기간 :  ${cl['cl_start'] }~${cl['cl_end'] }</p>
			                                           	<br/>
			                                           	<br/>
			                                            <c:url value="/creatorCenter/classView.do" var="classViewURL">
															<c:param name="what" value="${cl['cl_cd']}" />
														</c:url>
			                                            <c:url value="/creatorCenter/stuList.do" var="stuListURL">
															<c:param name="what" value="${cl['cl_cd']}" />
														</c:url>
			                                            <a href="${classViewURL}" class="btn btn-warning">상세정보조회</a>	
			                                            <a href="${stuListURL}" class="btn btn-warning">수강생리스트조회</a>
			                                            </div>	
			                                        </div>
			                                    </div>
			                                </div>
								</div>	
					</c:forEach>
								</div>
       	  	
       	  		<div class="guide inline-block">
			<div class="card" style="margin-top:20px; margin-bottom:20px; width:400px; height:auto; padding:20px 20px;">
			취준진담이 뭐에요?</br>
			나도 크리에이터가 될 수 있는지, 클래스 개설 과정은 어떻게 되는지 궁금증을 풀어보세요.
				<a href="https://www.notion.so/class101/f8720eac1aa646c89ee7d9e56483cb1a" target="_blank">자세히 보기</a>
			</div>
			<div class="card" style="margin-bottom:20px; width:400px; height:auto; padding:20px 20px;">
			수요조사 페이지 작성 가이드
			가이드를 따라 매력 넘치는 수요조사 페이지를 작성해보아요.
				<a href="https://www.notion.so/c5db60faeabe4f1da5fcbd5cf05c0a92" target="_blank">자세히 보기</a>
			</div>
			<div class="card" style="margin-bottom:20px; width:400px; height:auto; padding:20px 20px;">
			취준진담 회사소개서
				<a href="https://creator.class101.net/documents/class101_company_introduction.pdf" target="_blank">자세히 보기</a>
			</div>
			</div>
       	  	</c:otherwise>
       	 </c:choose>
			
			
			
			
	