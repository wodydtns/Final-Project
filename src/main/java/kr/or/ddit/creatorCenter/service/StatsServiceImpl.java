package kr.or.ddit.creatorCenter.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.creatorCenter.dao.IStatsDAO;
import kr.or.ddit.vo.CCStatsVO;
@Service
public class StatsServiceImpl implements IStatsService {

	@Inject
	IStatsDAO dao;
	
	@Override
	public List<CCStatsVO> readProfit(String mem_email) {
		return dao.selectProfit(mem_email);
	}

	@Override
	public List<CCStatsVO> readClLike(String mem_email) {
		return dao.selectClLike(mem_email);
	}

	@Override
	public  List<CCStatsVO> readStuCnt(String mem_email) {
		return dao.selectStuCnt(mem_email);
	}

}
