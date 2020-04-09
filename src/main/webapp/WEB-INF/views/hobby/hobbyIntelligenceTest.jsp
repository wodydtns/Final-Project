<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${cPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<form id="testScoreinsert"
		action="${cPath }/hobby/hobbyIntelligenceTest.do">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">번호</th>
					<th scope="col">문항</th>
					<th scope="col">전혀 아니다</th>
					<th scope="col">별로 아니다</th>
					<th scope="col">중간이다</th>
					<th scope="col">약간 그렇다</th>
					<th scope="col">매우 그렇다</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td scope="col">1</td>
					<td>모르는 사람에게 먼저 말을 건다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline1" name="score1"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline1">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline2" name="score1"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline2">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline3" name="score1"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline3">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline4" name="score1"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline4">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline5" name="score1"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline5">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">2</td>
					<td>다른 사람이 편안하고 행복한지 확인한다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline21" name="score2"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline21">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline22" name="score2"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline22">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline23" name="score2"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline23">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline24" name="score2"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline24">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline25" name="score2"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline25">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">3</td>
					<td>그림,글 음악을 창작한다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline31" name="score3"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline31">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline32" name="score3"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline32">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline33" name="score3"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline33">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline34" name="score3"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline34">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline35" name="score3"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline35">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">4</td>
					<td>모든 일을 사전에 준비한다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline41" name="score4"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline41">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline42" name="score4"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline42">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline43" name="score4"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline43">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline44" name="score4"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline44">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline45" name="score4"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline45">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">5</td>
					<td>울적하거나 우울함을 느낀다</td>
						<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline51" name="score5"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline51">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline52" name="score5"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline52">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline53" name="score5"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline53">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline54" name="score5"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline54">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline55" name="score5"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline55">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">6</td>
					<td>회식, 파티, 사교모임을 계획한다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline61" name="score6"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline61">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline62" name="score6"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline62">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline63" name="score6"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline63">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline64" name="score6"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline64">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline65" name="score6"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline65">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">7</td>
					<td>사람들을 모욕한다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline71" name="score7"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline71">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline72" name="score7"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline72">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline73" name="score7"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline73">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline74" name="score7"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline74">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline75" name="score7"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline75">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">8</td>
					<td>철학적이거나 영적인 문제들을 생각한다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline81" name="score8"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline81">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline82" name="score8"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline82">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline83" name="score8"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline83">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline84" name="score8"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline84">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline85" name="score8"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline85">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">9</td>
					<td>일이나 물건을 정리하지 않고 어지럽게 그냥 둔다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline91" name="score9"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline91">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline92" name="score9"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline92">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline93" name="score9"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline93">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline94" name="score9"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline94">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline95" name="score9"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline95">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">10</td>
					<td>스트레스나 걱정을 느낀다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline101" name="score10"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline101">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline102" name="score10"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline102">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline103" name="score10"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline103">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline104" name="score10"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline104">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline105" name="score10"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline105">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">11</td>
					<td>어려운 단어를 사용한다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline111" name="score11"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline111">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline112" name="score11"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline112">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline113" name="score11"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline113">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline114" name="score11"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline114">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline115" name="score11"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline115">5점</label>
						</div></td>
				</tr>
				<tr>
					<td scope="col">12</td>
					<td>타인의 감정에 공감한다</td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline121" name="score12"
								class="custom-control-input" value="1" required> <label
								class="custom-control-label" for="customRadioInline121">1점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline122" name="score12"
								class="custom-control-input" value="2"> <label
								class="custom-control-label" for="customRadioInline122">2점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline123" name="score12"
								class="custom-control-input" value="3"> <label
								class="custom-control-label" for="customRadioInline123">3점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline124" name="score12"
								class="custom-control-input" value="4"> <label
								class="custom-control-label" for="customRadioInline124">4점</label>
						</div></td>
					<td><div
							class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="customRadioInline125" name="score12"
								class="custom-control-input" value="5"> <label
								class="custom-control-label" for="customRadioInline125">5점</label>
						</div></td>
				</tr>
			</tbody>
		</table>
		<div class="k-float-right">
			<input type="reset" class="btn btn-warning" value="초기화" /> <input
				type="submit" class="btn btn-success" value="확인" />
		</div>
	</form>
	<script>
		
	</script>
</body>
</html>