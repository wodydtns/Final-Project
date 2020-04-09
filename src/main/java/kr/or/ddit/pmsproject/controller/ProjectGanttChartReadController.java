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

import kr.or.ddit.pmsproject.service.IProjectService;
import kr.or.ddit.pmsproject.service.IProjectWorkService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjectSearchVO;
import kr.or.ddit.vo.PwListVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 작성자명
 * @since 2020. 3. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 22.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("pmsProject")
public class ProjectGanttChartReadController {
	
	@Inject
	IProjectService service;
	WebApplicationContext context;
	ServletContext application;
	
	@Inject
	public void setContext(WebApplicationContext context) {
		this.context = context;
		application = context.getServletContext();
	}
	
	@GetMapping(value="projectAllGanttChart.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<PwListVO> allGanttChart(
			@RequestParam(name="who", required=true) String mem_email
			, @ModelAttribute("psVO") ProjectSearchVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			) {
		AllList(mem_email, searchDetail, searchVO, model);
		List<PwListVO> ganttWorkList = (List<PwListVO>) model.asMap().get("ganttWorkList");
		return ganttWorkList;
	}
	
	@GetMapping("projectAllGanttChart.do")
	public String AllList (
			@RequestParam(name="who", required=true) String mem_email
			, @ModelAttribute("psVO") ProjectSearchVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			){
		PwListVO<ProjectSearchVO> pwList = new PwListVO<>();
		pwList.setPsVO(searchDetail);
		pwList.setSearchVO(searchVO);
		pwList.getPsVO().setMem_email(mem_email);
		List<PwListVO> ganttWorkList = service.readAllGanttWork(pwList);
		String viewName = null;
		model.addAttribute("ganttWorkList", ganttWorkList);
		viewName = "pmsproject/pmsAllGanttChart";
		return viewName;
	}
	
	@GetMapping(value="projectGanttChart.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<PwListVO> ganttChart(
			@RequestParam(name="who", required=true) String mem_email
			, @RequestParam(name="what", required=true) int proj_cd
			, @ModelAttribute("psVO") ProjectSearchVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			) {
		List(mem_email, proj_cd, searchDetail, searchVO, model);
		List<PwListVO> ganttWorkList = (List<PwListVO>) model.asMap().get("ganttWorkList");
		return ganttWorkList;
	}
	
	@GetMapping("projectGanttChart.do")
	public String List (
			@RequestParam(name="who", required=true) String mem_email
			, @RequestParam(name="what", required=true) int proj_cd
			, @ModelAttribute("psVO") ProjectSearchVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			){
		PwListVO<ProjectSearchVO> pwList = new PwListVO<>();
		pwList.setPsVO(searchDetail);
		pwList.setSearchVO(searchVO);
		pwList.getPsVO().setMem_email(mem_email);
		pwList.getPsVO().setProj_cd(proj_cd);
		List<PwListVO> ganttWorkList = service.readGanttWork(pwList);
		String viewName = null;
		model.addAttribute("ganttWorkList", ganttWorkList);
		viewName = "pmsprojectwork/pmsGanttChart";
		return viewName;
	}
	
}
