package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author 작성자명
 * @since 2020. 4. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 4. 2.      작성자명    박재욱              최초작성 calendar 검색 vo
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
public class CalendarSearchVO implements Serializable{
	
	private int proj_cd; // 프로젝트 번호
	private String mem_email; // 특정 회원 아이디
	private String prior_cd; // 우선 순위 이름
	private String prog_cd; // 진행상황 코드
	private String dataCnt; // parsing해서 값을 넣어줄 property
	
	
}
