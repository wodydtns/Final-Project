package kr.or.ddit.pmsproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.pmsproject.dao.IProjectMemberDAO;
import kr.or.ddit.pmsproject.dao.IProjectWorkDAO;
import kr.or.ddit.vo.PMListVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjListVO;
import kr.or.ddit.vo.PwListVO;

/**
 * @author 작성자명
 * @since 2020. 3. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 24.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Service
public class ProjectMemberServiceImpl implements IProjectMemberService {

	@Inject
	IProjectMemberDAO pmDAO;
	
	// 작업 목록 출력을 위한 의존
	@Inject
	IProjectWorkDAO pwDAO;
	
	@Override
	public int readMemberCount(PagingVO<PMListVO> pagingVO) {
		return pmDAO.selectMemberCount(pagingVO);
	}

	@Override
	public List<PMListVO> readMemberList(PagingVO<PMListVO> pagingVO) {
		return pmDAO.selectMemberList(pagingVO);
	}

	@Override
	public List<PMListVO> readAgreeType(PMListVO pmList) {
		return null;
	}

	@Override
	public ServiceResult createMember(PMListVO pmList) {
		ServiceResult result = ServiceResult.OK;
//		String[] mem_email_list = pmList.getMem_email_list();
//		int check = pmDAO.selectProjectMemberCount(pmList);
//		if(check > 0) {
//			// 임시로 씀. 프로젝트 회원이 1명 이상일 경우.
//			result = ServiceResult.INVALIDPASSWORD;
//			return result;
//		}
		int rowcnt = pmDAO.insertMember(pmList);
		if(rowcnt <= 0) {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult modifyMember(PMListVO pmList) {
		return null;
	}
	
	@Override
	public ServiceResult modifyAgreeYes(PMListVO pmList) {
		int rowcnt = pmDAO.updateAgreeYes(pmList);
		ServiceResult result = ServiceResult.OK;
		if(rowcnt <= 0) {
			result = ServiceResult.FAIL;
		}
		return result;
	}
	
	@Override
	public ServiceResult modifyAgreeNo(PMListVO pmList) {
		int rowcnt = pmDAO.updateAgreeNo(pmList);
		ServiceResult result = ServiceResult.OK;
		if(rowcnt <= 0) {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(PMListVO pmList) {
		int rowcnt = pmDAO.deleteMember(pmList);
		ServiceResult result = ServiceResult.OK;
//		if(rowcnt <= 0) {
//			result = ServiceResult.FAIL;
//		}
		return result;
	}
	
	@Override
	public List<PwListVO> readProjectWorkName(PMListVO pmList){
		return pwDAO.selectProjectWorkName(pmList);
	}

	@Override
	public List<ProjListVO> readProjectMember(int proj_cd) {
		return pmDAO.readProjectMember(proj_cd);
	}
	
}
