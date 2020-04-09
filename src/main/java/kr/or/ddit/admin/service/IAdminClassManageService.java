package kr.or.ddit.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

public interface IAdminClassManageService {
	List<PIVO> readSelectPIList(PagingVO pagingVO);
	
	public int updateAcceptPI(int pi_cd);
	
	public int updateDenyPI(int pi_cd);
	
	public int readPIListToTalCount();
	
	List<PIVO> readSelectPreTestList(PagingVO pagingVO);
	
	public int readPreTestListTotal();
	
	List<DoingClassVO> readSelectDoingList();
	
}
