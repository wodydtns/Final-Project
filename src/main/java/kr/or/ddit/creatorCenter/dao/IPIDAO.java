package kr.or.ddit.creatorCenter.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.LODVO;
import kr.or.ddit.vo.PIVO;

/**
 * @author 작성자명
 * @since 2020. 3. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 19.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IPIDAO {
	public int insertPI(PIVO pi); //사전조사 insert
	
	public List<CategoryVO> selectCateList(); //카테고리리스트
	public List<LODVO> selectLevelList(); //난이도리스트
	public List<PIVO> select_My_PI_wait_List(String mem_email);
	public List<PIVO> select_My_PI_ing_List(String mem_email);
	public List<PIVO> select_My_PI_end_List(String mem_email);
	public PIVO select_My_pi(int pi_cd);
	
	public int updatePI(PIVO pi);
	public int deletePI(PIVO pi);
	public int updateClassYN(int pi_cd);
	
	public int updatePIstatus(Map<String, Object> pMap);
}
