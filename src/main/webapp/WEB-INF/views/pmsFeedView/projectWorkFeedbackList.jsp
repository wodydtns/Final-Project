<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/includee/pms/pmsproject-preScript.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<script src="${cPath }/css/pms/vendor/jquery/jquery.min.js"></script>
<script src="${cPath }/css/pms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 4. 3.      최효은      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
 
 <!-- 프로젝트 리스트 제목, 내용 -->
 <div class="container-fluid">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">피드백 확인</h6>
			<div >
				<a href="#" id="newFeed" class="pms-feed-font">새 피드백</a>
				<span class="pms-feed-font">|</span>
				<a href="#" id="targetFeed" class="pms-feed-font">보낸 피드백</a>
				<span class="pms-feed-font">|</span>
				<a href="#" id="senderFeed" class="pms-feed-font">받은 피드백</a>
			</div>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<!-- 검색 -->
				<div id="inputForm" class="form-inline mb-3 justify-content-center">
					<select id="searchType" class="form-control mr-2">
						<option value="">전체</option>
						<option value="proj">프로젝트</option>
						<option value="nick">닉네임</option>
						<option value="work">작업</option>
					</select>
					<input type="text" id="searchWord"  class="form-control mr-2"
						value="${search.searchWord }"
					/>
					<input type="button" value="검색" id="searchBtn" class="btn btn-info"/>
				</div>
				<table class="table table-bordered" id="feedbackTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th onclick="numberTableSort(this,true)" class="pms-table-center">No</th>
							<th onclick="tableSort(this)" class="pms-table-center">프로젝트</th>
							<th onclick="tableSort(this)" class="pms-table-center">수신인</th>
							<th onclick="tableSort(this)" class="pms-table-center">발신인</th>
							<th onclick="tableSort(this)" class="pms-table-center">작업</th>
							<th onclick="dateTimeTableSort(this,'DateTime')" class="pms-table-center">작성일</th>
						</tr>
					</thead>
					<tbody id="listBody"></tbody>
					<tr>
						<td colspan="6">
							<div id="pagingArea"></div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- 피드백 상세보기 -->
<div class="modal fade bd-example-modal-xl" id="feedModalView" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">피드백 상세화면</h5>
      </div>
      <div class="modal-body">
      </div>
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">확인</button> -->
<!--       </div> -->
    </div>
  </div>
</div>

	
<!-- 추후 수정 : ${authMember.mem_email} -->
<form id="searchForm" action="${cPath }/pmsProject/projectFeedbackList.do">
	<input type="hidden" name="page" />
	<input type="hidden" name="mem_email" value="${authMember.mem_email}" />
	<input type="hidden" name="sender_email" />
	<input type="hidden" name="target_email" />
	<input type="hidden" name="feed_check" />
<%-- 	<input type="hidden" name="proj_cd" value="${param.proj_cd }" /> --%>
	<input type="hidden" name="searchType" value="${param.searchType }"/>
	<input type="hidden" name="searchWord" value="${param.searchWord }"/>
</form>

<script type="text/javascript">
	
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var searchForm = $("#searchForm");
	var inputForm = $("#inputForm");
	
	var searchTypeTag = $("#searchType");
	var searchWordTag = $("#searchWord");
	
	var sender_emailTag = $("[name='sender_email']");
	var target_emailTag = $("[name='target_email']");
	var feed_checkTag = $("[name='feed_check']");
	
	var num = 1;
	var newFeed = $("#newFeed"); 
	var targetFeed = $("#targetFeed");
	var senderFeed = $("#senderFeed");
	
	newFeed.on("click", function(){
		num = 1;
		newFeed.css({"color":"#858796", "font-weight":"bold", "text-decoration":"underline"});
		targetFeed.css({"font-weight":"normal", "text-decoration":"none"});
		senderFeed.css({"font-weight":"normal", "text-decoration":"none"});
		searchForm.submit();
	});
	targetFeed.on("click", function(){
		num = 2;
		targetFeed.css({"color":"#858796", "font-weight":"bold", "text-decoration":"underline"});
		newFeed.css({"font-weight":"normal", "text-decoration":"none"});
		senderFeed.css({"font-weight":"normal", "text-decoration":"none"});
		searchForm.submit();
	});
	senderFeed.on("click", function(){
		num = 3;
		senderFeed.css({"color":"#858796", "font-weight":"bold", "text-decoration":"underline"});
		newFeed.css({"font-weight":"normal", "text-decoration":"none"});
		targetFeed.css({"font-weight":"normal", "text-decoration":"none"});
		searchForm.submit();
	});
	
