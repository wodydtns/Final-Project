<%@page import="kr.or.ddit.vo.CreatorVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<style>
#mainNav{
	background:white;
}

#navbarResponsive{
	width:250px;
	right:50%;
}

</style>

    
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
  <div class="container">
    <a class="navbar-brand js-scroll-trigger" href="<c:url value="/hobby/hobbyMain.do"/>"><img src="${cPath }/images/hobby/취준진담.png" style="width: 117px; height:60px;"></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarRespo.nsive" aria-expanded="false" aria-label="Toggle navigation">
      Menu
      <i class="fas fa-bars"></i>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav text-uppercase ml-auto" id="ul-menu">
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" href="#services" style="color:black;">클래스소개</a>
        </li>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" href="#portfolio" style="color:black;">제작자소개</a>
        </li>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" href="#about" style="color:black;">커리큘럼</a>
        </li>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" href="#team" style="color:black;">커뮤니티</a>
        </li>
      </ul>
    </div>
  </div>
</nav>




<script>
	$(function(){
		var shrinkHeader = 100;
		$(window).scroll(function(){
			var scroll = getCurrentScroll();
			if(scroll >= shrinkHeader){
				$(".navbar navbar-expand-lg navbar-dark fixed-top").addClass("navbar-shrink");
			}else {
				$(".navbar navbar-expand-lg navbar-dark fixed-top").removeClass("navbar-shrink");
			}
		});
		function getCurrentScroll(){
			return window.pageYOffset || document.documentElement.scrollTop;
		}
	});
</script>
