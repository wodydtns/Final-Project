<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2020. 3. 11.      김혜정      최초작성
* Copyright (c) 2020 by DDIT All right reserved
 --%>  
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>네이버로그인</title>
  </head>
  <body>
  <%
    String clientId = "ZEGZpfWcSQCDHquvdWxi";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost/JinDam/naver/callback.do", "UTF-8");//콜백uri
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
  <a href="<%=apiURL%>" onclick="window.open(this.href, '_blanck', 'width=600, height=400'); return false"><img height="50" width="220" src="../images/login/네이버 아이디로 로그인_완성형_Green.PNG"/></a>
  
  
  </body>
</html>

