package kr.or.ddit.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.dao.IAdminChartDetailDAO;
import kr.or.ddit.vo.CategoryStatVO;

@Service
public class AdminChartDetailServiceImpl implements IAdminChartDetailService {

	@Inject
	IAdminChartDetailDAO dao;
	
	@Override
	public List<CategoryStatVO> readSelectLikeRatio() {
		return dao.selectLikeRatio();
	}

	@Override
	public List<CategoryStatVO> readSelectHoleAvgData() {
		return dao.selectHoleAvgData();
	}

	@Override
	public List<CategoryStatVO> readSelectHoleLikesData() {
		return dao.selectHoleLikesData();
	}

	@Override
	public List<CategoryStatVO> readSelectHoleDislikesData() {
		return dao.selectHoleDislikesData();
	}

	@Override
	public List<CategoryStatVO> readSelectHoleCountData() {
		return dao.selectHoleCountData();
	}

	@Override
	public List<CategoryStatVO> readSelectHoleCommentData() {
		return dao.selectHoleCommentData();
	}

	@Override
	public List<CategoryStatVO> readSelectCorData() {
		return dao.selectCorrData();
	}

	@Override
	public List<CategoryStatVO> readSelectRegData() {
		return dao.selectRegData();
	}

}
