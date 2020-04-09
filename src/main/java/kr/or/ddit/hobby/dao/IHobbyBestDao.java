package kr.or.ddit.hobby.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

@Repository
public interface IHobbyBestDao {
	/**
	 * 인기순위별 클래스 조회 
	 * @param pagingVO 페이징처리
	 * @return
	 */
	public List<classPiAttVO> selectBestList(hobbyBestPagingVO<classPiAttVO> pagingVO);
	
	/**
	 * 인기순위별 클래스 카운트
	 * @param pagingVO 페이징처리
	 * @return
	 */
	public int selectBestCount(hobbyBestPagingVO<classPiAttVO> pagingVO);
}
