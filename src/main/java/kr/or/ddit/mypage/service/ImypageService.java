package kr.or.ddit.mypage.service;

import java.util.List;

import kr.or.ddit.vo.CouponVO;
import kr.or.ddit.vo.EscapeVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MypagePropertyVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PaymentVO;
/**
 * @author 작성자명
 * @since 2020. 3. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 25.      작성자명   박재욱             최초작성
 * 2020. 3. 25.      작성자명   박재욱             쿠폰 추가
 * 2020. 3. 25.      작성자명   박재욱             회원 탈퇴 구현
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

public interface ImypageService {
	
	public List<CouponVO> readSelectCouponList(String mem_email);
	
	public MypagePropertyVO readSelectMypageList(String mem_email);

	public List<PaymentVO> readSelectStudingList(PagingVO pagingVO);
	
	public int readSelectStudingtotal(PagingVO pagingVO);
	
	public int deleteEscape(EscapeVO escape);
	
	public int modifyUpdateMemberInfo(MypagePropertyVO mypage);
	
}
