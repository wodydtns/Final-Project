package kr.or.ddit.creatorCenter.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.creatorCenter.service.IPIService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PIVO;

/**
 * @author 작성자명
 * @since 2020. 3. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 20.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/creatorCenter")
public class PIRetrieveController {
	
	@Inject
	IPIService service;
	
	
	//나의 진행중인 pi조회
	@GetMapping(value="pi_wait_List.do")
	public String wait_List(HttpSession session, Model model) {
		
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		List<PIVO> dataList = service.read_My_PI_wait_List(member.getMem_email()); 
		model.addAttribute("dataList", dataList);
		return "creatorCenter/pi-wait"; //나의클래스목록
	}
	
	
	//나의 진행중인 pi조회
	@GetMapping(value="pi_ing_List.do")
	public String ing_List(HttpSession session, Model model) {
		
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		List<PIVO> dataList = service.read_My_PI_ing_List(member.getMem_email()); 
		model.addAttribute("dataList", dataList);
		return "creatorCenter/pi-ing"; //나의클래스목록
	}
	
	//입력했던 pi정보 조회
	@GetMapping(value="pi_view.do")
	public String ing_view(@RequestParam(name="what") int pi_cd, Model model) {
		PIVO pi = service.read_My_pi(pi_cd);
		model.addAttribute("pi", pi);
		return "creatorCenter/piView";
	}
	
	//나의 종료된 pi조회
	@GetMapping(value="pi_end_List.do")
	public String end_List(HttpSession session, Model model) {
		
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		List<PIVO> dataList = service.read_My_PI_end_List(member.getMem_email()); 
		model.addAttribute("dataList", dataList);
		return "creatorCenter/pi-end"; //나의클래스목록
	}
}
