package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author 작성자명
 * @since 2020. 3. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 19.      최효은       최초작성, 검색을 위한 VO 생성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
public class ProjectSearchVO implements Serializable {
	
	// 프로젝트 코드
	private int proj_cd;
	private Integer pm_cd;
	private String pm_cd_str;
	private String mem_nick;
	
	// 진행상황
	private String prog_cd;
	private String prog_nm;
	
	// 우선순위
	private String prior_cd;
	private String prior_nm;
	
	// 회원이메일
	private String mem_email;
}
