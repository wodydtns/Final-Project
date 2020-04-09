<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<style>
	textarea{
		width: 1000px;
	}
	
	#content{
	}
</style>

<div id="content">
<form action="<c:url value='/creatorCenter/creatorInsert.do'/>" method="post">
<input type="hidden" name="crea_email" value="${authMember.mem_email}">
<h1 class="h4 text-gray-900 mb-4">크리에이터 전환하기</h1>
<h3 class="h4 text-gray-900 mb-4"> 
사랑하는 일로
매달 연금 타가세요

단 한 번의 클래스 제작으로 지속적인 수익을
창출할 수 있어요. 사랑하는 일을 하면서요!</h3>

<h2 class="h4 text-gray-900 mb-4">소비자들의 관심을 사로잡을 수 있도록 나의 경험 등을 자세히 기재해주세요!</h2>
  <div class="form-group row">
  	<textarea class="form-group row" name="mem_intro">
  	
  	</textarea>
  	<form:errors path="creator.mem_intro" element="span" cssClass="error"/> 
  </div>
   <button type="submit" class="btn btn-warning mb-2">제출하기</button>
</form>  
</div>  
