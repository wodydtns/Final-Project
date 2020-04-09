<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 좌측 사이드바 -->
<style>
	#accordionSidebar{
		height:100%;
	}
</style>
    <ul class="navbar-nav bg-gradient-warning sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- 사이드바 홈버튼 -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/hobby/hobbyMain.do" >
        <div>
          <i class="fas fa-level-down-alt"></i>
		  <span>취준진담으로 돌아가기</span>
        </div>
      </a>
	  
	  <hr class="sidebar-divider my-0" />

      <!-- Heading -->
      <div class="sidebar-heading">
        MENU
      </div>

      <!-- Nav Item - Dashboard 사이드바 메뉴-->
      <li class="nav-item active" id="hover">
        <a class="nav-link" href="${pageContext.request.contextPath}/creatorCenter/classList.do">
           <i class="fas fa-list"></i>
          <span class="bg-blue-500 hover:bg-white ...">나의 클래스</span></a>
      </li>
      <hr class="sidebar-divider">

      <li class="nav-item active" id="hover">
        <a class="nav-link" href="<c:url value='/creatorCenter/pi_wait_List.do' />">
          <i class="fas fa-list"></i>
          <span>사전조사 대기 클래스</span></a>
      </li>
      <hr class="sidebar-divider">
      
      <li class="nav-item active" id="hover">
        <a class="nav-link" href="<c:url value='/creatorCenter/pi_ing_List.do' />">
          <i class="fas fa-list"></i>
          <span>사전조사 진행 클래스</span></a>
      </li>
      <hr class="sidebar-divider">
      
       <li class="nav-item active" id="hover">
<%--         <a class="nav-link" href="<c:url value='/pmsProject/projectWorkList.do'> --%>
<%-- 		<c:param name="who" value="member1@naver.com" /></c:url>"> --%>
        <a class="nav-link" href="<c:url value='/creatorCenter/pi_end_List.do' />">
          <i class="fas fa-list"></i>
          <span>사전조사 종료 클래스</span></a>
      </li>
      <hr class="sidebar-divider">
      
       <li class="nav-item active" id="hover">
        <a class="nav-link" href="<c:url value='/creatorCenter/statsMain.do' />">
          <i class="fas fa-chart-bar"></i>
          <span>통계</span></a>
      </li>
    </ul>
    <!-- End of Sidebar -->