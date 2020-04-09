<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	SimpleDateFormat formatter04 = new SimpleDateFormat("yyyy-MM-dd");
%>

<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-6 col-md-6 col-lg-3 mt-3">
				<div class="card">
					<div class="content">
						<div class="row">
							<div class="col-sm-4">
								<div class="icon-big text-center">
									<i class="teal fas fa-splotch"></i>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="detail text-center">
									<p>전체 크리에이터 수</p>
									<span class="number">${creatorTotal }</span>
								</div>
							</div>
						</div>
						<div class="footer">
							<hr />
							<div class="stats">
								<i class="fas fa-calendar-alt" style="color: #f56042"></i> 기준 :
								<%=formatter04.format(new Date())%>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-6 col-lg-3 mt-3">
				<div class="card">
					<div class="content">
						<div class="row">
							<div class="col-sm-4">
								<div class="icon-big text-center">
									<i class="olive fas fa-user"></i>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="detail text-center">
									<p>전체 회원 수</p>
									<span class="number">${memberTotal }</span>
								</div>
							</div>
						</div>
						<div class="footer">
							<hr />
							<div class="stats">
								<i class="fas fa-calendar-alt" style="color: dodgerblue"></i> 기준
								:
								<%=formatter04.format(new Date())%>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-6 col-lg-3 mt-3">
				<div class="card">
					<div class="content">
						<div class="row">
							<div class="col-sm-4">
								<div class="icon-big text-center">
									<i class="violet fas fa-book-open"></i>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="detail text-center">
									<p>진행중인 클래스 수</p>
									<span class="number">${ongoingClass }</span>
								</div>
							</div>
						</div>
						<div class="footer">
							<hr />
							<div class="stats">
								<i class="fas fa-calendar-alt" style="color: forestgreen"></i>
								기준 :
								<%=formatter04.format(new Date())%>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-6 col-lg-3 mt-3">
				<div class="card">
					<div class="content">
						<div class="row">
							<div class="col-sm-4">
								<div class="icon-big text-center">
									<i class="fas fa-calendar-alt" style="color: orange"></i>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="detail text-center">
									<p>사전조사 중인 클래스</p>
									<span class="number">${piClass }</span>
								</div>
							</div>
						</div>
						<div class="footer">
							<hr />
							<div class="stats">
								<i class="fas fa-calendar-alt" style="color: #f542b9"></i> 기준 :
								<%=formatter04.format(new Date())%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- rising words -->
		<div class="row">
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="head">

							<h4 class="mb-0">취미와 관련된 급상승 검색어</h4>

						</div>
						<div class="canvas-wrapper" id="rising"></div>
					</div>
				</div>
			</div>
			<script>
			  // List of words
			  	var myWords = [];
			    
			    <c:forEach items="${risingList }" var="rising">
					myWords.push("${rising.rising_word}");
				</c:forEach>
				
			    // set the dimensions and margins of the graph
			    var margin = {top: 10, right: 10, bottom: 10, left: 10},
			        width = 300 - margin.left - margin.right,
			        height = 300 - margin.top - margin.bottom;
			    
			    // append the svg object to the body of the page
			    var svg = d3.select("#rising").append("svg")
			        .attr("width", width + margin.left + margin.right)
			        .attr("height", height + margin.top + margin.bottom)
			      .append("g")
			        .attr("transform",
			              "translate(" + margin.left + "," + margin.top + ")");
			    
			    // Constructs a new cloud layout instance. It run an algorithm to find the position of words that suits your requirements
			    var layout = d3.layout.cloud()
			      .size([width, height])
			      .words(myWords.map(function(d) { return {text: d}; }))
			      .padding(10)
			      .fontSize(20)
			      .on("end", draw);
			    layout.start();
			    
			    // This function takes the output of 'layout' above and draw the words
			    // Better not to touch it. To change parameters, play with the 'layout' variable above
			    function draw(words) {
			      svg
			        .append("g")
			          .attr("transform", "translate(" + layout.size()[0] / 2 + "," + layout.size()[1] / 2 + ")")
			          .selectAll("text")
			            .data(words)
			          .enter().append("text")
			            .style("font-size", function(d) { return d.size + "px"; })
			            
			            .attr("text-anchor", "middle")
			            .attr("transform", function(d) {
			              return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
			            })
			            .text(function(d) { return d.text; });
			    }
  
    </script>
			<!-- top word 
		리팩토링 필요
	-->
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="head">
							<h4 class="mb-0">Top 연관 검색어</h4>
						</div>
						<div class="canvas-wrapper" id="top"></div>
					</div>
				</div>
			</div>
		</div>

		<script>
			  // List of words
			  	var myWords = [];
			    
			    <c:forEach items="${topList }" var="top">
					myWords.push("${top.top_word}");
				</c:forEach>
				
			    // set the dimensions and margins of the graph
			    var margin = {top: 10, right: 10, bottom: 10, left: 10},
			        width = 450 - margin.left - margin.right,
			        height = 450 - margin.top - margin.bottom;
			    
			    // append the svg object to the body of the page
			    var svg = d3.select("#top").append("svg")
			        .attr("width", width + margin.left + margin.right)
			        .attr("height", height + margin.top + margin.bottom)
			      .append("g")
			        .attr("transform",
			              "translate(" + margin.left + "," + margin.top + ")");
			    
			    // Constructs a new cloud layout instance. It run an algorithm to find the position of words that suits your requirements
			    var layout = d3.layout.cloud()
			      .size([width, height])
			      .words(myWords.map(function(d) { return {text: d}; }))
			      .padding(10)
			      .fontSize(20)
			      .on("end", draw);
			    layout.start();
			    
			    // This function takes the output of 'layout' above and draw the words
			    // Better not to touch it. To change parameters, play with the 'layout' variable above
			    function draw(words) {
			      svg
			        .append("g")
			          .attr("transform", "translate(" + layout.size()[0] / 2 + "," + layout.size()[1] / 2 + ")")
			          .selectAll("text")
			            .data(words)
			          .enter().append("text")
			            .style("font-size", function(d) { return d.size + "px"; })
			            
			            .attr("text-anchor", "middle")
			            .attr("transform", function(d) {
			              return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
			            })
			            .text(function(d) { return d.text; });
			    }
    </script>

		<div class="row">
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="head">
							<h4 class="mb-0">급상승 Youtube 통계(17년도)</h4>
							<p class="text-muted">상세 차트</p>
						</div>
						<div class="canvas-wrapper">
							<table class="table no-margin bg-lighter-grey">
							<thead class="thead-dark">
								<tr>
									<th onclick="tableSort(this)" scope="col">카테고리 이름</th>
									<th onclick="numberTableSort(this,true)" scope="col">카테고리 총계 </th>
									<th onclick="numberTableSort(this,true)" scope="col">평균 조회수</th>
									<th onclick="numberTableSort(this,true)" scope="col">평균 좋아요 수</th>
									<th onclick="numberTableSort(this,true)" scope="col">평균 싫어요 수</th>
									<th onclick="numberTableSort(this,true)" scope="col">평균 댓글 수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${youtubeSevenList }" var="seven">
									<tr>
										<td class="table-info">${seven.name }</td>
										<td class="table-primary">${seven.count }</td>
										<td class="table-info">${seven.avg }</td>
										<td class="table-primary">${seven.avg_likes }</td>
										<td class="table-info">${seven.avg_dislikes }</td>
										<td class="table-primary">${seven.avg_comment }</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div><div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="head">
							<h4 class="mb-0">급상승 Youtube 통계(18년도)</h4>
							<p class="text-muted">상세 차트</p>
						</div>
						<div class="canvas-wrapper">
							<table class="table no-margin bg-lighter-grey">
							<thead class="thead-dark">
							<tr>
									<th onclick="tableSort(this)" scope="col">카테고리 이름</th>
									<th onclick="numberTableSort(this,true)" scope="col">카테고리 총계 </th>
									<th onclick="numberTableSort(this,true)" scope="col">평균 조회수</th>
									<th onclick="numberTableSort(this,true)" scope="col">평균 좋아요 수</th>
									<th onclick="numberTableSort(this,true)" scope="col">평균 싫어요 수</th>
									<th onclick="numberTableSort(this,true)" scope="col">평균 댓글 수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${youtubeEightList }" var="eight">
									<tr>
										<td class="table-info">${eight.name }</td>
										<td class="table-primary">${eight.count }</td>
										<td class="table-info">${eight.avg }</td>
										<td class="table-primary">${eight.avg_likes }</td>
										<td class="table-info">${eight.avg_dislikes }</td>
										<td class="table-primary">${eight.avg_comment }</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		
		</div>
		<!--  클래스 랭킹 -->
		<div class="row">
			<div class="col-sm-6 col-md-6 col-lg-3">
				<div class="card">
					<div class="content">
						<p class="text-muted">클래스 좋아요 순위</p>
						<table class="table no-margin bg-lighter-grey">
							<thead class="success">
								<tr>
									<th>순위</th>
									<th class="text-right">클래스 이름</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${rankingList }" var="ranking">
									<tr>
										<td>${ranking.rank }<i class="fas fa-crown"
											style="color: #a83232"></i></td>
										<td class="text-right">${ranking.pi_nm }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>
			
			<div class="col-sm-6 col-md-6 col-lg-3">
				<div class="card">
					<div class="content">
						<div class="row">
							<div class="dfd text-center">
								<p class="text-muted">진행중인 프로젝트 수</p>
								<i class="blue large-icon mb-2 fas fa-project-diagram"></i>
								<h4 class="mb-0">${projectIng }</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-6 col-lg-3">
				<div class="card">
					<div class="content">
						<div class="row">
							<div class="dfd text-center">
								<p class="text-muted">조회수 높은 TOP3(youtube)</p>
								<c:forEach items="${trendRankingList }" var="trendRank">
									<h4 class="mb-0"><i class="fas fa-video" style="color:dodgerblue"></i>&nbsp;${trendRank.name } : ${trendRank.total }</h4>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div> 
			<div class="col-sm-6 col-md-6 col-lg-3">
				<div class="card">
					<div class="content">
						<div class="row">
							<div class="dfd text-center">
								<p class="text-muted">조회수 높은 TOP3(youtube)</p>
								<c:forEach items="${trendRatioList }" var="trendRatio">
									
									<h4 class="mb-0"><i class="fas fa-heart" style="color:red"></i>&nbsp;${trendRatio.name } : ${trendRatio.total }</h4>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
