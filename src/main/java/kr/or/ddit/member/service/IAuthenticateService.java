package kr.or.ddit.member.service;

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
public interface IAuthenticateService {
	
	/**인증로직
	 * @param inputMember
	 * @return UserNotFoundException , INVALIDPASSWORD, 인증에 성공한 MemberVO
	 */
	public Object authenticate(MemberVO inputMember);
}
