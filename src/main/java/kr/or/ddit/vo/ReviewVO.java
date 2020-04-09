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
 * 2020. 3. 11.      작성자명  박재욱      최초작성 박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
public class ReviewVO implements Serializable{
	private Integer rev_cd;
	private String mem_email;
	private Integer cl_cd;
	private String rev_cont;
	private String rev_date;
	private String crea_email;
	private String comm_cont;
	private String comm_date;
	private Integer rev_score;
}
