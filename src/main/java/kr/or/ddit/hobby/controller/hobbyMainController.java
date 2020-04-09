package kr.or.ddit.hobby.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.admin.service.IAdminCsCenterService;
import kr.or.ddit.hobby.service.IHobbyClassService;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CouponVO;
import kr.or.ddit.vo.CsCenterVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RecruitVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      최도혁       최초작성
 * 2020. 3. 19		최도혁		카운트, 페이징처리 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
public class hobbyMainController { //메인 페이지
	@Inject
	IHobbyClassService service;
	
	@GetMapping(value="/hobby/hobbyMain.do", produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public hobbyBestPagingVO<classPiAttVO> listForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("search") SearchVO searchVO
			,Model model
			){
		hobbyMain(currentPage, searchVO, model);
		hobbyBestPagingVO<classPiAttVO> pagingVO = (hobbyBestPagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@GetMapping(value="hobby/hobbyMain.do")
	public String hobbyMain(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("search") SearchVO searchVO
			,Model model
			) {
		hobbyBestPagingVO<classPiAttVO> pagingVO = new hobbyBestPagingVO<>(10, 5);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.selectClassCount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<classPiAttVO> hobbyClass = service.selectClassList(pagingVO);
		pagingVO.setDataList(hobbyClass);
		model.addAttribute("pagingVO", pagingVO);
		
		List<CouponVO> couponList = service.selectCouponList();
		model.addAttribute("couponList", couponList);
		return "hobby/hobbyMain";
	}
	

}
