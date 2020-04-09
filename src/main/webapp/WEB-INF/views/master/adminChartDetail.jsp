<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
	.content{
		margin-top : 10px;
		margin-right : 20px;
	}
</style>
<script
	src="https://cdn.jsdelivr.net/npm/patternomaly@1.3.2/dist/patternomaly.min.js"></script>
<script src="${cPath }/js/echarts.min.js"></script>

<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="row ">
							<div class="col-sm-8">
								<div class="detail text-center">
									<p>조회 수</p>
								</div>
								<canvas id="viewCount"></canvas>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="row">
							<div class="col-sm-8">
								<div class="detail text-center">
									<p>좋아요 수</p>
								</div>
								<canvas id="likeCount"></canvas>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="row">
							<div class="col-sm-8">
								<div class="detail text-center">
									<p>싫어요 수</p>
								</div>
								<canvas id="dislikeCount"></canvas>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="row">
							<div class="col-sm-8">
								<div class="detail text-center">
									<p>댓글 수</p>
								</div>
								<canvas id="commentCount"></canvas>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>

		<!-- 좋아요 / 싫어요 비율 -->
		<div class="row">
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="head">
							<h4 class="mb-0">좋아요 / 싫어요 비율</h4>
							<p class="text-muted">2017,2018년도 합산</p>
						</div>
						<div class="canvas-wrapper">
							<canvas id="barChart"></canvas>
						</div>
					</div>
				</div>
			</div>

			<!-- top word 
		리팩토링 필요
	-->
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="head">
							<h4 class="mb-0">평균 데이터</h4>
							<p class="text-muted">좋아요,싫어요,댓글 수(2018년도 기준)</p>
						</div>
						<div class="canvas-wrapper">
							<canvas id="multiValue"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="head">
							<h4 class="mb-0">회귀분석 결과 </h4>
							<p class="text-muted">조회 수에 따른 다중회귀분석</p>
							<table class="table no-margin bg-lighter-grey">
								<thead>
									<tr>
										<th>요인</th>
										<th>좋아요</th>
										<th>싫어요</th>
										<th>좋아요 댓글 수</th>
										<th>싫어요 댓글 수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${RegDataList }" var="reg">
										<tr>
											<td>${reg.mame }</td>
											<td>${reg.likes }</td>
											<td>${reg.dislikes }</td>
											<td>${reg.likes_comment_count }</td>
											<td>${reg.dislikes_comment_count }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="card">
					<div class="content">
						<div class="head">
							<h4 class="mb-0">18년도 상관관계 분석</h4>
							<p class="text-muted">상관관계 정도</p>
							<table class="table no-margin bg-lighter-grey">
								<thead>
									<tr>
										<th>요인</th>
										<th>조회수</th>
										<th>좋아요</th>
										<th>싫어요</th>
										<th>댓글</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${CorrDataList }" var="cor">
										<tr>
											<td>${cor.name }</td>
											<td>${cor.likes }</td>
											<td>${cor.dislikes }</td>
											<td>${cor.comment_count }</td>
											<td>${cor.comments_disabled }</td>
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
		
	</div>
</div>
<script type="text/javascript">
//비율
var ctx = document.getElementById("barChart").getContext('2d')
var labelName = [];
var dataRatio = [];
<c:forEach items="${RatioList }" var="ratio">
	labelName.push("${ratio.name}");
	dataRatio.push(${ratio.ratio});
</c:forEach>

var myChart = new Chart(ctx, {
	type: 'horizontalBar',
    data: {
        labels: labelName,
        datasets: [{
            label: '좋아요 - 싫어요 비율',
            data: dataRatio,
            backgroundColor:[
        	   pattern.draw('square', '#ff6384'),
               pattern.draw('circle', '#36a2eb'),
               pattern.draw('diamond', '#cc65fe'),
               pattern.draw('triangle', '#ffce56'),
        	   pattern.draw('square', '#ff6384'),
               pattern.draw('circle', '#36a2eb'),
               pattern.draw('diamond', '#cc65fe'),
               pattern.draw('triangle', '#ffce56'),
               pattern.draw('square', '#ff6384'),
               pattern.draw('circle', '#36a2eb'),
               pattern.draw('diamond', '#cc65fe'),
               pattern.draw('triangle', '#ffce56'),
               pattern.draw('square', '#ff6384'),
               pattern.draw('circle', '#36a2eb'),
               pattern.draw('diamond', '#cc65fe'),
               pattern.draw('triangle', '#ffce56'),
               pattern.draw('zigzag-horizontal', '#17becf')
           ]
        }]
      
    },
    options: {
      legend : {
    	  display : true
      }
    }
});
</script>
<script>
// 평균 데이터
var ctx = document.getElementById("multiValue").getContext("2d");
var labelName = [];
var dataValue1= [];
var dataValue2= [];
var dataValue3= [];
<c:forEach items="${HoleAvgDataList }" var="avg">
	labelName.push("${avg.name}");
	dataValue1.push(${avg.avg_likes});
	dataValue2.push(${avg.avg_dislikes});
	dataValue3.push(${avg.avg_comment});
	console.log()
