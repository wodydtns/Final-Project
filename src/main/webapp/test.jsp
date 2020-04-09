<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2>Open API - career 검색</h2>
		<form id="searchForm">
			<!-- 업종 -->
			<div class="form-group row">
				<label for="uclist" class="sr-only">업종</label>
				<div class="col-sm-5">
					<select name="uc" class="form-control" id="uclist">
						<option value="" selected="selected">선택</option>
						<option value="A1">서비스업</option>
						<option value="E1">유통,무역업</option>
						<option value="F1">금융,보험업</option>
						<option value="I1">의료,보건업</option>
						<option value="D1">미디어,문화</option>
						<option value="C1">정보통신,IT</option>
						<option value="B1">제조업</option>
						<option value="J1">건설업</option>
						<option value="H1">교육기관</option>
						<option value="G1">공공기관,공기업,협회</option>
					</select>
				</div>
			</div>
			<!-- 직종 -->
			<div class="form-group row">
				<label for="jclist" class="sr-only">업종</label>
				<div class="col-sm-5">
					<select name="jc" class="form-control" id="jclist">
						<option value="" selected="selected">선택</option>
						<option value="A001">경영,기획</option>
						<option value="B001">마케팅,광고</option>
						<option value="C001">전문직</option>
						<option value="M001">의료,보건</option>
						<option value="D001">무역,유통</option>
						<option value="E001">영업,TM,판매</option>
						<option value="N001">연구개발,엔지니어</option>
						<option value="F001">생산,제조</option>
						<option value="G001">건설</option>
						<option value="H001">IT,인터넷</option>
						<option value="J001">서비스</option>
						<option value="I001">디자인</option>
						<option value="K001">미디어</option>
						<option value="L001">교육</option>
					</select>
				</div>
			</div>
			<!--  키워드 -->
			<div class="form-group row">
				<label for="kw" class="col-sm-2 col-form-label">업직종 키워드</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="kw"
						placeholder="키워드 검색">
				</div>
			</div>
			<!--  근무지역 -->
			<div class="form-group row">
				<label for="ac1list" class="sr-only">업종</label>
				<div class="col-sm-5">
					<select name="ac1" class="form-control" id="ac1list">
						<option value="" selected="selected">선택</option>
						<option value="1">서울특별시</option>
						<option value="10">울산광역시</option>
						<option value="11">인천광역시</option>
						<option value="12">전라남도</option>
						<option value="13">전라북도</option>
						<option value="14">제주도</option>
						<option value="15">충청남도</option>
						<option value="16">충청북도</option>
						<option value="2">강원도</option>
						<option value="3">경기도</option>
						<option value="4">경상남도</option>
						<option value="5">경상북도</option>
						<option value="6">광주광역시</option>
						<option value="8">대전광역시</option>
						<option value="9">부산광역시</option>
						<option value="29">북한경제특구</option>
						<option value="25">아시아,중동</option>
						<option value="26">아프리카</option>
						<option value="27">아메리카</option>
						<option value="28">오세아니아</option>
					</select>
				</div>
			</div>
			<!--  고용형태 -->
			<div class="form-group row">
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="ec" id="ec1"
						value="0"> <label class="form-check-label" for="ec1">신입,경력</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="ec" id="ec2"
						value="1"> <label class="form-check-label" for="ec2">신입</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="ec" id="ec3"
						value="8"> <label class="form-check-label" for="ec3">경력</label>
				</div>
			</div>
			<!-- 학력조건 -->
			<div class="form-group row">
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="sc" id="sc1"
						value=""> <label class="form-check-label" for="sc1">전체</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="sc" id="sc2"
						value="1"> <label class="form-check-label" for="sc2">고졸</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="sc" id="sc3"
						value="2" > <label class="form-check-label"
						for="sc3">대학(2,3년) 졸업</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="sc" id="sc4"
						value="3" > <label class="form-check-label"
						for="sc4">대학교(4년) 졸업</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="sc" id="sc5"
						value="4" > <label class="form-check-label"
						for="sc5">석사 졸업</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="sc" id="sc6"
						value="5" > <label class="form-check-label"
						for="sc6">박사졸업</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="sc" id="sc0"
						value="0" > <label class="form-check-label"
						for="sc0">학력무관</label>
				</div>

			</div>
			<!-- 채용조건 -->
			<div class="form-group row">
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="ck" id="ck1"
						value=""> <label class="form-check-label" for="ck1">전체</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="ck" id="ck2"
						value="1"> <label class="form-check-label" for="ck2">정규직</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="ck" id="ck3"
						value="7" > <label class="form-check-label"
						for="ck3">계약직</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="ck" id="ck4"
						value="6" > <label class="form-check-label"
						for="ck4">인턴직</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="ck" id="ck5"
						value="9" > <label class="form-check-label"
						for="ck5">파견직</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="ck" id="ck6"
						value="10" > <label class="form-check-label"
						for="ck6">위촉직</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="ck" id="ck0"
						value="5" > <label class="form-check-label"
						for="ck0">병역특례</label>
				</div>
				<!-- 시간선택제, 대체인력 -> 구분자 없어서 뺌 -->
			</div>
			<div class="form-group row">
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="gubun"
						id="gubun1" value="0"> <label class="form-check-label"
						for="gubun1">모든 채용공고</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="gubun"
						id="gubun2" value="1"> <label class="form-check-label"
						for="gubun2">커리어 채용 속보</label>
				</div>
			</div>
			<input type="submit" value="검색하기" class="btn btn-info" id="btn">
			<input type="reset" value="초기화" class="btn btn-warning">
		</form>
		<table class="table table-bordered" id="tbl1">
			<thead>
				<tr>
					<th>회사명</th>
					<th>공고제목</th>
					<th>업종</th>
					<th>직종</th>
					<th>키워드</th>
					<th>채용조건</th>
					<th>연봉</th>
					<th>공고 시작일</th>
					<th>공고 마감일</th>
					<th>채용공고 지원방법</th>
					<th>지역</th>
					<th>상세내용</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>

	</div>
</body>
<script>
	var searchForm = $("#searchForm");
	var queryString = searchForm.serialize();
	$("#btn").on("click",function(event){
		event.preventDefault();
		console.log(searchForm);
		console.log(queryString);
		$.ajax({
			type:"get",
			url : "<c:url value='/careerapi/careerSearch.do'/>",
			data : queryString,
			dataType:"json",
			success:function(result){
				console.log(result);
			},
			error:function(error){
				console.log(error);
			}
		})
	});
</script>
</body>
</html>