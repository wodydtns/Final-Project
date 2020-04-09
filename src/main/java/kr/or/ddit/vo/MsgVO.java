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
public class MsgVO implements Serializable{
	private Integer msg_cd;
	private Integer pm_cd;
	private Integer sm_cd;
	private String msg_cont;
	private String msg_date;
	private String yn_code;
}
