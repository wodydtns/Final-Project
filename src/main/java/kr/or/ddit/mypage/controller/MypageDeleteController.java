package kr.or.ddit.mypage.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.ddit.mypage.service.ImypageService;
import kr.or.ddit.vo.EscapeVO;
import kr.or.ddit.vo.MemberVO;

/**
 * @author 작성자명
 * @since 2020. 3. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 26.      작성자명     박재욱             최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
public class MypageDeleteController {
	
	@Inject
	ImypageService mypageService;
	
	@PostMapping("/etc/mypageEscapeReason.do")
	public String escapeReasonInsert(
				EscapeVO escape,
				HttpSession session, Model model
			) {
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		String goPage = "";
		String message ="";
		String mem_email = member.getMem_email();
		escape.setMem_email(mem_email);
		int result = mypageService.deleteEscape(escape);
		if(result >0) {
			goPage = "redirect:/hobby/hobbyMain.do";
			message= "탈퇴 성공";
			model.addAttribute("message", message);
		}else {
			goPage = "redirect:/mypage/mypageMain.do";
			message ="서버 오류, 잠시 후에 시도하세요.";
			model.addAttribute("message", message);
		}
		return goPage;
	}
}
