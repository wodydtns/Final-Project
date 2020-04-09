package kr.or.ddit.pmsproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.pmsproject.dao.IProjectWorkFeedbackDAO;
import kr.or.ddit.vo.FeedbackVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class ProjectWorkFeedbackServiceImpl implements IProjectWorkFeedbackService {

	@Inject
	IProjectWorkFeedbackDAO pwfDAO;
	
	@Override
	public int readFeedbackCount(PagingVO<FeedbackVO> pagingVO) {
		return pwfDAO.selectFeedbackCount(pagingVO);
	}

	@Override
	public List<FeedbackVO> readFeedbackList(PagingVO<FeedbackVO> pagingVO) {
		return pwfDAO.selectFeedbackList(pagingVO);
	}

	@Override
	public FeedbackVO readFeedback(int feed_cd) {
		pwfDAO.updateFeedbackCheck(feed_cd);
		return pwfDAO.selectFeedback(feed_cd);
	}

	@Override
	public ServiceResult createFeedback(FeedbackVO feedList) {
		ServiceResult result = ServiceResult.OK;
		int rowcnt = pwfDAO.insertFeedback(feedList);
		if(rowcnt <= 0) {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult removeFeedback(int feed_cd) {
		ServiceResult result = ServiceResult.OK;
		int rowcnt = pwfDAO.deleteFeedback(feed_cd);
		if(rowcnt <= 0) {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public int readProjMember(FeedbackVO feedList) {
		return pwfDAO.selectProjMember(feedList);
	}

	@Override
	public int readPMSFeedbackCount(String mem_email) {
		return pwfDAO.selectPMSFeedbackCount(mem_email);
	}

}
