package kr.or.ddit.creatorCenter.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.creatorCenter.service.IPIService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PIVO;

@Controller
@RequestMapping("/creatorCenter/piUpdate.do")
public class PIUpdateCotroller {

	@Inject 
	IPIService service;
	
	@GetMapping
	public String doget(@RequestParam(name="what", required=true) int pi_cd, Model model) {
		System.out.println("컨트롤러 사전조사 번호 : " + pi_cd);
		PIVO pi = service.read_My_pi(pi_cd);
		model.addAttribute("pi", pi);
		
		return "creatorCenter/makePI";
	}
	
	@PostMapping
	public String doPost(@ModelAttribute("pi") PIVO pi
			, BindingResult errors
			, Model model) {
		
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyPI(pi);
			switch (result) {
				case OK:
					viewName = "redirect:/creatorCenter/pi_view.do?what="+pi.getPi_cd();
					break;
				default:
					message = "서버 오류 쫌따 다시.";
					viewName = "creatorCenter/makePI";
					break;
			}
		}else {
			viewName = "creatorCenter/makePI";
		}
		model.addAttribute("message", message);
		return viewName;
	}
}
