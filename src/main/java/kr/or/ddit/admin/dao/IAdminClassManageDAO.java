package kr.or.ddit.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.DoingClassVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author 작성자명
 * @since 2020. 3. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 24.      작성자명   박재욱            최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IAdminClassManageDAO {
	/**
	 * 관리자 대기 상태의 사전조사 class
	 * @param pagingVO
	 * @return
	 */
	List<PIVO> selectPIList(PagingVO pagingVO);
	
	/**
	 * 사전조사 승인
	 * @param pi_cd
	 * @return
	 */
	public int acceptPI(int pi_cd);
	
	/**
	 * 사전조사 미승인
	 * @param pi_cd
	 * @return
	 */
	public int denyPI(int pi_cd);
	
	/** 사전조사 리스트 총계
	 * @return 총계
	 */
	public int PIListToTalCount();
	
	/**
	 * 사전조사 중인 클래스 
	 * @param pagingVO
	 * @return
	 */
	List<PIVO> selectPreTestList(PagingVO pagingVO);
	
	/**
	 * 사전조사 중인 클래스 총계
	 * @return
	 */
	public int PreTestListTotal();

	/**
	 * 진행중인 클래스 리스트
	 * @param pagingVO
	 * @return
	 */
	List<DoingClassVO> selectDoingList();
	
}
