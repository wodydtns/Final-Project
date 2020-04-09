package kr.or.ddit.pmsproject.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.pmsproject.service.IProjectWorkService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjListVO;
import kr.or.ddit.vo.ProjectSearchVO;
import kr.or.ddit.vo.PwListVO;
import kr.or.ddit.vo.SearchVO;
 
/**
 * @author 최효은
 * @since 2020. 3. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 16.      최효은       최초작성
 * 2020. 3. 19.      최효은       프로젝트 개별 리스트 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("pmsProject")
public class ProjectWorkReadController {

	@Inject
	IProjectWorkService service;
	WebApplicationContext context;
	ServletContext application;
	
	@Inject
	public void setContext(WebApplicationContext context) {
		this.context = context;
		application = context.getServletContext();
	}
	
	// 전체 작업리스트 출력(ajax)
	@GetMapping(value="projectWorkAllList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<PwListVO> AllListForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="who", required=true) String mem_email
			, @ModelAttribute("psVO") ProjectSearchVO searchDetail
			, @ModelAttribute("search") SearchVO search
			, Model model
			) {
		AllList(currentPage, mem_email, searchDetail, search, model);
		PagingVO<PwListVO> pagingVO = (PagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	// 전체 작업리스트 출력(view)
	@GetMapping("projectWorkAllList.do")
	public String AllList (
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="who", required=true) String mem_email
			, @ModelAttribute("psVO") ProjectSearchVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			){
		PagingVO<PwListVO> pagingVO = new PagingVO<>();
		PwListVO pwList = new PwListVO();
		pwList.setPsVO(searchDetail);
		pwList.getPsVO().setMem_email(mem_email);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchDetail(pwList);
		pagingVO.setTotalRecord(service.readProjectWorkAllCount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<PwListVO> projectWorkAllList = service.readProjectWorkAllList(pagingVO);
		pagingVO.setDataList(projectWorkAllList);
		String viewName = null;
		model.addAttribute("pagingVO", pagingVO);
		viewName = "pmsproject/projectWorkAllList";
		return viewName;
	}
	
	// 프로젝트 별 작업리스트 출력(ajax)
	@GetMapping(value="projectWorkList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<PwListVO> ListForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="who", required=true) String mem_email
			, @RequestParam(name="what", required=true) int proj_cd
			, @ModelAttribute("psVO") ProjectSearchVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			) {
		list(currentPage, mem_email, proj_cd, searchDetail, searchVO, model);
		PagingVO<PwListVO> pagingVO = (PagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	// 프로젝트 별 작업리스트 출력(view)
	@GetMapping("projectWorkList.do")
	public String list (
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="who", required=true) String mem_email
			, @RequestParam(name="what", required=true) int proj_cd
			, @ModelAttribute("psVO") ProjectSearchVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			){
		PagingVO<PwListVO> pagingVO = new PagingVO<>();
		PwListVO pwList = new PwListVO();
		pwList.setPsVO(searchDetail);
		pwList.getPsVO().setMem_email(mem_email);
		pwList.getPsVO().setProj_cd(proj_cd);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchDetail(pwList);
		pagingVO.setTotalRecord(service.readProjectWorkCount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<PwListVO> projectWorkList = service.readProjectWorkList(pagingVO);
		pagingVO.setDataList(projectWorkList);
		String viewName = null;
		model.addAttribute("pagingVO", pagingVO);
		viewName = "pmsprojectwork/projectWorkList";
		return viewName;
	}
	
	// 프로젝트 상세 내역
	@GetMapping("projectWorkView.do")
	public String viewDisplay1(
			@RequestParam(name="what", required=true) int pw_cd
			, Model model
			) {
		ProjListVO proj = new ProjListVO();
		PwListVO projWorkList = service.readProjectWorkAtt(pw_cd);
		model.addAttribute("pwList", projWorkList);
		return "pmsprojectwork/projectWorkView";
	}
	
	// 전체 프로젝트 상세 내역
	@GetMapping("projectWorkAllView.do")
	public String viewDisplay2(
			@RequestParam(name="what", required=true) int pw_cd
			, Model model
			) {
		ProjListVO proj = new ProjListVO();
		PwListVO projWorkList = service.readProjectWorkAtt(pw_cd);
		model.addAttribute("pwList", projWorkList);
		return "pmsproject/projectWorkAllView";
	}
	
}
