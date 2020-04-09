package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
/**
 * @author 작성자명
 * @since 2020. 3. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 18.      작성자명   박재욱    최초작성 박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
public class RegressionVO implements Serializable{
	private String mame;
	private String likes;
	private String dislikes;
	private String comment_count;
	private String likes_comment_count;
	private String dislikes_comment_count;
	private String reg_date;
}
