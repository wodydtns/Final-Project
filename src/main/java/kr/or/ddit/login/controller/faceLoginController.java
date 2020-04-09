package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
public class faceLoginController {
	
	@GetMapping(value = "/face/login.do")
	public String doGet(HttpSession session, HttpServletResponse resp) throws IOException {
		String viewName= null;
		if(session.isNew()) {//로그인이 되지 않은것
			viewName = "facebook/facebookLogin";
		}else {
			//authmember외에도 모든 상태정보를 다 지움. 명시적으로 세션 완료
			session.invalidate();
			viewName = "facebook/facebookLogin"; 
		}
		return viewName;
		
	} 
	
	
}