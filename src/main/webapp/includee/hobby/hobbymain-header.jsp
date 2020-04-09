<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> 
<style>
#header{
	margin-top: 140px;
	background: #1e1c27;
}
#header1 {
	margin-top: -138px;
	background: white;
}

#section{
	background: white;
}
#inganin {
	width:80px;
	height:60px;
}
#category {
    width: 1100px;
    margin-left: -130px;
    background: white;
}
.modal-dialog{
	z-index  : 1500;
}
#searchWord, #searchType{
	color: white;
}
#searchType{
	background: black;
	height: 40px;
}
#searchBtn{
	color: white;
	background: black;
	height: 40px;
}
#searchWord{
	background: white;
	color: black;
	height: 40px;
}

/*플로팅바 css*/
#floatdiv {
    position: fixed;
    width: 250px;
    overflow: hidden;
    right: 0px;
    top: 180px;
    background-color: transparent;
    margin-top: 60px;
    padding: 0;
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
#headerleft img{
	z-index: -1;
    width: 160px;
    margin-left: 200px;
}
#loginMenu{
	font-size: 16px;
}
.nav-item img{
	height: 45px;
	font-size: 20px;
}
#maincategory, #maincategory a{
	font-size: 16px;
}
#white{
	color: white;
	font-size: 16px;
}
</style>
<!-- 플로팅바 -->
<div id="floatdiv">
      <div class="single-widget category-widget">
			<h5 class="title">기타메뉴</h5>
			<ul>
				<li>
					<a href="<c:url value='/mypage/mypageMain.do' />" class="justify-content-between align-items-center d-flex" target="blank">
						<p><img src="${pageContext.request.contextPath }/images/hobby/bullet.png" alt="">마이페이지</p>
					</a>
				</li>
				<li>
					<a href="<c:url value='/hobby/hobbyNotice.do' />" class="justify-content-between align-items-center d-flex" target="_blank"> 
						<p><img src="${pageContext.request.contextPath }/images/hobby/bullet.png" alt="">공지사항</p>
					</a>
				</li>
				<li>
					<a href="<c:url value='/careerapi/careerSearchForm.do' />" class="justify-content-between align-items-center d-flex" target="blank">
						<p><img src="${pageContext.request.contextPath }/images/hobby/bullet.png" alt="">커리어검색</p>
					</a>
				</li>
				<li>
					<a href="<c:url value='/recruit/recruitJob.do' />" class="justify-content-between align-items-center d-flex" target="blank">
						<p><img src="${pageContext.request.contextPath }/images/hobby/bullet.png" alt="">사람인 채용검색</p>
					</a>
				</li>
				<li>
					<a href="<c:url value='/hobby/hobbyIntelligenceForm.do' />" class="justify-content-between align-items-center d-flex" target="blank">
						<p><img src="${pageContext.request.contextPath }/images/hobby/bullet.png" alt="">인적성 검사</p>
					</a>
				</li>
			</ul>
		</div>
    </div>
    

    
<div class="modal fade" id="ModalPopup" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content" >
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">제작자 전환</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
	      <div class="modal-body">
      <form id="insertCreator" action="<c:url value='/member/creatorchange.do'/>" method="post">
     <p> 사랑하는 일로 매달 연금 타가세요! 단 한 번의 클래스 제작으로 지속적인 수익을 창출할 수 있어요. 사랑하는 일을 하면서요!</p>
			<textarea name="mem_intro" id="introArea" class="form-control" placeholder="소비자들의 관심을 사로잡을 수 있도록 내 소개를 해주세요!"></textarea>
	  </form>        	
	      </div>
	      <div class="modal-footer">
	        <button type="button" id="modalBtn" class="btn btn-primary">등록</button>
	      </div>
    </div>
  </div>
</div>

<section class="header-top" id="header">
	<div id="header1">
		<div class="row align-items-center justify-content-between" id="header2">
			<div class="col-lg-6 col-md-6 col-sm-6" id="headerleft">
				<a href="<c:url value='/hobby/hobbyMain.do' />">
					<img src="${cPath }/images/hobby/취준진담.png">
				</a>
			</div>
		
		<div class="col-lg-6 col-md-6 col-sm-6 search-trigger" id="loginMenu">
			<a href="#" class="search"> 검색
				<img src="${pageContext.request.contextPath }/buttons/main/search.png"id="search">
			</a> &nbsp;  
			
			
			<c:if test="${empty authMember}">
				<a href="<c:url value='/login/login.do' />" class="login"> 로그인
					<img id="login" src="${pageContext.request.contextPath }/buttons/main/login.png">
				</a> &nbsp;
			</c:if>
			<c:if test="${not empty authMember}">
				<a href="<c:url value='/login/logout.do' />" class="logout"> 로그아웃
					<img id="logout" src="${pageContext.request.contextPath }/buttons/main/관리자.png">
				</a> &nbsp;
			</c:if>
			<a href="<c:url value='/pmsProject/projectList.do'>
              <c:param name="who" value="${authMember.mem_email}" />
           		</c:url>">PMS
				<img src="${pageContext.request.contextPath }/buttons/main/pms.png">
			</a> &nbsp;
			
			<a href="#" id="creatorCenter">크리에이터센터
				<img src="${pageContext.request.contextPath }/buttons/main/pms.png">
			</a> &nbsp;
				
			</div>
		</div>
	</div>
		
