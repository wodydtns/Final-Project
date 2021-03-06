package kr.or.ddit.pmsproject.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PMSAttVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PwBoardVO;

/**
 * @author 작성자명
 * @since 2020. 4. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 4. 1.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

public interface IProjectBoardService {

	/**
	 * 프로젝트 게시판 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<PwBoardVO> readProjcetBoardList(PagingVO<PwBoardVO> pagingVO);
	/**
	 * 프로젝트 게시판 카운트 조회
	 * @param pagingVO
	 * @return
	 */
	public int readProjcetBoardCount(PagingVO<PwBoardVO> pagingVO);
	/**
	 * 프로젝트 게시판 상세보기
	 * @param bo_no
	 * @return
	 */
	public PwBoardVO readProjcetBoard(int stu_post_cd);
	/**
	 * 프로젝트 게시판 생성
	 * @param board
	 * @return
	 */
	public ServiceResult createProjcetBoard(PwBoardVO pbVO);
	/**
	 * 프로젝트 게시판 수정
	 * @param board
	 * @return
	 */
	public ServiceResult modifyProjcetBoard(PwBoardVO pbVO);
	/**
	 * 프로젝트 게시판 삭제
	 * @param board
	 * @return
	 */
	public ServiceResult removeProjcetBoard(PwBoardVO pbVO);
	/**
	 * 프로젝트 게시판 조회수
	 * @param bo_no
	 */
	public void incrementHit(int stu_post_cd);
	/**
	 * 프로젝트 멤버 조회
	 * @param pbVO
	 * @return
	 */
	public int readProjectMember(PwBoardVO pbVO);
	/**
	 * 게시판 첨부파일 다운로드
	 * @param st_cd
	 * @return
	 */
	public PMSAttVO selectBoardAttDownload(int st_cd);
	
	
}
