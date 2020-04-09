package kr.or.ddit.pmsproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FeedbackVO;
import kr.or.ddit.vo.PMListVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjListVO;
import kr.or.ddit.vo.PwListVO;

/**
 * @author 최효은
 * @since 2020. 3. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 13.      최효은       최초작성
 * 2020. 3. 17.      최효은       진행작업, 완료작업 메서드 추가
 * 2020. 3. 18.      최효은       프로젝트 회원 관련 메서드 추가
 * 
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
public interface IProjectService {
	
	/**
	 * 프로젝트 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ProjListVO> readProjectList(PagingVO<ProjListVO> pagingVO);
	/**
	 * 전체 프로젝트 개수 조회
	 * @param pagingVO
	 * @return
	 */
	public int readProjectCount(PagingVO<ProjListVO> pagingVO);
	/**
	 * 초대 대기중인 프로젝트 리스트 조회 페이징 처리
	 * @param pagingVO
	 * @return
	 */
	public List<ProjListVO> readProjAgreeList(PagingVO<ProjListVO> pagingVO);
	/**
	 * 초대 대기중인 프로젝트 리스트 전체 카운트 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int readProjAgreeCount(PagingVO<ProjListVO> pagingVO);
	/**
	 * 프로젝트 개별 조회
	 * @param proj
	 * @return
	 */
	public ProjListVO readProject(ProjListVO proj);
	/**
	 * 프로젝트 생성
	 * @param proj
	 * @return
	 */
	public ServiceResult createProject(ProjListVO proj);
	/**
	 * 프로젝트 생성 이후, 프로젝트에 초대된 멤버 추가 
	 * @param proJ
	 * @return
	 */
	public ServiceResult createProjectMember(ProjListVO proJ);
	/**
	 * 프로젝트 수정
	 * @param proj
	 * @return
	 */
	public ServiceResult modifyProject(ProjListVO proj);
	/**
	 * 프로젝트 삭제
	 * @param proj
	 * @return
	 */
	public ServiceResult removeProject(ProjListVO proj);
	/**
	 * 진행작업 개수
	 * @param proj
	 * @return
	 */
	public int readProgCount(ProjListVO proj);
	/**
	 * 완료작업 개수
	 * @param proj
	 * @return
	 */
	public int readCompCount(ProjListVO proj);
	/**
	 * 해당 프로젝트 작업의 소요시간
	 * @param proj_cd
	 * @return
	 */
	public List<ProjListVO> readProjectMember(int proj_cd);
	/**
	 * 해당 프로젝트의 참여 회원
	 * @param proj
	 * @return
	 */
	public int readTimeSum(ProjListVO proj);
	/**
	 * 간트차트 작업 리스트 조회
	 * @param pwList
	 * @return
	 */
	public List<PwListVO> readAllGanttWork(PwListVO pwList);
	/**
	 * 개별프로젝트 간트차트 작업 리스트 조회
	 * @param pwList
	 * @return
	 */
	public List<PwListVO> readGanttWork(PwListVO pwList);
	/**
	 * 확인하지 않은 피드백 리스트 조회
	 * @param mem_email
	 * @return
	 */
	public List<FeedbackVO> readNewFeedbackList(String mem_email);
	/**
	 * 확인한 피드백 업데이트
	 * @param pw_cd
	 * @return
	 */
	public int modifyNewFeedback(String pw_cd);
	
}
