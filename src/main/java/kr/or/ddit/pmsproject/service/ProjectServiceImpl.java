package kr.or.ddit.pmsproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.pmsproject.dao.IProjectDAO;
import kr.or.ddit.vo.FeedbackVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjListVO;
import kr.or.ddit.vo.PwListVO;

/**
 * @author 최효은
 * @since 2020. 3. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 13.      최효은       최초작성
 * 2020. 3. 17.      최효은       진행작업, 완료작업 메서드 추가
 * 2020. 3. 18.      최효은       프로젝트 회원 관련 메서드 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Service
public class ProjectServiceImpl implements IProjectService {

	@Inject
	IProjectDAO pmsProDAO;

	@Override
	public List<ProjListVO> readProjectList(PagingVO<ProjListVO> pagingVO) {
		return pmsProDAO.selectProjList(pagingVO);
	}

	@Override
	public int readProjectCount(PagingVO<ProjListVO> pagingVO) {
		return pmsProDAO.selectProjCount(pagingVO);
	}
	
	@Override
	public List<ProjListVO> readProjAgreeList(PagingVO<ProjListVO> pagingVO) {
		return pmsProDAO.selectProjAgreeList(pagingVO);
	}

	@Override
	public int readProjAgreeCount(PagingVO<ProjListVO> pagingVO) {
		return pmsProDAO.selectProjAgreeCount(pagingVO);
	}

	@Override
	public ProjListVO readProject(ProjListVO proj) {
		ProjListVO projList = pmsProDAO.selectProj(proj);
		if(projList==null) {
			throw new CommonException(proj.getProj_cd()+"에 해당하는 프로젝트가 없음.");
		}
		return projList;
	}
	
	@Transactional
	@Override
	public ServiceResult createProject(ProjListVO proj) {
		int rowcnt = pmsProDAO.insertProject(proj);
		ServiceResult result = ServiceResult.FAIL;
		if(rowcnt > 0) {
			ServiceResult rowcnt_mem = createProjectMember(proj);
			if(ServiceResult.OK.equals(rowcnt_mem)) {
				result = ServiceResult.OK;
			}
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult createProjectMember(ProjListVO proJ) {
		int rowcnt = pmsProDAO.insertProjectMemer(proJ);
		ServiceResult result = ServiceResult.OK;
		if(rowcnt <= 0) {
			result = ServiceResult.FAIL;
		}
		return result;
	}
	
	@Transactional
	@Override
	public ServiceResult modifyProject(ProjListVO proj) {
		int rowcnt = pmsProDAO.updateProject(proj);
		ServiceResult result = ServiceResult.OK;
		if(rowcnt <= 0) {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult removeProject(ProjListVO proj) {
		return null;
	}

	@Override
	public int readProgCount(ProjListVO proj) {
		return pmsProDAO.selectProgCount(proj);
	}

	@Override
	public int readCompCount(ProjListVO proj) {
		return pmsProDAO.selectCompCount(proj);
	}

	@Override
	public List<ProjListVO> readProjectMember(int proj_cd) {
		return pmsProDAO.selectProjectMember(proj_cd);
	}

	@Override
	public int readTimeSum(ProjListVO proj) {
		return pmsProDAO.selectTimeSum(proj);
	}

	@Override
	public List<PwListVO> readAllGanttWork(PwListVO pwList) {
		return pmsProDAO.selectAllGanttWork(pwList);
	}

	@Override
	public List<PwListVO> readGanttWork(PwListVO pwList) {
		return pmsProDAO.selectGanttWork(pwList);
	}

	@Override
	public List<FeedbackVO> readNewFeedbackList(String mem_email) {
		return pmsProDAO.selectNewFeedbackList(mem_email);
	}

	@Override
	public int modifyNewFeedback(String pw_cd) {
		return 0;
	}

}
