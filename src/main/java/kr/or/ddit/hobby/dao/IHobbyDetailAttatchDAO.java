package kr.or.ddit.hobby.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.hobbyDetailAttatchVO;
import kr.or.ddit.vo.CommReplyVO;

@Repository
public interface IHobbyDetailAttatchDAO {

	public int insertAttatch(CommReplyVO commReplyVO); 
	public hobbyDetailAttatchVO selectAttatch(int att_no);
}
