package kr.or.ddit.pmsproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PMListVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjListVO;

/**
 * @author 작성자명
 * @since 2020. 3. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 24.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IProjectMemberDAO {
	/**
	 * 페이징을 위한 멤버 수
	 * @param pmList
	 * @return
	 */
	public int selectMemberCount(PagingVO<PMListVO> pagingVO);
	/**
	 * 프로젝트의 멤버 리스트
	 * @param pmList
	 * @return
	 */
	public List<PMListVO> selectMemberList(PagingVO<PMListVO> pagingVO);
	/**
	 * 멤버의 수락, 미수락을 확인
	 * @param pmList
	 * @return
	 */
	public List<PMListVO> selectAgreeType(PMListVO pmList);
	/**
	 * 프로젝트 회원 추가
	 * @param pmList
	 * @return
	 */
	public int insertMember(PMListVO pmList);
	/**
	 * 프로젝트 회원 수정
	 * @param pmList
	 * @return
	 */
	public int updateMember(PMListVO pmList);
	/**
	 * 멤버의 수락, 미수락 변경사항 수락
	 * @param pmList
	 * @return
	 */
	public int updateAgreeYes(PMListVO pmList);
	/**
	 * 멤버의 수락, 미수락 변경사항 미수락
	 * @param pmList
	 * @return
	 */
	public int updateAgreeNo(PMListVO pmList);
	/**
	 * 프로젝트 회원 탈퇴(명시적 탈퇴, 실제 탈퇴 처리가 아님. 게시판까지 삭제하지 않음.)
	 * @param pmList
	 * @return
	 */
	public int deleteMember(PMListVO pmList);
	/**
	 * 프로젝트 멤버 조회
	 * @param proj_cd
	 * @return
	 */
	public List<ProjListVO> readProjectMember(int proj_cd);
	/**
	 * 프로젝트 멤버 조회
	 * @param proj_cd
	 * @return
	 */
	public int selectProjectMemberCount(PMListVO pmList);
	
	
}
