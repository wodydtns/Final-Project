package kr.or.ddit.login.controller;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @author 작성자명
 * @since 2020. 3. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 18.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
public class HpCheckController {
	
	//문자메세지 보내기
	@RequestMapping(value = "/member/sendMessage.do", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    private void sendMail(HttpSession session, @RequestParam String hpNum) {
		int result = (int)((Math.random()*10000)+1);
		String joinCode = String.valueOf(result);
	    session.setAttribute("joinCode", joinCode);
	        
	    String api_key = "NCSBRGEPBBI4XCXH";
	    String api_secret = "CIURSHEZ3Q04YEFDAHQWDOPPY4HIDYBX";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", hpNum);
	    params.put("from", "01075948109");
	    params.put("type", "SMS");
	    params.put("text", "회원가입 승인번호는"+ joinCode +" 입니다.");
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
        
        }
	
	
	
	@PostMapping(value="/member/checkHpAuthNum.do", produces = "application/json")
	 @ResponseBody
	public Map<String, Object> checkAuthNum(HttpSession session, @RequestParam(required=true) String hp_authNum) {
		String message = null;
		ServiceResult result = null;
		String joinCode = (String) session.getAttribute("joinCode");
		System.out.println(joinCode);
		if(hp_authNum.equals(joinCode)) {//인증번호와 일치할 때
			result = ServiceResult.OK;
			message = "인증 성공";
		}else {
			result = ServiceResult.FAIL;
			message = "인증번호가 일치하지 않습니다.";
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status",result.name());//enum은 이름과 똑같은 값을 가진다.
		resultMap.put("message",message);
		
		return resultMap;
	}
}
