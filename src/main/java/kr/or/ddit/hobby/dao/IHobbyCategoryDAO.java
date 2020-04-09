package kr.or.ddit.hobby.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.ClassPiVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.hobbyCategoryPagingVO;

@Repository
public interface IHobbyCategoryDAO {
	/**
	 * 카테고리별 화면 출력
	 * @param cate_cd
	 * @return
	 */
	public List<ClassPiVO> selectCategoryList(String cate_cd);
	
	/**
	 * 페이징 처리
	 * @param pagingVO
	 * @return
	 */
	public List<ClassPiVO> pagingCategoryList(hobbyCategoryPagingVO<ClassPiVO> pagingVO);
	
	/**
	 * 페이징 처리 위한 카운트
	 * @param pagingVO
	 * @return
	 */
	public int selectCategoryCount(hobbyCategoryPagingVO<ClassPiVO> pagingVO);
}
