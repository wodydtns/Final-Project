package kr.or.ddit.creatorCenter.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.ddit.creatorCenter.service.IStatsService;
import kr.or.ddit.vo.CCStatsVO;
import kr.or.ddit.vo.MemberVO;

/**
 * @author 작성자명
 * @since 2020. 3. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 28.      김혜정       최초작성 (클래스 통계조회)
 *  * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ClassStatsController {

	@Inject
	IStatsService service;
	
	@GetMapping(value="creatorCenter/statsMain.do")
	public String doget(HttpSession session, Model model) {
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		String currentMem = member.getMem_email();
		List<CCStatsVO> profit = service.readProfit(currentMem);
		model.addAttribute("profit", profit);
		List<CCStatsVO> like = service.readClLike(currentMem);
		model.addAttribute("like", like);
//		List<CCStatsVO> stu_cnt = service.readStuCnt(currentMem);
//		model.addAttribute("stu_cnt", stu_cnt);
		
		return "ccStats/stats-main";
	}
}
