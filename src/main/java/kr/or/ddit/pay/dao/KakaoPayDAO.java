package kr.or.ddit.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.vo.CouponClassVO;
import kr.or.ddit.vo.PaymentVO;

@Repository
public interface KakaoPayDAO {

	public List<CouponClassVO> selectMyCoupon(String mem_email);
	@Transactional
	public int insertPay(PaymentVO pay);
	@Transactional
	public int updateCouponHave(int cp_have_cd);
	
}
