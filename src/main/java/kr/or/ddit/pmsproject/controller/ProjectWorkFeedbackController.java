package kr.or.ddit.pmsproject.controller;

import java.util.List;

import javax.inject.Inject;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.pmsproject.dao.IProjectMemberDAO;
import kr.or.ddit.pmsproject.dao.IProjectWorkDAO;
import kr.or.ddit.pmsproject.service.IProjectWorkFeedbackService;
import kr.or.ddit.vo.FeedbackVO;
import kr.or.ddit.vo.PMListVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PwListVO;
import kr.or.ddit.vo.SearchVO;

@Controller
@RequestMapping("/pmsProject")
public class ProjectWorkFeedbackController {

	@Inject
	IProjectWorkFeedbackService service;
	@Inject
	IProjectWorkDAO dao;
	
	@GetMapping("projectFeedbackCount.do")
	public int feedbackCount(
			@RequestParam(name="mem_email", required=true) String mem_email
			, Model model
			) {
		int feedbackCount = service.readPMSFeedbackCount(mem_email);
		model.addAttribute("feedbackCount", feedbackCount);
		return feedbackCount;
	}
	
	// 전체 피드백 리스트 출력(ajax)
	@GetMapping(value="projectFeedbackList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<FeedbackVO> listForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="target_email", required=true) String target_email
			, @ModelAttribute("feedList") FeedbackVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			) {
		list(currentPage, target_email, searchDetail, searchVO, model);
		PagingVO<FeedbackVO> pagingVO = (PagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	// 전체 피드백 리스트 출력(view)
	@GetMapping("projectFeedbackList.do")
	public String list (
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="mem_email", required=true) String target_email
			, @ModelAttribute("feedList") FeedbackVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			){
		PagingVO<FeedbackVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchDetail(searchDetail);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.readFeedbackCount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<FeedbackVO> projectFeedbackList = service.readFeedbackList(pagingVO);
		pagingVO.setDataList(projectFeedbackList);
		String viewName = null;
		model.addAttribute("pagingVO", pagingVO);
		viewName = "pmsFeedView/projectWorkFeedbackList";
 		return viewName;
	}
	
	// 피드백 상세 내역
	@GetMapping("projectFeedbackView.do")
	public String viewDisplay1(
			@RequestParam(name="feed_cd", required=true) int feed_cd
			, Model model
			) {
		FeedbackVO feedList = new FeedbackVO();
		FeedbackVO projFeedList = service.readFeedback(feed_cd);
		model.addAttribute("feedList", projFeedList);
		return "pmsView/projectWorkFeedbackView";
	}
	
	// 피드백 작업 불러오기
	@GetMapping("projectFeedbackInsert.do")
	public String feedbackWorkName(
			@ModelAttribute("pwMember") PMListVO pmList
			, @RequestParam(name="pm_cd", required=true) int pm_cd
			, Model model
			) {
		List<PwListVO> pwMember = dao.selectProjectWorkName(pmList);
		model.addAttribute("pwMember", pwMember);
		model.addAttribute("pm_cd", pm_cd);
		String viewName = null;
		viewName = "pmsFeedView/projectWorkFeedbackForm";
		return viewName;
	}
	
	// 피드백 생성
	@PostMapping("projectFeedbackInsert.do")
	public String insert(
			@Validated(InsertHint.class) @ModelAttribute("feedList") FeedbackVO feedList
			, Errors errors
			, RedirectAttributes attributes
			, Model model
			){
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			int feed_sender = service.readProjMember(feedList);
			feedList.setFeed_sender(feed_sender);
			ServiceResult result = service.createFeedback(feedList);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/pmsProject/projectMemberList.do?what="+feedList.getProj_cd()+"&who="+feedList.getFeed_sender();
				message = "피드백을 보냈습니다.";
				attributes.addAttribute("message", message);
			}else {
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
				viewName = "pmsprojectwork/projectWorkFeedbackForm";
				model.addAttribute("message", message);
			}
		}else {
			viewName = "pmsprojectwork/projectWorkFeedbackForm";
			model.addAttribute("message", message);
		}
		return viewName;		
	}
	
	// 피드백 삭제
	@GetMapping("projectFeedbackDelete.do")
	public String delete(
			@RequestParam(name="what", required=true) int feed_cd
			, @RequestParam(name="who", required=true) String mem_email
			, Model model
			, RedirectAttributes redirectAttributes 
			) {
		FeedbackVO feedList = new FeedbackVO();
		feedList.setPw_cd(feed_cd);
		ServiceResult result = service.removeFeedback(feed_cd);
		String viewName = null;
		String message = null;
		switch (result) {
			case OK:
				viewName = "redirect:/pmsProject/projectFeedbackList.do?who="+mem_email;
				break;
			case NOTEXIST:
				message = "해당 피드백을 찾을 수 없습니다.";
				viewName = "redirect:/pmsProject/projectFeedbackList.do?who="+mem_email;
				break;
			default:
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
				viewName = "projectwork/projectFeedbackForm";
				break;
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
	
}
