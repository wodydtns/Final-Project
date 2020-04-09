package kr.or.ddit.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.dao.IAdminCompanyDAO;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RecruitVO;

@Service
public class IAdminCompanyServiceImpl implements IAdminCompanyService {

	@Inject
	IAdminCompanyDAO dao;

	@Override
	public List<CompanyVO> readCompanyList(PagingVO pagingVO) {
		return dao.companyList(pagingVO);
	}

	@Override
	public List<RecruitVO> readRecruitList(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return dao.recruitList(pagingVO);
	}

	@Override
	public int readCompanyCountTotal(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return dao.companyCountTotal(pagingVO);
	}

	@Override
	public int readRecruitCountTotal(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return dao.recruitCountTotal(pagingVO);
	}
	

	
}
