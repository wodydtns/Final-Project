package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.utils.SHA512_SaltedHash;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.MemberVO;

/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 *      </pre>
 */

@Controller
public class MemberInsertController {

	@Inject
	WebApplicationContext context;
	ServletContext application;

	@PostConstruct
	private void init() { // 필요한 모든것들이 주입된 후에 호출
		application = context.getServletContext();
	}

	@Inject
	IMemberService service;

	//이메일 회원가입 폼
	@GetMapping(value = "/member/emailInsert.do")
	public String emailInsertForm() {
		return "login/memberForm";
	}
	
	//이용약관폼
	@GetMapping(value="/member/clause.do")
	public String clauseForm() {
		return "login/clause";
	}
	
	//개인정보 수집 및 이용에 대한 안내폼
	@GetMapping(value="/member/private.do")
	public String privateForm() {
		return "login/private";
	}
	
	//이메일 회원가입
	@PostMapping(value = "/member/emailInsert.do")
	public String emailInsert(@Validated(InsertHint.class) @ModelAttribute("member") MemberVO member, Errors errors,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException, NoSuchAlgorithmException {

		System.out.println("여기까지오나?1");

		boolean valid = !errors.hasErrors();
		System.out.println(errors.getAllErrors());
		String goPage = null;
		String message = null;
		ServiceResult result = null;

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
		
		
		
		if (valid) {
			result = service.createMember(member);
			System.out.println("여기까지오나?2");

			switch (result) {
			case OK:
				goPage = "login/login";
				System.out.println("여기까지오나?3");
				break;
			default: // FAIL
				message = "실행 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.";
				goPage = "login/memberForm";
				break;
			}
		} else {
			goPage = "login/memberForm";
		}

		redirectAttributes.addFlashAttribute("message", message);// request에 저장

		return goPage;

	}

	//sns회원가입 insert(아이디,가입일자,탈퇴여부n)
	@PostMapping(value = "/member/snsInsert.do")
	public String snsInsert(HttpSession session,
			@Validated(InsertHint.class) @RequestParam(required = true) String mem_email,
			@ModelAttribute("member") MemberVO member, Errors errors, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException {
		member.setMem_email(mem_email);
		System.out.println("여기까지오나?1");
		boolean valid = !errors.hasErrors();
		String goPage = null;
		String message = null;
		ServiceResult result = null;
		if (valid) {
			result = service.createMember(member);
			System.out.println("여기까지오나?2");
			switch (result) {
			case OK:
				session.setAttribute("email", member.getMem_email());
				goPage = "redirect:/member/nickHpInsert.do";
				break;
			default: // FAIL
				System.out.println("에러");
				message = "실행 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.";
				goPage = "login/login";
				break;
			}
		} else {
			goPage = "login/login";
		}

		redirectAttributes.addFlashAttribute("message", message);// request에 저장

		return goPage;

	}

	//sns회원가입 insert 후 nickname, hp insert 받는 폼
	@GetMapping(value = "/member/nickHpInsert.do")
	public String nickHpInsertForm() {
		return "login/snsNickHp";
	}

	//sns회원가입 insert 후 nickname, hp insert
	@PostMapping(value = "/member/nickHpInsert.do")
	public String nickHpInsert(@Validated(UpdateHint.class) String mem_nick, @Validated(UpdateHint.class) String mem_hp,
			@ModelAttribute("member") MemberVO member, Errors errors, RedirectAttributes redirectAttributes, HttpSession session)
			throws IllegalStateException, IOException {

		boolean valid = !errors.hasErrors();
		String goPage = null;
		String message = null;
		ServiceResult result = null;
		if (valid) {
			result = service.modifyMemberSns(member);

			switch (result) {
			case OK:
				MemberVO auth = service.readMember(member.getMem_email());
				session.setAttribute("authMember", auth);
				goPage = "redirect:/hobby/hobbyMain.do"; // main페이지로 이동
				break;
			default: // FAIL
				message = "실행 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.";
				goPage = "login/snsNickHp"; // nick , hp 입력폼
				break;
			}
		} else {
			System.out.println("에러있음");
			goPage = "login/snsNickHp";// nick , hp 입력폼
		}

		redirectAttributes.addFlashAttribute("message", message);// request에 저장

		return goPage;

	}
	
	
	

}
