package kr.or.ddit.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
 * 2020. 3. 16.              박재욱              관리자 통계 (전체회원, 크리에이터 수 조회)
 * --------     --------    ----------------------
 * 2020. 3. 13.      작성자명   박재욱    최초작성   박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IAdminStatsDAO {
	
	public List<RisingVO> selectRisingWord();
	
	public List<TopWordVO> selectTopWord();
	
	public int creatorTotal();
	
	public int memberTotal();
	
	// 진행중인 클래스
	public int ongoingClass();
	
	//사전조사 중인 클래스
	public int PiClass();
	
	// 클래스 순위 메소드
	public List<PIVO> classRanking();
	
	// 진행중인 프로젝트 정보
	public int projectIngTotal();
	
	// video trending 순위
	public List<CategoryStatVO> TrendRanking(); 
	
	//video like-dislikes 비율 순위
	public List<CategoryStatVO> TrendRatio();
	
	public List<CategoryStatVO> youtubeStatsSeven();

	public List<CategoryStatVO> youtubeStatsEight();
	
	
}
