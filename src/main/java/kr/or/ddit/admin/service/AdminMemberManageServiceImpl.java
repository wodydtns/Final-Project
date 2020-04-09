package kr.or.ddit.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.dao.IAdminMemberManageDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReportVO;
/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 13.      작성자명   박재욱    최초작성   박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Service
public class AdminMemberManageServiceImpl implements IAdminMemberManageService {

	@Inject
	IAdminMemberManageDAO memberManageDAO;

	@Override
	public List<MemberVO> readMemberList(PagingVO pagingVO) {
		
		return memberManageDAO.selectMemberList(pagingVO);
	}

	@Override
	public List<MemberVO> readCreatorList(PagingVO pagingVO) {
		return memberManageDAO.selectCreatorList(pagingVO);
	}

	@Override
	public List<MemberVO> readCompanyList(PagingVO pagingVO) {
		return memberManageDAO.selectCompanyList(pagingVO);
	}

	@Override
	public int readMemberCount(PagingVO pagingVO) {
		return memberManageDAO.selectMemberCount(pagingVO);
	}

	@Override
	public int readCreatorCount(PagingVO pagingVO) {
		return memberManageDAO.selectCreatorCount(pagingVO);
	}

	@Override
	public int readCompanyCount(PagingVO pagingVO) {
		return memberManageDAO.selectCompanyCount(pagingVO);
	}

	@Override
	public List<ReportVO> readBlockList(PagingVO pagingVO) {
		return memberManageDAO.selectBlockList(pagingVO);
	}

	@Override
	public int readBlockCount(PagingVO pagingVO) {
		return memberManageDAO.selectBlockCount(pagingVO);
	}

	@Override
	public int executeDeleteMember(String memEmail) {
		int cnt =0;
		cnt = memberManageDAO.deleteMember(memEmail);
		return cnt;
	}
	

}
