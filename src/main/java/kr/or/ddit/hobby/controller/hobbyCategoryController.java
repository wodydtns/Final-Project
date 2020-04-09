package kr.or.ddit.hobby.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.hobby.service.IHobbyCategoryService;
import kr.or.ddit.vo.ClassPiVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.hobbyCategoryPagingVO;

@Controller
public class hobbyCategoryController {

	@Inject
	IHobbyCategoryService service;
	
	@GetMapping(value="hobby/hobbyCategory.do", produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public hobbyCategoryPagingVO<ClassPiVO> listForAjax(
		@RequestParam(name="page", required=false, defaultValue="1") int currentPage
//		, @RequestParam(name="what", required=true) String cate_cd
		,@ModelAttribute("search") SearchVO searchVO
		,Model model ){
		
		classCategory(currentPage, searchVO, model);
		hobbyCategoryPagingVO<ClassPiVO> pagingVO = (hobbyCategoryPagingVO<ClassPiVO>) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@GetMapping(value="hobby/hobbyCategory.do")
	public String classCategory(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
//			, @RequestParam(name="what", required=true) String cate_cd
		,@ModelAttribute("search") SearchVO searchVO, 
		Model model  ) {
		
		
		hobbyCategoryPagingVO<ClassPiVO> pagingVO = new hobbyCategoryPagingVO<>();
//		pagingVO.setCate_cd(cate_cd);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.selectCategoryCount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<ClassPiVO> pagingCategoryList = service.pagingCategoryList(pagingVO);
		pagingVO.setDataList(pagingCategoryList);
		model.addAttribute("pagingVO", pagingVO);
		return "hobby/hobbyCategory";	
	}
}
