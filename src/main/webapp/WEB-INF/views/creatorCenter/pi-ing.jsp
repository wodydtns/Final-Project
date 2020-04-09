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
#classListDIV{
	margin-left:300px;
	width:48%;
	float:left;
}
.myclass{
	margin-top :50px;
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
				<h4 style="color:black; font-weight:bold;">사전조사 진행 클래스</h4>
				</div>
				</div>
				<c:forEach items="${dataList }" var="pi">
        		<div class="myclass">
  					<div class="row" >
                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <h5 class="card-header">${pi['pi_nm'] }</h5>
                                        <div class="card-body">
                                        			<p>카테고리 : ${pi['cate_nm'] }</p>
                                                 <img src="/JinDam/piImages/${pi.temp_nm}" />  
                                            <h5 class="card-title"></h5>
                                        		    현재 좋아요 수<span class="number">${pi['pi_cnt'] }</span>
                                            <p class="card-text"> 	사전조사 기간 :  ${pi['pi_start'] }~${pi['pi_end'] }</p>
                                            <a href="<c:url value="/creatorCenter/pi_view.do?what=${pi['pi_cd']}"/>" class="btn btn-warning">상세보기 및 수정</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
					</div>	
				</div>
				 <br><br><br><br><br>
                       <br><br><br><br><br>	
		</c:forEach>


<!-- 플로팅바 -->
<div id="floatdiv" class="card">
      <div class="single-widget category-widget">
			사전조사가 진행중인 클래스들이에요!<br>
			오픈될 클래스일 수도 있으니 미리 커리큘럼을 준비해보세요!	<br>
			<br>
			<br>
			<br>
	  </div>
	  
	  <div>
	  	<img src="../images/new-service-point-01.db472084.png"/>
	 	더 빠르고, 더 쉽게<br>
		평균 8주 제작, 첫달 600만원 정산<br>
		출판보다 빠르고, 유튜브보다 쉽게<br>
		수익을 창출할 수 있습니다<br>
	  </div>
    </div>


