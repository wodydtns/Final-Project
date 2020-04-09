package kr.or.ddit.hobby.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.creatorCenter.service.IClassService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hobby.service.IHobbyDetailService;
import kr.or.ddit.vo.ClassCommunityVO;
import kr.or.ddit.vo.ClassDetailPagingVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CommReplyVO;
import kr.or.ddit.vo.CreatorVO;
import kr.or.ddit.vo.CurriculumVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PaymentVO;
import kr.or.ddit.vo.RecruitVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class hobbyDetailController {

	@Inject
	IHobbyDetailService service;
	
	@Inject
	IClassService classService;
	
	
	//상세보기 화면 데이터 불러오기
	@GetMapping(value="hobby/hobbyDetail.do")
	public String hobbyDetail(@RequestParam(name="what",required=true) int cl_cd, Model model
			, ClassDetailPagingVO<CommReplyVO> pagingVO
			){
		
		// 제작자 소개
		CreatorVO creator = classService.readCreator(cl_cd);
		model.addAttribute("creator", creator);
		
		//클래스제작자가 쓴 글 불러오기
		List<ClassCommunityVO> community = service.selectCommunityList(cl_cd);
		model.addAttribute("community", community);
		
		
		//강의 소개 및 커리큘럼, 금액 , 기간 불러오기
		ClassVO classInfo = classService.readMyClass(cl_cd);
		List<CurriculumVO> curriList = classService.readMyCurri(cl_cd);
		model.addAttribute("classInfo", classInfo);
		model.addAttribute("curriList", curriList);
		
		
		//커뮤니티 게시글
		List<ClassCommunityVO> communityList = service.selectCommunityList2(cl_cd); 
		model.addAttribute("communityList", communityList);
		
		//댓글
		List<CommReplyVO> communityReplyList = service.selectComunityReply(cl_cd);
		model.addAttribute("communityReplyList", communityReplyList);
		
		return "hobby/hobbyDetail";
	}
	
	
	//동영상 재생시 해당 클래스의 회원인지 확인하기
	@GetMapping(value="hobby/hobbyDetail.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> payCheck(
			@RequestParam(name="what",required=true) int cl_cd , HttpSession session
			) {
//		클래스번호, 세션아이디 필요
		ServiceResult result = null;
		PaymentVO pay = new PaymentVO();
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		if(member==null) {
			result = ServiceResult.NULLSESSION;
		}else {
			pay.setCl_cd(cl_cd);
			pay.setMem_email(member.getMem_email());
			result = classService.readPaidCheck(pay);
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", result.name());
		return resultMap; 
	}
	
	
	//클래스 동영상 재생
	@GetMapping(value="/hobby/playVideo.do")
	public String doget(@RequestParam(name="what") String temp_nm, Model model) {
		model.addAttribute("temp_nm", temp_nm);
		return "hobby/test";
	}
	
	
}
