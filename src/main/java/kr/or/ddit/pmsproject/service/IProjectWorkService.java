package kr.or.ddit.pmsproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PMSAttVO;
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
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
public interface IProjectWorkService {

	/**
	 * 회원별 전체 프로젝트 작업 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<PwListVO> readProjectWorkList(PagingVO<PwListVO> pagingVO);
	/**
	 * 회원별 전체 프로젝트 작업 개수 조회
	 * @param pagingVO
	 * @return
	 */
	public int readProjectWorkCount(PagingVO<PwListVO> pagingVO);
	/**
	 * 현 프로젝트의 작업 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<PwListVO> readProjectWorkAllList(PagingVO<PwListVO> pagingVO);
	/**
	 * 현 프로젝트의 전체 작업 개수 조회
	 * @param pagingVO
	 * @return
	 */
	public int readProjectWorkAllCount(PagingVO<PwListVO> pagingVO);
	/**
	 * 작업 개별 조회
	 * @param pw_cd
	 * @return
	 */
	public PwListVO readProjectWork(int pw_cd);
	/**
	 * 작업 첨부파일 개별 조회
	 * @param pw_cd
	 * @return
	 */
	public PwListVO readProjectWorkAtt(int pw_cd);
	/**
	 * 작업 생성
	 * @param proj
	 * @return
	 */
	public ServiceResult createProjectWork(PwListVO proj);
	/**
	 * 작업 수정
	 * @param proj
	 * @return
	 */
	public ServiceResult modifyProjectWork(PwListVO proj);
	/**
	 * 작업 삭제
	 * @param pwList
	 * @return
	 */
	public ServiceResult removeProjectWork(PwListVO pwList);
	/**
	 * 새로운 작업 리스트 조회
	 * @param mem_email
	 * @return
	 */
	public List<PwListVO> readNewProjectWorkList(String mem_email);
	/**
	 * 확인한 작업리스트 수정
	 * @param peed_cd
	 * @return
	 */
	public int modifyNewProjectWork(String peed_cd);
	/**
	 * 첨부파일 다운로드
	 * @param st_cd
	 * @return
	 */
	public PMSAttVO projectWorkAttDownload(int st_cd);
	/**
	 * 달력에 프로젝트 내 일감을 표시하기 위한 메소드 (전체 , 특정 회원)  
	 * @param pwCalendarVO
	 * @return
	 */
	
	public List<PwCalendarVO> readProjectWorkingCalender(PwCalendarVO pwCalendarVO);
}
