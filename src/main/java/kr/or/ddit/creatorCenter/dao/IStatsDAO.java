package kr.or.ddit.creatorCenter.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CCStatsVO;

@Repository
public interface IStatsDAO {

	/**수익조회
	 * @param mem_email
	 * @return
	 */
	public List<CCStatsVO>selectProfit(String mem_email);
	
	/**좋아요수 조회
	 * @param mem_email
	 * @return
	 */
	public List<CCStatsVO>selectClLike(String mem_email);
	
	/**누적 수강생수 조회
	 * @param mem_email
	 * @return
	 */
	public List<CCStatsVO>selectStuCnt(String mem_email);
	
	
	
}
