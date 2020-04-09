package kr.or.ddit.admin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.admin.service.IAdminCsCenterService;
import kr.or.ddit.vo.CsCenterVO;

@Controller
@RequestMapping(value = "/master/adminCSCenterBoardModify.do")
public class AdminCenterBoardUpdateController {

	@Inject
	IAdminCsCenterService service;

	@GetMapping
	public String form(@RequestParam(name = "cs_cd", required = true) int cs_cd, Model model) {
		CsCenterVO center = service.readSelectCSCenterBoard(cs_cd);
		model.addAttribute("center", center);
		return "master/adminCSCenterBoardWrite";
	}

	@PostMapping
	public String update(@ModelAttribute("center") CsCenterVO center, BindingResult errors, Model model) {
		String viewName = null;
		String message = null;
		if (!errors.hasErrors()) {
			int result = service.modifyUpdateCsCenterBoard(center);
			if (result > 0) {
				viewName = "redirect:/master/adminCSCenter.do?cs_cd="+center.getCs_cd();
			} else {
				message = "서버 오류";
				viewName = "master/adminCSCenterBoardWrite";
			}
		} else {
			viewName = "master/adminCSCenterBoardWrite";

		}
		model.addAttribute("message", message);
		return viewName;
	}
}
