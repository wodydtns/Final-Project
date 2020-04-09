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

import kr.or.ddit.hobby.service.IHobbyBestService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

@Controller
public class hobbyBestController {

	@Inject
	IHobbyBestService service;
	
	@GetMapping(value="/hobby/hobbyBest.do", produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public hobbyBestPagingVO<classPiAttVO> listForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("search") SearchVO searchVO
			,Model model
			){
		hobbyBest(currentPage, searchVO, model);
		hobbyBestPagingVO<classPiAttVO> pagingVO = (hobbyBestPagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@GetMapping(value="/hobby/hobbyBest.do")
	public String hobbyBest(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("search") SearchVO searchVO
			,Model model
			) {
		hobbyBestPagingVO<classPiAttVO> pagingVO = new hobbyBestPagingVO<>(9, 3);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.selectBestCount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<classPiAttVO> besttList = service.selectBestList(pagingVO);
		pagingVO.setDataList(besttList);
		model.addAttribute("pagingVO", pagingVO);
		return "hobby/hobbyBest";
	}
}
