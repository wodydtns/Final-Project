package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      작성자명   박재욱    최초작성  박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="mem_email")
@ToString
public class CompanyVO implements Serializable{
	private String mem_email;
	private String comt_cd;
	private String comp_nm;
	private String comp_lic_no;
	private String comp_cate;
	private Integer emp_cnt;
	private String comp_open;
	private String ceo_nm;
	private String comp_url;
	private String add_detail;
	private Integer area_cd;
	private String address;
	private Integer rnum;
	// 지역
	private AreaVO areaVO;
	// 기업형태
	private CompanyTypeVO companyTypeVO;
	
	private String comt_nm;
	
}
