package kr.or.ddit.mypage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.mypage.service.ImypageService;
import kr.or.ddit.vo.CouponVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MypagePropertyVO;

@RequestMapping("/mypage")
@Controller
public class MypageMainController {
	
	@Inject
	ImypageService service;
	

	
	@GetMapping("mypageMain.do")
	public String readMainList(
			HttpSession session, Model model
			) {
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		MypagePropertyVO mypage = service.readSelectMypageList(member.getMem_email());
		
		model.addAttribute("mypage", mypage);
		return "mypage/mypageMain";
	}
	
	@GetMapping("mypageCouponList.do")
	public String readCouponList(
			HttpSession session, Model model
			) {
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		List<CouponVO> couponList= service.readSelectCouponList(member.getMem_email());
		
		model.addAttribute("couponList", couponList);
		return "mypage/mypageCouponList";
	}
	
	@PostMapping("mypageMain.do")
	public String updateMainList(
			@Validated(UpdateHint.class) @ModelAttribute("mypage") MypagePropertyVO mypage,
			Errors errors,
			RedirectAttributes redirectAttributes, Model model
			) {
		String goPage = null;
		String message = null;
		int result = service.modifyUpdateMemberInfo(mypage);
		switch (result) {// ok, fail
		case 1:
			goPage = "redirect:/mypage/mypageMain.do";
			message = "변경이 완료되었습니다.";
			model.addAttribute("message", message);
			break;
		case 0:
			goPage = "redirect:/mypage/mypageMain.do";
			message = "서버 오류, 다시 시도하여주십시오.";
			model.addAttribute("message", message);
			break;
		}
		return goPage;
	}
}	
