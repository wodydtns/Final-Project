package kr.or.ddit.admin.service;

import java.util.List;

import kr.or.ddit.vo.CategoryStatVO;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RisingVO;
import kr.or.ddit.vo.TopWordVO;
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
public interface IAdminStatsService {

	public List<RisingVO> readRisingWord();
	
	public List<TopWordVO> readTopWord();
	
	public int readcreatorTotal();
	
	public int readmemberTotal();
	
	public int readOngoingClass();
	
	public int readPiClass();
	
	public List<PIVO> readClassRanking();
	
	public int readProjectIngTotal();
	
	public List<CategoryStatVO> readTrendRanking();
	
	public List<CategoryStatVO> readTrendRatio();
	
	public List<CategoryStatVO> readYoutubeStatsSeven();

	public List<CategoryStatVO> readYoutubeStatsEight();
}
