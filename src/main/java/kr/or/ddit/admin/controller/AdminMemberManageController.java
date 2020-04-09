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

import kr.or.ddit.admin.service.IAdminMemberManageService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReportVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 2020. 3. 16.              박재욱               paging 처리를 위한 list조회 메소드 추가 - 페이징 처리 완료
 * --------     --------    ----------------------
 * 2020. 3. 13.      작성자명  박재욱     최초작성  박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 *      </pre>
 */
@Controller
@RequestMapping("/master")
public class AdminMemberManageController {

	@Inject
	IAdminMemberManageService service;

	@GetMapping(value = "adminMemberManage.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<MemberVO> memberlistForAjax(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model) {
		memberList(currentPage, searchVO, model);
		return (PagingVO<MemberVO>) model.asMap().get("pagingVO");
	}

	@GetMapping(value = "adminMemberManage.do")
	public String memberList(@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model) {
		PagingVO<MemberVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);

		pagingVO.setCurrentPage(currentPage);

		int totalRecord = service.readMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<MemberVO> memberList = service.readMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		model.addAttribute("pagingVO", pagingVO);
		return "master/adminMemberManage";
	}

	// CREATOR ZONE
	@GetMapping(value = "adminCreatorManage.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<MemberVO> creatorlistForAjax(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model) {
		CreatorList(currentPage, searchVO, model);
		return (PagingVO<MemberVO>) model.asMap().get("pagingVO");
	}

	@GetMapping("adminCreatorManage.do")
	public String CreatorList(@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model) {
		PagingVO<MemberVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);

		pagingVO.setCurrentPage(currentPage);

		int totalRecord = service.readCreatorCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<MemberVO> creatorList = service.readCreatorList(pagingVO);
		pagingVO.setDataList(creatorList);
		model.addAttribute("pagingVO", pagingVO);
		return "master/adminCreatorManage";
	}

	// COMPANY ZONE
	@GetMapping(value = "adminCompanyManage.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<MemberVO> CompanylistForAjax(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model) {
		CompanyList(currentPage, searchVO, model);
		return (PagingVO<MemberVO>) model.asMap().get("pagingVO");
	}

	@GetMapping("adminCompanyManage.do")
	public String CompanyList(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model) {
		PagingVO<MemberVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);

		pagingVO.setCurrentPage(currentPage);

		int totalRecord = service.readCompanyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<MemberVO> creatorList = service.readCompanyList(pagingVO);
		pagingVO.setDataList(creatorList);
		model.addAttribute("pagingVO", pagingVO);
		return "master/adminCompanyManage";
	}
	
	// blockList ZONE
		@GetMapping(value = "adminBlockManage.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public PagingVO<ReportVO> BlocklistForAjax(
				@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
				@ModelAttribute("search") SearchVO searchVO, Model model) {
			BlockList(currentPage, searchVO, model);
			return (PagingVO<ReportVO>) model.asMap().get("pagingVO");
		}

	@GetMapping("adminBlockManage.do")
	public String BlockList(@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("search") SearchVO searchVO, Model model) {
		PagingVO<ReportVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);

		pagingVO.setCurrentPage(currentPage);

		int totalRecord = service.readBlockCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<ReportVO> creatorList = service.readBlockList(pagingVO);
		pagingVO.setDataList(creatorList);
		model.addAttribute("pagingVO", pagingVO);
		return "master/adminBlockManage";
	}

}
