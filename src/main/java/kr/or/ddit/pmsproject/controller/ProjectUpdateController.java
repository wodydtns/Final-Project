package kr.or.ddit.pmsproject.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.pmsproject.service.IProjectService;
import kr.or.ddit.vo.ProjListVO;

/**
 * @author 최효은
 * @since 2020. 3. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 17.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/pmsProject/projectUpdate.do")
public class ProjectUpdateController {

	@Inject
	IProjectService service;
	
	// 프로젝트 입력 폼 불러오기
	@GetMapping
	public String form(
			@RequestParam(name="what", required=true) int proj_cd
			, @RequestParam(name="who", required=true) String mem_email
			, Model model
			) {
		ProjListVO proj = new ProjListVO();
		proj.setProj_cd(proj_cd);
		proj.setMem_email(mem_email);
		ProjListVO projList = service.readProject(proj);
		model.addAttribute("proj", projList);
		return "pmsproject/projectForm";
	}
	
	// 프로젝트 수정
	@PostMapping
	public String update(
			@Validated(UpdateHint.class) @ModelAttribute("projectList") ProjListVO proj
			, Errors errors
			, RedirectAttributes redirectAttributes
			, Model model
			) {
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {	// 프로젝트 정보를 전부 입력한 경우
			ServiceResult result = service.modifyProject(proj);
			if(ServiceResult.OK.equals(result)) {	// 프로젝트 수정에 성공한 경우
				viewName = "redirect:/pmsProject/projectView.do?what="+proj.getProj_cd()+"&who="+ proj.getProj_leader();
			}else {	// 프로젝트 수정에 실패한 경우 (서버 오류)
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
				viewName = "pmsproject/pmsprojectForm";
			}
		}else {	// 프로젝트 정보를 전부 입력하지 않은 경우
			viewName = "pmsproject/projectForm";
		}
		model.addAttribute("message", message);
		return viewName;
	}
	
}



















