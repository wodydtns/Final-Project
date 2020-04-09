package kr.or.ddit.pmsproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.PMListVO;
import kr.or.ddit.vo.PriorityVO;
import kr.or.ddit.vo.ProgressVO;
import kr.or.ddit.vo.PwListVO;
import kr.or.ddit.vo.YNCheckVO;

/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 13.      최효은       최초작성
 * 2020. 3. 19.      최효은       검색을 위한 PROGRESS, PRIORITY 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IProjectOthersDAO {
	/**
	 * 프로젝트의 카테고리 명 출력용
	 * @return List<CategoryVO>
	 */
	public List<CategoryVO> selecrCategoryList();
	/**
	 * 작업의 진행상황 명 출력용
	 * @return List<ProgressVO>
	 */
	public List<ProgressVO> selectProgressList();
	/**
	 * 작업의 우선순위 명 출력용
	 * @return List<PriorityVO>
	 */
	public List<PriorityVO> selectPriorityList();
	/**
	 * 회원의 수락, 미수락 여부 출력용
	 * @return List<YNCheckVO>
	 */
	public List<YNCheckVO> selectAgreeYNCheck();
	/**
	 * 탈퇴, 미탈퇴 여부 출력용
	 * @return List<YNCheckVO>
	 */
	public List<YNCheckVO> selectDeleteYNCheck();
	/**
	 * 작업 미확인 리스트
	 * @param mem_email
	 * @return int
	 */
	public int selectMemberWorkCount(String mem_email);
	/**
	 * 피드백 미확인 리스트
	 * @param mem_email
	 * @return int
	 */
	public int selectPMSFeedbackCount(String mem_email);
	/**
	 * 프로젝트 회원 출력
	 * @param pmList
	 * @return List<PMListVO> 
	 */
	public List<PMListVO> selectProjectMember(int proj_cd);
}
