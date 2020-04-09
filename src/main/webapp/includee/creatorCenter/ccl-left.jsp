<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 좌측 사이드바 -->
<style>
	#accordionSidebar{
		height:100%;
	}
</style>
    <ul class="navbar-nav bg-gradient-warning sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- 사이드바 홈버튼 -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/creatorCenter/classList.do" >
        <div>
          <i class="fas fa-level-down-alt"></i>
		  <span>이전으로 돌아가기</span>
        </div>
      </a>
	  
	  <hr class="sidebar-divider my-0" />

      <!-- Heading -->
      <div class="sidebar-heading">
        MENU
      </div>

      <!-- Nav Item - Dashboard 사이드바 메뉴-->
      <li class="nav-item active" id="hover">
        <a class="nav-link" href="<c:url value='/creatorCenter/statsMain.do' />">
          <i class="fas fa-list"></i>
          <span>클래스별 통계</span></a>
      </li>

      
    </ul>
    <!-- End of Sidebar -->