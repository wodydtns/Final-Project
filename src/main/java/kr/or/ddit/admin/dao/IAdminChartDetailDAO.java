package kr.or.ddit.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CategoryStatVO;

/**
 * @author 작성자명
 * @since 2020. 3. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 2020. 3. 18.      작성자명   박재욱    		  통계 자료 리스트 출력
 * --------     --------    ----------------------
 * 2020. 3. 17.      작성자명   박재욱    최초작성  박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IAdminChartDetailDAO {
	/**
	 * 좋아요 / 싫어요 비율
	 * @return 이름 / 비율
	 */
	public List<CategoryStatVO> selectLikeRatio();
	
	/**
	 * 평균 데이터 출력
	 * @return 이름, 좋아요, 싫어요, 댓글 수
	 */
	public List<CategoryStatVO> selectHoleAvgData();

	/**
	 * 전체 좋아요
	 * @return name,count_likes 
	 */
	public List<CategoryStatVO> selectHoleLikesData();
	
	/**
	 * 전체 싫어요
	 * @return name,count_dislikes
	 */
	public List<CategoryStatVO> selectHoleDislikesData();

	/**
	 * 전체 댓글
	 * @return name count_comment
	 */
	public List<CategoryStatVO> selectHoleCommentData();

	
	/**
	 * 전체 카테고리 수
	 * @return name, count
	 */
	public List<CategoryStatVO> selectHoleCountData();

	/**
	 * 상관 분석 데이터 리스트
	 * @return
	 */
	public List<CategoryStatVO> selectCorrData();
	
	/**
	 * 회귀분석 데이터 리스트
	 * @return
	 */
	public List<CategoryStatVO> selectRegData();
	
	
	
}
