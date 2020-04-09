package kr.or.ddit.pmsproject.controller;

import java.util.List;
import java.util.Locale.Category;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.pmsproject.dao.IProjectOthersDAO;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PMListVO;
import kr.or.ddit.vo.PriorityVO;
import kr.or.ddit.vo.ProgressVO;
import kr.or.ddit.vo.PwListVO;
import kr.or.ddit.vo.YNCheckVO;

/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 13.      최효은       최초작성
 * 2020. 3. 19.      최효은       검색을 위한 PROGRESS, PRIORITY, PwList 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@ControllerAdvice
public class ProjectCommonController {
	
	@Inject
	IProjectOthersDAO othersDAO;
	
	// 카테고리 명 출력
	@ModelAttribute("categoryList")
	public List<CategoryVO> categoryList(){
		return othersDAO.selecrCategoryList();
	}
	// 진행상황 명 출력
	@ModelAttribute("progressList")
	public List<ProgressVO> progressList(){
		return othersDAO.selectProgressList();
	}
	// 우선순위 명 출력
	@ModelAttribute("priorityList")
	public List<PriorityVO> priorityList(){
		return othersDAO.selectPriorityList();
	}
	// 프로젝트 수락 여부 출력
	@ModelAttribute("agreeList")
	public List<YNCheckVO> agreeList(){
		return othersDAO.selectAgreeYNCheck();
	}
	// 프로젝트 탈퇴여부 출력
	@ModelAttribute("deleteList")
	public List<YNCheckVO> deleteList(){
		return othersDAO.selectDeleteYNCheck();
	}
	// 확인하지 않은 작업리스트 출력 
//	@ModelAttribute("PMWorkCount")
//	public int PMWorkCount(HttpSession session){
//		MemberVO authMember = new MemberVO();
//		if(session.getAttribute("authMember")!=null) {
//			authMember = (MemberVO) session.getAttribute("authMember");
//		}else {
//			authMember.setMem_email("admin");
//		}
//		return othersDAO.selectMemberWorkCount(authMember.getMem_email());
//	}
//	// 확인하지 않은 피드백 출력 
//	@ModelAttribute("FeedbackCount")
//	public int FeedbackCount(HttpSession session){
//		MemberVO authMember = new MemberVO();
//		if(session.getAttribute("authMember")!=null) {
//			authMember = (MemberVO) session.getAttribute("authMember");
//		}else {
//			authMember.setMem_email("admin");
//		}
//		return othersDAO.selectPMSFeedbackCount(authMember.getMem_email());
//	}
	// 프로젝트 멤버 조회 
	@ModelAttribute("projectMemberList")
	public List<PMListVO> projectMemberList(HttpSession session){
		int proj_cd = 0;
		try {
			proj_cd = (int) session.getAttribute("proj_cd");
		} catch (NullPointerException e) {
			proj_cd = 1;
		}
		return othersDAO.selectProjectMember(proj_cd);
	}
	
	
}
