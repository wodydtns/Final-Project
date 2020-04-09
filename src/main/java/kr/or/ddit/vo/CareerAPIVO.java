package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 작성자명
 * @since 2020. 4. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 4. 1.      작성자명    박재욱              최초작성 - 커리어  api 프로퍼티 
 * Copyright (c) 2020 by DDIT All right reserved
 *      </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "uc")
public class CareerAPIVO {

	private String uc; // 업종
	private String jc; // 직종
	private String kw; // 키워드
	private String ac1; // 근무지역
	private String ec; // 고용형태
	private String ck; // 채용조건
	private String sc; // 학력조건
	private String gubun; // 공고구분
}