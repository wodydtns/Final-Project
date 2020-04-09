package kr.or.ddit.creatorCenter.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CreatorVO;
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
 * 클래스생성을 제외한 나머지
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
public interface IPIService {
	/** 사전조사 클래스 생성
	 * @param piVO
	 * @return
	 */
	public ServiceResult createPI(PIVO pi);
	

	
	/** 나의 대기중인 사전조사 목록 조회
	 * @param mem_email
	 * @return
	 */
	public List<PIVO> read_My_PI_wait_List(String mem_email);
	
	/** 나의 진행중인 사전조사 목록 조회
	 * @param mem_email
	 * @return
	 */
	public List<PIVO> read_My_PI_ing_List(String mem_email);
	
	/**나의 종료된 사전조사 조회
	 * @param mem_email
	 * @return
	 */
	public List<PIVO> read_My_PI_end_List(String mem_email);
	/**진행 or 종료된 사전조사 상세정보 조회
	 * @param pi_cd
	 * @return
	 */
	public PIVO read_My_pi(int pi_cd);
	
	
	/**사전조사 수정
	 * @param pi
	 * @return
	 */
	public ServiceResult modifyPI(PIVO pi);
	
	public ServiceResult modifyPIStatus(Map<String, Object> pMap);
	/**클래스생성후 class_yn YN_G02로 변경하기
	 * @param pi_cd
	 * @return
	 */
	public ServiceResult modifyClassYN(int pi_cd);
	/**사전조사 삭제
	 * @param pi
	 * @return
	 */
	public ServiceResult removePi(PIVO pi);
}
