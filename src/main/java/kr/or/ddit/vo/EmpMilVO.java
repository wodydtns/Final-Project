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
 * 2020. 3. 11.      작성자명   박재욱    최초작성 박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
public class EmpMilVO implements Serializable{
	private Integer em_cd;
	private Integer res_cd;
	private String em_type_cd;
	private String mil_cd;
}