// 	// 피드백 버튼
// 	listBody.on("click", ".feedView", function(){
// 		pwData = $(this).closest("tr").data("pmList");
// 		feedbackFormModal.modal("show");
// 	});

	// 피드백 상세화면 보기
	$("#feedModalView").on("show.bs.modal", function(event){
// 		let feed_cd = $(this).closest("tr").data("feed_cd");
		// 추후 수정 : ${authMember.mem_email}
		let aTag = $(event.relatedTarget);
		let modalBody = $(this).find(".modal-body")
		console.log(num);
		$.ajax({
			url : "<c:url value='/pmsProject/projectFeedbackView.do'/>",
			data : {
				feed_cd: aTag.data("feed_cd")
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
	
	
 	// 비동기 처리 요청(진행중인 프로젝트)
 	// 추후 수정 : ${authMember.mem_email}
	var searchForm = $("#searchForm").on("submit", function(event){
		event.preventDefault();
		target_emailTag.val(null);
		feed_checkTag.val(null);
		sender_emailTag.val(null);
		if(num == 1){
			target_emailTag.val("${authMember.mem_email}");
			feed_checkTag.val("YN_F02");
		}else if(num == 2){
			sender_emailTag.val("${authMember.mem_email}");
		}else if(num == 3){
			target_emailTag.val("${authMember.mem_email}");
		}
		let queryString = $(this).serialize();
		$.ajax({
			url: "<c:url value='/pmsProject/projectFeedbackList.do' />",
			method: "get",
			data : queryString,
			dataType : "json", 
			success : function(resp) {
				let dataList = resp.dataList;
				let trTags = [];
				if(dataList.length>0){ 
					$(dataList).each(function(index, feedList){
						let eachTr = $("<tr>");
						eachTr.append(
							$("<td>").text(feedList.rnum),
							$("<td>").text(feedList.proj_nm),
							$("<td>").text(feedList.sender_nick),
							$("<td>").text(feedList.target_nick),
							$("<td>").html(
									$("<a>").attr({
										"href" : "#",
										"data-toggle" : "modal",
										"data-target" : "#feedModalView",
									})
									.addClass(".feedView")
									.text(feedList.pw_nm)
									.data("feed_cd", feedList.feed_cd)
								),	
							$("<td>").text(feedList.feed_date)
						);
						trTags.push(eachTr);
					});
				}else{
					trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"6"
							}).text("피드백 목록이 없습니다.")		
						)
					);
				}
				listBody.empty();
				listBody.append(trTags);	
				let pagingHTML = resp.pagingHTML;
				pagingArea.empty(); //**
				pagingArea.html(pagingHTML);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	});
 	
	
	//검색 조건에 값 채워주는 부분
	var searchBtn = $("#searchBtn").on("click", function(){
		let searchType = searchTypeTag.val();
		let searchWord = searchWordTag.val();
		var inputTags = inputForm.find(":input[name]");
		$(inputTags).each(function(index, element){
			let name = $(this).attr("name");
			let value = $(this).val();
			let searchFormInput = searchForm.find("input[name='"+name+"']");
			searchFormInput.val(value);
		});
		searchForm.find("[name='searchType']").val(searchType); 
		searchForm.find("[name='searchWord']").val(searchWord); 
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
	});
	
	// 페이징 처리
	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	});
	
	//검색 버튼을 누르지 않아도 submit 할 수 있도록
	searchForm.submit();
	
</script>


