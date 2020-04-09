package kr.or.ddit.pmsproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PwBoardCommentVO;
/**
 * @author 작성자명
 * @since 2020. 4. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 4. 2.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

@Service
public interface IProjectBoardReplyService {

	/**
	 * 게시글 댓글 전체 수 조회
	 * @param pagingVO
	 * @return int
	 */
	public int readProjectBoardReplyCount(PagingVO<PwBoardCommentVO> pagingVO);
	/**
	 * 게시글 댓글 리스트 조회
	 * @param pagingVO
	 * @return List<PwBoardCommentVO>
	 */
	public List<PwBoardCommentVO> readProjectBoardReplyList(PagingVO<PwBoardCommentVO> pagingVO);
	/**
	 * 게시글 댓글 등록
	 * @param pbVO
	 * @return int
	 */
	public ServiceResult createReply(PwBoardCommentVO pbVO);
	/**
	 * 게시글 댓글 수정
	 * @param pbVO
	 * @return int
	 */
	public ServiceResult modifyReply(PwBoardCommentVO pbVO);
	/**
	 * 게시글 댓글 삭제
	 * @param pbVO
	 * @return int
	 */
	public ServiceResult removeReply(PwBoardCommentVO pbVO);
	/**
	 * 프로젝트 회원 PM 코드 조회
	 * @param PMListVO
	 * @return int
	 */
	public int readProjectMember(PwBoardCommentVO psVO);
}
