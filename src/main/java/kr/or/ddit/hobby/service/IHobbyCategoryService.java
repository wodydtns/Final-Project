package kr.or.ddit.hobby.service;

import java.util.List;


import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.ClassPiVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.hobbyCategoryPagingVO;


public interface IHobbyCategoryService {
	public List<ClassPiVO> selectCategoryList(String cate_cd);
	public List<ClassPiVO> pagingCategoryList(hobbyCategoryPagingVO<ClassPiVO> pagingVO);
	public int selectCategoryCount(hobbyCategoryPagingVO<ClassPiVO> pagingVO);
}
