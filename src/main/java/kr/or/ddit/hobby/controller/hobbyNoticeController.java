package kr.or.ddit.hobby.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.service.IAdminCsCenterService;
import kr.or.ddit.vo.AdminVO;
import kr.or.ddit.vo.CsCenterVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
@RequestMapping("/hobby")
public class hobbyNoticeController {

	@Inject
	IAdminCsCenterService service;
	
	@GetMapping(value = "hobbyNotice.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<CsCenterVO> csCenterlistForAjax(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model) {
		readCSCenterboardList(currentPage, searchVO, model);
		return (PagingVO<CsCenterVO>) model.asMap().get("pagingVO");
	}

	
	@GetMapping("hobbyNotice.do")
	public String readCSCenterboardList(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model) {
		PagingVO<CsCenterVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);

		pagingVO.setCurrentPage(currentPage);

		int totalRecord = service.readSelectCSCenterBoardTotal(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<CsCenterVO> csBoardList = service.readCsCenterBoard(pagingVO);
		pagingVO.setDataList(csBoardList);
		model.addAttribute("pagingVO", pagingVO);
		return "hobby/hobbyNotice";
	}
	
	
	@GetMapping("hobbyNoticeDetail.do")
	public String readCSCenterBoard(
				int cs_cd, Model model
			) {
		CsCenterVO csCenterBoard = service.readSelectCSCenterBoard(cs_cd);
		service.incrementHit(cs_cd);
		String goPage = "";
		if(csCenterBoard ==null) {
			goPage = "redirect:/hobby/hobbyMain.do";
		}else {
			goPage = "hobby/hobbyNotice";
			model.addAttribute("csCenterBoard", csCenterBoard);
		}
		
		return goPage;
	}
	
	@GetMapping(value="noticeIdCheck.do")
	public String idCheck(@RequestParam(name="mem_email", required=true) String mem_email
			, HttpSession session
			, RedirectAttributes redirectAttributes
			) {
			
		MemberVO memberVO =  (MemberVO) session.getAttribute("authMember");
		String gopage = null;
		String message = null;
		
		try {
			
			if( "admin".equals(memberVO.getMem_email())) {
				message = "관리자만 작성이 가능합니다.";
				gopage = "redirect:/hobby/hobbyNotice.do";
			}else {
				gopage = "redirect:/master/adminCSCenterBoardWrite.do";
			}
		}catch (NullPointerException e) {
			e.printStackTrace();
			gopage ="redirect:/login/login.do";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		return gopage;
		
		
	}

	
}
