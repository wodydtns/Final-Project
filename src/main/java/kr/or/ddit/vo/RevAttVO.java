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
 * 2020. 3. 11.      작성자명 박재욱      최초작성 박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

@Data
public class RevAttVO implements Serializable{
	private Integer att_cd;
	private Integer rev_cd;
	private String temp_nm;
	private String ori_nm;
	private Integer rev_size;
	private String fancy;
	private String mime;
}
