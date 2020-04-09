package kr.or.ddit.hobby.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.hobby.service.IHobbyPIService;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

@Controller
public class hobbyPIController {
	
	@Inject
	IHobbyPIService service;
	
	@GetMapping(value="/hobby/hobbyPI.do", produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public hobbyBestPagingVO<classPiAttVO> listForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("search") SearchVO searchVO
			,Model model
			){
		hobbyPI(currentPage, searchVO, model);
		hobbyBestPagingVO<classPiAttVO> pagingVO = (hobbyBestPagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@GetMapping(value="/hobby/hobbyPI.do")
	public String hobbyPI(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("search") SearchVO searchVO
			,Model model
			) {
		hobbyBestPagingVO<classPiAttVO> pagingVO = new hobbyBestPagingVO<>(9, 3);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.selectPICount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<classPiAttVO> hobbyPi = service.selectPIList(pagingVO);
		pagingVO.setDataList(hobbyPi);
//		model.addAttribute("hobbyPi", hobbyPi);
		model.addAttribute("pagingVO", pagingVO);
		return "hobby/hobbyPI";
	}
}
