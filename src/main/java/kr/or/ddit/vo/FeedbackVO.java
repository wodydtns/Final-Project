package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      작성자명  박재욱    최초작성 박재욱
 * 2020. 4. 02.      최효은    피드백 프라퍼티 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
public class FeedbackVO implements Serializable{
	
	private int rnum;				// 순번 추가
	
	private Integer feed_cd;		// 피드백 번호
	private Integer feed_target;	// 피드백 수신 회원번호
	private Integer feed_sender;	// 피드백 발신 회원번호
	private String feed_cont;		// 피드백 내용
	private Integer pw_cd;			// 작업 번호
	private String feed_date;		// 피드백 작성일
	private String feed_check;		// 피드백 확인 여부
	
	// 관계 프라퍼티 추가
	private int proj_cd;			// 프로젝트 번호
	private String proj_nm;			// 프로젝트 이름
	private String pw_nm;			// 작업 이름
	private String target_email;	// 피드백 수신 이메일
	private String sender_email;	// 피드백 발신 이메일
	private String target_nick;		// 피드백 수신 닉네임
	private String sender_nick;		// 피드백 발신 닉네임
	
}
