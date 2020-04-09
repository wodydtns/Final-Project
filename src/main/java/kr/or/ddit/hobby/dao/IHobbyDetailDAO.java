package kr.or.ddit.hobby.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ClassCommunityVO;
import kr.or.ddit.vo.ClassDetailPagingVO;
import kr.or.ddit.vo.CommReplyVO;
import kr.or.ddit.vo.CreatorVO;
import kr.or.ddit.vo.CurriculumVO;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 24.      최도혁       최초작성
 * 2020. 3. 25.      최도혁       커리큘럼 조회 추가
 * 2020. 3. 26.      최도혁       커뮤니티 조회 추가
 */ 

@Repository
public interface IHobbyDetailDAO {

	/**
	 * 클래스코드에 맞는 크리에이터 조회
	 * @param cl_cd 클래스코드
	 * @return
	 */
	public CreatorVO selectCreator(int cl_cd);

	/**
	 * 클래스코드에 맞는 상세자료 리스트
	 * @param cl_cd 클래스코드
	 * @return
	 */
	public List<CreatorVO> selectCreatorList(int cl_cd);
	
	/**
	 * 커뮤니티 클래스 불러오기
	 * @return
	 */
	public List<ClassCommunityVO> selectCommunityList(int cl_cd);
	
	
	public List<ClassCommunityVO> selectCommunityList2(int cl_cd);
	
	public List<CommReplyVO> selectComunityReply(int cl_cd);
	
	/**커뮤니티 글에 대한 댓글작성
	 * @param commReplyVO
	 * @return
	 */
	public int insertReply(CommReplyVO commReplyVO); 
	/**첨부파일없는 댓글작성
	 * @param commReplyVO
	 * @return
	 */
	public int insertReply2(CommReplyVO commReplyVO); 
	public int insertBoard(CommReplyVO commReplyVO);
	
	
}
