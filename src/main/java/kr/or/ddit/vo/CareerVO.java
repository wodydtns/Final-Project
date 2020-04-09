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
public class CareerVO implements Serializable{
	private Integer career_cd;
	private Integer res_cd;
	private String career_comp_nm;
	private String career_dep_nm;
	private String career_position;
	private Integer career_ann_sal;
	private String career_join;
	private String career_retire;
	private String career_task;
	private String emp_type_cd;
	private Integer job_cd;
}
