package kr.or.ddit.hobby.service;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

public interface IHobbyBestService {

	public List<classPiAttVO> selectBestList(hobbyBestPagingVO<classPiAttVO> pagingVO);
	
	public int selectBestCount(hobbyBestPagingVO<classPiAttVO> pagingVO);
}
