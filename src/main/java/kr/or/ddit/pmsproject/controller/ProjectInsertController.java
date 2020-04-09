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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.pmsproject.service.IProjectService;
import kr.or.ddit.vo.PMListVO;
import kr.or.ddit.vo.ProjListVO;

/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 13.      최효은       최초작성
 * 2020. 3. 18.      최효은       프로젝트 회원 관련 메서드 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/pmsProject/projectInsert.do")
public class ProjectInsertController {
	
	@Inject
	IProjectService service;
	
	// 프로젝트 입력 폼 불러오기
	@GetMapping
	public String form() {
		return "pmsproject/projectForm";
	}
	
	// 프로젝트 등록
	@PostMapping
	public String insert(
			@Validated(InsertHint.class) @ModelAttribute("projectList") ProjListVO proj
			, Errors errors
			, Model model
			) {
		PMListVO pmList = new PMListVO(proj.getProj_leader(), "YN_E01", "YN_A02");
		proj.setPmList(pmList);
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {	// 프로젝트 정보를 전부 입력한 경우
			ServiceResult result = service.createProject(proj);
			if(ServiceResult.OK.equals(result)) {	// 프로젝트 생성에 성공한 경우
				viewName = "redirect:/pmsProject/projectView.do?what="+proj.getProj_cd()+"&who="+proj.getProj_leader();
			}else {	// 프로젝트 생성에 실패한 경우 (서버 오류)
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
				viewName = "pmsproject/projectForm";
			}
		}else {	// 프로젝트 정보를 전부 입력하지 않은 경우
			viewName = "pmsproject/projectForm";
		}
		model.addAttribute("message", message);
		return viewName;
	}
}
