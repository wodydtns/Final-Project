package kr.or.ddit.login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.login.service.MailService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
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
public class IdCheckController{
	@Inject
	IMemberService service;
	
	@Inject
	private MailService mailService;
	
	//아이디 중복확인
	@PostMapping(value="/member/checkId.do", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody //마샬링과정에서 잭슨을 쓴다.
	public Map<String, Object> checkId(@RequestParam(required=true) String email) {
		
		System.out.println(email);
		String message = null;
		ServiceResult result = null;

		try {
			MemberVO savedMember = service.readMember(email);//null이면 exception이 발생하기 때문에 try- catch
			result = ServiceResult.PKDUPLICATED;
			message = "이미 존재하는 이메일입니다.";
		}catch(UserNotFoundException e) {
			result = ServiceResult.OK; 
			message = "사용 가능";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status",result.name());//enum은 이름과 똑같은 값을 가진다.
		resultMap.put("message",message);
		System.out.println(resultMap);
		System.out.println("아이디 중복확인 끝");
		return resultMap;
	}
	
	//이메일 인증번호 보내기
	@RequestMapping(value = "/member/sendMail.do", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    private boolean sendMail(HttpSession session, @RequestParam String email) {
		System.out.println(email);
        int randomCode = new Random().nextInt(10000) + 1000;
        String joinCode = String.valueOf(randomCode);
        session.setAttribute("joinCode", joinCode);
 
        String subject = "회원가입 승인 번호 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("회원가입 승인번호는 ").append(joinCode).append(" 입니다.");
        return mailService.send(subject, sb.toString(), "ddit05050814@gmail.com", email);
        }
	
	
	//인증번호 체크하기
	@PostMapping(value="/member/checkAuthNum.do", produces = "application/json")
	 @ResponseBody
	public Map<String, Object> checkAuthNum(HttpSession session, @RequestParam(required=true) String authNum) {
		String message = null;
		ServiceResult result = null;
		String joinCode = (String) session.getAttribute("joinCode");
		System.out.println(joinCode);
		if(authNum.equals(joinCode)) {//인증번호와 일치할 때
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
