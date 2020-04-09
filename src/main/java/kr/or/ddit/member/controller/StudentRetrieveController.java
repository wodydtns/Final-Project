package kr.or.ddit.member.controller;

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

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PaymentVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 작성자명
 * @since 2020. 3. 30.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 30.      김혜정       최초작성 크리에이터센터 수강생리스트조회용
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("creatorCenter")
public class StudentRetrieveController {

	@Inject
	IMemberService service;
	
	
	@GetMapping(value="stuList.do", 
			 produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<PaymentVO> listForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="what", required=true) int cl_cd
			, @ModelAttribute("classVO") ClassVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO 
			, Model model
			, HttpSession session
			) {
		list(currentPage, cl_cd, searchDetail, searchVO,  model, session);
		PagingVO<PaymentVO> pagingVO = (PagingVO<PaymentVO>) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	
	@GetMapping(value="stuList.do")
	public String list(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="what", required=true) int cl_cd
			, @ModelAttribute("classVO") ClassVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO 
			, Model model
			, HttpSession session
			){
		session.setAttribute("cl_cd", cl_cd);
		PagingVO<PaymentVO> pagingVO = new PagingVO<>(5, 3);
		PaymentVO payList = new PaymentVO();
		payList.setClassVO(searchDetail);
		payList.getClassVO().setCl_cd(cl_cd);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchDetail(payList);
		pagingVO.setTotalRecord(service.readMemberCount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<PaymentVO> stuList = service.readMemberList(pagingVO);
		pagingVO.setDataList(stuList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "creatorCenter/studentView";
		
	}
}
