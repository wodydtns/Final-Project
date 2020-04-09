<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


       <!-- Topbar 상단메뉴바-->
        <nav class="navbar navbar-expand navbar-light bg-gray-100 topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

<!--           Topbar Search 상단 서치바 -->
<!--           <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"> -->
<!--             <div class="input-group"> -->
<!--               <input type="text" class="form-control bg-light border-0 small" placeholder="검색" aria-label="Search" aria-describedby="basic-addon2"> -->
<!--               <div class="input-group-append"> -->
<!--                 <button class="btn btn-primary" type="button"> -->
<!--                   <i class="fas fa-search fa-sm"></i> -->
<!--                 </button> -->
<!--               </div> -->
<!--             </div> -->
<!--           </form> -->

          <!-- Topbar Navbar 상단 우측-->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- 드롭다운 - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>

            <!-- Nav Item - Alerts -->
<!--             <li class="nav-item dropdown no-arrow mx-1"> -->
<!--               <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!--                 <i class="fas fa-bell fa-fw"></i> -->
<!--                 알림 갯수 -->
<%--                 <span class="badge badge-danger badge-counter">${PMWorkCount }</span> --%>
<!--               </a> -->
<!--             </li> -->
            
            
            <!-- Nav Item - Messages -->
            <li class="nav-item dropdown no-arrow mx-1">
<!--               <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" data-toggle="modal" data-target="#feedModalLong"> -->
              <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" onclick="feedbackOpen('${authMember.mem_email}');">
              <!-- data-toggle="dropdown" 잠깐 뺐음 -->
                <i class="fas fa-envelope fa-fw"></i>
                <!-- Counter - Messages -->
                <span class="badge badge-danger badge-counter" id="feedCount">${FeedbackCount }</span>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                <h6 class="dropdown-header">
                 	쪽지 보관함
                </h6>
                <a class="dropdown-item text-center small text-gray-500"
                	href="#" >피드백 관리</a>
              </div>
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>
            

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              	<!-- 추후 수정 : 일반회원1 -->
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${authMember.mem_nick}
                <img class="img-profile rounded-circle" src="<c:url value='../images/login/user.jpg'/>"></span>
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <!-- 추후 수정 : 로그아웃 버튼 추가해야 함 -->
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->
    
      <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">로그아웃 하시겠습니까?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">로그아웃 버튼을 클릭하시면 세션이 만료됩니다.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
          <a class="btn btn-primary" href="${cPath }/login/logout.do">로그아웃</a>
        </div>
      </div>
    </div>
  </div>
  
	  <!-- 피드백 상세보기 모달창 -->
	<div class="modal fade bd-example-modal-xl" id="feedModalLong" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">피드백 관리</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div id="modal-body">
	      </div>
	    </div>
	  </div>
	</div>
	
	

<script type="text/javascript">

	function feedbackOpen(a){
		window.open("${cPath}/pmsProject/projectFeedbackList.do?mem_email="+a,"feedback",
						"width=800,height=630");
	}

	//초대하기 모달창 출력
	$("#feedbackList").on("click", function(event){
		// 추후 수정 : ${authMember.mem_email}
		let aTag = $(event.relatedTarget);
		let modalBody = $(this).find("#modal-body")
		$.ajax({
			url : "<c:url value='/pmsProject/projectFeedbackList.do'/>",
			data : {
				mem_email: "${authMember.mem_email}",
			},
			method : "get",
			dataType : "html",
			success : function(resp) {
				modalBody.html(resp);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	}); 
	
	$(document).on('hidden.bs.modal', function (event) {
		if ($('.modal:visible').length) {
			$('body').addClass('modal-open');
		}
	});
	
// 	var feedCount = $("#feedCount"); 
	
// 	function poll(){
// 		$.ajax({
// 			url : "<c:url value='pmsProject/projectFeedbackCount.do' />",
// 			method : "get",
// 			data : {
// 				mem_email : "${authMember.mem_email}"
// 			},
// 			dataType : "json",
// 			success : function(resp) {
// 				console.log("resp"+resp);
// // 				feedCount.val(resp);
// 			},
// 			error : function(errorResp) {
// 				console.log(errorResp.responseText);
// 			},timeout: 3000,
// 			complete: poll, timeout: 3000
// 		});
// 	});
	
</script>













    