package kr.or.ddit.pmsproject.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FeedbackVO;
import kr.or.ddit.vo.PagingVO;

public interface IProjectWorkFeedbackService {

	/**
	 * 피드백 전체 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int readFeedbackCount(PagingVO<FeedbackVO> pagingVO);
	/**
	 * 피드백 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<FeedbackVO> readFeedbackList(PagingVO<FeedbackVO> pagingVO);
	/**
	 * 피드백 상세보기
	 * @param feedList
	 * @return
	 */
	public FeedbackVO readFeedback(int feed_cd);
	/**
	 * 피드백 등록
	 * @param feedList
	 * @return
	 */
	public ServiceResult createFeedback(FeedbackVO feedList);
	/**
	 * 피드백 삭제
	 * @param feedList
	 * @return
	 */
	public ServiceResult removeFeedback(int feed_cd);
	/**
	 * 멤버 조회
	 * @param feedList
	 * @return
	 */
	public int readProjMember(FeedbackVO feedList);
	/**
	 * 피드백 미확인 리스트
	 * @param mem_email
	 * @return int
	 */
	public int readPMSFeedbackCount(String mem_email);
	
}
