package kr.or.ddit.hobby.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

@Repository
public interface IHobbyPIDAO {

	/**
	 * 사전조사 리스트 출력
	 * @param pagingVO 페이징처리
	 * @return
	 */
	public List<classPiAttVO> selectPIList(hobbyBestPagingVO<classPiAttVO> pagingVO);
	
	/**
	 * 사전조사 카운트
	 * @param pagingVO 페이징처리
	 * @return
	 */
	public int selectPICount(hobbyBestPagingVO<classPiAttVO> pagingVO);
	
}
