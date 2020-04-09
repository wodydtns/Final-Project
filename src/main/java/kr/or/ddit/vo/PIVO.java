package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * 2020. 3. 20 				김혜정 coverImg,attatchList 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
public class PIVO implements Serializable{
	
	// 클래스 순위를 위한 property
	private Integer rank;
	//@NotNull(groups = InsertHint.class)
	private Integer pi_cd;
	@NotBlank(groups = InsertHint.class)
	private String cate_cd;
	@NotBlank(groups = InsertHint.class)
	private String lod_cd;
	private String lod_nm;
	@NotBlank(groups = InsertHint.class)
	private String crea_email;
	@NotBlank(groups = InsertHint.class)
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
	
	private ClassVO classVO;
	
	private String cate_nm;
	
	@NotNull
	private MultipartFile cover_img; //커버이미지 1:1
	
	public void setCover_img(MultipartFile cover_img) {
		this.cover_img = cover_img;
		if(cover_img!=null) {
				if(StringUtils.isNotBlank(cover_img.getOriginalFilename())) {
//				2) 메타
				PiAttVO attatchVO = new PiAttVO(cover_img);
				this.pi_att = attatchVO;
			}
		}
	}
	
	//1:1 클래스 커버이미지
	private PiAttVO pi_att;
	
	// 게시글 순번을 위한 rownum
	private Integer rnum;
	
	private Integer rowcnt;
	
	private String temp_nm; //커버이미지 출력을 위해 생성
	
	
	
	//클래스 커뮤니티
	private Integer commu_cd;
	private String mem_email;
	private String commu_cont;
	private String commu_date;
	
	//커뮤니티 댓글
	private Integer comr_cd;
	private String comr_comm_cont;
	private String comr_comm_date;
	private String comr_temp_nm;
	private String comr_ori_nm;
	private Integer comr_size;
	private String comr_fancy;
	private String comr_mime;

	
	
	
}
