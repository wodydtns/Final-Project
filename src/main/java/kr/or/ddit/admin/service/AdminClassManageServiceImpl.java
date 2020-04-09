package kr.or.ddit.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.admin.dao.IAdminClassManageDAO;
import kr.or.ddit.vo.DoingClassVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class AdminClassManageServiceImpl implements IAdminClassManageService {
	
	@Inject
	IAdminClassManageDAO dao;
	
	@Override
	public List<PIVO> readSelectPIList(PagingVO pagingVO) {
		return dao.selectPIList(pagingVO);
	}

	@Transactional
	@Override
	public int updateAcceptPI(int pi_cd) {
		int cnt =dao.acceptPI(pi_cd);
		if(cnt <=0) {
			throw new RuntimeException("서버 에러");
		}
		return cnt; 
	}

	@Transactional
	@Override
	public int updateDenyPI(int pi_cd) {
		int cnt = dao.denyPI(pi_cd);
		if(cnt <=0) {
			throw new RuntimeException("서버 에러");
		}
		return cnt;
	}

	@Override
	public int readPIListToTalCount() {
		return dao.PIListToTalCount();
	}

	@Override
	public List<PIVO> readSelectPreTestList(PagingVO pagingVO) {
		return dao.selectPreTestList(pagingVO);
	}

	@Override
	public int readPreTestListTotal() {
		return dao.PreTestListTotal();
	}

	@Override
	public List<DoingClassVO> readSelectDoingList() {
		return dao.selectDoingList();
	}


}
