package kr.or.ddit.creatorCenter.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.creatorCenter.service.IPIService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PIVO;

@Controller
@RequestMapping("/creatorCenter/piDelete.do")
public class PIDeleteController {
	
	@Inject
	IPIService service;

	@GetMapping
	public String doget(@RequestParam(name="what",required=true) String pi_cd
			, RedirectAttributes redirectAttributes,HttpSession session) {
		PIVO pi = new PIVO();
		pi.setPi_cd(Integer.parseInt(pi_cd));
		ServiceResult result = service.removePi(pi);
		String viewName = null;
		String message = null;
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		switch (result) {
			case OK:
				//wait , ing , end 뷰
				viewName = "redirect:/creatorCenter/pi_wait_List.do?what="+member.getMem_email();
				break;
			default:
				message = "서버 오류 쫌따 다시.";
				viewName = "redirect:/creatorCenter/classList.do?what="+member.getMem_email();
				break;
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName; 
				
	}
}
