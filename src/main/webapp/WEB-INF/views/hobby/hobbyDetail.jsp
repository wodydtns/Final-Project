<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:if test="${not empty message }">
	<script  type="text/javascript">
		alert("${message}");
	</script>
</c:if>

<style type="text/css">
#contentpic {
	background-color: lightyellow; 
}
#introduce {
	width: 1500px;
}
#ol{
	padding-left:300px;
	padding-right:300px;
	border:2px solid black;
	
}

/* 커리큘럼폼 */
#curriForm{
	width:200px;
	}
/* 글작성 부분 */
#insertForm {
	width: 1000px;
	padding-left:500px;
}
#buttons{
	padding-left: 270px;
    box-sizing: unset;
}

#buttons2{
	float : right;
	margin-right: 5px;
	margin-top : 5px;
}
#summernote{
	width:700px;
	height:100px;
}
#center_file{
	width:220px;
}
/*댓글작성*/

#table {
	width:720px;
}
 #tablePadding{
 	padding-left:500px; 
 } 
 #repBtn1{
 	margin-left: 570px;
    margin-top: 10px;
 }
 #repBtn2{
    margin-top: 10px;
 }
label[for="hobbyDetail_file"] { display: inline-block; padding: .5em .75em; color: white; font-size: inherit; line-height: normal; vertical-align: middle; background-color: #5232a8; cursor: pointer; border: 1px solid #ebebeb; border-bottom-color: #e2e2e2; border-radius: .25em; margin:5px }
#hobbyDetail_file { /* 파일 필드 숨기기 */ position: absolute; width: 1px; height: 1px; padding: 0; margin: -1px; overflow: hidden; clip:rect(0,0,0,0); border: 0; }




/*플로팅바 css*/
#floatdiv {
	box-shadow: 3px 3px lightgray;
    position: fixed;
    width: 400px;
    height:500px;
    overflow: hidden;
    right: 200px;
    top: 100px;
    background-color: white;
    margin: 0;
    padding: 30px;
}
#floatdiv ul {
  list-style: none;
}
#floatdiv li {
  margin-bottom: 2px;
  text-align: center;
}
#floatdiv a {
  color: #5d5d5d;
  border: 0px;
  text-decoration: none;
  display: block;
}
#floatdiv a:hover,
#floatdiv .menu {
  color: #5d5d5d;
  color: #fff;
}
#floatdiv .menu,
#floatdiv .last {
  margin-bottom: 0px;
}

header.masthead {
    text-align: center;
    color: #fff;
    background-image: url(/JinDam/piImages/${classInfo.temp_nm});
}
</style>


<!-- 커뮤니티에 댓글 작성 -->
<script type="text/javascript">
        
        var sel_files = [];
 
        $(document).ready(function() {
            $(".comm_att").on("change", handleImgsFilesSelect);
        }); 
 
        function handleImgsFilesSelect(e) {
            var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);
 
            filesArr.forEach(function(f) {
                if(!f.type.match("image.*")) {
                    alert("확장자는 이미지 확장자만 가능합니다.");
                    return;
                }
 
                sel_files.push(f);
 
                var reader = new FileReader();
                reader.onload = function(e) {
//                    var img_html = "<img src=\"" + e.target.result + "\" style='width:100px; height:100px;' />";
//                    $(".comm_attatch *").remove();
//                    $(".comm_attatch").append(img_html);
//                     this.prev().prev().append(img_html);
                }
                reader.readAsDataURL(f);
            });
        }
 
    </script>



<!-- 플로팅바 -->
<div id="floatdiv" class="card">
      <div class="single-widget category-widget">
			<h6 class="title">By.${creator.crea_email}</h6><br>
			<h5 class="title">${classInfo.pi_nm}</h5>
			<h6 class="title">${classInfo.cl_start} ~ ${classInfo.cl_end}</h6><br>
			<h5 class="title">${classInfo.cl_fee}원</h5>
			<hr>
			<div>
				<div style="width:50%; display:inline-block; float:left;">
				<img src="../images/im-level-gray.png" style="width:40px; height:40px;"/>
				<small class="title">수강대상</small>
				<h6>${classInfo.pi_add_info}</h6>
				</div>
				<div style="width:50%; display:inline-block; float:left;">
				<img src="../images/im-play-gray.png" style="width:40px; height:40px;"/>
				<small class="title">포함내역</small>
				<h6>콘텐츠이용권</h6>
				</div>
			</div>
		</div><br><br><br>
			<a href="#" id="kakaoPay">
				<p style="width:100%; height:50px; font-size: 20px; font-weight:bold; text-align:center; padding:5px; background-color:orange; color:white;">클래스 신청하기</p>
			</a>
    </div>

