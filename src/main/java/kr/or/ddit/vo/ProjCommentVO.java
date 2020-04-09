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
 * 2020. 3. 11.      작성자명   박재욱    최초작성  박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
public class ProjCommentVO implements Serializable{
	private Integer proj_comm_cd;
	private Integer pw_cd;
	private Integer pm_cd;
	private String proj_comm_cont;
	private String proj_comm_date;
	private String work_check;
	
	// 댓글 처리를 위한 관계 형성
	private String mem_email;
	private String mem_nick;
	private int proj_cd;
	
}
