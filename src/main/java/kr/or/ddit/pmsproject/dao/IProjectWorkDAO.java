package kr.or.ddit.pmsproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PMListVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PwCalendarVO;
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
 * 2020. 3. 16.      최효은       최초작성
 * 2020. 3. 20.      최효은       프로젝트별 작업 리스트 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IProjectWorkDAO {
	/**
	 * 회원별 전체 프로젝트 작업 리스트 조회 페이징 처리
	 * @param pagingVO
	 * @return List<PwListVO>
	 */
	public List<PwListVO> selectProjectWorkAllList(PagingVO<PwListVO> pagingVO);
	/**
	 * 회원별 전체 프로젝트 작업 리스트 전체 카운트 수 조회
	 * @param pagingVO
	 * @return int
	 */
	public int selectProjectWorkAllCount(PagingVO<PwListVO> pagingVO);
	/**
	 * 현 프로젝트의 작업 리스트 카운트 수 조회
	 * @param pagingVO
	 * @return int
	 */
	public int selectProjectWorkCount(PagingVO<PwListVO> pagingVO);
	/**
	 * 현 프로젝트의 작업 리스트 조회 페이징 처리
	 * @param pagingVO
	 * @return List<PwListVO>
	 */
	public List<PwListVO> selectProjectWorkList(PagingVO<PwListVO> pagingVO);
	/**
	 * 작업 개별 조회
	 * @param proj
	 * @return PwListVO
	 */
	public PwListVO selectProjectWork(int pw_cd);
	/**
	 * 작업 첨부파일 개별 조회
	 * @param proj
	 * @return PwListVO
	 */
	public PwListVO selectProjectWorkAtt(int pw_cd);
	/**
	 * 작업 생성
	 * @param pro
	 * @return int
	 */
	public int insertProjectWork(PwListVO pwList);
	/**
	 * 기본 작업 업데이트
	 * @param proj
	 * @return int
	 */
	public int updateProjectWork(PwListVO pwList);
	/**
	 * 담당자가 바뀔 경우, 확인코드(YN_F02) 변경
	 * @param proj
	 * @return int
	 */
	public int updateProjectWorkAgreeNo(int pw_cd);
	/**
	 * 알림에서 본인의 알림을 확인했을 경우 확인코드(YN_F01) 변경
	 * @param proj
	 * @return int
	 */
	public int updateProjectWorkAgreeYes(int pw_cd);
	/**
	 * 작업 삭제
	 * @param proj
	 * @return int
	 */
	public int deleteProjectWork(PwListVO pwList);
	/**
	 * 새로운 작업 리스트 조회
	 * @param mem_email
	 * @return
	 */
	public List<PwListVO> selectNewProjectWorkList(String mem_email);
	/**
	 * 확인한 작업리스트 수정
	 * @param peed_cd
	 * @return
	 */
	public int updateNewProjectWork(String peed_cd);
	/**
	 * 작업리스트 코드, 이름 조회
	 * @param pmList
	 * @return
	 */
	public List<PwListVO> selectProjectWorkName(PMListVO pmList);
	/**
	 * 달력에 프로젝트 내 일감을 표시하기 위한 메소드 (전체 , 특정 회원)  
	 * @param pwCalendarVO
	 * @return
	 */
	public List<PwCalendarVO> ProjectWorkingList(PwCalendarVO pwCalendarVO);
}
