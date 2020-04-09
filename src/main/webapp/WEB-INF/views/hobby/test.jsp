<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>동영상 재생</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link href="https://vjs.zencdn.net/7.6.6/video-js.css" rel="stylesheet" />
<!-- Fantasy -->
<link href="https://unpkg.com/@videojs/themes@1/dist/fantasy/index.css"
	rel="stylesheet">

<script src="https://vjs.zencdn.net/7.6.6/video.js"></script>
<!-- If you'd like to support IE8 (for Video.js versions prior to v7) -->
<script src="https://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>
</head>
<style>

#vod {
	.video-js .vjs-time-control{display:block;}
    .video-js .vjs-remaining-time{display: none;}
}
#hole {
	width : 500px;
}
/* .vjs-layout-tiny	{width:210   px;    } */
/* .vjs-layout-x-small	{width:320   px;  } */
/* .vjs-layout-small	{width:425   px;  } */
/* .vjs-layout-medium	{width:768   px;  } */
/* .vjs-layout-large	{width:1440  px;  } */
/* .vjs-layout-x-large	{width:2560  px; } */
/* .vjs-layout-huge	{width:2561  px;      } */
.p-2{
	margin-right:10px;
}
#hole{
	padding:0%;
	width:100%;
	height:100%;
}
.form-group{
	padding-left:20px;
	margin-right:-20px;
	width: 100%;
	height:100%;
}

</style>
<body>

	<div id="hole">
<!-- 	<div class="d-flex"> -->
<!-- 		<div class="mr-auto p-2"> -->
		
<!-- 		</div> -->
<!-- 		<div class="p-2"> -->
<!-- 		<button class="btn btn-primary video">이전 영상</button> -->
<!-- 		<button class="btn btn-success video">다음 영상</button> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<div class="form-group row">
	

	<video id="vod" class="video-js vjs-theme-city vjs-big-play-button vjs-big-play-centered"/> 
	</div>
	
	
	</div>
	<script type="text/javascript">
	// 비디오 출력 부분 
	//poster : 썸네일  

	 var myPlayer =videojs("vod",
		     {
		 	 "controls":true, 
		     "autoplay":true, 
		     "preload":"auto",
		     "loop":false,
		     "width": 1000,
		     "height": 600,
		     "playbackRates": [0.5, 1, 1.5, 2],
		     
		     "userActions":{
		    	 hotkeys:{
		    		 fullscreenKey:function(e){
		    			 return (e.which===13);
		    		 }
		    	 }
		     },
		     currentTimeDisplay : true
		     });
	  videojs("vod").ready(function(){
	   myPlayer.src({ type: "video/mp4", src: "/JinDam/classVideos/${temp_nm}"
		   });
	   });
	  var onPlay = function(){
	   console.info("on Play");
	  };
	  myPlayer.on("play",onPlay);
	  
	</script>
</body>
</html>