package kr.or.ddit.pmsproject.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PMSAttVO;
import kr.or.ddit.vo.PwBoardVO;
import kr.or.ddit.vo.PwListVO;

/**
 * @author 작성자명
 * @since 2020. 4. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 4. 2.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IProjectboardAttatchDAO {

	/**
	 * 작업 첨부파일 추가(insert all 사용)
	 * @param pbList
	 * @return int 
	 */
	public int insertProjectBoardAttatch(PwBoardVO pbList);
	/**
	 * 임시저장명을 DB에서 가져오기 위한 작업 조회, 다운로드
	 * @param pw_cd
	 * @return PMSAttVO
	 */
	public PMSAttVO selectProjectBoardAttatch(int stu_post_cd);
	/**
	 * 수정할 때 첨부파일을 삭제하는 용도
	 * @param pbList
	 * @return int
	 */
	public int deleteProjectBoardAttatch(PwBoardVO pbList);
	
}
