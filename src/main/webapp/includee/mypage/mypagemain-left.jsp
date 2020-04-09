<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
#accordionSidebar{    
	background:gray;
}

</style>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- 사이드바 홈버튼 -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${cPath }/hobby/hobbyMain.do" >
        <div>
		  <span>메인 홈페이지</span>
        </div>
      </a>
	  
	  <hr class="sidebar-divider my-0" />

      <!-- Heading -->
      <div class="sidebar-heading">
        	취미
      </div>

      <!-- 취미 메뉴-->
      <li class="nav-item active" id="hover">
        <a class="nav-link" href="${cPath}/mypage/mypageMain.do">
          <span class="bg-blue-500 hover:bg-white ...">나의정보</span></a>
      </li>
      <hr class="sidebar-divider">
      <li class="nav-item active" id="hover">
        <a class="nav-link" href="${cPath }/mypage/mypageStudyingList.do">
        
          <span>수강중인 클래스</span></a>
      </li>
      <hr class="sidebar-divider">
       <li class="nav-item active" id="hover">
        <a class="nav-link" href="#" onclick="getPassWord()">
          <span>비밀번호 변경</span></a>
      </li>
      <hr class="sidebar-divider">
      
       <li class="nav-item active" id="hover">
        <a class="nav-link" href="${cPath }/mypage/mypageCouponList.do">
          <span>나의 쿠폰 리스트</span></a>
      </li>
      
</ul>
<script>
	function getPassWord(){
		window.open("${cPath}/etc/mypagePassCheck.do",'비밀번호 변경','left=400,top=400,width=600,height=600,menubar=no,resizable=no,scrollbars=no,toolbars=no');
	}
	
</script>
