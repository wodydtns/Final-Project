package kr.or.ddit.admin.service;

import java.util.List;

import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RecruitVO;

public interface IAdminCompanyService {
	
	List<CompanyVO> readCompanyList(PagingVO pagingVO);
	
	List<RecruitVO> readRecruitList(PagingVO pagingVO);
	
	public int readCompanyCountTotal(PagingVO pagingVO);
	
	public int readRecruitCountTotal(PagingVO pagingVO);
}
