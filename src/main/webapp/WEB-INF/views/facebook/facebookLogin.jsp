<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 11.      김혜정      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Facebook Login JavaScript Example</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
</head>
<body>
<form id="checkForm2">
<input type="hidden" name="email"/>
</form>
<form id="insertForm2" action="<c:url value='/member/snsInsert.do'/>" method="post">
<input type="hidden" name="mem_email"/>
</form>
<form id="loginForm2" action='<c:url value='/login/snsLogin.do'/>' method="post">
<input type="hidden" name="email2"/>
</form>
<div class="fb-login-button" data-width="" data-size="large"
		data-button-type="login_with" data-layout="default"
		data-auto-logout-link="false" data-use-continue-as="false"
		scope="public_profile,email" onlogin="checkLoginState();" style="width:300px; height:60px; margin-top:20px;"></div>
<div id="status">
</div>
</body>
<script>

  function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
    console.log('statusChangeCallback');
    console.log(response);                   // The current login status of the person.
    if (response.status === 'connected') {   // Logged into your webpage and Facebook.
    	testAPI();  
    } else {                                 // Not logged into your webpage or we are unable to tell.
    }
  }


  function checkLoginState() {               // Called when a person is finished with the Login Button.
    FB.getLoginStatus(function(response) {   // See the onlogin handler
      statusChangeCallback(response);
    });
  }


  window.fbAsyncInit = function() {
    FB.init({
      appId      : '193298191917805',
      cookie     : true,                     // Enable cookies to allow the server to access the session.
      xfbml      : true,                     // Parse social plugins on this webpage.
      version    : 'v6.0'           // Use this Graph API version for this call.
    });


    FB.getLoginStatus(function(response) {   // Called after the JS SDK has been initialized.
      statusChangeCallback(response);        // Returns the login status.
    });
  };

  
  (function(d, s, id) {                      // Load the SDK asynchronously
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/ko_KR/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

 var email;
  function testAPI() {// Testing Graph API after login.  See statusChangeCallback() for when this call is made.
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me',  {fields: 'email'}, function(response) {
      console.log('Successful login for: ' + response.name);
      console.log('Successful login for: ' + response.email);
      console.log('Successful login for: ' + response);
      $("input[name='email']").val(response.email);
      $("#checkForm2").submit();
    });
  }
  
  function logout() {
  FB.logout(function(response) {
	  //https://www.facebook.com/logout.php 얘호출하기
	});
  };
</script>
<script type="text/javascript">
var loginForm = $("#loginForm2");
$("#checkForm2").on("submit",function(event){
	   event.preventDefault(); 
		let emailInputVal =   $("input[name='email']").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/member/checkId.do",
			data : {
				email : emailInputVal
			},
			method : "post",
			dataType : "json", 
			success : function(resp) { 
				if(resp.status=="PKDUPLICATED"){
					$("input[name='email2']").val(emailInputVal);
					console.log("test");
					$("#loginForm2").submit();
				}else{//중복되지않을때
					$("input[name='mem_email']").val(emailInputVal);
					$("#insertForm2").submit();//insert
				}
			},
			error : function(xhr) {
				alert("에러");
				console.log(xhr.status)
		}
		});
		
	});	
</script>
</html>