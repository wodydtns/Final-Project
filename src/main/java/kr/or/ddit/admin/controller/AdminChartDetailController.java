package kr.or.ddit.admin.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.admin.service.IAdminChartDetailService;
import kr.or.ddit.vo.CategoryStatVO;

@Controller
@RequestMapping("/master")
public class AdminChartDetailController {
	
	@Inject
	IAdminChartDetailService service;
	
	@GetMapping("adminChartDetail.do")
	public void getCharts(Model model) {
		List<CategoryStatVO> RatioList = service.readSelectLikeRatio();
		model.addAttribute("RatioList", RatioList);

		List<CategoryStatVO> HoleAvgDataList = service.readSelectHoleAvgData();
		model.addAttribute("HoleAvgDataList", HoleAvgDataList);
		
		List<CategoryStatVO> HoleCountList = service.readSelectHoleCountData();
		List<CategoryStatVO> HoleLikesList = service.readSelectHoleLikesData();
		List<CategoryStatVO> HoleDislikesList = service.readSelectHoleDislikesData();
		List<CategoryStatVO> HoleCommentList = service.readSelectHoleCommentData();
		List<CategoryStatVO> CorrDataList = service.readSelectCorData();
		List<CategoryStatVO> RegDataList = service.readSelectRegData();
		
		model.addAttribute("CorrDataList", CorrDataList);
		model.addAttribute("RegDataList", RegDataList);
		model.addAttribute("HoleCountList", HoleCountList);
		model.addAttribute("HoleLikesList", HoleLikesList);
		model.addAttribute("HoleDislikesList", HoleDislikesList);
		model.addAttribute("HoleCommentList", HoleCommentList);
	}
}
