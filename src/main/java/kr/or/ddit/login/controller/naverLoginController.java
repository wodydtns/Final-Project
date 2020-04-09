package kr.or.ddit.login.controller;

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
public class naverLoginController {

	
	@GetMapping(value = "/naver/login.do")
	public String test1(HttpSession session) {
		return "naver/login";

	}

	@GetMapping(value = "/naver/callback.do")
	public String test2(HttpSession session) {
		return "naver/callback";

	}
}
