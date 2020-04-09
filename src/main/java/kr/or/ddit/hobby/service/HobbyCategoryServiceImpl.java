package kr.or.ddit.hobby.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.hobby.dao.IHobbyCategoryDAO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.ClassPiVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.hobbyCategoryPagingVO;


@Service
public class HobbyCategoryServiceImpl implements IHobbyCategoryService {
	
	@Inject
	IHobbyCategoryDAO hobbycategorydao;

	@Override
	public List<ClassPiVO> selectCategoryList(String cate_cd) {
		return hobbycategorydao.selectCategoryList(cate_cd);
	}

	@Override
	public List<ClassPiVO> pagingCategoryList(hobbyCategoryPagingVO<ClassPiVO> pagingVO) {
		return hobbycategorydao.pagingCategoryList(pagingVO);
	}

	@Override
	public int selectCategoryCount(hobbyCategoryPagingVO<ClassPiVO> pagingVO) {
		return hobbycategorydao.selectCategoryCount(pagingVO);
	}



}
