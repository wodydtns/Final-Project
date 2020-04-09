package kr.or.ddit.admin.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.service.IAdminCsCenterService;
import kr.or.ddit.vo.CsCenterVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 13.      작성자명   박재욱    최초작성  박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/master")
public class AdminCsCenterController {
	
	@Inject
	IAdminCsCenterService service;
	
	@GetMapping(value = "adminCSCenter.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<CsCenterVO> csCenterlistForAjax(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model) {
		readCSCenterboardList(currentPage, searchVO, model);
		return (PagingVO<CsCenterVO>) model.asMap().get("pagingVO");
	}

	
	@GetMapping("adminCSCenter.do")
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
		return "master/adminCSCenter";
	}
	
	@GetMapping("adminCSBoardDetail.do")
	public String readCSCenterBoard(
				int cs_cd, Model model
			) {
		CsCenterVO csCenterBoard = service.readSelectCSCenterBoard(cs_cd);
		service.incrementHit(cs_cd);
		String goPage = "";
		if(csCenterBoard ==null) {
			goPage = "redirect:/master/adminCSCenter.do";
		}else {
			goPage = "master/adminCSBoardDetail";
			model.addAttribute("csCenterBoard", csCenterBoard);
		}
		
		return goPage;
	}

}
