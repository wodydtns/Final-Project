package kr.or.ddit.admin.service;

import java.util.List;

import kr.or.ddit.vo.CsCenterAttVO;
import kr.or.ddit.vo.CsCenterVO;
import kr.or.ddit.vo.PagingVO;

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
 * 2020. 3. 19.      작성자명   박재욱              공지사항, FAQ 상세 글 조회
 * 2020. 3. 20.      작성자명   박재욱              공지사항 추가,수정 / 파일 업로드 기능
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
public interface IAdminCsCenterService {
	public List<CsCenterVO> readCsCenterBoard(PagingVO pagingVO);

	public int readSelectCSCenterBoardTotal(PagingVO pagingVO);
	
	public CsCenterVO readSelectCSCenterBoard(int cs_cd);
	
	public List<CsCenterVO> readSelectHeaderList();
	
	public int createCsCenterBoard(CsCenterVO csCenterVO);
	
	public int modifyUpdateCsCenterBoard(CsCenterVO csCenterVO);
	// 첨부파일 다운로드
	public CsCenterAttVO attDownload(int st_cd);
	
	public int incrementHit(int cs_cd);
	
	/**
	 * 게시글 삭제
	 * @param cs_cd
	 * @return
	 */
	public int removeCsCenterBoard(CsCenterVO center);
	
}
