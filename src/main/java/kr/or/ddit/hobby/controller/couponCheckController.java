package kr.or.ddit.hobby.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.hobby.service.IHobbyClassService;
import kr.or.ddit.vo.CouponHaveVO;
import kr.or.ddit.vo.CouponVO;
import kr.or.ddit.vo.MemberVO;

@Controller
public class couponCheckController {

	@Inject
	IHobbyClassService service;
	
	@GetMapping(value="/hobby/hobbyMainCheck.do") 
	public String checkCoupon(@RequestParam(name="what",required=false) 
			int cp_cd
			, String mem_email
			, HttpSession session
			, RedirectAttributes redirectAttributes) {
		
		String view = null;
		String message = null;
		ServiceResult result = null;
		MemberVO memberVO = (MemberVO) session.getAttribute("authMember"); 
//		memberVO.setCp_cd(cp_cd);
		memberVO.setMem_email(mem_email);
		
		try {
			CouponHaveVO selectcou = service.selectCoupon(memberVO);//null이면 exception이 발생하기 때문에 try- catch
			if(selectcou!=null) {
				message = "이미 보유한 쿠폰입니다.";
				view = "redirect:/hobby/hobbyMain.do";
			}else {
				CouponHaveVO couponHave = new CouponHaveVO();
				couponHave.setCp_cd(cp_cd);
				couponHave.setMem_email(memberVO.getMem_email());
				session.setAttribute("couponHave", couponHave);
				view = "redirect:/hobby/couponInsert.do";
			}
			if(memberVO.getMem_email().equals(null)){
				view ="redirect:/login/login.do";
			}
			
		}catch(Exception e) {
			view ="redirect:/login/login.do";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		return view;
		
	
	}
	
}
