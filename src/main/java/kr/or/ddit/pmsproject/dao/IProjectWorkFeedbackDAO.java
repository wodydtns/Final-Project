package kr.or.ddit.pmsproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FeedbackVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface IProjectWorkFeedbackDAO {

	/**
	 * 피드백 전체 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectFeedbackCount(PagingVO<FeedbackVO> pagingVO);
	/**
	 * 피드백 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<FeedbackVO> selectFeedbackList(PagingVO<FeedbackVO> pagingVO);
	/**
	 * 피드백 상세보기
	 * @param feedList
	 * @return
	 */
	public FeedbackVO selectFeedback(int feed_cd);
	/**
	 * 피드백 등록
	 * @param feedList
	 * @return
	 */
	public int insertFeedback(FeedbackVO feedList);
	/**
	 * 피드백 삭제
	 * @param feed_cd
	 * @return
	 */
	public int deleteFeedback(int feed_cd);
	/**
	 * 멤버 조회
	 * @param feedList
	 * @return
	 */
	public int selectProjMember(FeedbackVO feedList);
	/**
	 * 피드백 알림 업데이트
	 * @param feed_cd
	 * @return
	 */
	public int updateFeedbackCheck(int feed_cd);
	/**
	 * 피드백 미확인 리스트
	 * @param mem_email
	 * @return int
	 */
	public int selectPMSFeedbackCount(String mem_email);
}
