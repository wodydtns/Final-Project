package kr.or.ddit.hobby.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ClassPiVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CouponHaveVO;
import kr.or.ddit.vo.CouponVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RecruitVO;
import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;
/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      최도혁       최초작성
 * 2020. 3. 18		최도혁		카운트, 페이징처리 추가
 * 2020. 3. 19		최도혁		쿠폰조회 추가
 * 2020. 3. 20		최도혁		쿠폰선택 및 쿠폰함 등록 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IHobbyClassDAO {
	
	/**
	 * 최신순 모든 클래스 조회
	 * @param pagingVO 페이징처리
	 * @return
	 */
	public List<classPiAttVO> selectClassList(hobbyBestPagingVO<classPiAttVO> pagingVO);
	
	
	/**
	 * 페이징 카운트
	 * @param pagingVO
	 * @return
	 */
	public int selectClassCount(hobbyBestPagingVO<classPiAttVO> pagingVO);
	
	/**
	 * 쿠폰 리스트 메인화면에 띄우기
	 * @return
	 */
	public List<CouponVO> selectCouponList();
	
	
	/**
	 * 쿠폰 중복체크
	 * @return
	 */
	public CouponHaveVO selectCoupon(MemberVO memberVO);
	
	/**
	 * 쿠폰추가
	 * @param couponHave
	 * @return
	 */
	public int insertCoupon(CouponHaveVO couponHave);
	
	
	
	
	
}
