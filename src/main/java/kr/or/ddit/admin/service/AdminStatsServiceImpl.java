package kr.or.ddit.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.dao.IAdminStatsDAO;
import kr.or.ddit.vo.CategoryStatVO;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RisingVO;
import kr.or.ddit.vo.TopWordVO;
/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 2020. 3. 16.              박재욱              관리자 통계 (전체회원, 크리에이터 수 조회)
 * --------     --------    ----------------------
 * 2020. 3. 13.      작성자명   박재욱    최초작성   박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Service
public class AdminStatsServiceImpl implements IAdminStatsService{

	@Inject
	IAdminStatsDAO statsDAO;
	
	@Override
	public List<RisingVO> readRisingWord() {
		
		return statsDAO.selectRisingWord();
	}

	@Override
	public List<TopWordVO> readTopWord() {
		
		return statsDAO.selectTopWord();
	}

	@Override
	public int readcreatorTotal() {
		int cnt = 0;
		cnt = statsDAO.creatorTotal();
		return cnt;
	}

	@Override
	public int readmemberTotal() {
		int cnt = 0;
		cnt = statsDAO.memberTotal();
		return cnt;
	}

	@Override
	public int readOngoingClass() {
		int cnt = 0;
		cnt = statsDAO.ongoingClass();
		return cnt;
	}

	@Override
	public int readPiClass() {
		int cnt = 0;
		cnt = statsDAO.PiClass();
		return cnt;
	}

	@Override
	public List<PIVO> readClassRanking() {
		return statsDAO.classRanking();
	}

	@Override
	public int readProjectIngTotal() {
		int cnt = 0;
		cnt = statsDAO.projectIngTotal();
		return cnt;
	}

	@Override
	public List<CategoryStatVO> readTrendRanking() {
		
		return statsDAO.TrendRanking();
	}

	@Override
	public List<CategoryStatVO> readTrendRatio() {
		return statsDAO.TrendRatio();
	}

	@Override
	public List<CategoryStatVO> readYoutubeStatsSeven() {
		return statsDAO.youtubeStatsSeven();
	}

	@Override
	public List<CategoryStatVO> readYoutubeStatsEight() {
		return statsDAO.youtubeStatsEight();
	}

	
}
