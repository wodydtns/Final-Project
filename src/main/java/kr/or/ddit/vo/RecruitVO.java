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
 * 2020. 3. 11.      작성자명  박재욱              채용공고 리스트 property 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
public class RecruitVO implements Serializable {
	private Integer rec_cd;
	private String mem_email;
	private String rec_title;
	private String rec_cont;
	private String rec_start;
	private String rec_end;
	private String ca_code;
	private String aca_cd;
	private String rec_sal;
	private String rec_position;
	private String rec_duty;
	private Integer hire_cnt;
	private String rec_write_date;
	private String emp_type_cd;
	private Integer area_cd;
	
	private int rnum;
	
	private JobVO jobVO;
	
	private String ca_name;
	private String aca_nm;
	
	private String emp_teyp_nm;
	
	private String job;
	private String jobdetail;
	
	private String city;
	private String si;
}
