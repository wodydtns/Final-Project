package kr.or.ddit.recruit.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.recruit.service.IRecruitService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RecruitVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 최도혁
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 17.      최도혁       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
//참고하세요. 사람인 open api 주소 https://oapi.saramin.co.kr/guide/job-search
//아이디 keemye0814@gmail.com 비번	Pgm06N
@Controller
@RequestMapping("/recruit")
public class recruitMainController {
	
	@Inject
	IRecruitService service;
	WebApplicationContext context;
	ServletContext application;
	
	
	@GetMapping(value="recruitMain.do", produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<RecruitVO> listForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("search") SearchVO searchVO
			,Model model
			){
		recruitMain(currentPage, searchVO, model);
		PagingVO<RecruitVO> pagingVO = (PagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@RequestMapping(value="recruitList.do", method=RequestMethod.GET)
	public String recruitMain(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("search") SearchVO searchVO
			,Model model) {
		PagingVO<RecruitVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.selectRecruitCount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<RecruitVO> recruitList = service.selectRecruitList(pagingVO);
		pagingVO.setDataList(recruitList);
		model.addAttribute("pagingVO", pagingVO);
		return "recruit/recruitMain";
	}
	
	@GetMapping(value="recruitMain.do")
	public String topList(Model model) {	
		List<RecruitVO> recruitList2 = service.selectRecruitList2();
		model.addAttribute("recruitList2", recruitList2);
		return "recruit/recruitMain";
	}
}
