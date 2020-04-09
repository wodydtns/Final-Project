<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav id="sidebar" style="height:1000px">
	
	<ul class="list-unstyled components">
		<li><a href="adminStats.do"><i class="fas fa-home"></i>대시보드</a></li>
		
		<!-- 회원관리 부분 -->
		<li><a href="#memberMenu" data-toggle="collapse"
			aria-expanded="false" class="dropdown-toggle no-caret-down"><i
				class="fas fa-people-carry" style="color:#ffffff"></i>회원관리</a>
			<ul class="collapse list-unstyled" id="memberMenu">
				<li><a href="${cPath }/master/adminMemberManage.do"><i class="fas fa-user" style="color:#fc037b"></i>일반회원</a></li>
				<li><a href="${cPath }/master/adminCreatorManage.do"><i class="fas fa-user-cog" style="color:#fce703"></i>컨텐츠 제작자</a></li>
				<li><a href="${cPath }/master/adminBlockManage.do"><i class="fas fa-user-slash" style="color:#f542f5"></i>신고리스트</a></li>
			</ul>
		</li>
		<!--  차트 부분 -->
		<li><a href="${cPath }/master/adminChartDetail.do"><i class="fas fa-chart-bar" style="color:#ffffff"></i>상세 차트</a></li>
		<!--  고객 센터 -->
		<li><a href="#csMenu" data-toggle="collapse"
			aria-expanded="false" class="dropdown-toggle no-caret-down"><i
				class="fas fa-satellite" style="color:#ffffff"></i>고객센터</a>
			<ul class="collapse list-unstyled" id="csMenu">
				<li><a href="${cPath }/master/adminCSCenter.do"><i class="fas fa-bell" style="color:#fc037b"></i>공지사항</a></li>
			</ul>
		</li>
		
		<li><a href="#classManageMenu" data-toggle="collapse"
			aria-expanded="false" class="dropdown-toggle no-caret-down"><i
				class="fas fa-school" style="color:#ffffff"></i>클래스 관리</a>
			<ul class="collapse list-unstyled" id="classManageMenu">
				<li><a href="${cPath }/master/adminClassList.do"><i class="fas fa-school" style="color:#fc037b"></i>사전조사 수락 클래스 리스트</a></li>
				<li><a href="${cPath }/master/adminPreClassList.do"><i class="fas fa-school" style="color:#fce703"></i>사전조사 중인 클래스 리스트</a></li>
				<li><a href="${cPath }/master/adminDoingClassList.do"><i class="fas fa-school" style="color:#03a1fc"></i>진행중인 클래스 리스트</a></li>
			</ul>
		</li>
	</ul>
</nav>


