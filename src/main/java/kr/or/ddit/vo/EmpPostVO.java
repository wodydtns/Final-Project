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
 *  * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
public class EmpPostVO implements Serializable{
	private Integer post_cd;
	private String bo_type_cd;
	private String ext_header_cd;
	private Integer post_parent_cd;
	private String mem_email;
	private String emp_title;
	private String emp_cont;
	private String emp_date;
	private Integer emp_hit;
}
