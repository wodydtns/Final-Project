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

import kr.or.ddit.admin.service.IAdminClassManageService;
import kr.or.ddit.vo.DoingClassVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 작성자명
 * @since 2020. 3. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 24.      작성자명  박재욱               최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/master")
public class AdminClassManageController {
	
	@Inject
	IAdminClassManageService service;
	
	@GetMapping(value="adminClassList.do", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<PIVO> listForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			) {
		classList(currentPage, searchVO, model);
		PagingVO<PIVO> pagingVO =(PagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@GetMapping("adminClassList.do")
	public String classList(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			) {
		PagingVO<PIVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);
		
		pagingVO.setTotalRecord(service.readPIListToTalCount());
		
		pagingVO.setCurrentPage(currentPage);
		List<PIVO> boardList = service.readSelectPIList(pagingVO);
		pagingVO.setDataList(boardList);
		String viewName = null;
		model.addAttribute("pagingVO", pagingVO);
		viewName = "master/adminClassList";
		return viewName;
	}
	
	@GetMapping("accept.do")
	public String acceptPI(
			@RequestParam(name="permit", required=true) int pi_cd,
			Model model
			) {
		int result = service.updateAcceptPI(pi_cd);
		String message = "";
		if(result >0) {
			message = "승인 처리";
		}else {
			message = "서버 에러";
		}
		model.addAttribute("message", message);
		return "master/adminClassList";
	}
	
	@GetMapping("deny.do")
	public String denyPI(	
			@RequestParam(name="permit", required=true) int pi_cd,
			Model model) {
		int result = service.updateDenyPI(pi_cd);
		String message = "";
		if(result >0) {
			message = "미승인 처리";
		}else {
			message = "서버 에러";
		}
		model.addAttribute("message", message);
		return "master/adminClassList";
	}
	// 사전조사 중인 클래스 리스트
	@GetMapping(value="adminPreClassList.do", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<PIVO> preListForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			) {
		preClassList(currentPage, searchVO, model);
		PagingVO<PIVO> pagingVO =(PagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@GetMapping("adminPreClassList.do")
	public String preClassList(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			) {
		PagingVO<PIVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);
		
		pagingVO.setTotalRecord(service.readPreTestListTotal());
		
		pagingVO.setCurrentPage(currentPage);
		List<PIVO> preBoardList = service.readSelectPreTestList(pagingVO);
		pagingVO.setDataList(preBoardList);
		String viewName = null;
		model.addAttribute("pagingVO", pagingVO);
		viewName = "master/adminPreClassList";
		return viewName;
	}
	
		@GetMapping("adminDoingClassList.do")
		public String doingClassList(
				 Model model
				) {
			String viewName = null;
			List<DoingClassVO> doingBoardList = service.readSelectDoingList();
			model.addAttribute("doingBoardList", doingBoardList);
			viewName = "master/adminDoingClassList";
			return viewName;
		}
	
}
