package kr.or.ddit.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 13.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
public class NickCheckController {
	@Inject
	IMemberService service;
	
	@PostMapping(value="/member/checkNick.do", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody //마샬링과정에서 잭슨을 쓴다.
	public Map<String, Object> checkNick(@RequestParam(required=true) String nick) {
		String message = null;
		ServiceResult result = null;

		try {
			MemberVO savedMember = service.readNickMember(nick);//null이면 exception이 발생하기 때문에 try- catch
			result = ServiceResult.PKDUPLICATED;
			message = "이미 존재하는 닉네임입니다.";
		}catch(UserNotFoundException e) {
			result = ServiceResult.OK; //property명으로 가져가야함
			message = "사용가능";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status",result.name());//enum은 이름과 똑같은 값을 가진다.
		resultMap.put("message",message);
		return resultMap;
		
	}
}
