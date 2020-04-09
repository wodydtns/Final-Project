package kr.or.ddit.creatorCenter.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PiAttVO;

@Repository
public interface IPiAttatchDAO {

	/**
	 * insert all 구조 활용
	 * @param board
	 * @return
	 */
	public int insertAttach(PIVO pi);
	/**
	 * 한번에 여러건의 첨부파일 메타 삭제
	 * @param board
	 * @return
	 */
	public int deleteAttatch(int pi_cd);
}
