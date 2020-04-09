<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 19.      김혜정      최초작성
	지역별 
* Copyright (c) 2020 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<%@ include file="/includee/preScript.jsp" %>
		<script>
			$(document).ready(function(){
				
				$("#btn").click(function() {
					
					$.ajax({
						type : "get",
						url : "<c:url value='/saramin/search.do'/>",
						data : "query=" + $("#query").val(),
						dataType : "json",
						success:function(result) {
// 							console.log(result);
// 							console.log(result.jobs);
// 							console.log($(result["job-search"].jobs.job));

							$("#tbl1 tbody").html("");

							$(result["job-search"].jobs.job).each(function(index, item) {
// 							$(result.jobs.job).each(function(index, item) {
								
								var name = item.company.name.content;
								var title = item.position.title;
								var job = item.position.industry.content;
								var location = item.position.location.content;
								
								console.log(name);
								console.log(title);
								console.log(job);
								console.log(location);
								
								var tr = "<tr><td>\""+ name +"</td><td>\""+ title +"</td><td>\""+ job +"</td><td>\""+ location +"</td></tr>";
								
								$("#tbl1 tbody").append(tr);
								
							});
							
						},
						error : function(a,b,c) {
							console.log(a,b,c);
						}
						
					});
					
				});
				
			});
		</script>
	</head>
	<body>
		<!-- ex03.jsp -->
		<div class="container">
			<h1 class="page-header">Application Programming Interface</h1>
			
			<h2>Open API - 사람인 검색</h2>
			
			<form id="search" class="well form-inline" onsubmit="return false;">
				<label>키워드 : </label>
				<input type="text" name="query" id="query" class="form-control" />
				<input type="button" value="검색하기" class="btn btn-default" id="btn" />
			</form>

			<table class="table table-bordered" id="tbl1">
				<thead>
					<tr>
						<th>기업명</th>
						<th>공고명</th>
						<th>업직종</th>
						<th>위치</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
			
		</div>
	</body>
</html>