package kr.or.ddit.hobby.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassPiVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CouponHaveVO;
import kr.or.ddit.vo.CouponVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

public interface IHobbyClassService {
	public List<classPiAttVO> selectClassList(hobbyBestPagingVO<classPiAttVO> pagingVO);
	
	public int selectClassCount(hobbyBestPagingVO<classPiAttVO> pagingVO);
	
	public List<CouponVO> selectCouponList();
	
	public CouponHaveVO selectCoupon(MemberVO memberVO);
	
	public ServiceResult insertCoupon(CouponHaveVO couponHave);
	
}
