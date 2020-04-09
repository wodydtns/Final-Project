package kr.or.ddit.hobby.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.hobby.dao.IHobbyClassDAO;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.vo.ClassPiVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CouponHaveVO;
import kr.or.ddit.vo.CouponVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.classPiAttVO;
import kr.or.ddit.vo.hobbyBestPagingVO;

@Service
public class HobbyClassServiceImpl implements IHobbyClassService {

	@Inject
	IHobbyClassDAO hobbyclassdao;
	
	IAuthenticateService authservice;

	@Override
	public List<classPiAttVO> selectClassList(hobbyBestPagingVO<classPiAttVO> pagingVO) {
		return hobbyclassdao.selectClassList(pagingVO);
	}

	@Override
	public int selectClassCount(hobbyBestPagingVO<classPiAttVO> pagingVO) {
		return hobbyclassdao.selectClassCount(pagingVO);
	}



	@Override
	public List<CouponVO> selectCouponList() {
		return hobbyclassdao.selectCouponList();
	}

	@Transactional
	@Override
	public ServiceResult insertCoupon(CouponHaveVO couponHave) {
		ServiceResult result = null;
		int rowcnt = hobbyclassdao.insertCoupon(couponHave);
		if(rowcnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public CouponHaveVO selectCoupon(MemberVO memberVO) {
		return hobbyclassdao.selectCoupon(memberVO);
	}

	


	

	

	

	

	

	
	

	
}
