package kr.or.ddit.pmsproject.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PMSAttVO;
import kr.or.ddit.vo.PwListVO;

/**
 * @author 작성자명
 * @since 2020. 3. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 28.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IProjectAttatchDAO {

	/**
	 * 작업 첨부파일 추가(insert all 사용)
	 * @param pwList
	 * @return int 
	 */
	public int insertProjectWorkAttatch(PwListVO pwList);
	/**
	 * 임시저장명을 DB에서 가져오기 위한 작업 조회, 다운로드
	 * @param pw_cd
	 * @return PwListVO
	 */
	public PMSAttVO selectProjectWorkAttatch(int pw_cd);
	/**
	 * 수정할 때 첨부파일을 삭제하는 용도
	 * @param pwList
	 * @return int
	 */
	public int deleteProjectWorkAttatch(PwListVO pwList);
	
}
