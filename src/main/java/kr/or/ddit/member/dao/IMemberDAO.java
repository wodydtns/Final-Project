package kr.or.ddit.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IMemberDAO {

	/**회원생성(이메일-아이디,비번,가입일자,탈퇴여부 / sns-비번빼고)
	 * @param member
	 * @return 생성된 갯수
	 */
	public int insertMember(MemberVO member);
	
	
	//아래의 두 update는 insert후 진행되는 것들
	
	/**비번,닉네임,휴대폰번호 업뎃(이메일가입회원)
	 * @param member
	 * @return
	 */
	public int updatePaNiHp(MemberVO member);
	
	/**닉네임,휴대폰번호 업뎃(sns가입회원)
	 * @param member
	 * @return
	 */
	public int updateNiHp(MemberVO member);
	
	/**
	 * 한명의 회원 조회 , 이메일 중복여부 체크
	 * @param mem_email 조회할 회원의 PK
	 * @return 존재하지 않으면, null반환.
	 */
	public MemberVO selectMember(String mem_email);
	
	/**크리에이터센터에서 수강생리스트 조회용
	 * @return
	 */
	public int selectMemberCount(PagingVO pagingVO);
	public List<PaymentVO> selectMemberList(PagingVO pagingVO);
	
	
	/**
	 * 한명의 회원 조회 , 닉네임 중복여부 체크
	 * @param mem_nick
	 * @return 존재하지 않으면, null반환.
	 */
	public MemberVO selectNickMember(String mem_nick);
	
	/**로그인 시 salt값 조회
	 * @param mem_email
	 * @return 
	 */
	public String selectSalt(String mem_email);
	
	/**salt값과 입력받은 값을 암호화한것을 db안에 pass와 비교하기 위해 pass조회
	 * @param mem_email
	 * @return
	 */
	public String selectPass(String mem_email);

	/**회원탈퇴
	 * @param mem_email
	 * @return
	 */
	public int deleteMember(String mem_email);
}
