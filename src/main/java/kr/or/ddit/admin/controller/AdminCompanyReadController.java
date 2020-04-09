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

import kr.or.ddit.admin.service.IAdminCompanyService;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RecruitVO;
import kr.or.ddit.vo.SearchVO;

@Controller
@RequestMapping("/master")
public class AdminCompanyReadController {
	
	@Inject
	IAdminCompanyService service;
	
	@GetMapping(value="adminCompanyList.do", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<CompanyVO> companyAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			) {
		getCompanyList(currentPage, searchVO,  model);
		PagingVO<CompanyVO> pagingVO = (PagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@GetMapping("adminCompanyList.do")
	public String getCompanyList(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("search") SearchVO searchVO,
			Model model
			) {
		PagingVO<CompanyVO> pagingVO = new PagingVO<>( );
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.readCompanyCountTotal(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<CompanyVO> companyList = service.readCompanyList(pagingVO);
		pagingVO.setDataList(companyList);
		String viewName =null;
		model.addAttribute("pagingVO", pagingVO);
		viewName = "master/adminCompanyList";		
		return viewName;
	}
	

	@GetMapping(value="adminRecruitList.do", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<RecruitVO> listForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			) {
		getRecruitList(currentPage, searchVO,  model);
		PagingVO<RecruitVO> pagingVO = (PagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@GetMapping("adminRecruitList.do")
	public String getRecruitList(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("search") SearchVO searchVO,
			Model model
			) {
		PagingVO<RecruitVO> pagingVO = new PagingVO<>( );
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.readRecruitCountTotal(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<RecruitVO> recruitList = service.readRecruitList(pagingVO);
		pagingVO.setDataList(recruitList);
		String viewName =null;
		model.addAttribute("pagingVO", pagingVO);
		viewName = "master/adminRecruitList";		
		return viewName;
	}
}
