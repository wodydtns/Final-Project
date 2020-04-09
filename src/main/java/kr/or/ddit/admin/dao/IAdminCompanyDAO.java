package kr.or.ddit.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RecruitVO;

@Repository
public interface IAdminCompanyDAO {
	/**
	 * 회사 리스트
	 * @return 회사 리스트 
	 */
	List<CompanyVO> companyList(PagingVO pagingVO);
	
	/**
	 * 채용공고 리스트
	 * @return 채용공고 리스트
	 */
	List<RecruitVO> recruitList(PagingVO pagingVO);
	
	/**
	 * 총 회사 수
	 * @param pagingVO
	 * @return
	 */
	public int companyCountTotal(PagingVO pagingVO);

	/**
	 * 총 채용공고 수
	 * @param pagingVO
	 * @return
	 */
	public int recruitCountTotal(PagingVO pagingVO);
}
