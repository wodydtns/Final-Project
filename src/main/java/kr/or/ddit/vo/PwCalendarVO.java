package kr.or.ddit.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 작성자명
 * @since 2020. 4. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 4. 2.      작성자명    박재욱  		 최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@ToString
@EqualsAndHashCode(of="pw_cd")

public class PwCalendarVO {
	
	private Integer proj_cd;
	private Integer pw_cd;
	private String prog_cd;
	private String prior_cd;
	private Integer pm_cd;
	private String proj_nm; //프로젝트 이름
	
	@JsonProperty("title")
	private String pw_nm; // 일감 이름
	
	@JsonProperty("end")
	private String pw_end; // 일감 막날
	@JsonProperty("start")
	private String pw_start; // 일감 시작일
	
	private Integer pw_level; // 진척도
	private String yn_code; // 
	private String prog_nm; // 진행상황
	private String prior_nm; // 우선도
	@JsonProperty("id")
	private String mem_email; // 아이디
	private Integer dayCnt ; // 날짜 계산에 필요한 프로퍼티
	private String mem_nick; // 회원 닉네임
	
	private CalendarSearchVO CalendarSearchVO;
	
	
}
