package kr.or.ddit.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * @author 작성자명
 * @since 2020. 3. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 28.      김혜정       최초작성 크리에이터센터 통계용
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

@Data
@Alias("ccStatsVO")
public class CCStatsVO {

	private Integer cl_cd; // 클래스 코드
	private String pi_nm; //클래스명
	private Integer pay_amt; //결제금액
	private Integer stu_cnt; //수강생수
	private Integer cl_like; //좋아요수
}
