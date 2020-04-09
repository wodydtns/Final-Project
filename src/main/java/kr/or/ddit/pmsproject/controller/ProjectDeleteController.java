package kr.or.ddit.pmsproject.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.pmsproject.service.IProjectWorkService;
import kr.or.ddit.vo.PwListVO;

@Controller
@RequestMapping("/pmsProject/projectDelete.do")
public class ProjectDeleteController {
	
	@Inject
	IProjectWorkService service;
	
	// 작업 삭제
	@GetMapping
	public String delete(
			@RequestParam(name="what", required=true) int pw_cd
			, @RequestParam(name="who", required=true) String mem_email
			, Model model
			, RedirectAttributes redirectAttributes 
			) {
		PwListVO pwList = new PwListVO();
		pwList.setPw_cd(pw_cd);
		ServiceResult result = service.removeProjectWork(pwList);
		String viewName = null;
		String message = null;
		switch (result) {
			case OK:
				viewName = "redirect:/pmsProject/projectWorkList.do?who="+mem_email;
				break;
			case NOTEXIST:
				message = "해당 작업을 찾을 수 없습니다.";
				viewName = "redirect:/pmsProject/projectWorkList.do?who="+mem_email;
				break;
			default:
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
				viewName = "projectwork/projectWorkForm";
				break;
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}
