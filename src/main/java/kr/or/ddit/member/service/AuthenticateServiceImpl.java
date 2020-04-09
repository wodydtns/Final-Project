package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MemberVO;
/**
 * @author 작성자명
 * @since 2020. 3. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 18.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Service("authService")
public class AuthenticateServiceImpl implements IAuthenticateService {
	
	@Inject 
	IMemberDAO memberDAO;
	
	@Override
	public Object authenticate(MemberVO inputMember) {
		Object result = null;
		MemberVO savedMember = memberDAO.selectMember(inputMember.getMem_email());
		if(savedMember!=null) {
			String inputPass = inputMember.getMem_pass();
			String savedPass = savedMember.getMem_pass();
			if("YN_A01".equals(savedMember.getYn_code())) {
				result = ServiceResult.REMOVED;
			}else if(savedPass.equals(inputPass)) {
				result = savedMember; //result에 vo저장
			}else if(!savedPass.equals(inputPass)){
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {//null일때
			throw new UserNotFoundException(inputMember.getMem_email()+"존재하지 않음");
			
		}
		return result;
	}

}
