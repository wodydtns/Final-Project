<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<jsp:useBean id="pi" class="kr.or.ddit.vo.PIVO" scope="request" />
<jsp:useBean id="errors" class="java.util.HashMap" scope="request" />  
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
<c:set var="end" value="<%=new Date(new Date().getTime()+(60*60*24*7*1000))%>"/>
<fmt:formatDate value="${end}" pattern="yyyy-MM-dd" var="end"/>
<c:set var="open" value="<%=new Date(new Date().getTime()+(60*60*24*15*1000))%>"/>
<fmt:formatDate value="${open}" pattern="yyyy-MM-dd" var="open"/>
<c:if test="${!empty message }">
	alert("${message}");		    		
</c:if>
<div style="margin-left:300px;">
 <div class="col-lg-12" style="width:50%; float:left;">
                            <div class="card">
                                <div class="card-header">클래스 생성</div>
                                <div class="card-body">
                                    <h5 class="card-title"></h5>
                                    <form class="form-horizontal" id="insertPI" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="crea_email" value="${authMember.mem_email}">
                                    <input type="hidden" name="pi_open" value="${open}">
                                    <input type="hidden" name="pi_cd" value="${pi.pi_cd}">
                                       <div class="line"></div><br>
                                        <div class="form-group row">
                                            <label class="col-sm-4">클래스 카테고리</label>
                                            <div class="col-sm-8 select">
                                                <select class="form-control" name="cate_cd">
                                                	<c:forEach items="${cateList}" var="cate" >
                                                		<option value="${cate.cate_cd}">${cate.cate_nm}</option>
                                                    </c:forEach>
                                                </select></br>
                                            </div>
                                            <label class="col-sm-4">클래스 제목</label>
                                            <div class="col-sm-8" id="title">
                                               <!--  <input name="pi_nm" id="validInput" style="display: none;" type="text" maxlength="40" class="form-control is-valid">
                                                <input name="pi_nm" id="invalidInput" style="display: none;" type="text" maxlength="40" class="form-control is-invalid"> -->
                                                <input name="pi_nm" value="${pi.pi_nm}" id="writeInput" type="text" maxlength="40" class="form-control" placeholder="최대40자">
