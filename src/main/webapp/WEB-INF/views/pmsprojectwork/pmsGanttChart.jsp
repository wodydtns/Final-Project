<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 20.      최효은      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>


<!-- content 제목 -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">Gantt 차트</h1>
</div>
<div class="card shadow mb-4">
		<div class="card-header py-3">
			<span class="m-0 font-weight-bold text-primary">진행중인 작업</span>
			<div id="inputForm" class="form-inline justify-content-center mb-3">
				<select name="prog_cd" class="pms-input-border pms-align-right" >
					<!-- <spring:message code="proj.proj_cate" /> -->
					<option value="">진행상황 선택</option>
					<c:forEach items="${progressList }" var="prog">
						<option value="${prog['prog_cd'] }">${prog['prog_nm'] }</option>
					</c:forEach>
				</select>
				<select name="prior_cd" class="pms-input-border pms-align-right" >
					<!-- <spring:message code="proj.proj_cate" /> -->
					<option value="">우선순위 선택</option>
					<c:forEach items="${priorityList }" var="prio">
						<option value="${prio['prior_cd'] }">${prio['prior_nm'] }</option>
					</c:forEach>
				</select>
				<input type="text" name="" id="searchWord"  class="form-control form-control-sm"
						placeholder="작업명을 입력해주세요."	value="${search.searchWord }"	/>	
				<input type="button" value="검색" id="searchBtn"	width="100"
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"/>
			</div>

		</div>
		<div id="pms-gantt-exp">
			<div id="pms-div-new" class="pms-inline-block">&nbsp;          &nbsp;</div>&nbsp;신규
			<div id="pms-div-prog" class="pms-inline-block">&nbsp;          &nbsp;</div>&nbsp;진행
			<div id="pms-div-comp" class="pms-inline-block">&nbsp;          &nbsp;</div>&nbsp;완료
		</div>
		<!-- 간트차트 -->
		<div class="card-body">
			<div class="gantt"></div>
		</div>
	</div>

<form id="searchForm">
	<input type="hidden" name="who" value="${authMember.mem_email}" />
	<input type="hidden" name="what" value="${sessionScope.proj_cd }" />
	<input type="hidden" name="prog_cd" value="${param.prog_cd }"/>
	<input type="hidden" name="prior_cd"  value="${param.prior_cd }"/>
	<input type="hidden" name="searchWord" value="${search.searchWord }"/>
</form>
    
<script type="text/javascript">
		var searchForm = $("#searchForm");
		var inputForm = $("#inputForm");
		var searchTypeTag = $("#searchType");
		var searchWordTag = $("#searchWord");
		var prog_cdTag = $("[name='prog_cd']");
		var prior_cdTag = $("[name='prior_cd']");
	
        	
		"use strict";	// 엄격모드, JavaScript 의 제한된 버전을 선택
           
		var demoSource = [];
		var tmp = [];
       	
       	var searchForm = $("#searchForm").on("submit", function(event){
       		event.preventDefault();
			let queryString = $(searchForm).serialize();
	       	let method = $(searchForm).attr("method");
           	// 간트차트에 들어갈 데이터 넣어주는 부분
        	$.ajax({
				url: "<c:url value='/pmsProject/projectGanttChart.do' />",
				method: method,
				data : queryString,
				dataType : "json", 
				success : function(resp) {
					let color = "";
					if(resp.length>0){
						let pw_end = "";
						let pw_start = "";
						let pw_level = "";
						
						demoSource.length = 0;
						tmp.length = 0;
						tmp = [];
						for(var i = 0; i < resp.length; i++){
							pw_end = new Date(resp[i].pw_end).getTime();
							pw_start = new Date(resp[i].pw_start).getTime();
							pw_level = resp[i].pw_level.toString()+"%";
							
							if(resp[i].prog_nm == "신규"){
								color = "ganttGreen";	
							}else if(resp[i].prog_nm == "진행"){
								color = "ganttBlue";
							}else if(resp[i].prog_nm == "완료"){
								color = "ganttRed";
							}else{
								color = "ganttOrange";
							}

							tmp = 
								{name: resp[i].rnum
								, desc: resp[i].pw_nm
								, values:
									[{from: pw_start
									, to: pw_end
									, label: pw_level
									, customClass: color
									}]
								}
							;
							
							demoSource.push(tmp);
							
							result(demoSource);
						}
					}	
				},
				error : function(xhr) {
					console.log(xhr.status);
				}
			});
           	
       	});
          	function result(demoSource){
          		
            // shifts dates closer to Date.now()
            // 날짜 변경 부분
            // Date.now() : 1970년 1월 1일 0시 0분 0초부터 현재까지 경과된 밀리 초
            var offset = new Date().setHours(0, 0, 0, 0) - new Date(demoSource[0].values[0].from).setDate(35);
            	// 현재 일자 시간 세팅 X, 비동기로 받아온 데이터의 첫 번째 값의 종료일자를 불러와 35일로 세팅해주는 부분
            	
            for (var i = 0, len = demoSource.length, value; i < len; i++) {	// demoSource의 길이만큼 반복문
                value = demoSource[i].values[0];	// values[0] = from, 종료일자를 불러와 value에 세팅
                value.from += offset;				// value의 종료일자에 
                value.to += offset;
            }

            $(".gantt").gantt({
                source: demoSource,
                navigate: "scroll",
                scale: "weeks",
                maxScale: "months",
                minScale: "hours",
                itemsPerPage: 10,
                scrollToToday: false,
                useCookie: true,
                onItemClick: function(data) {
// 	                    alert("Item clicked - show some details");
                },
                onAddClick: function(dt, rowId) {
// 	                    alert("Empty space clicked - add an item!");
                },
                onRender: function() {
                    if (window.console && typeof console.log === "function") {
                        console.log("chart rendered");
                    }
                }
            });

//             $(".gantt").popover({
//                 selector: ".bar",
//                 title: function _getItemText() {
//                     return this.textContent;
//                 },
//                 container: '.gantt',
//                 content: "Here's some useful information.",
//                 trigger: "hover",
//                 placement: "auto right"
//             });

            prettyPrint();
	            
		}
          	
        // 검색 버튼을 눌렀을 때의 처리
       	var searchBtn = $("#searchBtn").on("click", function(){
       		let searchType = searchTypeTag.val();
       		let searchWord = searchWordTag.val();
       		var inputTags = inputForm.find(":input[name]");
       		$(inputTags).each(function(index, element){
       			let name = $(this).attr("name");
       			let value = $(this).val();
       			let searchFormInput = searchForm.find("input[name='"+name+"']");
       			searchFormInput.val(value);
       			console.log("name: "+name);
       			console.log("value: "+value);
       		});
       		searchForm.find("[name='searchWord']").val(searchWord); 
       		console.log("searchWord: " + searchWord);
       		searchForm.submit();
       	});
        
       	// 검색 조건에 값 채워주는 부분
       	searchTypeTag.val("${search.searchType }");
       	prog_cdTag.val("${param['prog_cd']}");
       	prior_cdTag.val("${param['prior_cd']}");
       	
       	console.log();
       	
       	searchForm.submit();

    </script>