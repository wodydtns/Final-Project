package kr.or.ddit.hobby.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CommReplyVO;
import kr.or.ddit.vo.PagingVO;

public interface IHobbyDetailReplyService {

	public ServiceResult createReply(CommReplyVO reply);
	public int readReplyCount(PagingVO<CommReplyVO> pagingVO);
}
