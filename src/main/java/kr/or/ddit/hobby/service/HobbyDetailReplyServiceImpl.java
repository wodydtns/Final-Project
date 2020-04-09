package kr.or.ddit.hobby.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hobby.dao.IHobbyDetailReplyDAO;
import kr.or.ddit.vo.CommReplyVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class HobbyDetailReplyServiceImpl implements IHobbyDetailReplyService {
	IHobbyDetailReplyDAO replydao;

	@Override
	public ServiceResult createReply(CommReplyVO reply) {
		ServiceResult result = null;
		int rowcnt = replydao.insertReply(reply);
		if(rowcnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public int readReplyCount(PagingVO<CommReplyVO> pagingVO) {
		return replydao.selectTeplyCount(pagingVO);
	}



}
