package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.SHA512_SaltedHash;
import kr.or.ddit.utils.CookieUtils.TextType;
import kr.or.ddit.vo.MemberVO;
/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자               수정내용
 * --------     --------    ----------------------
 * 2020.03.12	김혜정		최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
public class LoginController {
	
	private WebApplicationContext context; //ApplicationContext가 아닌 WebApplicationContext
	private ServletContext application;
	
	@Inject //setter injection을 통해 이값을 넣어준다.
	public void setContext(WebApplicationContext context) {
		this.context=  context;
		application = context.getServletContext();
	}
	
	@Inject
	IMemberService memService;
	
	@Inject
	IAuthenticateService service;
	
	//로그인화면 출력
	@GetMapping(value="/login/login.do")
	public String loginForm() {
		return "login/login";
	}
	
	//이메일 로그인
	@PostMapping(value="/login/login.do")
	public String mailLogin(@RequestParam(required=true) String mem_email
			,@RequestParam(required=true) String mem_pass
			,@RequestParam(required=false) String idSave
			,HttpServletResponse resp
			,HttpSession session
			, RedirectAttributes redirectAttributes
		) throws IOException { 

		String msg  = null;
	String goPage = null;
	
	try {
	System.out.println("설마 여기타니?");
	//파라미터 비번+salt값 암호화해서 mem_salt 컬럼과 비교하기
	String mem_salt = memService.readSalt(mem_email);
	//암호화
	SHA512_SaltedHash saltedHash = new SHA512_SaltedHash();
	String saltedPass = saltedHash.getSHA512(mem_pass, mem_salt);
	System.out.println(saltedPass);
	mem_pass = saltedPass; //mem_pass에 값 할당

	
	Object result = service.authenticate(new MemberVO(mem_email, mem_pass));
	
	if(result instanceof MemberVO) {//result가 memberVO 생성자 함수를 사용하였는지 확인
		Cookie idCookie = CookieUtils.createCookie("idCookie", mem_email, application.getContextPath()+"/", TextType.PATH);
		int maxAge = 0; //아이디저장 안했을때
		
		if("save".equals(idSave)) { //idsave는 체크하지않으면 value가null
			maxAge = 60*60*24*4;
		}
		idCookie.setMaxAge(maxAge);
		resp.addCookie(idCookie);
		
		session.setAttribute("authMember", result);
		
		if(mem_email.equals("admin")) {
			goPage = "redirect:/master/adminStats.do";
		}else {
			goPage = "redirect:/hobby/hobbyMain.do"; 
		}
		
	}else {//실패, 서버에서 사용하기 때문에 서블릿을 거칠필요가 없음. jsp사용
		
		if(result.equals(ServiceResult.INVALIDPASSWORD)) {
			msg = "사용자 ID 또는 비밀번호가 일치하지 않습니다.";
		}else if(result.equals(ServiceResult.REMOVED)) {
			msg = "탈퇴한 회원입니다.";
		}
		
		goPage = "redirect:/login/login.do"; 
		
	}//if end
	
	}catch (UserNotFoundException e) {//예외 직접처리
		msg  = "사용자 ID 및 비밀번호가 일치하지 않습니다";
		goPage = "redirect:/login/login.do";
	}//try ~ catch end
	
	redirectAttributes.addFlashAttribute("message", msg);
	return goPage;
	}
	
	@PostMapping(value="/login/snsLogin.do")
	public String snsLogin(@RequestParam(name="email2",required=true) String email
			,HttpSession session) {
		System.out.println("오나??????");
		String goPage = null;
		MemberVO member = memService.readMember(email);
		if(member.getYn_code().equals("YN_A01")) {
			String msg = "탈퇴한 회원입니다.";
			goPage = "redirect:/login/login.do"; 
		}else {
			if(member.getMem_nick()==null && member.getMem_hp()==null) {//닉네임 휴대폰번호를 안받았었다면
				goPage = "redirect:/member/nickHpInsert.do";
			}else {
				session.setAttribute("authMember", member);
				goPage = "redirect:/hobby/hobbyMain.do";
			}
		}
		
		return goPage;
	}
	
}
