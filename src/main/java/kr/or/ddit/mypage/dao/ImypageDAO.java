package kr.or.ddit.mypage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
 * 2020. 3. 25.      작성자명  박재욱  			 최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ImypageDAO {
	
	
	
	/**
	 * 쿠폰 리스트
	 * @param couponVO
	 * @return
	 */
	public List<CouponVO> selectCouponList(String mem_email);
	

	/**
	 * 찜 리스트 조회
	 * @param mem_email
	 * @return
	 */
	public List<PaymentVO> selectStudingList(PagingVO pagingVO);
	
	public int selectStudingtotal(PagingVO pagingVO);
	
	
	/**
	 * 마이페이지 구성 내용 리스트
	 * @param mem_email
	 * @return
	 */
	public MypagePropertyVO selectMypageList(String mem_email);
	
	/**
	 * 탈퇴 - 탈퇴 상태로 업데이트
	 * @param mem_email
	 * @return
	 */
	public int escape(EscapeVO escape);
	
	/**
	 * 탈퇴 사유 작성
	 * @param escape
	 * @return
	 */
	public int escapeReasonInsert(EscapeVO escape);
	
	/** 회원 정보 업데이트
	 * @param memberVO
	 * @return
	 */
	public int updateMemberInfo(MypagePropertyVO mypage);

}
