package kr.or.ddit.hobby.service;


import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassCommunityVO;
import kr.or.ddit.vo.ClassDetailPagingVO;
import kr.or.ddit.vo.CommReplyVO;
import kr.or.ddit.vo.CreatorVO;
import kr.or.ddit.vo.PagingVO;

public interface IHobbyDetailService {

	public CreatorVO selectCreator(int cl_cd);
	public List<CreatorVO> selectCreatorList(int cl_cd);
	public List<ClassCommunityVO> selectCommunityList(int cl_cd);
	public List<ClassCommunityVO> selectCommunityList2(int cl_cd);
	public List<CommReplyVO> selectComunityReply(int cl_cd);
	public ServiceResult createReply(CommReplyVO commReplyVO);
	public int createBoard(CommReplyVO commReplyVO);
	
}
