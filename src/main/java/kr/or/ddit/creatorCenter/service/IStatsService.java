package kr.or.ddit.creatorCenter.service;

import java.util.List;

import kr.or.ddit.vo.CCStatsVO;

public interface IStatsService {

		/**수익조회
		 * @param mem_email
		 * @return
		 */
		public List<CCStatsVO> readProfit(String mem_email);
		
		/**좋아요수 조회
		 * @param mem_email
		 * @return
		 */
		public List<CCStatsVO> readClLike(String mem_email);
		
		/**누적 수강생수 조회
		 * @param mem_email
		 * @return
		 */
		public  List<CCStatsVO> readStuCnt(String mem_email);
}