</section>
	<!--================ End header top Area =================-->

<!-- Start header Area -->
<header id="header">
	<div class="container box_1170 main-menu">
		<div class="row align-items-center justify-content-center d-flex">
			<nav id="nav-menu-container">
				<form action="<c:url value='hobby/hobbyCategory.do'/>" method="get">
				<input type="hidden" name="cate_cd" value="${hobbycategorypaging.cate_cd}"/>
				<ul class="nav-menu" id="maincategory">
					<li class="nav-item"><a href="#">전체카테고리▼</a>
						<ul class="nav" id="category">
							<li class="nav-item">
								<a class="nav-link" href="<c:url value='/hobby/hobbyCategory.do?what=C001' />" target="_blank">
									<img alt="디자인" src="${cPath }/images/hobby/디자인.png" />
									디자인
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value='/hobby/hobbyCategory.do?what=C002' />" target="_blank">
									<img alt="음악" src="${cPath }/images/hobby/음악.png" />
									음악
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value='/hobby/hobbyCategory.do?what=C003' />" target="_blank">
									<img alt="외국어" src="${cPath }/images/hobby/외국어.png" />
									외국어
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value='/hobby/hobbyCategory.do?what=C004' />" target="_blank">
									<img alt="커리어" src="${cPath }/images/hobby/커리어.png" />
									커리어
								</a>
							
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value='/hobby/hobbyCategory.do?what=C005' />" target="_blank">
									<img alt="영상" src="${cPath }/images/hobby/영상.png" />
									영상
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value='/hobby/hobbyCategory.do?what=C006' />" target="_blank">
									<img alt="프로그래밍" src="${cPath }/images/hobby/프로그래밍.png" />
									프로그래밍
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value='/hobby/hobbyCategory.do?what=C007' />" target="_blank">
									<img alt="라이프스타일" src="${cPath }/images/hobby/lifestyle.png" />
									라이프스타일
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value='/hobby/hobbyCategory.do?what=C008' />" target="_blank">
									<img alt="운동" src="${cPath }/images/hobby/운동.png" />
									운동
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value='/hobby/hobbyCategory.do?what=C009' />" target="_blank">
									<img alt="뷰티" src="${cPath }/images/hobby/뷰티.png" />
									뷰티
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value='/hobby/hobbyCategory.do?what=C010' />" target="_blank">
									<img alt="기타" src="${cPath }/images/hobby/기타.png" />
									기타
								</a>
							</li>
						</ul>
					</li>
					<li><a href="<c:url value='/hobby/hobbyMain.do' />">HOME</a></li>
					<li><a href="<c:url value='/hobby/hobbyBest.do' />">BEST</a></li>
					<li class="menu-has-children"><a href="<c:url value='/hobby/hobbyPI.do' />">사전조사</a></li>
				</ul>
				</form>  	
			</nav>
		</div>
	</div>
	
	<!-- 서치바  -->
	<div class="search_input" id="search_input_box" > 
		<div class="container box_1170">
			<div class="form-inline mb-3 justify-content-center" id="searchTypeTag">
				<select id="searchType">
					<option value="">선택</option>
					<option value="writer">작성자</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
				<input type="text" id="searchWord" value="${param.searchWord }" placeholder="검색어 입력"/>
				<input type="button" value="검색" id="searchBtn" />
			</div>
<form action="searchForm">
<input type="hidden" name="page" />
<input type="hidden" name="searchType" value="${param.searchType }"/>
<input type="hidden" name="searchWord" value="${param.searchWord }"/>
</form>

		</div>
	</div>
</header>

<!-- End header Area -->

<!-- Top Stories Area 상단메뉴 밑 사진들(6)-->
<section class="top-stories-area" id="section">
	<div class="container-fluid">
		<div class="row"></div>
	</div>
</section>


<form id="classList" action="<c:url value='/creatorCenter/classList.do'/>" method="get">
</form>
<form id="loginForm" action="<c:url value='/login/login.do'/>" method="get">
</form>



<script>

$("#creatorCenter").on("click",function(e){
	e.preventDefault();
	$.ajax({
		url : "<c:url value='/creatorCenter/classList.do' />",
		method : "get",
		dataType : "json", 
		success : function(resp) { 
			if(resp.status=="NOTEXIST"){//제작자등록을 해야할 때
				 $("#ModalPopup").modal("toggle");
			}else if(resp.status=="EXIST"){//제작자등록이 된 회원일 때
				$("#classList").submit();
			}else if(resp.status =="NULLSESSION"){//로그인되어 있지 않을 때
				$("#loginForm").submit();
			}
		},
		error : function(xhr) {
			console.log(xhr.status)
	},
	});
	
});

var txtareaTag = $("#introArea");
var txtarea = txtareaTag.val();

$("#modalBtn").on("click",function(){
	if($("#introArea").val() == ""){
		console.log("여기를타야지");
		$("#introArea").focus();
		return;
	}else {
		console.log("왜 여길타");
		$("#insertCreator").submit();
	}
	
});


</script>