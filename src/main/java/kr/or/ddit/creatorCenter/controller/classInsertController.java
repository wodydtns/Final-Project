package kr.or.ddit.creatorCenter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.creatorCenter.service.IClassService;
import kr.or.ddit.creatorCenter.service.IPIService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CurriculumVO;

@Controller
@RequestMapping("/creatorCenter/classInsert.do")
public class classInsertController {

	@Inject
	IClassService service;
	@Inject
	IPIService piService;
	
	@GetMapping
	public String doGet(@RequestParam(name="what", required=true) int pi_cd , 
			Model model) {
		model.addAttribute("pi_cd", pi_cd);
		return "creatorCenter/makeClass";
	}
	
	@PostMapping
	public String doPost(
			@ModelAttribute ClassVO cl
			, Errors errors
			//,@RequestParam Map<String, Object> map
			, Model model
			) {
		
		cl.genGroups(cl.getCurriculumList());
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createClass(cl);
			if(ServiceResult.OK.equals(result)) {
				ServiceResult result2 = piService.modifyClassYN(cl.getPre_cd());//pi에 클래스개설상태로 바꾸기
				if(ServiceResult.OK.equals(result)) {
					viewName = "redirect:/creatorCenter/classList.do";
				}else {
					message = "서버 오류 쫌따 다시.";
					viewName = "creatorCenter/makeClass";
				}
			}else {
				message = "서버 오류 쫌따 다시.";
				viewName = "creatorCenter/makeClass";
			}
		}else {
			viewName = "creatorCenter/makeClass";
		}
		model.addAttribute("message", message);
		return viewName;	
	}
}
