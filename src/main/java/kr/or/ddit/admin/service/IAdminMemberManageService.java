package kr.or.ddit.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
 * 2020. 3. 13.      작성자명   박재욱    			페이징 처리
 * --------     --------    ----------------------
 * 2020. 3. 13.      작성자명   박재욱    최초작성   박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
public interface IAdminMemberManageService {
	public List<MemberVO> readMemberList(PagingVO pagingVO );
	public List<MemberVO> readCreatorList(PagingVO pagingVO);
	public List<MemberVO> readCompanyList(PagingVO pagingVO);
	public List<ReportVO> readBlockList(PagingVO pagingVO);
	
	public int readMemberCount(PagingVO pagingVO);
	public int readCreatorCount(PagingVO pagingVO);
	public int readCompanyCount(PagingVO pagingVO);
	public int readBlockCount(PagingVO pagingVO);
	
	public int executeDeleteMember(String memEmail);
}
