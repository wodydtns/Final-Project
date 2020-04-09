package kr.or.ddit.pmsproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.pmsproject.service.IProjectService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjListVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 최효은
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 13.      최효은       최초작성
 * 2020. 3. 26.      최효은       초대 대기 페이징 리스트 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("pmsProject")
public class ProjectReadController {

	@Inject
	IProjectService service;
	WebApplicationContext context;
	ServletContext application;
	  
	@Inject 
	public void setContext(WebApplicationContext context) {
		this.context = context;
		application = context.getServletContext();
	}
	
	
	// 진행 프로젝트 리스트 페이징 처리(json)
	@GetMapping(value="projectList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ProjListVO> listForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="who", required=true) String mem_email
			, @ModelAttribute("search") SearchVO search
			, Model model
			) {
		list(currentPage, mem_email, search, model);
		PagingVO<ProjListVO> pagingVO = (PagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	// 진행 프로젝트 리스트 페이징 처리
	@GetMapping("projectList.do")
	public String list (
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="who", required=true) String mem_email
			, @ModelAttribute("search") SearchVO search
			, Model model
			){
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		if(StringUtils.isBlank(mem_email)) {
			viewName = "hobby/hobbyMain";
			message = "프로젝트 서비스는 로그인한 회원만 이용할 수 있습니다.";
			return viewName;
		}
		PagingVO<ProjListVO> pagingVO = new PagingVO<>();
		search.setSearchWord(mem_email);
		pagingVO.setSearchVO(search);
		pagingVO.setTotalRecord(service.readProjectCount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<ProjListVO> projectList = service.readProjectList(pagingVO);
		pagingVO.setDataList(projectList);
		viewName = "pmsproject/projectList";
		model.addAttribute("pagingVO", pagingVO);
		return viewName;
	}
	
	// 초대 대기 중 프로젝트 리스트 페이징 처리(json)
	@GetMapping(value="projectList2.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ProjListVO> listForAjax2(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="who", required=true) String mem_email
			, @ModelAttribute("search") SearchVO search
			, Model model
			) {
		list2(currentPage, mem_email, search, model);
		PagingVO<ProjListVO> pagingVO2 = (PagingVO) model.asMap().get("pagingVO2");
		return pagingVO2;
	}
	
	// 초대 대기 중 프로젝트 리스트 페이징 처리
	@GetMapping("projectList2.do")
	public String list2 (
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="who", required=true) String mem_email
			, @ModelAttribute("search") SearchVO search
			, Model model
			){
		PagingVO<ProjListVO> pagingVO2 = new PagingVO<>(5, 5);
		search.setSearchWord(mem_email);
		pagingVO2.setSearchVO(search);
		pagingVO2.setTotalRecord(service.readProjAgreeCount(pagingVO2));
		pagingVO2.setCurrentPage(currentPage);
		List<ProjListVO> projectAgreeList = service.readProjAgreeList(pagingVO2);
		pagingVO2.setDataList(projectAgreeList);
		String viewName = null;
		model.addAttribute("projectAgreeList", projectAgreeList);
		model.addAttribute("pagingVO2", pagingVO2);
		viewName = "pmsproject/projectList";
		return viewName;
	}
	
	// 프로젝트 상세 내역
	@GetMapping("projectView.do")
	public String viewDisplay(
			@RequestParam(name="what", required=true) int proj_cd
			, @RequestParam(name="who", required=true) String mem_email
			, HttpSession session
			, Model model
			) {
		ProjListVO proj = new ProjListVO();
		proj.setProj_cd(proj_cd);
		proj.setMem_email(mem_email);
		ProjListVO projResult = service.readProject(proj);
		int progCount = service.readProgCount(proj);
		int compCount = service.readCompCount(proj);
		List<ProjListVO> projMemberList = service.readProjectMember(proj_cd);
		int projWorkTime = service.readTimeSum(projResult);
		Map<String, Object> projList = new HashMap<>();
		projList.put("proj", projResult);
		projList.put("progCount", progCount);
		projList.put("compCount", compCount);
		projList.put("projMemberList", projMemberList);
		projList.put("projWorkTime", projWorkTime);
		model.addAttribute("projList", projList);
		session.setAttribute("proj_cd", projResult.getProj_cd());
		return "pmsprojectwork/projectView";
	}

	
}



















