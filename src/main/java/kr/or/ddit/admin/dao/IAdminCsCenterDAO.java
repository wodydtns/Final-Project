package kr.or.ddit.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
 * 2020. 3. 18.              박재욱               공지사항, FAQ 리스트 처리
 * 2020. 3. 19.              박재욱               공지사항,FAQ 상세보기
 * --------     --------    ----------------------
 * 2020. 3. 13.      작성자명   박재욱    최초작성   박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IAdminCsCenterDAO {
	/** 공지사항 리스트 
	 * @param pagingVO : 페이징 처리
	 * @return
	 */
	public List<CsCenterVO> selectCsCenterBoardList(PagingVO pagingVO);

	/**
	 * 공지사항 카운트
	 * @param pagingVO
	 * @return
	 */
	public int selectCSCenterBoardTotal(PagingVO pagingVO);
	
	/**
	 * 공지사항 게시글
	 * @param cs_cd : 게시글 번호
	 * @return
	 */
	public CsCenterVO selectCsCenterBoard(int cs_cd);
	
	// 게시글 작성 시 필요한 header (공지사항 등 select box 구성)
	public List<CsCenterVO> selectHeaderList();
	
	/**
	 * 공지사항 게시글 작성
	 * @param csCenterVO : 게시글 내용
	 * @return
	 */
	public int writeCsCenterBoard(CsCenterVO csCenterVO);
	
	/** 공지가
	 * @param csCenterVO : 게시글 내용
	 * @return
	 */
	public int updateCsCenterBoard(CsCenterVO csCenterVO);
	
	/**
	 * 조회 수 증가
	 * @param cs_cd : 게시글 번호
	 * @return
	 */
	public int incrementHit(int cs_cd);
	
	public void deleteCsCenterBoard(CsCenterVO csCenterVO);

}