<!-- Header -->
<header class="masthead" id="contentpic">
  <div class="container">
    <div class="intro-text">
      <div class="intro-lead-in">
     	<h3 style="max-width:80%;">${classInfo.pi_nm}</h3> 
      </div>
    </div>
  </div>
</header> 
  <!-- 컨텐츠 소개 -->
<section class="page-section" id="services">
  <div class="container">
    <div class="row">
      <div class="col-lg-12 text-center" style="max-width:80%;">
        <h2 class="section-heading text-uppercase">클래스 소개</h2>
        ${classInfo.pi_intro}
      </div>
    </div>
  </div>
</section>

<!-- 제작자 소개 -->
<section class="page-section" id="portfolio">
  <div class="container">
    <div class="row">
      <div class="col-lg-12 text-center" style="max-width:80%;">
        <h2 class="section-heading text-uppercase">제작자 소개</h2>
      </div>
    </div>
    <div class="row" >
        <div class="portfolio-caption" id="introduce" style="max-width:80%;">
           <h6 class="text-muted">${creator.mem_intro }</h6>
        </div>
      </div>
    </div>
</section>

<!-- 커리큘럼 -->
<section class="page-section" id="about">
  <div class="container">
    <div class="row">
      <div class="col-lg-12 text-center">
        <h2 class="section-heading text-uppercase" style="max-width:80%;">커리큘럼</h2>
        <h3 class="section-subheading text-muted" style="max-width:80%;">클래스를 신청하신 분들이 배우고 있는 커리큘럼입니다.</h3>
      </div>
    </div>
    <div class="row" style="margin-right: 800px;">
      <div class="col-lg-12">
		<div class="row" style="max-width:80%;">
       		
       		
       		
       		<div class="col-lg-12" id="curriForm">
       		
       		
<!-- Grid row -->
<div class="row accordion-gradient-bcg d-flex justify-content-center" >

  <!-- Grid column -->
  <div class="col-md-10 col-xl-6 py-5" style="width:700px;">

    <!--Accordion wrapper-->
    <div class="accordion md-accordion accordion-2" id="accordionEx7" role="tablist"
      aria-multiselectable="true" style="width:700px;">

     
<c:forEach items="${curriList}" var="curri" >
	<c:if test="${empty curri.curri_cd2}">
		      <!-- Accordion card -->
		      <div class="card" style="width:700px;">
		
		        <!-- Card header -->
		        <div class="card-header rgba-stylish-strong z-depth-1 mb-1" role="tab" id="heading3" style="width:700px;">
		          <a class="collapsed" data-toggle="collapse" data-parent="#accordionEx7" href="#collapse3"
		            aria-expanded="false" aria-controls="collapse3" style="color:black;">
		            <h5 class="mb-0 white-text text-uppercase font-thin">
		              ${curri.curri_nm} <i class="fas fa-angle-down rotate-icon"></i>
		            </h5>
		          </a>
		        </div>
		        
  	</c:if>
	<c:if test="${not empty curri.curri_cd2}">
		        <!-- Card body -->
		        <div id="collapse3" class="collapse" role="tabpanel" aria-labelledby="heading3"
		          data-parent="#accordionEx7" style="width:700px;">
		          <div class="card-body mb-1 rgba-grey-light white-text" style="width:700px;">
            	${curri.curri_order}. ${curri.curri_nm}
            	<input type="hidden" name="curri_temp_nm" value="${curri.curri_temp_nm}"/>
				<input id="playVideo" type="button" value="동영상재생" class="btn btn-warning" style="color:white;"/>
	          </div>
	        </div>
      <!-- Accordion card -->
    </c:if>
 </c:forEach>    
	      </div>
      
    </div>
    <!--/.Accordion wrapper-->

  </div>
  <!-- Grid column -->

</div>
<!-- Grid row -->
       		
       		</div>
       		
       		
      	</div>
      </div>
    </div>
  </div>
</section>