</c:forEach>
var data = {
    labels: labelName,
    datasets: [
        {
            label: "평균 좋아요 수",
            backgroundColor:pattern.draw('zigzag-horizontal', '#17becf'),
            data : dataValue1
        },
        {
            label: " 평균 싫어요 수",
            backgroundColor:pattern.draw('triangle', '#ffce56'),
            data : dataValue2
        },
        {
            label: "평균 댓글 수",
            backgroundColor:pattern.draw('circle', '#36a2eb'),
            data : dataValue3
        }
    ]
};

var myBarChart = new Chart(ctx, {
    type: 'bar',
    data: data,
    options: {
        barValueSpacing: 20,
        scales: {
            yAxes: [{
                ticks: {
                    min: 0,
                }
            }]
        }
    }
});
</script>

<script>
function util(lableList,dataList){
	var labelNames = lableList;
	var dataValues = dataList;
	data = {
			labels: labelNames,
		    datasets: [{
		        data:dataValues,
		        backgroundColor:[
		        	   pattern.draw('square', '#ff6384'),
		               pattern.draw('circle', '#36a2eb'),
		               pattern.draw('diamond', '#cc65fe'),
		               pattern.draw('triangle', '#ffce56'),
		               pattern.draw('zigzag-horizontal', '#17becf'),
		               pattern.draw('square', '#ff6384'),
		               pattern.draw('circle', '#36a2eb'),
		               pattern.draw('diamond', '#cc65fe'),
		               pattern.draw('triangle', '#ffce56'),
		               pattern.draw('zigzag-horizontal', '#17becf')
		        	   
		           ]
		    }]

		};
		return data;
	}
function doChart(chart,view){
	var chartData = chart;
	var viewDimension = view;
var myDoughnutChart = new Chart(viewDimension, {
    type: 'doughnut',
    data: chartData,
    options:{
   	 legend: {
   	      display: true,
   	      position: 'right',
   	      align:'end',
   	      labels: {
   	        fontColor: "#000080",
   	      }
   	    },
   	    scales: {
   	      yAxes: [{
   	        ticks: {
   	          beginAtZero: true
   	        }
   	      }]
   	    },
   	 layout: {
         padding: {
             left: 0,
             right: 0,
             top: 0,
             bottom: 0
         }
     }
   }
    
});
}

</script>
<script>
//view count
var dataValue4 = [];
var labelName4 = [];
<c:forEach items="${HoleCountList }" var="count">
	labelName4.push("${count.name}");
	dataValue4.push(${count.count});
</c:forEach>
var chartData1 = util(labelName4, dataValue4);
var viewCtx = document.getElementById("viewCount").getContext("2d");
doChart(chartData1,viewCtx);
 
// like count
</script>
<script>
var dataValue5 = [];
var labelName5 = [];
<c:forEach items="${HoleLikesList }" var="like">
labelName5.push("${like.name}");
	dataValue5.push(${like.count_likes});
</c:forEach>
var chartData2 = util(labelName5, dataValue5);
var likeCtx = document.getElementById("likeCount").getContext("2d");
doChart(chartData2,likeCtx);
</script>
<script>
//dislike count
var dataValue6 = [];
var labelName6 = [];
<c:forEach items="${HoleDislikesList }" var="dislike">
labelName6.push("${dislike.name}");
dataValue6.push(${dislike.count_dislikes});

</c:forEach>
var chartData3 = util(labelName6, dataValue6);
var dislikeCtx = document.getElementById("dislikeCount").getContext("2d");
doChart(chartData3,dislikeCtx);
</script>
<script>
//comment count
var dataValue7 = [];
var labelName7 = [];
<c:forEach items="${HoleCommentList }" var="comment">
labelName7.push("${comment.name}");
dataValue7.push(${comment.count_comment});
</c:forEach>
var chartData4 = util(labelName7, dataValue7);
var commentCtx = document.getElementById("commentCount").getContext("2d");
doChart(chartData4,commentCtx);
</script>
