package kr.or.ddit.pmsproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.pmsproject.service.IProjectMemberService;
import kr.or.ddit.vo.PMListVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjListVO;
import kr.or.ddit.vo.PwListVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 작성자명
 * @since 2020. 3. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 24.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/pmsProject")
public class ProjectMemberController {
	
	@Inject
	IProjectMemberService service;
	
		// 프로젝트 멤버 리스트 페이징 처리(json)
		@GetMapping(value="projectMemberList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public PagingVO<ProjListVO> listForAjax(
				@RequestParam(name="page", required=false, defaultValue="1") int currentPage
				, @RequestParam(name="who", required=true) String mem_email
				, @RequestParam(name="what", required=true) int proj_cd
				, @ModelAttribute("pmVO") PMListVO searchDetail
				, @ModelAttribute("search") SearchVO search
				, Model model
				) {
			list(currentPage, mem_email, proj_cd, searchDetail, search, model);
			PagingVO<ProjListVO> pagingVO = (PagingVO) model.asMap().get("pagingVO");
			return pagingVO;
		}
		
		// 프로젝트 멤버 리스트 페이징 처리
		@GetMapping("projectMemberList.do")
		public String list (
				@RequestParam(name="page", required=false, defaultValue="1") int currentPage
				, @RequestParam(name="who", required=true) String mem_email
				, @RequestParam(name="what", required=true) int proj_cd
				, @ModelAttribute("pmVO") PMListVO searchDetail
				, @ModelAttribute("search") SearchVO searchVO
				, Model model
				){
			PagingVO<PMListVO> pagingVO = new PagingVO<>();
			searchDetail.setProj_cd(proj_cd);
			searchDetail.setMem_email(mem_email);
			pagingVO.setSearchDetail(searchDetail);
			pagingVO.setSearchVO(searchVO);
			pagingVO.setTotalRecord(service.readMemberCount(pagingVO));
			pagingVO.setCurrentPage(currentPage);
			List<PMListVO> projectMemberList = service.readMemberList(pagingVO);
//			List<PwListVO> pwMember = service.readProjectWorkName(proj_cd);
			pagingVO.setDataList(projectMemberList);
			String viewName = null;
			model.addAttribute("pagingVO", pagingVO);
//			model.addAttribute("pwMember", pwMember);
			viewName = "pmsprojectwork/projectMemberList";
			return viewName;
		}
	
		// 프로젝트 멤버 삭제
		@GetMapping(value="projectMemberDelete.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public void MemberDelete(
				@RequestParam(name="who", required=true) String mem_email
				, @RequestParam(name="what", required=true) int proj_cd
				, Model model
				, RedirectAttributes redirectAttributes 
				) {
			PMListVO pmList = new PMListVO();
			pmList.setMem_email(mem_email);
			pmList.setProj_cd(proj_cd);
			ServiceResult result = service.removeMember(pmList);
			String viewName = null;
			String message = null;
			
			switch (result) {
				case OK:
					message = "탈퇴가 완료되었습니다.";
					break;
				default:
					message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
					break;
			}
			
			Map<String, Object> resultMap = new HashMap<>();
			if(message!=null) {
				redirectAttributes.addFlashAttribute("error.message", message);
			}
			
		}
		
		// 프로젝트 멤버 초대 폼
		@GetMapping("projectMember.do")
		public String InvitationForm() {
			return "pmsView/projectMemberForm";
		}
		
