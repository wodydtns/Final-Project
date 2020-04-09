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

import kr.or.ddit.admin.service.IAdminStatsService;
import kr.or.ddit.vo.CategoryStatVO;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RisingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.TopWordVO;

/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 2020. 3. 16.              박재욱              관리자 통계 (전체회원, 크리에이터 수 조회)   
 * --------     --------    ----------------------
 * 2020. 3. 13.      작성자명   박재욱    최초작성  박재욱
 * 
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/master")
public class AdminStatsController {
	
	@Inject
	IAdminStatsService service;

	@GetMapping("adminStats.do")
	public String getWordCloudSource(
			
			Model model
			) {
		List<RisingVO> risingList = service.readRisingWord();
		List<TopWordVO> topList = service.readTopWord();
		List<PIVO> rankingList = service.readClassRanking();
		List<CategoryStatVO> trendRankingList = service.readTrendRanking();
		List<CategoryStatVO> trendRatioList = service.readTrendRatio();
		List<CategoryStatVO> youtubeSevenList= service.readYoutubeStatsSeven();
		List<CategoryStatVO> youtubeEightList = service.readYoutubeStatsEight();
		int creatorTotal = 0;
		creatorTotal  = service.readcreatorTotal();
		int memberTotal = 0;
		memberTotal  = service.readmemberTotal();
		int ongoingClass = 0;
		ongoingClass  = service.readOngoingClass();
		int piClass = 0;
		piClass  = service.readPiClass();
		int projectIng = 0;
		projectIng = service.readProjectIngTotal();
		
		model.addAttribute("youtubeSevenList", youtubeSevenList);
		model.addAttribute("youtubeEightList", youtubeEightList);
		model.addAttribute("trendRankingList",trendRankingList);
		model.addAttribute("trendRatioList",trendRatioList);
		model.addAttribute("projectIng", projectIng);
		model.addAttribute("rankingList", rankingList);
		model.addAttribute("memberTotal", memberTotal);
		model.addAttribute("ongoingClass", ongoingClass);
		model.addAttribute("piClass", piClass);
		// 리팩토링 필요
		model.addAttribute("risingList", risingList);
		model.addAttribute("topList", topList);
		model.addAttribute("creatorTotal", creatorTotal);
		return "master/adminStats";
	}
	
	
	

}
