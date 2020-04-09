<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.section-gap{
	padding:0px;
}
#footerImg{
	width:100%;
}
#sns{
	width:250px;
}
#sns img:hover, a:focus {
   background-color: #fff;
   text-decoration: underline;
}
#footer1{
	color:white;
	font-size: 12px;
}
#explain{
	font-size:12px;
}
#explain li a{
	color:#828bb2;
}
#explain li a:hover{
	color:white;
}
#footMenu1{
	margin-left:35px;
	width:250px;
}
#footMenu2{
	margin-left:90px;
}  
#footMenu3{
	padding-left: 80px;
}
</style>
<!-- start footer Area -->
<footer class="footer-area section-gap">
	<a href="<c:url value='/hobby/hobbyMain.do' />">
	<img src="${cPath }/images/hobby/classpic/footerPic.png" id="footerImg">
	</a>
	<div class="container box_1170">
		<div class="row">
			<div class="col-lg-3 col-md-6 col-sm-6" id="footMenu1">
				<div class="single-footer-widget">
					<h6 class="footer_title">사업소개</h6>
					<p>
						취미를 시작하는데 필요한 모든 것, 취준진담에서 만나보세요! 당신의 꿈이 더 이상 꿈으로만 머물지 않도록,
						 각 분야 최고의 크리에이터들과 시작해보세요.
					</p>
				</div>
			</div>
			
			<div class="col-lg-4 col-md-6 col-sm-6" id="footMenu2">
				<div class="single-footer-widget instafeed">
					<h6 class="footer_title">약관 및 문의</h6>
					<ul class="list instafeed d-flex flex-wrap" id="explain">
						<li>
							<a href="javascript:;" onclick="window.open('${cPath}/TermsOfService.html','name','resizebale=no','width=200px','height=500px', 'left=1000', 'top=500');return false">이용약관 |</a>
						</li>
						<li>
							<a href="javascript:;" onclick="window.open('${cPath}/TermsOfService2.html','name','resizebale=no','width=200px','height=500px', 'left=1000', 'top=500');return false"">개인정보 처리방침 |</a>
						</li>
						<li>
							<a href="mailto: qhdud2098@naver.com" target="_blank">단체/기업교육 문의</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-lg-2 col-md-6 col-sm-6" id="footMenu3">
				<div class="single-footer-widget f_social_wd">
					<h6 class="footer_title">Follow Us</h6>
					<div id="sns">
						<a href="#"><img alt="구글" src="${cPath }/buttons/main/구글.png"></a>
						<a href="#"><img alt="트위터" src="${cPath }/buttons/main/트위터.png""></a>
						<a href="#"><img alt="페이스북" src="${cPath }/buttons/main/페이스북.png""></a>
						<a href="#"><img alt="인스타그램" src="${cPath }/buttons/main/인스타그램.png""></a>
					</div>
				</div>
			</div>
		</div>
		<div class="row footer-bottom d-flex justify-content-between align-items-center">
			<p class="col-lg-12 footer-text text-center" id="footer1">
				(주)취준진담 | 대표 박재욱 | 이사 김혜정 | 개인정보보호 책임자 최효은 | 기술영업 팀장 최도혁 |  대전광역시 중구 대흥동 영민빌딩 204호 | 대표번호 : 042-222-8201 
				<br>| 통신판매업신고 : 2020-대전중구-0087 |(주)취준진담은 통신판매중개자이며 통신판매의 당사자가 아닙니다. 
				<br>따라서 (주)취준진담은 상품, 거래정보 및 거래에 대하여 책임을 지지 않습니다. &copy;
				<script>document.write(new Date().getFullYear());</script> 
			</p>
		</div>
	</div>
</footer>

	<!-- End footer Area -->