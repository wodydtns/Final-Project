package kr.or.ddit.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PaymentVO;
/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Service
public class MemberServiceImpl implements IMemberService {

	@Inject
	IMemberDAO memberDAO;
	
	/*@Resource(name="authService") //검색조건 명시
	IAuthenticateService authService;*/
	
	
	@Override
	@Transactional
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
			int rowcnt = memberDAO.insertMember(member); 
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		
		return result;
	}


	@Override
	@Transactional
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result = null;
		/*Object authResult = authService.authenticate(member);
		if(authResult instanceof ServiceResult) {//not exist or invalid
			result = (ServiceResult) authResult;
			
		}else {//return으로 memberVO면 수정가능
*/			int rowcnt = memberDAO.updatePaNiHp(member);
			if(rowcnt >0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		//}
		return result;
	}

	@Override
	public MemberVO readMember(String mem_email) {
		MemberVO member = memberDAO.selectMember(mem_email);
		if(member==null) {
			throw new UserNotFoundException(); //예외의 형태로 호출자에게 넘김
		}
		return member;
	}


	@Override
	public MemberVO readNickMember(String mem_nick) {
		MemberVO member = memberDAO.selectNickMember(mem_nick);
		if(member==null) {
			throw new UserNotFoundException(); //예외의 형태로 호출자에게 넘김
		}
		return member;
	}


	@Override
	public ServiceResult modifyMemberSns(MemberVO member) {
		ServiceResult result = null;
		int rowcnt = memberDAO.updateNiHp(member);
		 if(rowcnt >0) {
			 result = ServiceResult.OK;
		 }else {
			 result = ServiceResult.FAIL;
		 }
		 return result;
	}


	@Override
	public String readSalt(String mem_email) {
		String mem_salt = memberDAO.selectSalt(mem_email);
		if(mem_salt==null) {
			//throw new CommonException("salt가 없음"); //예외의 형태로 호출자에게 넘김
			throw new UserNotFoundException();
		}
		return mem_salt;
	}


	@Override
	public String readPass(String mem_email) {
		String mem_pass = memberDAO.selectPass(mem_email);
		if(mem_pass==null) {
			throw new CommonException("비밀번호가 없음"); //예외의 형태로 호출자에게 넘김
		}
		return mem_pass;
	}


	@Override
	public ServiceResult removeMember(String mem_email) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int readMemberCount(PagingVO pagingVO) {
		return memberDAO.selectMemberCount(pagingVO);
	}


	@Override
	public List<PaymentVO> readMemberList(PagingVO pagingVO) {
		return memberDAO.selectMemberList(pagingVO);
	}

}
