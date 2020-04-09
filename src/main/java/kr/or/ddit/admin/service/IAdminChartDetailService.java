package kr.or.ddit.admin.service;

import java.util.List;

import kr.or.ddit.vo.CategoryStatVO;
/**
 * @author 작성자명
 * @since 2020. 3. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 18.      작성자명   박재욱    		  최초작성  
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
public interface IAdminChartDetailService {
	public List<CategoryStatVO> readSelectLikeRatio();
	
	public List<CategoryStatVO> readSelectHoleAvgData();
	
	public List<CategoryStatVO> readSelectHoleLikesData();
	public List<CategoryStatVO> readSelectHoleDislikesData();
	public List<CategoryStatVO> readSelectHoleCountData();
	public List<CategoryStatVO> readSelectHoleCommentData();

	public List<CategoryStatVO> readSelectCorData();
	public List<CategoryStatVO> readSelectRegData();
}
