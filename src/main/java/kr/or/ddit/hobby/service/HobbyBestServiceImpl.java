package kr.or.ddit.hobby.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.hobby.dao.IHobbyBestDao;
import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

@Service
public class HobbyBestServiceImpl implements IHobbyBestService {

	@Inject
	IHobbyBestDao hobbyBestdao;
	
	@Override
	public List<classPiAttVO> selectBestList(hobbyBestPagingVO<classPiAttVO> pagingVO) {
		return hobbyBestdao.selectBestList(pagingVO);
	}

	@Override
	public int selectBestCount(hobbyBestPagingVO<classPiAttVO> pagingVO) {
		return hobbyBestdao.selectBestCount(pagingVO);
	}

}
