package kr.or.ddit.pmsproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
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
public interface IProjectWorkReplyService {

	/**
	 * 작업 댓글 전체 수 조회
	 * @param pcVO
	 * @return int
	 */
	public int readProjectWorkReplyCount(PagingVO<ProjCommentVO> pcVO);
	/**
	 * 작업 댓글 리스트 조회
	 * @param pcVO
	 * @return List<ProjCommentVO>
	 */
	public List<ProjCommentVO> readProjectWorkReplyList(PagingVO<ProjCommentVO> pcVO);
	/**
	 * 작업 댓글 등록
	 * @param pcVO
	 * @return int
	 */
	public ServiceResult createReply(ProjCommentVO pcVO);
	/**
	 * 작업 댓글 수정
	 * @param pcVO
	 * @return int
	 */
	public ServiceResult modifyReply(ProjCommentVO pcVO);
	/**
	 * 작업 댓글 삭제
	 * @param pcVO
	 * @return int
	 */
	public ServiceResult removeReply(ProjCommentVO pcVO);
	/**
	 * 프로젝트 회원 PM 코드 조회
	 * @param PMListVO
	 * @return int
	 */
	public int readProjectMember(ProjCommentVO psVO);
	
}
