package kr.or.ddit.recruit.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.recruit.dao.IRecruitDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RecruitVO;

/**
 * @author 작성자명
 * @since 2020. 3. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 16.      최도혁       최초작성
 * 2020. 3. 17.      최도혁       단일조회 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Service
public class recruitServiceImpl implements IRecruitService {
	@Inject
	IRecruitDAO recruitdao;

	@Override
	public List<RecruitVO> selectRecruitList(PagingVO<RecruitVO> pagingVO) {
		return recruitdao.selectRecruitList(pagingVO);
	}

	@Override
	public int selectRecruitCount(PagingVO<RecruitVO> pagingVO) {
		return recruitdao.selectRecruitCount(pagingVO);
	}

	@Override
	public List<RecruitVO> selectRecruitList2() {
		return recruitdao.selectRecruitList2();
	}
	
	

}
