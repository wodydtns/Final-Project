package kr.or.ddit.pmsproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
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
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IProjectDAO {
	/**
	 * 진행중인 프로젝트 리스트 조회 페이징 처리
	 * @param pagingVO
	 * @return
	 */
	public List<ProjListVO> selectProjList(PagingVO<ProjListVO> pagingVO);
	/**
	 * 진행중인 프로젝트 리스트 전체 카운트 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectProjCount(PagingVO<ProjListVO> pagingVO);
	/**
	 * 초대 대기중인 프로젝트 리스트 조회 페이징 처리
	 * @param pagingVO
	 * @return
	 */
	public List<ProjListVO> selectProjAgreeList(PagingVO<ProjListVO> pagingVO);
	/**
	 * 초대 대기중인 프로젝트 리스트 전체 카운트 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectProjAgreeCount(PagingVO<ProjListVO> pagingVO);
	/**
	 * 프로젝트 개별 조회
	 * @param proj
	 * @return
	 */
	public ProjListVO selectProj(ProjListVO proJ);
	/**
	 * 프로젝트 생성
	 * @param pro
	 * @return
	 */
	public int insertProject(ProjListVO proJ);
	/**
	 * 프로젝트 생성 이후, 멤버리스트에 리더 추가 
	 * @param pmList
	 * @return
	 */
	public int insertProjectMemer(ProjListVO proJ);
	/**
	 * 프로젝트 업데이트
	 * @param proj
	 * @return
	 */
	public int updateProject(ProjListVO proj);
	/**
	 * 프로젝트 삭제
	 * @param proj
	 * @return
	 */
	public int deleteProject(ProjListVO proj);
	/**
	 * 진행작업 개수
	 * @param proj
	 * @return
	 */
	public int selectProgCount(ProjListVO proj);
	/**
	 * 완료작업 개수
	 * @param proj
	 * @return
	 */
	public int selectCompCount(ProjListVO proj);
	/**
	 * 해당 프로젝트의 참여 회원
	 * @param proj
	 * @return
	 */
	public List<ProjListVO> selectProjectMember(int proj_cd);
	/**
	 * 해당 프로젝트 작업의 소요시간
	 * @param proj
	 * @return
	 */
	public int selectTimeSum(ProjListVO proj);
	/**
	 * 간트차트 작업 리스트 조회
	 * @param pwList
	 * @return
	 */
	public List<PwListVO> selectAllGanttWork(PwListVO pwList);
	/**
	 * 개별프로젝트 간트차트 작업 리스트 조회
	 * @param pwList
	 * @return
	 */
	public List<PwListVO> selectGanttWork(PwListVO pwList);
	/**
	 * 확인하지 않은 피드백 리스트 조회
	 * @param mem_email
	 * @return
	 */
	public List<FeedbackVO> selectNewFeedbackList(String mem_email);
	/**
	 * 확인한 피드백 업데이트
	 * @param pw_cd
	 * @return
	 */
	public int updateNewFeedback(String pw_cd);
}
