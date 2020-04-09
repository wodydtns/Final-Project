package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.hint.InsertHint;
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
@Alias("creatorVO")
public class CreatorVO implements Serializable{
	private String crea_email;
	@NotBlank
	private String mem_intro;
	private String crea_date;
	
	//class 부분
	private int cl_cd;
	private String cl_start;
	private String cl_end;
	private int cl_fee;
	private int cl_like;
	
	//pi부분
	private Integer rank;
	//@NotNull(groups = InsertHint.class)
	private Integer pi_cd;
	@NotBlank(groups = InsertHint.class)
	private String cate_cd;
	@NotBlank(groups = InsertHint.class)
	private String lod_cd;
	private String lod_nm;
	private String pi_nm;
	@NotBlank(groups = InsertHint.class)
	private String pi_intro;
	@NotBlank(groups = InsertHint.class)
	private String pi_add_info;
	private String pi_start;
	private String pi_end;
	private String pi_open;
	//@NotNull(groups = InsertHint.class)
	private Integer pi_cnt;
	//@NotBlank(groups = InsertHint.class)
	private String yn_code;
	//@NotBlank(groups = InsertHint.class)
	private String class_yn;
	
	//curriculum 부분
	private Integer curri_cd;
	private String curri_nm;
	private Integer curri_cd2;
	private String video_src;
	private Integer curri_order;
	
	//class_community 부분
	private Integer commu_cd;
	private String mem_email;
	private String commu_cont;
	private String commu_date;

	//comm_reply 부분
	private Integer comr_cd;
	private String comr_comm_cont;
	private String comr_comm_date;
	private String comr_temp_nm;
	private String comr_ori_nm;
	private Integer comr_size;
	private String comr_fancy;
	private String comr_mime;

	
}
