package kr.or.ddit.mypage.controller;

import java.security.SecureRandom;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.utils.SHA512_SaltedHash;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MypageCheckPassController {

	@Inject
	IMemberService memService;

	@Inject
	IAuthenticateService service;

	@GetMapping("/etc/mypagePassCheck.do")
	public String passwordCheck() {
		return "/etc/mypagePassCheck";
	}

	@PostMapping("/etc/mypagePassCheck.do")
	public String passwordCheckForm(@RequestParam(required = true) String mem_pass,
			RedirectAttributes redirectAttributes, HttpSession session) {
		String message = null;
		String goPage = null;
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		String mem_email = member.getMem_email();
		String mem_salt = memService.readSalt(mem_email);
		SHA512_SaltedHash saltedHash = new SHA512_SaltedHash();
		String saltedPass = saltedHash.getSHA512(mem_pass, mem_salt);
		mem_pass = saltedPass;
		Object result = service.authenticate(new MemberVO(mem_email, mem_pass));
		if (result.equals(ServiceResult.INVALIDPASSWORD)) {
			message = "사용자 ID 또는 비밀번호가 일치하지 않습니다.";
			goPage = "redirect:/etc/mypagePassCheck.do";
		} else {
			goPage = "redirect:/etc/mypagePassChange.do";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return goPage;
	}

	@GetMapping("/etc/mypagePassChange.do")
	public String passwordChangeForm() {
		return "/etc/mypagePassChange";
	}

	@PostMapping("/etc/mypagePassChange.do")
	public String passwordChange(@Validated(UpdateHint.class) MemberVO member, HttpSession session, Errors errors,
			RedirectAttributes redirectAttributes, Model model) {
		boolean valid = !errors.hasErrors();
		MemberVO memberVO = (MemberVO) session.getAttribute("authMember");
		String mem_email = memberVO.getMem_email();
		String goPage = null;
		String message = null;
		if (member.getMem_pass() != null) {
			SecureRandom random = new SecureRandom();
			byte[] saltbyte = new byte[16];
			random.nextBytes(saltbyte);
			String salt = saltbyte.toString();
			member.setMem_email(mem_email);
			member.setMem_salt(salt);
			SHA512_SaltedHash saltedHash = new SHA512_SaltedHash();
			String saltedPass = saltedHash.getSHA512(member.getMem_pass(), salt);
			member.setMem_pass(saltedPass);
			System.out.println(saltedPass);
		}
		if (valid) {
			ServiceResult result = memService.modifyMember(member);
			switch (result) {// ok, fail
			case OK:
				goPage = "redirect:/mypage/mypageMain.do";
				message = "변경이 완료되었습니다.";
				model.addAttribute("message", message);
				break;
			case FAIL:
				goPage = "/etc/mypagePassChange";
				message = "서버 오류, 다시 시도하여주십시오.";
				model.addAttribute("message", message);
				break;
			}
		} else {
			goPage = "/etc/mypagePassChange";
		}

		return goPage;
	}
}
