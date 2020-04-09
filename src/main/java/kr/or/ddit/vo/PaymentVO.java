
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
 * 2020. 3. 11.      작성자명  박재욱     최초작성  박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
public class PaymentVO implements Serializable{
	private String mem_email;
	private Integer cl_cd;
	private Integer cp_have_cd;
	private Integer pay_amt;
	private Integer final_pay_amt;
	private String pay_date;
	private String mem_hp;
	private int rnum;
	private int cp_disc;
	private String mem_nick;
	
	
	private ClassVO classVO; //검색을 위한 vo 관계 형성
	private PIVO piVO;
	private String pi_nm; //결제창에서 쓰기위해 생성
	private String temp_nm; //결제창에서 쓰기위해 생성
}	

