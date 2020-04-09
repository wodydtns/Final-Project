<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${cPath }/js/Chart.js"></script>
<script type="text/javascript" src="${cPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/patternomaly@1.3.2/dist/patternomaly.min.js"></script>
<title>검사 결과</title>
</head>

<body>
	<div>
		<canvas id="scoreRadar" width="500" height="200"></canvas>
	</div>
	<div>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th></th>
					<th scope="col">점수</th>
					<th scope="col">정도</th>
					<th scope="col">해석</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>개방성</td>
					<td>${scoreMap.open }</td>
					<c:if test="${1 <= scoreMap.open and scoreMap.open <= 4 }">
						<td>낮음</td>
					</c:if>
					<c:if test="${5 <= scoreMap.open and scoreMap.open <= 6 }">
						<td>중간</td>
					</c:if>
					<c:if test="${7 <= scoreMap.open and scoreMap.open <= 8 }">
						<td>중상</td>
					</c:if>
					<c:if test="${scoreMap.open >= 9 }">
						<td>높음</td>
					</c:if>
					<td>광범위한 주제에서 "새로운 것" 에 대해 개인이 판단하는 경향을 확인하는 요인</td>
				</tr>
				<tr>
					<td>성실성</td>
					<td>${scoreMap.conscient }</td>
					<c:if
						test="${1 <= scoreMap.conscient and scoreMap.conscient <= 4 }">
						<td>낮음</td>
					</c:if>
					<c:if
						test="${5 <= scoreMap.conscient and scoreMap.conscient <= 6 }">
						<td>중간</td>
					</c:if>
					<c:if
						test="${7 <= scoreMap.conscient and scoreMap.conscient <= 8 }">
						<td>중상</td>
					</c:if>
					<c:if test="${ scoreMap.conscient >= 9 }">
						<td>높음</td>
					</c:if>
					<td>개인의 조직화된 정도를 확인하는 요인</td>
				</tr>
				<tr>
					<td>외향성</td>
					<td>${scoreMap.extravert }</td>
					<c:if
						test="${1 <= scoreMap.extravert and scoreMap.extravert <= 4 }">
						<td>낮음</td>
					</c:if>
					<c:if
						test="${5 <= scoreMap.extravert and scoreMap.extravert <= 6 }">
						<td>중간</td>
					</c:if>
					<c:if
						test="${7 <= scoreMap.extravert and scoreMap.extravert <= 8 }">
						<td>중상</td>
					</c:if>
					<c:if test="${scoreMap.extravert  >= 9 }">
						<td>높음</td>
					</c:if>
					<td>광범위한 주제에서 "새로운 것" 에 대해 개인이 판단하는 경향을 확인하는 요인</td>
				</tr>
				<tr>
					<td>친화성</td>
					<td>${scoreMap.agreeable }</td>
					<c:if
						test="${1 <= scoreMap.agreeable  and scoreMap.agreeable <= 9 }">
						<td>낮음</td>
					</c:if>
					<c:if
						test="${10 <= scoreMap.agreeable and scoreMap.agreeable <= 11 }">
						<td>중하</td>
					</c:if>
					<c:if
						test="${12 <= scoreMap.agreeable and scoreMap.agreeable <= 13 }">
						<td>중상</td>
					</c:if>
					<c:if test="${ scoreMap.agreeable >= 14 }">
						<td>높음</td>
					</c:if>
					<td>대인 관계에서 보이는 질적인 측면을 확인하는 요인</td>
				</tr>
				<tr>
					<td>신경성</td>
					<td>${scoreMap.neuro }</td>
					<c:if test="${1 <= scoreMap.neuro and scoreMap.neuro <= 4 }">
						<td>낮음</td>
					</c:if>
					<c:if test="${5 <= scoreMap.neuro and scoreMap.neuro <= 6 }">
						<td>중간</td>
					</c:if>
					<c:if test="${7 <= scoreMap.neuro and scoreMap.neuro <= 8 }">
						<td>중상</td>
					</c:if>
					<c:if test="${scoreMap.neuro >= 9 }">
						<td>높음</td>
					</c:if>
					<td>개인이 일상 속에서 발생하는 힘든 경험들에 부정적 정서를 얼마나 자주 경험하는지를 확인하는 요인</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
			</tfoot>
		</table>
	</div>
	<script>
		var labelName = [ '개방성', '성실성', '외향성', '친화성', '신경성' ];
		var ctx = document.getElementById('scoreRadar').getContext('2d');
		
		var radar = new Chart(ctx, {
			type: 'horizontalBar',
			data : {
				labels : labelName,
				datasets : [ {
					label : "${scoreMap.member}",
					data : [${scoreMap.open},${scoreMap.conscient},${scoreMap.extravert},${scoreMap.agreeable},${scoreMap.neuro}],
					barPercentage : 0.5,
					barThickness : 6,
					maxBarThickness : 8,
					minBarLength : 2,
					backgroundColor : [ pattern.draw('square', '#ff6384'),
							pattern.draw('circle', '#36a2eb'),
							pattern.draw('diamond', '#cc65fe'),
							pattern.draw('triangle', '#ffce56'),
							pattern.draw('zigzag-horizontal', '#17becf')]
				}]
			},
			 options: {
				 responsive: false,
			        scales: {
			            xAxes: [{
			                stacked: true
			            }],
			            yAxes: [{
			                stacked: true
			            }]
			        },
					
			    }
		});
	</script>
</body>
</html>