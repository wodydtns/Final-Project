package kr.or.ddit.pmsproject.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.pmsproject.service.IProjectWorkService;
import kr.or.ddit.vo.CalendarSearchVO;
import kr.or.ddit.vo.PwCalendarVO;

@Controller
@RequestMapping("/pmsProject")
public class ProjectWorkCalendarController {
	
	@Inject
	IProjectWorkService service;
	// 캘린더 화면 불러오기

	@GetMapping(value="projectWorkCalendar.do")
	public String calendarForm() {
		return "pmsprojectwork/projectWorkCalendar";
	}
	
	@GetMapping(value="projectWorkCalendarData.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<PwCalendarVO> calendarListForAjax(
			@ModelAttribute() CalendarSearchVO calendarSearchVO,String mem_email,
			PwCalendarVO pwCalendarVO, Model model, int proj_cd,
			HttpSession session ) {
		calendarView(calendarSearchVO, pwCalendarVO, model, proj_cd, session);
		return (List<PwCalendarVO>) model.asMap().get("calendarList");
	}
	
	@GetMapping(value="projectWorkCalendarData.do")
	public String calendarView(
			CalendarSearchVO calendarSearchVO,
			PwCalendarVO pwCalendarVO, Model model, int proj_cd,
			HttpSession session 
			) {
		
		pwCalendarVO.setCalendarSearchVO(calendarSearchVO);
		
		List<PwCalendarVO> calendarList = service.readProjectWorkingCalender(pwCalendarVO);
		model.addAttribute("calendarList", calendarList);
		return "pmsprojectwork/projectWorkCalendar";
	}

}
