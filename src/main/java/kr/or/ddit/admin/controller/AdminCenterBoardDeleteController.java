package kr.or.ddit.admin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.service.IAdminCsCenterService;
import kr.or.ddit.vo.CsCenterVO;

@Controller
public class AdminCenterBoardDeleteController {
	@Inject
	IAdminCsCenterService service;

	@GetMapping(value = "/master/centerboardDelete.do")
	public String delete(@RequestParam(required = true) int cs_cd, RedirectAttributes redirectAttributes) {
		CsCenterVO center = new CsCenterVO();
		center.setCs_cd(cs_cd);
		int result = service.removeCsCenterBoard(center);
		String viewName = null;
		String message = null;
		switch (result) {
			case 0:
				message = "서버 오류 쫌따 다시.";
				viewName = "redirect:/master/adminCSBoardDetail.do?cs_cd=" + center.getCs_cd();
				break;
			default:
				viewName = "redirect:/master/adminCSCenter.do";
				break;

		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}
