package kr.or.ddit.hobby.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.hobby.dao.IHobbyPIDAO;
import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

@Service
public class HobbyPIServiceImpl implements IHobbyPIService {
	
	@Inject
	IHobbyPIDAO hobbyPidao;
	
	@Override
	public List<classPiAttVO> selectPIList(hobbyBestPagingVO<classPiAttVO> pagingVO) {
		return hobbyPidao.selectPIList(pagingVO);
	}

	@Override
	public int selectPICount(hobbyBestPagingVO<classPiAttVO> pagingVO) {
		return hobbyPidao.selectPICount(pagingVO);
	}

}
