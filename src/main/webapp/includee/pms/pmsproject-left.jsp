<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 13.      최효은      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
<!-- 좌측 사이드바 -->
<style>
	#accordionSidebar{
		height:100%;
	}
</style>
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- 사이드바 홈버튼 -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value='/hobby/hobbyMain.do' />" >
        <div>
          <i class="fas fa-home"></i>
		  <span>홈으로</span>
        </div>
      </a>
	  
	  <hr class="sidebar-divider my-0" />

      <!-- Heading -->
      <div class="sidebar-heading">
        MENU
      </div>

      <li class="nav-item active" id="hover">
        <a class="nav-link" href="<c:url value='/pmsProject/projectList.do'>
	        	<c:param name="who" value="${authMember.mem_email}" />
	        	<c:param name="what" value="${SessionScrope.proj_cd }" />
	        </c:url>">
          <i class="fas fa-list"></i>
          <span>프로젝트 리스트</span></a>
      </li>
      <hr class="sidebar-divider">
      
       <li class="nav-item active" id="hover">
<%--         <a class="nav-link" href="<c:url value='/pmsProject/projectWorkList.do'> --%>
<%-- 		<c:param name="who" value="${authMember.mem_email}" /></c:url>"> --%>
        <a class="nav-link" href="<c:url value='/pmsProject/projectWorkAllList.do?who=${authMember.mem_email}' />">
          <i class="fas fa-table"></i>
          <span>작업내역</span></a>
      </li>
      <hr class="sidebar-divider">
      
       <li class="nav-item active" id="hover">
        <a class="nav-link" href="<c:url value='/pmsProject/projectAllGanttChart.do?who=${authMember.mem_email}' />">
          <i class="fas fa-chart-bar"></i>
          <span>Gantt차트</span></a>
      </li>
    </ul>
    <!-- End of Sidebar -->