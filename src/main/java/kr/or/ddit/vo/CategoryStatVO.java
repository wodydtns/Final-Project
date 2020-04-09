package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * @author 작성자명
 * @since 2020. 3. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 16.      작성자명   박재욱    최초작성  박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@Alias("CategoryStatVO")
public class CategoryStatVO implements Serializable{ 

	private String cate_date;
	private String name;
	private Integer count;
	private float count_likes;
	private Integer count_dislikes;
	private Integer count_comment;
	private Integer avg;
	private Integer avg_likes;
	private Integer avg_dislikes;
	private Integer avg_comment;
	private Integer total;
	// 좋아요 / 싫어요 비율
	private Integer ratio;
}
