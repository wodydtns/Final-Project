package kr.or.ddit.creatorCenter.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CurriculumVO;

@Repository
public interface IClassAttatchDAO {
	/**
	 * insert all 구조 활용
	 * @param board
	 * @return
	 */
	public int insertCurriculum(ClassVO cl);
	
	public int insertCurriculumTitle(CurriculumVO curri);
	public int selectCurri_SEQ();
	
	//public CurriculumVO selectAttach(int pi_att_cd);
	/**
	 * 한번에 여러건의 첨부파일 메타 삭제
	 * @param board
	 * @return
	 */
	public int deleteCurriculum(ClassVO cl);
}
