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
<c:if test="${!empty message }">
	alert("${message}");		    		
</c:if>
 <div class="col-lg-12" style="margin:0 auto; width:50%;">
                            <div class="card">
                                <div class="card-header">사전조사 조회</div>
                                <div class="card-body">
                                    <h5 class="card-title"></h5>
                                    <form class="form-horizontal" id="updatePI" >
                                       <div class="line"></div><br>
                                        <div class="form-group row">
                                            <label class="col-sm-4">클래스 카테고리</label>
                                            <div class="col-sm-8 select">
                                            	${pi.cate_nm}
                                            </div>
											<div class="line"></div><br>                                           
											<div class="line"></div><br>                                           
                                            <label class="col-sm-4">클래스 제목</label>
                                            <div class="col-sm-8" id="title">
                                                ${pi.pi_nm}
                                            </div>
                                            
                                      	  <div class="line"></div><br>
                                            <label class="col-sm-4">클래스 난이도</label>
	                                             	 <div class="i-checks">
	                                                    <label>${pi.lod_nm}</label>
	                                                </div>
                                            	 <form:errors path="pi.lod_cd" element="span" cssClass="error" />
										<div class="line"></div><br>
                                        </div>
                                          <div class="form-group row">
                                         <label class="col-sm-4">커버이미지</label>
                                        	${pi.pi_att.ori_nm}
                                         	</div>
                                         <div class="form-group row">
                                          <label class="col-sm-4">클래스 소개</label>
                                           </div>
                                          <div class="form-group row" style="width:auto;">
                                          <div  class="col-sm-12" style="border:1px solid lightgray; width:auto; border-radius:20px; padding:20px;">
         									${pi.pi_intro}
         									</div>
                                          </div>
                                         <div class="form-group row">
                                          <label class="col-sm-4">클래스 부가정보 ex)추천대상 등</label>
                                         ${pi.pi_add_info}
                                          </div>
                                          <div class="form-group row">
                                          </div>
                                        	<div class="line"></div><br>
                                           <div class="line"></div><br>
                                           <div class="line"></div><br>
                                   <div class="form-group row">
                                         <label class="col-sm-4">사전조사 시작일</label>
                                         <div class="col-sm-8">
                                   			${pi.pi_start}
                                         </div>
                                   </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4">사전조사 종료일</label>
                                       <div class="col-sm-8">
                                   			${pi.pi_end}
                                       </div>
                                  </div>
                                        <div class="form-group row">
                                            <div class="col-sm-10 offset-sm-2" style="margin-left:40%;">
                                                <a href="<c:url value='/creatorCenter/piUpdate.do?what=${pi.pi_cd}'/>"><input type="button" style="width:200px;" id="save" class="btn btn-warning mb-2" value="수정" ></a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
