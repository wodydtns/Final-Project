package kr.or.ddit.pmsproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.pmsproject.dao.IProjectWorkReplyDAO;
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
@Service
public class ProjectWorkReplyServiceImpl implements IProjectWorkReplyService {

	@Inject
	IProjectWorkReplyDAO pwrDAO;
	
	@Override
	public int readProjectWorkReplyCount(PagingVO<ProjCommentVO> pcVO) {
		return pwrDAO.selectProjectWorkReplyCount(pcVO);
	}

	@Override
	public List<ProjCommentVO> readProjectWorkReplyList(PagingVO<ProjCommentVO> pcVO) {
		return pwrDAO.selectProjectWorkReplyList(pcVO);
	}

	@Override
	public ServiceResult createReply(ProjCommentVO pcVO) {
		ServiceResult result = ServiceResult.FAIL;
		int rowcnt = pwrDAO.insertReply(pcVO);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult modifyReply(ProjCommentVO pcVO) {
		ServiceResult result = ServiceResult.FAIL;
		int rowcnt = pwrDAO.updateReply(pcVO);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult removeReply(ProjCommentVO pcVO) {
		ServiceResult result = ServiceResult.FAIL;
		int rowcnt = pwrDAO.deleteReply(pcVO);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public int readProjectMember(ProjCommentVO pcVO) {
		return pwrDAO.selectProjectMember(pcVO);
	}

}