<!-- 커뮤니티 -->
<section class="page-section" id="team">
  <div class="container">
    <div class="row">
      <div class="col-lg-12 text-center">
        <h2 class="section-heading text-uppercase" style="max-width:80%;">커뮤니티</h2>
        <h3 class="section-subheading text-muted" style="max-width:80%;">클래스 제작자와 수강생들의 커뮤니티 입니다.</h3>
      </div>
    </div>
  </div>
	
	<div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel" id="commu">
		<!-- 컨텐츠제작자 글 -->
		<div class="carousel-inner" role="listbox" id="ol"> 
			 <c:forEach items="${community }" var="comm" begin="0" end="0">
			 <div class="carousel-item active">
			   <p class="d-block img-fluid">${comm.mem_nick } &nbsp;&nbsp;&nbsp; ${comm.commu_date }</p>
			   <p class="d-block img-fluid">${comm.commu_cont } <br><br><br><br></p>
			 </div>
			 </c:forEach>
			 <c:forEach items="${community }" var="comm" begin="1">
			 <div class="carousel-item">
			   <p class="d-block img-fluid">${comm.mem_nick } &nbsp;&nbsp; ${comm.commu_date }</p>
			   <p class="d-block img-fluid">${comm.commu_cont } <br><br><br><br></p>
			 </div>
			 </c:forEach>
		</div>
	</div>
	
	
	<form method="post" enctype="multipart/form-data" id="insertForm" action="<c:url value='/hobby/hobbyDetailInsert.do'/>">
	<input type="hidden" name="commu_cd" value="${commReply.commu_cd }"/>
	<!-- <h4>게시글 작성</h4> -->
	<div class="form-group row" style="margin-top: 5px;">
			<label for="inputTitle" class="col-sm-2 col-form-label" ></label>
			
			<div class="col-sm-10">
				 
			</div>
		</div>
<%-- 	<textarea name="commu_cont"  required="required" id="summernote">${commReply.commu_cont }</textarea> --%>
	<div id="buttons">		
		<input type="file" name="hobbyDetail_file" id="hobbyDetail_file" >
		<!-- <button type="submit" id="confirm" class="btn btn-success">작성</button> -->
	</div>
	</form>
	<br><br><br>


   <div class="row">
      <div class="col-lg-12" id="tablePadding">
        	<c:forEach items="${communityList}" var="communityList">
        	<br>		
	        <table class="table table-bordered" id="table">
        	<tbody id="listBody">
        		<tr>
        			<td ><img class="img-profile rounded-circle" src="../images/login/user.jpg" style="width:30px; height:30px;">${communityList.mem_nick} </td>
        			<td colspan="2">${communityList.commu_cont}</td>
        			<td>${communityList.commu_date}</td>
        		</tr>
        		<c:forEach items="${communityReplyList }" var="communityReplyList">
        			<c:if test="${communityList.commu_cd eq communityReplyList.commu_cd}">
		        		<tr>
		        			<td ><img class="img-profile rounded-circle" src="../images/login/user.jpg" style="width:30px; height:30px;">${communityReplyList.mem_nick }</td>
		        			<c:if test="${not empty communityReplyList.comr_temp_nm}">
		        			<td colspan="2"><img src="/JinDam/replyImages/${communityReplyList.comr_temp_nm }" style="width:80px; height:80px;">
			        			${communityReplyList.comr_comm_cont }</td>
			        		</c:if>	
			        		<c:if test="${empty communityReplyList.comr_temp_nm}">
			        		<td colspan="2">${communityReplyList.comr_comm_cont }</td>
			        		</c:if>	
		        			<td>${communityReplyList.comr_comm_date }</td>
		        		</tr>
        			</c:if>


        		</c:forEach>
        			<tr>
        			<td colspan="4">
        				<div>
	        				<div style="width:100%;">
	        					<form action="${cPath}/reply/replyInsert.do?what=${communityList.cl_cd}" method="post" enctype="multipart/form-data">
			        				<div class="comm_attatch"></div>
			        				<p style="font-weight:bold;">댓글 작성</p>
			        				<textarea style="border-radius:30px; background-color:#f0f5f3;" name="comr_comm_cont" placeholder="내용 200자 미만" class="form-control" ></textarea>
									<br><br>
									<input type="file" name="reply_img" class="comm_att" accept="image/*">
									<input type="hidden" name="commu_cd" value="${communityList.commu_cd}">
									<button type="submit" class="btn btn-dark">작성</button>
								</form>
	        				</div>
						</div>
					</td>
					</tr>
        		
        	</tbody>
        </table>
        	
        	</c:forEach>
      </div>
    </div>
