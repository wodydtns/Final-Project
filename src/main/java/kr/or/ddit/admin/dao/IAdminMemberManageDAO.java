package kr.or.ddit.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReportVO;

/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 13.      작성자명   박재욱    최초작성   박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IAdminMemberManageDAO {
	/**
	 * 일반회원 리스트
	 * @param pagingVO : 페이징 처리
	 * @return
	 */
	public List<MemberVO> selectMemberList(PagingVO pagingVO);
	/**
	 * 크리에이터 리스트
	 * @param pagingVO :페이징 처리
	 * @return
	 */
	public List<MemberVO> selectCreatorList(PagingVO pagingVO);
	/**
	 * 기업회원 리스트
	 * @param pagingVO :페이징 처리
	 * @return
	 */
	public List<MemberVO> selectCompanyList(PagingVO pagingVO);
	/**
	 * 블락 리스트
	 * @param pagingVO
	 * @return
	 */
	public List<ReportVO> selectBlockList(PagingVO pagingVO);
	
	/**
	 * 전체 일반회원 수 
	 * @param pagingVO
	 * @return
	 */
	public int selectMemberCount(PagingVO pagingVO);
	/**
	 * creator 회원 수
	 * @param pagingVO
	 * @return
	 */
	public int selectCreatorCount(PagingVO pagingVO);
	/**
	 * 기업회원 수
	 * @param pagingVO
	 * @return
	 */
	public int selectCompanyCount(PagingVO pagingVO);
	/**
	 * 블락 리스트 수
	 * @param pagingVO
	 * @return
	 */
	public int selectBlockCount(PagingVO pagingVO);
	
	/**
	 * 멤버 강퇴 처리(비활성화)
	 * @param memEmail : 멤버의 아이디
	 * @return
	 */
	public int deleteMember(String memEmail);
	/**
	 * 크리에이터 강퇴 처리(비활성화)
	 * @param memEmail : 크리에이터 아이디
	 * @return
	 */

}
