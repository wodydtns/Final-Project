package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * @author 작성자명
 * @since 2020. 4. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 4. 1.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

@Data
public class PwBoardCommentVO {
	private Integer comm_cd;
	private Integer stu_post_cd;
	private Integer pm_cd;
	private String pw_comm_cont;
	private String pw_comm_date;
	private String yn_code;
	
	private String mem_email;
	private String mem_nick;
	private int proj_cd;
}
