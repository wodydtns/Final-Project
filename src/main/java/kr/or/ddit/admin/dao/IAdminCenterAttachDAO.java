package kr.or.ddit.admin.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CsCenterAttVO;
import kr.or.ddit.vo.CsCenterVO;

@Repository
public interface IAdminCenterAttachDAO {
	/**insert all 구조 활용
	 * @param centerAtt 
	 * @return
	 */
	public int insertAttach(CsCenterVO center);
	
	/**
	 * 첨부 파일 select
	 * @param cs_cd : 고객센터 게시글 번호
	 * @return
	 */
	public CsCenterAttVO selectAttach(int cs_cd);
	
	/**
	 * 파일 삭제
	 * @param centerAtt : 한번에 여러 건의 첨부 파일의 메타 데이터 삭제
	 * @return
	 */
	public int deleteAttach(CsCenterVO center);
}
