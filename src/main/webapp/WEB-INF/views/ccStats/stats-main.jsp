<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<script
	src="https://cdn.jsdelivr.net/npm/patternomaly@1.3.2/dist/patternomaly.min.js"></script>   
<script src="${cPath }/js/echarts.min.js"></script>
<style>
.content, .card
{
	width:1100px;
	 height:730px;
}
.content1{
	margin-left:230px;
}
</style>
<div class="content1" >
	<div class="container-fluid">
			<div class="col-md-6">
					<div class="card">
						<div class="content" >
							<div class="head" style="width: 100%; margin-left:40%; margin-top:30px; ">
								<h4 class="mb-0">클래스별 통계조회</h4>
								<p class="text-muted">수익금,수강생 수,좋아요 수(현재날짜 기준)</p>
							</div>
							<div class="canvas-wrapper" style="padding:20px;">
								<canvas id="totalProfit"></canvas>
							</div>
						</div>
					</div>
			</div>
	</div>
</div>	
<script>
// 평균 데이터
var ctx = document.getElementById("totalProfit").getContext("2d");
var labelName = [];
var dataValue1= [];
var dataValue2= [];
var dataValue3= [];
<c:forEach items="${like}" var="like">
labelName.push("${like.pi_nm}");
</c:forEach>
<c:forEach items="${profit}" var="pro">
	dataValue1.push("${pro.pay_amt}");
	dataValue2.push("${pro.stu_cnt}");
	dataValue3.push("${pro.cl_like}");
	console.log()
</c:forEach>
var data = {
    labels: labelName,
    datasets: [
        {
            label: "수익금",
            backgroundColor:pattern.draw('zigzag-horizontal', '#17becf'),
            data : dataValue1
        },
        {
            label: " 수강생수",
            backgroundColor:pattern.draw('triangle', '#ffce56'),
            data : dataValue2
        },
        {
            label: "좋아요수",
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
