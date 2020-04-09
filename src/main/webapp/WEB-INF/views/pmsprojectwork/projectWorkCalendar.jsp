<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="${cPath }/js/moment.js"></script>
<style>
.fc-sun {
	color: #e31b23
}

.fc-sat {
	color: #007dc3
}
</style>

<!-- 프로젝트 리스트 제목, 내용 -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<div>
		<h1 class="h3 mb-2 text-gray-800">캘린더</h1>
		<br>
		<p class="mb-4">프로젝트의 캘린더가 표시됩니다.</p>
	</div>
</div>


<div class="card shadow mb-4">
	<div class="card-body">
		
			<div class="col">
				<div id='calendar'></div>
			</div>
			
		</div>
	</div>



<script>
	
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			plugins : [ 'dayGrid', 'bootstrap' ],
			themeSystem : 'bootstrap',
			buttonText : {
				today : '오늘',
				 prevYear: parseInt(new Date().getFullYear(), 10) - 1,
		         nextYear: parseInt(new Date().getFullYear(), 10) + 1
			},
			locale : 'ko',
			header : {
				left : 'prevYear,nextYear',
				center : 'title',
				right : 'prev,next today'
			},
			eventSources:[{
				events:function(info,successCallback,failureCallback){
					$.ajax({
						url : "${cPath}/pmsProject/projectWorkCalendarData.do",
						method : "get",
						dataType: "json",
						data :{
							start : moment(info.startStr).format('YYYY-MM-DD'),
							end : moment(info.endStr).format('YYYY-MM-DD'),
							proj_cd : ${proj_cd},
							mem_email : "${authMember.mem_email}"
						
						},
						success:function(data){
							successCallback(data);
							console.log(data);
						},
						error :function(error){
							alert('there was an error while fetching events!');
						}
					});
		
				},
			}],
			eventColor: '#fcd703',
			eventClick:function(info){

			    // change the border color just for fun
			    info.el.style.borderColor = 'red';
			},
			eventMouseEnter: function(info) {
			       var e = info.el;
			       e.setAttribute('data-toggle','popover');
			       e.setAttribute('title',info.event.title);
			       $(e).popover().tooltip().css('cursor','pointer');
			},
			eventMouseLeave:function(info){
				var e = info.el;
				$(e).tooltip('hide');
			}
		});
			
		calendar.render();
	});

	
</script>