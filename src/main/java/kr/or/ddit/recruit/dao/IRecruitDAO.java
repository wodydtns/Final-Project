package kr.or.ddit.recruit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RecruitVO;

@Repository
public interface IRecruitDAO {

	
	
	/**
	 * 취업공고 리스트 조회 페이징 처리
	 * @param pagingVO
	 * @return
	 */
	public List<RecruitVO> selectRecruitList(PagingVO<RecruitVO> pagingVO);
	
	/**
	 * 단일 취업공고 리스트 조회
	 * @return
	 */
	public List<RecruitVO> selectRecruitList2();
	
	/**
	 * 취업공고 리스트 전체 카운트 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectRecruitCount(PagingVO<RecruitVO> pagingVO);
	
	
}
