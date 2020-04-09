package kr.or.ddit.pmsproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.pmsproject.dao.IProjectBoardReplyDAO;
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
public class ProjectBoardReplyServiceImpl implements IProjectBoardReplyService {
	
	@Inject
	IProjectBoardReplyDAO pbrDAO;

	@Override
	public int readProjectBoardReplyCount(PagingVO<PwBoardCommentVO> pagingVO) {
		return pbrDAO.selectProjectBoardReplyCount(pagingVO);
	}

	@Override
	public List<PwBoardCommentVO> readProjectBoardReplyList(PagingVO<PwBoardCommentVO> pagingVO) {
		return pbrDAO.selectProjectBoardReplyList(pagingVO);
	}

	@Override
	public ServiceResult createReply(PwBoardCommentVO pbVO) {
		ServiceResult result = ServiceResult.FAIL;
		int rowcnt = pbrDAO.insertReply(pbVO);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult modifyReply(PwBoardCommentVO pbVO) {
		ServiceResult result = ServiceResult.FAIL;
		int rowcnt = pbrDAO.updateReply(pbVO);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult removeReply(PwBoardCommentVO pbVO) {
		ServiceResult result = ServiceResult.FAIL;
		int rowcnt = pbrDAO.deleteReply(pbVO);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public int readProjectMember(PwBoardCommentVO psVO) {
		return pbrDAO.selectProjectMember(psVO);
	}

}
