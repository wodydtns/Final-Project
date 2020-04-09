<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 16.      최효은      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
 <style>
 	#accordionSidebar{
		height:100%;
	}
 </style>

<c:set var="proj_cd" value="${sessionScope.proj_cd }" scope="session" ></c:set>
<c:set var="mem_nick" value="${authMember.mem_nick }" scope="session" ></c:set>
<c:set var="mem_email" value="${authMember.mem_email }" scope="session" ></c:set>

<!-- 전부 추후 수정 -->

<!-- 좌측 사이드바 -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- 사이드바 홈버튼 -->
        <div>
	      <a class="sidebar-brand d-flex align-items-center justify-content-center"
	      href="<c:url value='/pmsProject/projectList.do'>
	        	<c:param name="who" value="${authMember.mem_email}" />
	        </c:url>">
          <i class="fas fa-level-down-alt"></i>
		  <span>&nbsp;&nbsp;프로젝트 리스트 돌아가기</span></a>
        </div>
	  
	  <hr class="sidebar-divider my-0" />

      <!-- Heading -->
      <div class="sidebar-heading">
        MENU
      </div>

      <!-- Nav Item - Dashboard 사이드바 메뉴-->
      <li class="nav-item active" id="hover">
        <a class="nav-link"
        	href="<c:url value='/hobby/hobbyMain.do' />">
          <i class="fas fa-home"></i>
          <span class="bg-blue-500 hover:bg-white ...">홈으로</span></a>
      </li>
      <hr class="sidebar-divider">

      <li class="nav-item active" id="hover">
	      <a class="nav-link" href="<c:url value='/pmsProject/projectView.do'>
		      <c:param name="who" value="${authMember.mem_email}" />
		      <c:param name="what" value="${sessionScope.proj_cd }" />
	      </c:url>">
          <i class="fas fa-list"></i>
          <span>프로젝트 관리</span></a>
      </li>
      <hr class="sidebar-divider">
      
       <li class="nav-item active" id="hover">
<%--         <a class="nav-link" href="<c:url value='/pmsProject/projectWorkList.do'> --%>
<%-- 		<c:param name="who" value="${authMember.mem_email}" /></c:url>"> --%>
        <a class="nav-link" href="<c:url value='/pmsProject/projectWorkList.do'>
        	<c:param name="who" value="${authMember.mem_email}" />
        	<c:param name="what" value="${sessionScope.proj_cd }" />
        </c:url>">
          <i class="fas fa-table"></i>
          <span>작업내역</span></a>
      </li>
      <hr class="sidebar-divider">
      
       <li class="nav-item active" id="hover">
<%--         <a class="nav-link" href="<c:url value='/pmsProject/projectWorkList.do'> --%>
<%-- 		<c:param name="who" value="${authMember.mem_email}" /></c:url>"> --%>
        <a class="nav-link" href="<c:url value='/pmsProject/projectGanttChart.do'>
        	<c:param name="who" value="${authMember.mem_email}" />
        	<c:param name="what" value="${sessionScope.proj_cd }" />
        </c:url>">
          <i class="fas fa-chart-bar"></i>
          <span>업무 진행 현황 확인<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Gantt, chart)</span></a>
      </li>
      <hr class="sidebar-divider">
      
       <li class="nav-item active" id="hover">
<%--         <a class="nav-link" href="<c:url value='/pmsProject/projectWorkList.do'> --%>
<%-- 		<c:param name="who" value="${authMember.mem_email}" /></c:url>"> --%>
        <a class="nav-link" href="<c:url value='/pmsProject/projectWorkCalendar.do'/>">
          <i class="fas fa-chart-bar"></i>
          <span>캘린더</span></a>
      </li>
      <hr class="sidebar-divider">
      
       <li class="nav-item active" id="hover">
        <a class="nav-link" href="<c:url value='/pmsProject/projectBoardList.do'>
        	<c:param name="what" value="${sessionScope.proj_cd }" />
        </c:url>">
          <i class="fas fa-list"></i>
          <span>프로젝트 게시판</span></a>
      </li>
      <hr class="sidebar-divider">
      
       <li class="nav-item active" id="hover">
<%--         <a class="nav-link" href="<c:url value='/pmsProject/projectWorkList.do'> --%>
<%-- 		<c:param name="who" value="${authMember.mem_email}" /></c:url>"> --%>
        <a class="nav-link" href="<c:url value='/pmsProject/projectMemberList.do'>
        	<c:param name="who" value="${authMember.mem_email}" />
        	<c:param name="what" value="${sessionScope.proj_cd }" />
        </c:url>">
          <i class="fas fa-user"></i>
          <span>멤버관리</span></a>
      </li>
      
      <hr class="sidebar-divider">
    </ul>
    <!-- End of Sidebar -->