package kr.or.ddit.hobby.service;

import java.util.List;

import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

public interface IHobbyPIService {

	public List<classPiAttVO> selectPIList(hobbyBestPagingVO<classPiAttVO> pagingVO);
	public int selectPICount(hobbyBestPagingVO<classPiAttVO> pagingVO);
}
