package kr.or.ddit.member.controller;

import java.security.SecureRandom;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.creatorCenter.service.IClassService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.utils.SHA512_SaltedHash;
import kr.or.ddit.vo.CreatorVO;
import kr.or.ddit.vo.CreatorVO2;
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
public class MemberUpdateController {

	@Inject
	IMemberService service;
	
	@Inject
	IClassService classService;
	
	//로그인창에서 비밀번호 변경시 사용
	@PostMapping(value="/member/memberUpdatePass.do")
	public String memberUpdate
			(@Validated(UpdateHint.class) @ModelAttribute("member") MemberVO member,
			Errors errors,
			RedirectAttributes redirectAttributes
			, Model model //request대용
			) {
		
		boolean valid = !errors.hasErrors();
		String goPage = null;
		String message = null;
		
		if(member.getMem_pass()!=null) {
			// 비밀번호 소금값 생성
			SecureRandom random = new SecureRandom();
			byte[] saltbyte = new byte[16];
			random.nextBytes(saltbyte);
			String salt = saltbyte.toString();
			System.out.println(salt);
			member.setMem_salt(salt); 
			
			//암호화
			SHA512_SaltedHash saltedHash = new SHA512_SaltedHash();
			String saltedPass = saltedHash.getSHA512(member.getMem_pass(), salt);
			System.out.println(saltedPass);
			member.setMem_pass(saltedPass);
		}
		
		
		
		if(valid) {//검증 통과
			//4. service로직사용
			ServiceResult result = service.modifyMember(member);
			
			//5. scope로 공유
			switch (result) {// ok, fail
			case OK: // 
				goPage = "redirect:/login/login.do";
				break;
			case FAIL: // mypage (서버의 잘못이라 데이터를 그대로 유지시켜야함)
				//6. 뷰설정
				goPage = "/login/forgotPassword";
				System.out.println("여긴가?");
				message = "서버 오류";
				model.addAttribute("message", message);
				break;
			}
		}else {//검증실패시 그 정보를 다시 가지고 간다.
			goPage = "/login/forgotPassword";
		}
		
		return goPage;
	}
	
	@PostMapping(value="/member/creatorchange.do")
	public String creatorchange(@Validated(InsertHint.class) @ModelAttribute CreatorVO2 crea,
		 Errors errors, HttpSession session, Model model) {
		boolean valid = !errors.hasErrors();
		String message = null;
		String goPage = null;
		MemberVO mem = (MemberVO) session.getAttribute("authMember");
		crea.setCrea_email(mem.getMem_email());
		
		if(valid) {
		System.out.println(valid);
		ServiceResult result = classService.createCreator(crea);
		
		switch (result) {// ok, fail
		case OK: // 
			goPage = "redirect:/creatorCenter/classList.do";
			break;
		case FAIL: // mypage (서버의 잘못이라 데이터를 그대로 유지시켜야함)
			//6. 뷰설정
			goPage = "hobby/hobbyMain";
			System.out.println("여긴가?");
			message = "서버 오류";
			model.addAttribute("message", message);
			break;
			}
		
		}
		return goPage;
	}
	
	
	
	
}
