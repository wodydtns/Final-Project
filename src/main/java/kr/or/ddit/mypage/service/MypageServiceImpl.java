package kr.or.ddit.mypage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mypage.dao.ImypageDAO;
import kr.or.ddit.vo.CouponVO;
import kr.or.ddit.vo.EscapeVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MypagePropertyVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PaymentVO;

@Service
public class MypageServiceImpl implements ImypageService {

	@Inject
	ImypageDAO dao;
	

	@Override
	public List<CouponVO> readSelectCouponList(String mem_email) {
		return dao.selectCouponList(mem_email);
	}

	@Override
	public List<PaymentVO> readSelectStudingList(PagingVO pagingVO) {
		return dao.selectStudingList(pagingVO);
	}

	@Override
	public MypagePropertyVO readSelectMypageList(String mem_email) {
		return dao.selectMypageList(mem_email);
	}

	@Override
	public int readSelectStudingtotal(PagingVO pagingVO) {
		
		return dao.selectStudingtotal(pagingVO);
	}

	@Override
	public int deleteEscape(EscapeVO escape) {
		int insertCnt = dao.escapeReasonInsert(escape);
		int cnt =0;
		if(insertCnt >0) {
			cnt = dao.escape(escape);
			if(cnt >0) {
				cnt = 1;
			}else {
				cnt = 0;
			}
		}else {
			throw new RuntimeException();
		}
		return cnt;
	}

	@Override
	public int modifyUpdateMemberInfo(MypagePropertyVO mypage) {
		int cnt = dao.updateMemberInfo(mypage);
		if(cnt <=0) {
			throw new RuntimeException();
		}
		return cnt;
	}

	

}
