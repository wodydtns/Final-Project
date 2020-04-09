package kr.or.ddit.hobby.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CommReplyVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface IHobbyDetailReplyDAO {
	public int insertReply(CommReplyVO reply);
	
	public int selectTeplyCount(PagingVO<CommReplyVO> pagingVO);
	
	
	
}