		// 프로젝트 멤버 리스트에 추가
		@PostMapping("projectMember.do")
		public String InvitationInsert(
				@Validated(InsertHint.class) @ModelAttribute("pmList") PMListVO pmList
				, Errors errors
				, RedirectAttributes redirectAttributes
				, Model model
				) {
			String viewName = null;
			String message = null;
			if(!errors.hasErrors()) {	// 프로젝트 정보를 전부 입력한 경우
				ServiceResult result = service.createMember(pmList);
				if(ServiceResult.OK.equals(result)) {	// 프로젝트 생성에 성공한 경우
					viewName = "redirect:/pmsProject/projectMemberList.do?what="+pmList.getProj_cd()+"&who="+pmList.getMem_email();
					message = "초대가 완료되었습니다.";
					redirectAttributes.addFlashAttribute("message", message);
				}else if(ServiceResult.INVALIDPASSWORD.equals(result)) {	// 이미 초대되어 있는 멤버인 경우
					message = "이미 초대되어 있거나 초대할 수 없는 회원입니다.";
					viewName = "pmsView/projectMemberForm";
					model.addAttribute("message", message);
				}else {	// 프로젝트 생성에 실패한 경우 (서버 오류)
					message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
					viewName = "pmsView/projectMemberForm";
					model.addAttribute("message", message);
				}
			}else {	// 프로젝트 정보를 전부 입력하지 않은 경우
				viewName = "pmsView/projectMemberForm";
				model.addAttribute("message", message);
			}
			return viewName;
		}

		// 초대여부 update
		@PostMapping("pmsUpdateAgree.do")
		public String updateYes(
				@Validated(UpdateHint.class) @ModelAttribute("pmList") PMListVO pmList
				, Errors errors
				, RedirectAttributes redirectAttributes
				, Model model
				) {
			String viewName = null;
			String message = null;
			if(!errors.hasErrors()) {	// 프로젝트 정보를 전부 입력한 경우
				ServiceResult result = null;
				if("YN_E01".equals(pmList.getYn_code2())){
					result = service.modifyAgreeYes(pmList);
				}else {
					result = service.modifyAgreeNo(pmList);
				}
				if(ServiceResult.OK.equals(result)) {	// 프로젝트 수정에 성공한 경우
					viewName = "redirect:/pmsProject/projectList.do?who="+pmList.getMem_email();
					message = "프로젝트가 추가되었습니다.";
				}else {	// 프로젝트 수정에 실패한 경우 (서버 오류)
					message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
					viewName = "pmsproject/projectList";
				}
			}else {	// 프로젝트 정보를 전부 입력하지 않은 경우
				viewName = "pmsproject/projectList";
			}
			model.addAttribute("message", message);
			return viewName;
		}
		
		// 프로젝트 멤버 탈퇴(명시적, 코드만 변경)
		@PostMapping("projectWorkMemberDelete.do")
		public String projectWorkMemberDelete(
				@Validated(UpdateHint.class) @ModelAttribute("pmList") PMListVO pmList
				, Errors errors
				, RedirectAttributes redirectAttributes
				, Model model
				) {
			String viewName = null;
			String message = null;
			String mem_email = pmList.getMem_email();
			Integer proj_cd = pmList.getProj_cd();
			if(!errors.hasErrors()) {	// 프로젝트 정보를 전부 입력한 경우
				ServiceResult result = service.removeMember(pmList);
				List<ProjListVO> projList = service.readProjectMember(proj_cd);
				if(ServiceResult.OK.equals(result)) {	// 프로젝트 수정에 성공한 경우
					if(projList.get(0).getMem_email().equals(mem_email)) {
						viewName = "redirect:/pmsProject/projectMemberList.do?who="+mem_email+"&what="+proj_cd;
						message = "강퇴 처리가 완료되었습니다.";
					}else {
						viewName = "redirect:/pmsProject/projectList.do?who="+mem_email;
						message = "탈퇴 처리가 완료되었습니다.";
					}
				}else {	// 프로젝트 수정에 실패한 경우 (서버 오류)
					message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
					viewName = "pmsprojectwork/projectMemberList";
				}
			}else {	// 프로젝트 정보를 전부 입력하지 않은 경우
				viewName = "pmsprojectwork/projectMemberList";
			}
			model.addAttribute("message", message);
			return viewName;
		}
		
		
}


