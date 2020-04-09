package kr.or.ddit.hobby.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.junit.internal.runners.ErrorReportingRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hobby.service.IHobbyClassService;
import kr.or.ddit.vo.CouponHaveVO;
import kr.or.ddit.vo.MemberVO;

@Controller
public class couponInsertController {

	@Inject
	IHobbyClassService service;
	
	@GetMapping(value="/hobby/couponInsert.do")
	public String insert(
			HttpSession session
			, RedirectAttributes redirectAttributes
			, Model model) {
		
		String viewName = null;
		String message = null;
		ServiceResult result = null;
		
			CouponHaveVO couponhave = (CouponHaveVO) session.getAttribute("couponHave");
			result = service.insertCoupon(couponhave);
			switch(result) {
			case OK:
				System.out.println("성공");
				message = "쿠폰받기 성공.";
				viewName = "redirect:/hobby/hobbyMain.do";
				break;
			case FAIL: //insert실패 왜 안되지
				System.out.println("실패");
				message = "다시 시도.";
				viewName = "redirect:/hobby/hobbyMain.do";
				break;
			}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}
