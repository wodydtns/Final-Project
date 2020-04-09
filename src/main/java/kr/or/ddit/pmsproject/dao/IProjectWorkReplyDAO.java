package kr.or.ddit.pmsproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjCommentVO;

/**
 * @author 작성자명
 * @since 2020. 3. 31.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 31.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IProjectWorkReplyDAO {

	/**
	 * 작업 댓글 전체 수 조회
	 * @param pcVO
	 * @return int
	 */
	public int selectProjectWorkReplyCount(PagingVO<ProjCommentVO> pcVO);
	/**
	 * 작업 댓글 리스트 조회
	 * @param pcVO
	 * @return List<ProjCommentVO>
	 */
	public List<ProjCommentVO> selectProjectWorkReplyList(PagingVO<ProjCommentVO> pcVO);
	/**
	 * 작업 댓글 등록
	 * @param pcVO
	 * @return int
	 */
	public int insertReply(ProjCommentVO pcVO);
	/**
	 * 작업 댓글 수정
	 * @param pcVO
	 * @return int
	 */
	public int updateReply(ProjCommentVO pcVO);
	/**
	 * 작업 댓글 삭제
	 * @param pcVO
	 * @return int
	 */
	public int deleteReply(ProjCommentVO pcVO);
	/**
	 * 프로젝트 회원 PM 코드 조회
	 * @param PMListVO
	 * @return int
	 */
	public int selectProjectMember(ProjCommentVO pcVO);
	
}
