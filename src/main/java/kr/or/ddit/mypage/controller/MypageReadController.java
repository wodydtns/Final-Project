package kr.or.ddit.mypage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.mypage.service.ImypageService;
import kr.or.ddit.vo.ApplyListVO;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.CsCenterVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PaymentVO;
import kr.or.ddit.vo.ResumeVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 작성자명
 * @since 2020. 3. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 25.      작성자명  박재욱              최초작성  
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

@Controller
@RequestMapping("/mypage")
public class MypageReadController {
	
	@Inject
	ImypageService service;

	@GetMapping(value = "mypageStudyingList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ApplyListVO> PreRecruitlistForAjax(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model,
			@ModelAttribute("apply") PaymentVO searchDetail,
			HttpSession session) {
		readFavorList(currentPage, searchVO, model, searchDetail, session);
		return (PagingVO<ApplyListVO>) model.asMap().get("pagingVO");
	}
	
	@GetMapping(value="mypageStudyingList.do")
	public String readFavorList(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model,
			@ModelAttribute("apply") PaymentVO searchDetail,
			HttpSession session
			) {
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		PagingVO<PaymentVO> pagingVO = new PagingVO<>();
		searchDetail.setMem_email(member.getMem_email());
		pagingVO.setSearchDetail(searchDetail);		
		pagingVO.setSearchVO(searchVO);
		
		pagingVO.setCurrentPage(currentPage);

		int totalRecord = service.readSelectStudingtotal(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<PaymentVO> payList = service.readSelectStudingList(pagingVO);
		pagingVO.setDataList(payList);
		model.addAttribute("pagingVO", pagingVO);
		return "mypage/mypageStudyingList";
	}
}
