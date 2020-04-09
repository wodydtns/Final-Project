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
 * 2020. 3. 11.      작성자명  박재욱     최초작성 박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

@Data
public class CouponClassVO implements Serializable{
	private Integer cp_cl_cd;
	private Integer cl_cd;
	private Integer cp_cd;
	private String cp_end_date;
	private String mem_email;
	private String cp_nm;
	private Integer cp_disc;
	
	private Integer cp_have_cd; //결제창에서 쓰기위해 사용
}
