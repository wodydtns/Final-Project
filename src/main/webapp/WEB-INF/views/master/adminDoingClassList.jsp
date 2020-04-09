<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link href="${cPath }/css/master/assets/vendor/fullcalendar/packages/core/main.css" rel="stylesheet" type="text/css" />
<link href="${cPath }/css/master/assets/vendor/fullcalendar/packages/daygrid/main.css"  rel="stylesheet" type="text/css"  />
<link href="${cPath }/css/master/assets/vendor/fullcalendar/packages/bootstrap/main.css" rel="stylesheet" type="text/css" />
<link href="${cPath }/css/master/assets/vendor/fullcalendar/packages/all.css" rel="stylesheet" type="text/css">
<link href="${cPath }/css/master/assets/vendor/fullcalendar/packages/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${cPath }/css/master/assets/vendor/fullcalendar/packages/list/main.css" rel="stylesheet" />

<script src="${cPath }/css/master/assets/vendor/fullcalendar/packages/core/main.js"></script>
<script src="${cPath }/css/master/assets/vendor/fullcalendar/packages/daygrid/main.js"></script>
<script src="${cPath }/css/master/assets/vendor/fullcalendar/packages/bootstrap/main.js"></script>
<script src="${cPath }/css/master/assets/vendor/fullcalendar/packages/core/locales/es.js"></script>
<script src="${cPath }/css/master/assets/vendor/fullcalendar/packages/list/main.js"></script>

<c:if test="${not empty message }">
	<script>
		confirm("${message}");
	</script>
</c:if>

<script>

      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
        	 plugins: [ 'list' ],
        	 defaultView: 'listWeek',
        	 noEventsMessage : '검색할 클래스가 없습니다.',
	        locale:'ko',
	        
	        views: {
	            listDay: { buttonText: '일별' },
	            listWeek: { buttonText: '주별' },
	            listMonth: { buttonText: '월별' }
	          },

	          header: {
	            left: 'title',
	            center: '',
	            right: 'listDay,listWeek,listMonth'
	          },
	        events:[
	        	<c:forEach items="${doingBoardList }" var="doing" varStatus="status" >
	        	{
	        		title:"${doing.pi_nm }",
	        		start : "${doing.cl_start }",
	        		end :"${doing.cl_end }"
	        		
	       		},
	        </c:forEach>
	        	
	        ],
	              	
        });

        calendar.render();
      });
	
    </script>
<!--  <style>
    	.fc-right .fc-prev-button, .fc-right .fc-next-button{
    background-color: #b1d583;
    background-image: none;
    // 버튼 색 바꾸기
}
    </style> -->

<div class="col-md-6">

	<div class="card">
		<div class="content">
			<div class="head">
				<h4 class="mb-0">진행중인 클래스</h4>
			</div>
			<div id='calendar'></div>
		</div>
	</div>
</div>


