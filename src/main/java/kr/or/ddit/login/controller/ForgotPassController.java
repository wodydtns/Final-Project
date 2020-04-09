package kr.or.ddit.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author 작성자명
 * @since 2020. 3. 12.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 12.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ForgotPassController {

	//비밀번호 찾기 폼
	@GetMapping(value="/login/forgotPass.do")
	public String findPassForm() {
		return "login/forgotPassword";
	}
}