</section>
<form id="loginForm" action="<c:url value='/login/login.do'/>" method="get"></form>



	<script>
		  $('#summernote').summernote({
			  width: 700,
			  height: 150,                 // set editor height
			  minHeight: null,             // set minimum height of editor
			  maxHeight: null,
			  lang : "ko-KR",
			  placeholder: "최대 2048자까지 쓸 수 있습니다.",
			  callbacks:{
				  onImageUpload: function (files) {
					  console.log(files);
					uploadSummernoteImageFile(files[0],this);
				}
			  }
		  });
		  
		  function uploadSummernoteImageFile(file,editor){
			  data = new FormData();
				data.append("file", file);
				$.ajax({
					data : data,
					type : "POST",
					url : "${cPath }/hobby/hobbyDetailImageUpload.do",
					enctype: 'multipart/form-data',
					contentType : false,
					processData : false,
					success : function(data) {
						console.log(data);
		            	//항상 업로드된 파일의 url이 있어야 한다.
						$(editor).summernote('insertImage', data.url);
					},
					error : function(error){
						alert('에러임');
					}
				});
			}
		  
	  	var confirm = $("#confirm");
		var modify = $("#modify");
		var insertForm = $("#insertForm");
		
		$(function() {
			$("#confirm").on("click",	function() {
				confirm.empty();
				confirm.append('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>');
				confirm.append('<span class="sr-only">Loading...</span>');
			});
							
			$("#modify").on("click",function() {
				modify.empty();
				modify.append('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>');
				modify.append('<span class="sr-only">Loading...</span>');
			});
		});
  </script>
  
  
<script>

// 댓글등록
// let replyForm = $("#replyForm").on("submit", function(event){
// 	event.preventDefault();
// 	let action = $(this).attr("action");
// 	var formData = new FormData($("#replyForm").get(0));
	
// 	// FormData Log
// 	var object = {};
// 	formData.forEach(function(value, key){
// 	    object[key] = value;
// 	});
// 	console.log(object);
	
// 	$.ajax({
//         method: "POST",
//         url: action,
//         data: formData,
//         dataType:"json",
// 			processData:false,
//         enctype: 'multipart/form-data',
// 			contentType:false,
// 			success:function(resp){
// 				if(resp.success){
// 					replyForm.get(0).reset();
// 					searchForm.submit();
// 				}else if(resp.message){
// 					alert(resp.message);
// 				}else{
// 					alert(JSON.stringify(resp.errors));
// 				}
// 			},
// 			error:function(errorResp){
// 				alert(errorResp.status+", "+errorResp.responseText);
// 			}
//     });
// 	return false;
// }).on("reset", function(){
// 	replyForm.attr("action", INSERTURL);
// });	
</script>

<!-- 동영상재생 -->
<script>
var loginForm = $("#loginForm");
$("#playVideo").on("click",function(){
	//결제한회원인지 확인
	$.ajax({
		url : "<c:url value='/hobby/hobbyDetail.do'/>",
		method : "GET",
		data : {"what" : "${classInfo.cl_cd}"},
		dataType: "json",
		success : function(resp) {
			console.log(resp);
			if(resp.status=="NULLSESSION"){
				alert("로그인 후 이용가능합니다.");
				loginForm.submit();
			}else if(resp.status=="EXIST"){
				   var tmpnm = $("#playVideo").prev().val();
				   console.log(tmpnm);
				   var url = "<c:url value='/hobby/playVideo.do?what='/>"+tmpnm;
				   var name = $(this).parent().prev().val();
				   var option = "width = 1000, height = 600, top = 100, left = 200, location = no"
				   window.open(url, name, option);
			}else if(resp.status=="NOTEXIST"){
				alert("결제 후 재생이 가능합니다.");
			}	
		},
		error : function(error){
			alert('에러발생');
		}
	});

});




//클래스 신청하기 눌렀을 때
$("#kakaoPay").on("click",function(){

	$.ajax({
		url : "<c:url value='/hobby/hobbyDetail.do'/>",
		method : "GET",
		data : {"what" : "${classInfo.cl_cd}"},
		dataType: "json",
		success : function(resp) {
			console.log(resp);
			if(resp.status=="NULLSESSION"){
				alert("로그인 후 이용가능합니다.");
				loginForm.submit();
			}else if(resp.status=="EXIST"){
				alert("이미 결제한 클래스입니다."); 
				return false;
			}else if(resp.status=="NOTEXIST"){
				window.open(
					"<c:url value='/kakaoPay.do'>
					<c:param name='what' value='${classInfo.cl_cd}' />
					<c:param name='cname' value='${classInfo.pi_nm}' />
					<c:param name='who' value='${authMember.mem_email}' />
					<c:param name='amt' value='${classInfo.cl_fee}' />
					<c:param name='tmp' value='${classInfo.temp_nm}' />
					</c:url>","팝업창","width=400,height=700,top=200,left=800"); 
				return false;
			}	
		},
		error : function(error){
			alert('에러발생');
		}
	});

	
});







</script>