<!--                                                 <small class="form-text text-muted">최대 40자</small> -->
                                                <form:errors path="pi.pi_nm" element="span" cssClass="error" />
                                                </br>
                                            </div>
                                            
                                            <label class="col-sm-4">클래스 난이도</label>
                                            	<c:forEach items="${levelList}" var="level">
	                                             	 <div class="i-checks ">
		                                             	 	<c:if test="${pi.lod_cd eq level.lod_cd}">
		                                                    <input type="radio" value="${level.lod_cd}" name="lod_cd" checked="checked">
		                                                    <label>${level.lod_nm}</label>
		                                                    </c:if>
		                                                    
		                                             	 	<c:if test="${pi.lod_cd ne level.lod_cd}">
		                                                    <input type="radio" value="${level.lod_cd}" name="lod_cd" >
		                                                    <label>${level.lod_nm}</label>
		                                                    </c:if>
	                                                </div>
                                            	</c:forEach>
                                            	</br>
                                            	 <form:errors path="pi.lod_cd" element="span" cssClass="error" />
                                        </div>
                                        <c:if test="${not empty pi.pi_att.ori_nm }">
                                          <div class="form-group row">
                                         <label class="col-sm-4">기존 커버이미지</label>${pi.pi_att.ori_nm}
 										<form:errors path="pi.cover_img" element="span" cssClass="error" />  
                                         	</div>
                                         </c:if>
                                         <div class="form-group row " > 
	                                         <label class="col-sm-4">커버이미지</label>
	                                        <input type="file" name="cover_img" />
	                                        </br>
                                         </div>
                                         <div class="form-group row">
                                          <label class="col-sm-4">클래스 소개</label>
                                          <textarea id="summernote" name="pi_intro">${pi.pi_intro}</textarea>
                                          </br>
                                          </div>
                                           <form:errors path="pi.pi_intro" element="span" cssClass="error" />
                                         <div class="form-group row">
                                       		 이런 분들이 들으면 좋아요</br></br>
                                         <div style="background-color:#eaecf4;">
			                                         클래스 추천대상 작성법</br></br>
											<small>클래스를 어떤 분들에게 추천하고 싶은지 4가지를 나열해주세요. 첫 2가지는 대부분의 사람에게 적용될만한 범용적인 대상을 추천하고, 나머지 2가지는 구체적인 대상을 추천하는 것이 좋습니다.</br>
											예시) 페이퍼플라워 클래스</br>
											손을 꼬물대며 무언가 만드는 것을 좋아하는 분, 꽃을 좋아하지만, 알러지가 있거나 식물을 잘 관리하며 키우기가 힘겨우신 분 페이퍼플라워를 보고 예쁘다고 생각을 하셨던 분, 다양한 소품을 활용하여 촬영하는 일이 많은 블로거</br>
											</small>
	                                       </div>
                                          <label class="col-sm-4" style="margin-top:60px;">클래스 부가정보</label>
                                          <div class="col-sm-8" style="margin-top:60px;">
                                          <input class="form-control" type="text" name="pi_add_info" placeholder="추천대상" value="${pi.pi_add_info}">
                                          </div>
                                          </div>
                                           <form:errors path="pi.pi_add_info" element="span" cssClass="error" />
                                         <div class="line"></div><br>
                                         <div class="line"></div><br>
                                   	※등록을 누르시면 관리자 승인된 건에 한해 아래 기간동안 사전조사가 이루어 집니다.
                                           <div class="line"></div><br>
                                           <div class="line"></div><br>
                                   <div class="form-group row">
                                         <label class="col-sm-4">사전조사 시작일</label>
                                         <div class="col-sm-8">
                                   				<input type="text" name="pi_start" value="${today}" class="form-control" readonly>
                                         </div>
                                   </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4">사전조사 종료일</label>
                                       <div class="col-sm-8">
                                   			<input type="text" name="pi_end" value="${end}" class="form-control" readonly>
                                       </div>
                                  </div>
                                        <div class="form-group row">
                                            <div class="col-sm-4 offset-sm-2">
                                                <button type="submit" id="save" class="btn btn-warning mb-2"><i class="fas fa-save"></i>등록</button>
                                                <button type="reset" id="cancel" class="btn btn-secondary mb-2"><i class="fas fa-times"></i>취소</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        
                        <div id="assist" style="width:15%; float:left;">
                        	<div style="width:300px; height:150px; border-radius:30px; background-color:#eaecf4; text-align:left; padding-top:10px; padding-left:10px;">
								클래스 정보</br>
								자 우선, 내 클래스의 큰 틀을 잡아봅시다.</br>
								간단해요!</br>
								언제든 돌아와서 수정할 수 있으니까</br>
								일단 시작해볼까요?</br>
                        	</div>
                        	
                        	<div style="margin-top:100px; width:300px;">
								클래스 제목에도 정답이 있고 오답이 있다?</br>
								<a href="https://www.notion.so/f4b0960c7a6e40da92d3cbdcb7a9f5ee" target="_blank">정답이 궁금하다면 클릭!</a>
                        	</div>
                        	
                        	<div style="margin-top:100px; width:300px; height:200px; border-radius:30px; background-color:#eaecf4; text-align:left; padding-top:10px; padding-left:10px;">
							클래스 소개</br>
							제일 중요한 부분이에요! 이것만 넘기면 나머지는 누워서 떡먹기, 식은 죽 먹기!</br>
							클래스를 통해 무엇을 배우게 될지, 완강 후에는 어떻게 활용할 수 있을지를 보여주고 수강생분들께 어필해보세요.</br>
                        	</div>
                        	
                        	<div style="margin-top:400px; width:300px; height:250px; border-radius:30px; background-color:#eaecf4; text-align:left; padding-top:10px; padding-left:10px;">
							클래스 부가정보</br>
							와아- 제일 큰 산을 넘기셨네요!</br>
							수고하셨어요 : )</br>
							</br>
							이번엔 수강생분들과 교감하게 될 부분이에요.</br>
							어떤 분들이 들으면 좋을지 추천하고 클래스</br>
							주제 투표를 받아봅시다.</br>
                        	</div>
                        	
	                        <div style="margin-top:100px; width:300px;">
	                       	 역시 준비되어 있어요!</br>
							<a href="https://www.notion.so/9b80ef4e80ba43e9bc7223b0b165c89f" target="_blank">수요조사 가이드에서 작성법 한 번 더 확인하기</a>
							</div>
                        </div>
                        </div>
<script>


$('#summernote').summernote({
    width: 610,                 // set editor height
    height: 500,                 // set editor height
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
        url : "${cPath}/creatorCenter/piInsert1.do",
        enctype: "multipart/form-data",
        contentType : false,
        processData : false,
        success : function(data) {
           console.log(data);
              //항상 업로드된 파일의 url이 있어야 한다.
           $(editor).summernote("insertImage", data.url);
        },
        error : function(error){
           alert('에러임');
        }
     });
  }
 

    
</script>