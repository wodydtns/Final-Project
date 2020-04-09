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
import kr.or.ddit.vo.ProjectSearchVO;
import kr.or.ddit.vo.PwListVO;
import kr.or.ddit.vo.SearchVO;

@Controller
@RequestMapping("pmsProject")
public class ProjectChartController {
	
	@Inject
	IProjectWorkService service;
	WebApplicationContext context;
	ServletContext application;

	@GetMapping("projectChart.do")
	public String chartForm() {
		return "pmsprojectwork/projectChart";
	}
	
	
//	@GetMapping(value="projectAllGanttChart.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public List<PwListVO> chart(
//			@RequestParam(name="who", required=true) String mem_email
//			, @ModelAttribute("psVO") ProjectSearchVO searchDetail
//			, @ModelAttribute("search") SearchVO searchVO
//			, Model model
//			) {
//		AllList(mem_email, searchDetail, searchVO, model);
//		List<PwListVO> ganttWorkList = (List<PwListVO>) model.asMap().get("ganttWorkList");
//		return ganttWorkList;
//	}
//	
//	@GetMapping("projectAllGanttChart.do")
//	public String AllList (
//			@RequestParam(name="who", required=true) String mem_email
//			, @ModelAttribute("psVO") ProjectSearchVO searchDetail
//			, @ModelAttribute("search") SearchVO searchVO
//			, Model model
//			){
//		PwListVO<ProjectSearchVO> pwList = new PwListVO<>();
//		pwList.setPsVO(searchDetail);
//		pwList.setSearchVO(searchVO);
//		pwList.getPsVO().setMem_email(mem_email);
//		List<PwListVO> ganttWorkList = service.readGanttWork(pwList);
//		String viewName = null;
//		model.addAttribute("ganttWorkList", ganttWorkList);
//		viewName = "pmsproject/pmsAllGanttChart";
//		return viewName;
//	}
	
}
